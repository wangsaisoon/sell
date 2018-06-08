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
                        <th>类目id</th>
                        <th>名称</th>
                        <th>编号</th>
                        <th>创建时间</th>
                        <th>修改时间</th>
                        <th>操作</th>
                    </tr>
                    </thead>
                    <tbody>
                    <#list categoryList as category>
                    <tr <#if category_index % 2 == 1>class="warning"</#if>>
                        <td>${category.categoryId}</td>
                        <td>${category.categoryName}</td>
                        <td>${category.categoryType}</td>
                        <td>${category.createTime}</td>
                        <td>${category.updateTime}</td>
                        <td>
                            <a class="btn btn-default btn-success" href="/sell/seller/category/toEdit?categoryId=${category.categoryId}">修改</a>
                        </td>
                    </tr>
                    </#list>
                    </tbody>
                </table>
            </div>
            <div class="col-md-12 column">
                <a class="btn btn-default btn-default" href="/sell/seller/category/toEdit">新增</a>
            </div>
        </div>
    </div>
</body>
</html>