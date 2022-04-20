package com.geek.demo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import org.junit.Test;

import java.util.Collections;

/**
 * 代码生成器
 *
 * @author whc
 * @since 2021/10/13
 */

public class CodeGenerator {
    static final String URL = "jdbc:mysql://localhost:3306/guli_edu?useUnicode=true&characterEncoding=utf8&serverTimezone=GMT%2B8&useSSL=true";
    public static void main(String[] args) {
        String projectPath = "D:\\guli_edu\\guli_edu\\service\\service_order";//获取项目路径
        FastAutoGenerator.create(URL, "root", "123456")
                //全局配置
                .globalConfig(builder -> {
                    builder.author("Lambert")
                            .outputDir(projectPath + "/src/main/java")//输出路径
                            .enableSwagger()//开启swagger3
//                            .fileOverride()//覆盖文件
                            .disableOpenDir()//不打开文件夹
                            .dateType(DateType.ONLY_DATE);
                })
                //包名配置
                .packageConfig(builder -> {
                    builder.parent("com.geek")
                            .moduleName("eduorder")
                            .service("service")
                            .serviceImpl("service.impl")
                            .controller("controller")
                            .entity("entity")
                            .mapper("mapper");
//                            //自定义输出路径，mapper.xml生成到resources目录下
//                            .pathInfo(Collections.singletonMap(OutputFile.mapperXml, projectPath + "/src/main/resources/mapper"));
                })
                //策略配置
                .strategyConfig(builder -> {
                    builder.addInclude("t_order","t_pay_log") //数据库表名 作为每个文件名开头
                            .addTablePrefix("t_")//生成实体类时去掉表前缀
                            .serviceBuilder().formatServiceFileName("%sService")//去掉Service的 "I" 前缀
                            .controllerBuilder().enableRestStyle()//restful开启,url中驼峰转连字符
                            .enableHyphenStyle()//url改变 例如：index_id_1
                            .entityBuilder().enableLombok()//开启lombok
                            .columnNaming(NamingStrategy.underline_to_camel);//字段驼峰命名法映射
                })
                //执行
                .execute();
    }

}
