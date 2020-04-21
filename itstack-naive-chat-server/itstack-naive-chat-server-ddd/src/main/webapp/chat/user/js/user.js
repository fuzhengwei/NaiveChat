layui.use(['form', 'table'], function () {
    var $ = layui.jquery, form = layui.form, table = layui.table;

    // 监听搜索操作
    form.on('submit(data-search-btn)', function (data) {
        var index = layer.msg("查询中，请稍后...", {icon: 16, time: false, shade: 0});

        //执行搜索重载
        table.reload('userTable', {
            where: {
                 json: JSON.stringify(util.serializeObject(data.field))
            },
            page: {
                 curr: 1 //重新从第 1 页开始
            }

        }, 'data');
        layer.close(index);
        return false;
    });
});

util = {
    statusInfo: function (d) {
        if (d.status === true) return '<span style="color: #5FB878;font-weight: bolder;">在线</span>';
        if (d.status === false) return '<span style="color: #b8000e;font-weight: bolder;">下线</span>';
    },
    userHeadInfo: function (d) {
        return '<img src="/chat/user/img/'+ d.userHead+'.png"/>';
    },
    formatDate: function (d) {
        return layui.util.toDateString(d.entranceDate, 'yyyy-MM-dd HH:mm:ss');
    },
    //field="number|dateBegin|dateEnd"
    serializeObject: function (obj) {
        for (var c in obj) {
            if (obj[c].length === 0) {
                obj[c] = null;
                continue;
            }
        }
        return obj;
    }
};