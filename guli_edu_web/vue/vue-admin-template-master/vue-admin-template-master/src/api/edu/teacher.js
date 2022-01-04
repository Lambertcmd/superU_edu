import request from '@/utils/request'

export default {
    /**
     * 讲师列表(对应分页复杂查询讲师接口)
     * @param {*} page          当前页
     * @param {*} size          每页大小
     * @param {*} teacherQuery  查询条件
     * @returns 
     */
    getTeacherListPage(page, size, teacherQuery) {
        return request({
            url: `/eduservice/edu-teacher/pageTeacherCondition/${page}/${size}`,
            method: "post",
            //teacherQuery条件对象，后端使用RequestBody获取数据,需要用json数据传递,
            //data表示将对象转换为json的形式
            data: teacherQuery
        })
    },

    /**
     * 根据id逻辑删除讲师
     * @param {*} id 
     * @returns 
     */
    removeTeacherById(id) {
        return request({
            url: `/eduservice/edu-teacher/${id}`,
            method: "delete"
        })
    },

    /**
     * 添加讲师
     * @param {} teacher 
     * @returns 
     */
    addTeacher(teacher) {
        return request({
            url: `/eduservice/edu-teacher/addTeacher`,
            method: "post",
            data: teacher
        })
    }

}