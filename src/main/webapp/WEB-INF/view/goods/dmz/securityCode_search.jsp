<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@include file="/WEB-INF/view/common/common.jsp" %>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport"
          content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="${keywords}">
    <meta http-equiv="description" content="">
    <jsp:include page="/WEB-INF/view/common/head_css.jsp"></jsp:include>
    <jsp:include page="/WEB-INF/view/common/head_css_fav.jsp"></jsp:include>

    <style type="text/css">
        .login_logo {
            padding-top: 22px;
            /*height: 207px;*/
            color: white;
        }
    </style>
</head>
<body style="background:white;">
<nav class="navbar navbar-default navbar-static-top" role="navigation">
    <div class="container-fluid">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle animated flip pull-left"
                    data-toggle="collapse" data-target=".navbar-collapse">
                <span class="sr-only">Toggle navigation</span> <span
                    class="icon-bar"></span> <span class="icon-bar"></span> <span
                    class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="<c:url value='/login.html'/>"><img
                    alt="Brand" style="width:20px;height:20px;"
                    src="<c:url value='/resources/images/title-1.png'/>"></a>
            <p class="navbar-text">咖盟微商</p>
        </div>
        <div class="collapse navbar-collapse"
             id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav">
                <li class="active"><a
                        href="<c:url value='/dmz/securityCode/search.html' />"><span
                        class="glyphicon glyphicon-barcode"></span>防伪码查询</a></li>
                <li><a
                        href="<c:url value='/dmz/agent/search.html?f=1' />"><span
                        class="glyphicon glyphicon-user"></span>代理信息查询</a></li>
            </ul>

        </div>
    </div>
</nav>
<div class="jumbotron" style="background-color:white;padding-bottom:0;padding-top:0;">
    <div class="container-fluid">
        <div class="row">
            <div class="col-md-12 col-sm-12 col-xs-12">
                <div class="login_logo">
                    <img src="<c:url value='/resources/images/title-1.png'/>" class="center-block img-responsive">
                </div>
            </div>
        </div>
    </div>
</div>
<div class="container-fluid">
    <div class="row" style="margin-bottom:20px;">
        <div class="col-md-4 col-md-offset-4 col-sm-8 col-sm-offset-2 col-xs-12">
            <form role="form" class="form-horizontal"
                  action="<c:url value='/dmz/securityCode/search.html' />"
                  method="GET" name="searchForm" id="searchForm">
                <div class="form-group input-group">
                    <input type="search" name="code" id="code" value="${parameter.code}"
                           class="form-control input-lg" placeholder="请输入防伪码进行查询"
                           autofocus="autofocus" tabindex="1"> <span
                        class="input-group-btn">
							<button type="submit" class="btn btn-lg btn-primary">
								<span class="glyphicon glyphicon-search"></span>查询
							</button>
						</span>
                </div>

            </form>
        </div>
    </div>
    <%--<c:if test="${ empty codes and empty f}">--%>
    <%--<div class="row">--%>
    <%--<div--%>
    <%--class="col-md-12 col-sm-12 col-xs-12">--%>
    <%--<div class="alert alert-warning text-center" role="alert">未查询到咖盟商品信息<a href="http://ht.zmz365.com/dreamer/dmz/securityCode/search.html?code=${param.code}">点我去筑美查询看看</a></div>--%>
    <%--</div>--%>
    <%--</div>--%>
    <%--</c:if>--%>
    <c:if test="${ ! empty codes}">
        <div class="row">
            <div
                    class="col-md-12 col-sm-12 col-xs-12">
                <div class="table-responsive">
                    <table class="table table-hover dataTable">
                        <thead>
                        <tr>
                            <th>商品名称</th>
                            <th>商品所有者</th>
                            <th>防伪码</th>
                            <th>录入时间</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${codes}" var="c">
                            <tr class="success">
                                <%--<td>${c.goodsName}</td>--%>
                                <td>玻尿酸/洗眼液</td>
                                <td>${c.owner}</td>
                                <td>${c.code}</td>
                                <td>${c.updateTime}</td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>

            </div>
        </div>
    </c:if>
</div>
<c:if test="${not empty codes}">
    <div style="text-align: center">
        <img style="max-width: 600px;width: 100%" src="<c:url value="/resources/images/sbgm.jpeg"/>" alt="">
    </div>
</c:if>

<c:if test="${empty codes}">
    <div style="text-align: center">
        <c:if test="${isActivity==false}">
            非正品，请谨慎购买！
        </c:if>
        <c:if test="${isActivity!=false}">
           防伪码没有关联，请联系公司！${isActivity}
        </c:if>
    </div>
</c:if>

<%--<h3 align="center" align="center" style="color:red">11月19日之前的货物请点击<a href="http://www.zmz365.com/zmz365/Search.jsp?pt=0">旧版查询</a></h3>--%>
<jsp:include page="/WEB-INF/view/common/head.jsp"></jsp:include>
<jsp:include page="/WEB-INF/view/common/datatables.jsp"></jsp:include>
<jsp:include page="/WEB-INF/view/common/script_common.jsp"></jsp:include>
<script type="text/javascript">

</script>
</body>
</html>
