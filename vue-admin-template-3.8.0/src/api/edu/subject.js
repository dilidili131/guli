import request from '@/utils/request'
import url from 'postcss-url'

export default {

    getAllSubject() {
        return request({
            url: `/eduService/subject/getAllSubject`,
            method: 'get'
        })
    }
}