<html>
<head>
    <#include "../common/header.ftl">
</head>
<body>
<br><br><br><br>
    <div class="container">
        <div class="row clearfix">
            <div class="col-md-12 column">
                <form role="form" action="/sell/seller/category/save" method="post">
                    <div class="form-group">
                        <label>名称</label><input type="text" class="form-control" name="categoryName" value="${(category.categoryName) ! ''}" />
                    </div>
                    <div class="form-group">
                        <label>编号</label><input type="text" class="form-control" name="categoryType" value="${(category.categoryType) ! ''}" />
                    </div>
                    <input type="hidden" name="categoryId" value="${(category.categoryId) ! ''}">
                    <button type="submit" class="btn btn-default">Submit</button>
                </form>
            </div>
        </div>
    </div>
</body>
</html>