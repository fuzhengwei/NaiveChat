export function numberFilter (number, fixed = 2) {
    if (number > 10000) {
        return Number((number / 10000).toFixed(fixed))
    } else {
        return number
    }
}

const ImgUrlTester = /^(https?:)?\/\//

export function imageLoadFilter (src) {
    if (ImgUrlTester.test(src)) {
        return src
    } else {
        return `https://m.360buyimg.com/yocial/${src}`
    }
}

export default {
    install (Vue) {
        [
            numberFilter,
            imageLoadFilter
        ].forEach(filter => Vue.filter(filter.name, filter))
    }
}
