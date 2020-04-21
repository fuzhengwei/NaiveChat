import * as config from './config.js'

axios.defaults.headers.post['Content-Type'] = 'Content-Type:application/x-www-form-urlencoded; charset=UTF-8';

/**
 * 初始表单数据
 */
export function initData (evaluationId) {
    const data = new URLSearchParams()
    data.append('evaluationId', evaluationId)
    return axios.post(config.API_FETCH_COMMENT, data).then(res => {
        if (res == null || res.status != 200 || res.data.code != 0) {
            console.log(res)
            layer.msg('数据拉取失败')
            return {
                bannerImages: [],
                subitems: [
                    {
                        image: '',
                        content: '',
                        title: ''
                    }
                ]
            }
        }
        return res.data.data
    }, err => {
        console.log(err)
        layer.msg(err.message || '数据拉取失败')

        // 拉取失败时返回兜底数据
        return {
            bannerImages: [],
            subitems: [
                {
                    image: '',
                    content: '',
                    title: ''
                }
            ]
        }
    })
}

/**
 * 保存评论数据
 * @param data
 * @returns {*}
 */
export function saveComment (data) {
    const url = new URL(location.href)
    data.venderId = url.searchParams.get('venderId')
    data.evaluationid = url.searchParams.get('evaluationId')
    const postdata = new URLSearchParams()
    postdata.append('json', JSON.stringify(data))
    return axios.post(config.API_SAVE_COMMENT, postdata)
}