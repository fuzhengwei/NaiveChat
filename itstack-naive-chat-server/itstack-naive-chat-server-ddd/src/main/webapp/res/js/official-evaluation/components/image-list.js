import * as config from '../config.js'

const { layer } = layui

/**
 * @component 图片展示及上传组件
 */
export const ImageList = Vue.component('ImageList', {
    props: {
        // 图片数据 {String | Array}
        value: {
            required: true,
            default: ''
        },

        // 自定义样式
        styl: Object,

        // 图片列表最大长度
        limit: {
            type: Number,
            default: 3
        }
    },

    template: `<div class="form__inputImage">
        <div 
            v-for="(item, $index) in computedVal"
            :key="item"
            class="form__inputImage__image">
            <img 
                :src="item | imageLoadFilter"
                :style="styl" 
                class="form__image"/>
            <button
                type="button"
                @click="del($index)" 
                class="form__inputImage__del">×</button> 
        </div>
        <button
            ref="formUpload"
            v-show="computedVal.length < limit"
            type="button" class="layui-btn form__inputImage__upload">
            <i class="layui-icon">&#xe67c;</i>上传图片
        </button>
    </div>`,

    mounted () {
        const {
            upload
        } = layui

        const self = this
        // 初始化上传
        upload.render({
            elem: this.$refs.formUpload,
            url: config.API_UPLOAD_IMAGE,
            done: function (res){
                // 将图片地址更新至 value
                if (res && res.msg) {
                    if (self.isStringVal) {
                        self.$emit('input', res.msg)
                    } else {
                        self.$emit('input', self.value.concat([res.msg]))
                    }
                    layer.msg('上传成功')
                }
            },
            error: function (e){
                //请求异常回调
                layer.msg(e.message || '上传失败')
            }
        });
    },

    computed: {
        computedVal () {
            return this.isStringVal ?
                this.value ? [this.value] : []
                : this.value
        },

        isStringVal () {
            return typeof this.value === 'string'
        }
    },

    methods: {
        /**
         * 删除第 index 张图片
         * @param index {number} 图片索引值
         */
        del (index) {
            if (this.isStringVal) {
                this.$emit('input', '')
            } else {
                const newVal = this.value.slice()

                newVal.splice(index, 1)
                this.$emit('input',  newVal)
            }
        }
    }
})