<html>
<head>
    <#include "../common/header.ftl">
</head>
<body>
<br><br><br><br>
    <div class="container">
        <div class="row clearfix">
            <div class="col-md-12 column">
                <form role="form" action="/sell/seller/product/save" method="post">
                    <div class="form-group">
                        <label>商品名称</label><input type="text" class="form-control" name="productName" value="${(productInfo.productName) ! ''}" />
                    </div>
                    <div class="form-group">
                        <label>单价</label><input type="text" class="form-control" name="productPrice" value="${(productInfo.productPrice) ! ''}" />
                    </div>
                    <div class="form-group">
                        <label>库存</label><input type="number" class="form-control" name="productStock" value="${(productInfo.productStock) ! ''}" />
                    </div>
                    <div class="form-group">
                        <label>描述</label><input type="text" class="form-control" name="productDescription" value="${(productInfo.productDescription) ! ''}" />
                    </div>
                    <div class="form-group">
                        <label>图片</label>
                        <#if (productInfo.productIcon) ??>
                            <img src="${(productInfo.productIcon) ! ''}" width="200" height="200">
                        </#if>
                        <input type="file" class="form-control" name="productIcon" value="${(productInfo.productIcon) ! ''}" />
                    </div>
                    <div class="form-group">
                        <label>类目</label>
                        <select name="categoryType" class="form-control">
                            <#list productCategoryList as category>
                                <option value="${category.categoryType}" <#if ((productInfo.categoryType) ??) && productInfo.categoryType = category.categoryType>selected="selected"</#if>>${category.categoryName}</option>
                            </#list>
                        </select>
                    </div>
                    <input type="hidden" name="productId" value="${(productInfo.productId) ! ''}">
                    <button type="submit" class="btn btn-default">Submit</button>
                </form>
            </div>
        </div>
    </div>
</body>
</html>