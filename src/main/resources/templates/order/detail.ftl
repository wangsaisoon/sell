<html>
<head>
    <#include "../common/header.ftl">
</head>
<body>
    <br><br><br><br>
    <div class="container">
        <div class="row clearfix">
            <div class="col-md-6 column">
                <table class="table table-bordered table-hover">
                    <thead>
                    <tr>
                        <th>订单id</th>
                        <th>订单总金额</th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr>
                        <td>${orderMasterDTO.orderId}</td>
                        <td>${orderMasterDTO.orderAmount}</td>
                    </tr>
                    </tbody>
                </table>
            </div>
            <div class="col-md-16 column">
                <table class="table table-bordered table-hover">
                    <thead>
                    <tr>
                        <th>商品id</th>
                        <th>商品名称</th>
                        <th>单价</th>
                        <th>数量</th>
                        <th>总价</th>
                    </tr>
                    </thead>
                    <tbody>
                    <#list orderMasterDTO.getOrderDetailList() as detail>
                        <tr>
                            <td>${detail.productId}</td>
                            <td>${detail.productName}</td>
                            <td>${detail.productPrice}</td>
                            <td>${detail.productQuantity}</td>
                            <td>${detail.productPrice * detail.productQuantity}</td>
                        </tr>
                    </#list>
                    </tbody>
                </table>
            </div>
            <#if orderMasterDTO.getOrderStatus() == "0"><#-- order_status=0是新下单 pay_status=0是未支付 -->
                <a class="btn btn-default btn-primary" href="/sell/seller/order/finish?orderId=${orderMasterDTO.orderId}">完结订单</a>
            </#if>
            <#if orderMasterDTO.getOrderStatus() == "0" && orderMasterDTO.getPayStatus() == "0"><#-- order_status=0是新下单 pay_status=0是未支付 -->
                <a class="btn btn-default btn-danger" href="/sell/seller/order/cancel?orderId=${orderMasterDTO.orderId}">取消订单</a>
            </#if>
        </div>
    </div>
</body>
</html>