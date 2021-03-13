<!DOCTYPE html>
<html lang="zxx" xmlns="http://www.w3.org/1999/html">
<head>
    <#assign basePath=request.contextPath />
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>请假管理</title>
    <link rel="stylesheet" href="${basePath }/layui/css/layui.css" media="all"/>
</head>
<body>
<div>
    <p>该测试主要测试审核流程，其他尽量简洁省略。用户切换可执行修改上方链接中loginName值即可。</p>
    &nbsp;&nbsp;&nbsp;&nbsp;
    测试步骤：
    </br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    1: 员工用户user登录 > 点击‘填写表单’按钮 。自动执行流程：请假流程启动》》填写请假表单》》是否请假》》提交请假表单（后台表单信息写死，提交表单并送往经理）
    </br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    2: 部门用户manager登录 > 点击‘通过申请’按钮 。自动执行流程：部门经理审批【请假申请】》》部门经理是否同意（后台写死，同意申请并送往boss）
    </br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    3: boss用户admin登录 > 点击‘通过’按钮 。自动执行流程：Boss审批【请假申请】》》请假流程结束（后台写死，同意申请）
    <p style="color: red">注：‘通过申请’或‘通过’等按钮，没有做显示条件判断。例：如果manager登录，同一表单两次点击‘通过申请’，会直接执行到流程结束。</p>
</div>
</br>

<#if user.uid == 1>
    <a class="layui-btn layui-btn-xs" id="apply">填写表单</a>
</#if>

</br>
<div class="layui-form news_list">
    <table class="layui-table" id="datasTable"  lay-filter="tableTool"> </table>
</div>
<div id="page" style="text-align: right;"></div>

<script type="text/javascript" src="${basePath }/js/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="${basePath }/layui/layui.all.js"></script>
<script>
    var table = layui.table;

    $(document).ready(function(){
        // 请假
        $("#apply").click(function () {
            $.get("${basePath }/leave/addApply?uid=${user.uid}",function () {
                query();
            });
        });

        query();
    });

    layui.use('table', function(){
        // 监听工具条
        table.on('tool(tableTool)', function(obj){
            var data = obj.data;
            if(obj.event === 'submitApply'){ // 经理 提交申请
                $.get("${basePath }/leave/submitApply?procInstId="+data.procInstanId,function () {
                    query();
                });
            }else if(obj.event === 'bossApply'){// boss 提交申请
                $.get("${basePath }/leave/bossApply?procInstId="+data.procInstanId,function () {
                    query();
                });
            }else if(obj.event === 'giveUp'){// 放弃申请
                $.get("${basePath }/leave/giveUp?procInstId="+data.procInstanId,function () {
                    query();
                });
            }else if(obj.event === 'log'){
                $.get("${basePath }/leave/HiProcActiList/"+data.procInstanId,function (json) {
                    var data=eval(json);
                    var str='<div>';
                    for (var i = 0; i < data.length; i++) {
                        str += '<p>' + data[i].actName + '</p>';
                    }
                    str += '</div>';
                    layer.alert(str);
                });
            }
        });
    });

    function query(){
        var url="${basePath }/leave/list?uid=${user.uid}";
        $.post(url,function(json){
            layui.use('table', function(){
                //展示已知数据
                table.render({
                    elem: '#datasTable',
                    data: json,
                    cellMinWidth: 100, //全局定义常规单元格的最小宽度，layui 2.2.1 新增
                    cols: [[ //标题栏
                        {type:'checkbox'},
                        {field: 'title', title: '标题'},
                        {field: 'procInstanId', title: '实例id'},
                        {field: 'leaveContent', title: '请假内容'},
                        {field: 'status', title: '状态'},
                        {fixed: 'right', align:'center', toolbar: '#barDemo',width:280}
                    ]],
                    skin: 'row', // 表格风格
                    even: true,// 开启隔行背景
                    page: true, // 是否显示分页
                    limits: [4,5,7,8,10],
                    limit: 10 // 每页默认显示的数量
                });
            });
        },"json");
    }
</script>

<#-- 数据操作按钮 -->
<script type="text/html" id="barDemo">
    <#if user.uid == 1 >
        <a class="layui-btn layui-btn-xs  layui-btn-danger" lay-event="giveUp">放弃请假</a>
    </#if>
    <#if user.uid == 2 >
        <a class="layui-btn layui-btn-xs" lay-event="submitApply">通过申请</a>
    </#if>
    <#if user.uid == 3 >
        <a class="layui-btn layui-btn-xs" lay-event="bossApply">通过</a>
    </#if>
    <a class="layui-btn layui-btn-xs" lay-event="log">进程</a>
</script>

</body>
</html>