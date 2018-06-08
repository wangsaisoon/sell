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
                        <th>商品id</th>
                        <th>名称</th>
                        <th>图片</th>
                        <th>单价</th>
                        <th>库存</th>
                        <th>描述</th>
                        <th>类目</th>
                        <th>创建时间</th>
                        <th>修改时间</th>
                        <th>操作</th>
                    </tr>
                    </thead>
                    <tbody>
                    <#list productInfoPage.content as productInfo>
                    <tr <#if productInfo_index % 2 == 1>class="warning"</#if>>
                        <td>${productInfo.productId}</td>
                        <td>${productInfo.productName}</td>
                        <td><img src="${productInfo.productIcon}" width="50" height="50"></td>
                        <td>${productInfo.productPrice}</td>
                        <td>${productInfo.productStock}</td>
                        <td>${productInfo.productDescription}</td>
                        <td>${productInfo.categoryType}</td>
                        <td>${productInfo.createTime}</td>
                        <td>${productInfo.updateTime}</td>
                        <td>
                            <a class="btn btn-default btn-success" href="/sell/seller/product/toEdit?productId=${productInfo.productId}">修改</a>
                            <#if productInfo.productStatus == "0">
                                <a class="btn btn-default btn-warning" href="/sell/seller/product/offSale?productId=${productInfo.productId}">下架</a>
                            <#else>
                                <a class="btn btn-default btn-warning" href="/sell/seller/product/onSale?productId=${productInfo.productId}">上架</a>
                            </#if>
                        </td>
                    </tr>
                    </#list>
                    </tbody>
                </table>
            </div>
            <div class="col-md-12 column">
                <a class="btn btn-default btn-default" href="/sell/seller/product/toEdit">新增</a>
                <#if productInfoPage.totalPages != 0>
                    <ul class="pagination pagination-sm pull-right">
                        <#if currentPage == 1>
                            <li class="disabled"><a>上一页</a></li>
                        <#else>
                            <li><a href="/sell/seller/product/list?size=${size}&page=${currentPage-1}">上一页</a></li>
                        </#if>
                        <#list 1..productInfoPage.totalPages as  index>
                            <#if currentPage == index>
                                <li class="disabled"><a>${index}</a></li>
                            <#else>
                                <li><a href="/sell/seller/product/list?size=${size}&page=${index}">${index}</a></li>
                            </#if>
                        </#list>
                        <#if currentPage == productInfoPage.totalPages>
                            <li class="disabled"><a>下一页</a></li>
                        <#else>
                            <li><a href="/sell/seller/product/list?size=${size}&page=${currentPage+1}">下一页</a></li>
                        </#if>
                    </ul>
                </#if>
            </div>
        </div>
    </div>
</body>
</html>