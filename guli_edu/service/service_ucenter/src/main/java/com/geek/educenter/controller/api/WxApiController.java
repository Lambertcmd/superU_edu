package com.geek.educenter.controller.api;

import com.geek.commonutils.jwt.JwtUtils;
import com.geek.educenter.entity.UcenterMember;
import com.geek.educenter.service.UcenterMemberService;
import com.geek.educenter.utils.ConstantWxUtils;
import com.geek.educenter.utils.HttpClientUtils;
import com.geek.servicebase.exception.GuliException;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.net.URLEncoder;
import java.util.HashMap;

/**
 * @ClassName 微信授权登录
 * @Description TODO
 * @Author Lambert
 * @Date 2022/4/15 13:16
 * @Version 1.0
 **/
@Controller //只是请求地址 不需要返回数据
@Slf4j
@RequestMapping("/api/ucenter/wx")
public class WxApiController {

    @Autowired
    private UcenterMemberService memberService;

    /**
     * 生成微信登录二维码
     *
     * @return
     */
    @GetMapping("login")
    public String getWxQRCode() {

        //微信开放平台授权baseUrl  %s代表占位符
        String baseUrl = "https://open.weixin.qq.com/connect/qrconnect" +
                "?appid=%s" +
                "&redirect_uri=%s" +
                "&response_type=code" +
                "&scope=snsapi_login" +
                "&state=%s" +
                "#wechat_redirect";
        //使用URLEncoder对redirect_url进行编码
        String redirectUrl = ConstantWxUtils.WX_OPEN_REDIRECT_URL;
        try {
            redirectUrl = URLEncoder.encode(redirectUrl, "utf-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        //参数一：需要拼接的字符串 参数二或更多：替换字符串内的%s
        String url = String.format(
                baseUrl,
                ConstantWxUtils.WX_OPEN_APP_ID,
                redirectUrl,
                "geek"
        );
        log.info("url:"+url);
        //重定向到请求微信地址里面
        return "redirect:" + url;
    }

    /**
     * 授权登录后的回调，获取扫码人信息
     *
     * @param code
     * @param state
     * @return
     */
    @GetMapping("callback")
    public String callback(String code, String state) {
        //得到授权临时票据code

        //2.利用code请求微信固定的地址,得到两个值 access_token和openid
        String baseAccessTokenUrl = "https://api.weixin.qq.com/sns/oauth2/access_token" +
                "?appid=%s" +
                "&secret=%s" +
                "&code=%s" +
                "&grant_type=authorization_code";
        //拼接三个参数
        String AccessTokenUrl = String.format(
                baseAccessTokenUrl,
                ConstantWxUtils.WX_OPEN_APP_ID,
                ConstantWxUtils.WX_OPEN_APP_SECRET,
                code
        );
        //使用httpClient发送请求,得到返回结果
        String result = null;
        try {
            result = HttpClientUtils.get(AccessTokenUrl);
        } catch (Exception e) {
            throw new GuliException(20001, "获取token出错");
        }
        log.info("result:" + result);
        //解析json字符串 取出accesstoken和openid
        Gson gson = new Gson();
        HashMap map = gson.fromJson(result, HashMap.class);
        String accessToken = (String) map.get("access_token");
        log.info("accessToken:" + accessToken);
        String openid = (String) map.get("openid");
        log.info("openid:" + openid);

        //查询数据库当前用用户是否曾经使用过微信登录
        UcenterMember member = memberService.getByOpenId(openid);
        if (member == null) {
            log.info("新用户注册");
            //通过accessToken和openid 请求路径获取个人信息
            String baseUserInfoUrl = "https://api.weixin.qq.com/sns/userinfo" +
                    "?access_token=%s" +
                    "&openid=%s";
            String userInfoUrl = String.format(
                    baseUserInfoUrl,
                    accessToken,
                    openid
            );
            String resultUserInfo = null;
            try {
                resultUserInfo = HttpClientUtils.get(userInfoUrl);
                log.info("userInfo:" + resultUserInfo);
            } catch (Exception e) {
                throw new GuliException(20001, "请求用户个人信息出错");
            }
            //对个人信息进行json转换取出
            HashMap userInfoMap = gson.fromJson(resultUserInfo, HashMap.class);
            String nickname = (String) userInfoMap.get("nickname");
            log.info("nickname:" + nickname);
            String headimgurl = (String) userInfoMap.get("headimgurl");
            log.info("headimgurl:" + headimgurl);

            //将用户存入数据库
            member = new UcenterMember();
            member.setNickname(nickname);
            member.setAvatar(headimgurl);
            member.setOpenid(openid);
            memberService.save(member);
        }
        //使用jwt根据member对象生成token字符串
        String jwtToken = JwtUtils.getJwtToken(member.getId(), member.getNickname());
        //存入cookie
        //CookieUtils.setCookie(request, response, "guli_jwt_token", token);
        //因为端口号不同存在蛞蝓问题，cookie不能跨域，所以这里使用url重写
        return "redirect:http://localhost:3000?token=" + jwtToken;
    }
}
