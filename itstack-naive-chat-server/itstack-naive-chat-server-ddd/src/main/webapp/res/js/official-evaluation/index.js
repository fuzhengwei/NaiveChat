import filter from './filter.js'
import { initData, saveComment } from './utils.js'
import { ImageList } from './components/image-list.js'

Vue.use(filter)

const { layer } = layui
const app = new Vue({
    el: '#app',

    template: `<form class="layui-form" id="form">
        <div class="form__banner layui-form-item layuimini-container">
            <label class="layui-form-label">Banner</label>
            <div class="layui-input-block">
                <ImageList 
                    v-model="data.bannerImages"
                    :limit="3"
                    :styl="{
                        width: '200px',
                        height: '100px'
                    }"/>
            </div>
        </div>
        <div
            v-for="(item, $index) in data.subitems"
            :key="$index"
            class="form__paragraph layui-form-item layuimini-container">
            <button 
                @click="del($index)"
                type="button" 
                class="form__paragraph__del layui-btn layui-btn-primary layui-btn-sm">
                <i class="layui-icon">&#xe640;</i>
            </button>
            
            <div class="layui-form-item layui-form-text">
                <label class="layui-form-label">段落{{$index + 1}}标题</label>
                <div class="layui-input-block">
                    <input 
                        v-model="item.title"
                        placeholder="请输入标题" 
                        class="layui-input"/>  
                </div>
            </div>
            <div class="layui-form-item layui-form-text">
                <label class="layui-form-label">段落{{$index + 1}}正文</label>
                <div class="layui-input-block">
                    <textarea 
                        v-model="item.content"
                        placeholder="请输入内容" 
                        class="layui-textarea"
                        rows="10"/>  
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">段落{{$index + 1}}图片</label>
                <div class="layui-input-block">
                    <ImageList 
                        :limit="1"
                        v-model="item.image"/>
                </div>
            </div>
        </div> 
        <div class="layui-form-item">
            <div class="layui-input-block">
                <button 
                    @click="addParagraph"
                    class="layui-btn layui-btn-primary" 
                    type="button">新增段落</button>
                <button 
                    @click="saveData"
                    :class="[
                        'layui-btn',
                        {
                            'layui-btn-disabled': status.loading
                        }    
                    ]" 
                    type="button">保存</button>
            </div>
        </div>   
    </form>`,

    data () {
        return {
            status: {
                loading: false
            },
            data: {
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
    },

    created () {
        const url = new URL(location.href)
        const evaluationId = url.searchParams.get('evaluationId')
        if (evaluationId != null) {
            initData(evaluationId).then(data => {
                this.data = data
            })
        }
    },

    methods: {
        /**
         * 添加段落
         */
        addParagraph () {
            this.data.subitems.push({
                image: '',
                title: '',
                content: ''
            })
        },

        /**
         * 点击保存数据
         */
        saveData () {
            if (!this.validate(this.data)) return

            this.status.loading = true
            const parentLayer = parent.layer
            saveComment({
                ...this.data
            }).then((res) => {
                if (res == null || res.status != 200 || res.data.code != 0) {
                    layer.msg('保存失败')
                    return
                }
                layer.msg('保存成功')
                setTimeout(() => {
                    parentLayer.closeAll()
                }, 1000)
            }).catch(err => {
                layer.msg(err.message || '保存失败')
            }).finally(() => {
                setTimeout(() => {
                    this.status.loading = false
                }, 400)
            })
        },

        /**
         * 删除段落
         * @param index 段落索引值
         */
        del (index) {
            this.data.subitems.splice(index, 1)
        },

        /**
         * 验证官评数据格式
         * @param data
         * @returns {boolean} 合规则返回 true
         */
        validate (data) {
            if (!data.subitems || !data.subitems.length) {
                layer.msg('至少需要添加 1 个段落')
                return false
            }

            if (!data.bannerImages || !data.bannerImages.length) {
                layer.msg('至少需要添加 1 个 Banner')
                return false
            }

            if (data.subitems.some((item, index) => {
                if (!item.image) {
                    layer.msg(`第${index + 1}段落未上传图片`)
                    return true
                }

                if (!item.title) {
                    layer.msg(`第${index + 1}段落未填写标题`)
                    return true
                }

                if (item.title.length > 40) {
                    layer.msg(`第${index + 1}段落标题超过最大40字限制`)
                    return true
                }

                if (!item.content) {
                    layer.msg(`第${index + 1}段落未填写内容`)
                    return true
                }

                return false
            })) return false

            return true
        }
    },

    /**
     * 注册组件
     */
    component: {
        ImageList
    }
})

