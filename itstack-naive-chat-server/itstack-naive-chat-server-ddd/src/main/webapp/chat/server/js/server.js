layui.use(['form', 'table'], function () {
    var $ = layui.jquery,
        form = layui.form,
        table = layui.table;

    // 监听搜索操作
    form.on('submit(data-search-btn)', function (data) {
        //执行搜索重载
        table.reload('currentTableId', {
            url: '/api/queryNettyServerInfo?',
            page: {
                curr: 1
            }
        }, 'data');
        return false;
    });

    // 事件操作
    $(".data-add-btn").on("click", function () {
        var checkTable = table.checkStatus('currentTableId')
        if (null == checkTable) return;

        var data = checkTable.data[0];

        var instruct = {};
        instruct.ip = data.ip;
        instruct.schedulerServerId = data.schedulerServerId;
        instruct.beanName = data.beanName;
        instruct.methodName = data.methodName;
        instruct.cron = data.cron;
        instruct.status = 1;

        $.ajax({
            type: 'post',
            url: '/api/pushInstruct',
            data: {
                json: JSON.stringify(instruct)
            },
            success: function (res) {
                if (res.code === 0) {
                    layer.msg('启动成功');
                    table.reload('currentTableId');
                } else {
                    layer.msg('启动失败');
                }
            }
        });

    });

    //监听表格复选框选择
    table.on('checkbox(currentTableFilter)', function (obj) {
        console.log(obj)
    });

    table.on('tool(currentTableFilter)', function (obj) {
        var data = obj.data;
        if (obj.event === 'edit') {
            layer.alert('编辑行：<br>' + JSON.stringify(data))
        } else if (obj.event === 'delete') {
            layer.confirm('真的删除行么', function (index) {
                obj.del();
                layer.close(index);
            });
        }
    });

});

menu = {


};

util = {
    statusInfo: function (d) {
        if (d.status === true) return '<span style="color: #5FB878;font-weight: bolder;">启动</span>';
        if (d.status === false) return '<span style="color: #b8000e;font-weight: bolder;">关闭</span>';
    },
    formatDate: function (d) {
        return layui.util.toDateString(d.entranceDate, 'yyyy-MM-dd HH:mm:ss');
    }
};