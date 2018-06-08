<html>
    <#include "../common/header.ftl">
<body>
    <br><br><br><br>
    <div class="container-fluid">
        <div class="row clearfix">
            <div class="col-md-12 column">
                <table class="table table-bordered table-hover table-condensed">
                    <thead>
                    <tr>
                        <th>订单id</th>
                        <th>姓名</th>
                        <th>手机号</th>
                        <th>地址</th>
                        <th>金额</th>
                        <th>订单状态</th>
                        <th>支付状态</th>
                        <th>创建时间</th>
                        <th>操作</th>
                    </tr>
                    </thead>
                    <tbody>
                    <#list orderMasterDTOPage.getContent() as orderMasterDTO>
                    <tr <#if orderMasterDTO_index % 2 == 1>class="warning"</#if>>
                        <td>${orderMasterDTO.getOrderId()}</td>
                        <td>${orderMasterDTO.getBuyerName()}</td>
                        <td>${orderMasterDTO.getBuyerPhone()}</td>
                        <td>${orderMasterDTO.getBuyerAddress()}</td>
                        <td>${orderMasterDTO.getOrderAmount()}</td>
                        <td>${orderMasterDTO.getOrderStatusEnum().getMessage()}</td>
                        <td>${orderMasterDTO.getPayStatusEnum().getMessage()}</td>
                        <td>${orderMasterDTO.getCreateTime()}</td>
                        <td>
                            <a class="btn btn-default btn-success" href="/sell/seller/order/detail?orderId=${orderMasterDTO.getOrderId()}">详情</a>
                            <#if orderMasterDTO.getOrderStatus() == "0" && orderMasterDTO.getPayStatus() == "0"><#-- order_status=0是新下单 pay_status=0是未支付 -->
                                <a class="btn btn-default btn-warning" href="/sell/seller/order/cancel?orderId=${orderMasterDTO.getOrderId()}">取消</a>
                            </#if>
                        </td>
                    </tr>
                    </#list>
                    </tbody>
                </table>
            </div>
            <div class="col-md-12 column">
                <#if orderMasterDTOPage.totalPages != 0>
                    <ul class="pagination pagination-sm pull-right">
                        <#if currentPage == 1>
                            <li class="disabled"><a>上一页</a></li>
                        <#else>
                            <li><a href="/sell/seller/order/list?size=${size}&page=${currentPage-1}">上一页</a></li>
                        </#if>
                        <#list 1..orderMasterDTOPage.getTotalPages() as  index>
                            <#if currentPage == index>
                                <li class="disabled"><a>${index}</a></li>
                            <#else>
                                <li><a href="/sell/seller/order/list?size=${size}&page=${index}">${index}</a></li>
                            </#if>
                        </#list>
                        <#if currentPage == orderMasterDTOPage.getTotalPages()>
                            <li class="disabled"><a>下一页</a></li>
                        <#else>
                            <li><a href="/sell/seller/order/list?size=${size}&page=${currentPage+1}">下一页</a></li>
                        </#if>
                    </ul>
                </#if>
            </div>
        </div>
    </div>
    <a id="modelA" href="#myModal" role="button" class="btn" data-toggle="modal">触发遮罩窗体</a>

    <#-- 弹窗 -->
    <div class="modal fade" id="myModal" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="static">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <#--<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>-->
                    <h4 class="modal-title" id="myModalLabel">
                        提醒
                    </h4>
                </div>
                <div class="modal-body">
                    你有新的订单，请注意查收...
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal" onclick="javascript:document.getElementById('notice').pause();">关闭</button>
                    <button type="button" class="btn btn-primary" onclick="window.location.reload();">查看新的订单</button>
                </div>
            </div>
        </div>
    </div>

    <#-- 播放音乐 -->
    <audio id="notice" loop="loop">
        <#--<source src="/sell/mp3/city.mp4" type="audio/mpeg">-->
    <source src="/sell/mp3/aqy.mp3" type="audio/mpeg">
    </audio>
    <script type="text/javascript">
        // $('#myModal').modal({backdrop: 'static', keyboard: false});
        var websocket = null;
        if ("WebSocket" in window) {
            websocket = new WebSocket("ws://localhost:8081/sell/websocket2");
        } else {
            alert("该浏览器不支持WebSocket");
        }
        websocket.onopen = function (event) {
            console.log("建立连接");
        };
        websocket.onclose = function (event) {
            console.log("连接关闭");
        };
        websocket.onmessage = function (event) {
            console.log("收到消息：" + event.data);
            // 弹窗提醒，播放音乐
            $("#myModal").show();
        };
        websocket.onerror = function (event) {
            alert("WebSocket通信发生错误！");
            $("#myModal").modal('show');
            document.getElementById('notice').play();
        };
        // 关闭窗口之前，关闭WebSocket
        window.onbeforeunload = function (event) {
            websocket.close();
        };

    </script>
</body>
</html>