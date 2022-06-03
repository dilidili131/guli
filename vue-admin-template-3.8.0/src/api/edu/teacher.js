import request from '@/utils/request'
import url from 'postcss-url'

export default {
    //current 当前页
    // limit 每页记录数
    // teacherQuery 条件对象
    getTeacherListPage(current, limit, teacherQuery) {
        return request({
            url: `/eduService/teacher/pageTeacherCondition/${current}/${limit}`,
            method: 'post',
            data: teacherQuery
        })
    },
    removeTeacherById(id) {
        return request({
            url: `/eduService/teacher/removeTeacherById/${id}`,
            method: 'delete'
        })
    },
    addTeacher(eduTeacher) {
        return request({
            url: '/eduService/teacher/addTeacher',
            method: 'post',
            data: eduTeacher
        })
    },
    getTeacherById(id) {
        return request({
            url: `/eduService/teacher/getTeacherById/${id}`,
            method: 'get'
        })
    },
    updateTeacher(eduTeacher) {
        return request({
            url: '/eduService/teacher/updateTeacher',
            method: 'post',
            data: eduTeacher
        })
    }
}

// export function getList(params) {
//     return request({
//         url: '/table/list',
//         method: 'get',
//         params
//     })
// }