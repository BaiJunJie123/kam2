<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>防伪编号录入</title>
    <link href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
</head>
<body style="background:white;">
<nav class="navbar-default">
    <div class="container-fluid">
        <div class="form-inline navbar-form navbar-left">
            <div class="form-group">
                <label for="code">发货  批量扫描 （先扫大码  后扫运单）</label>
                <input class="form-control typeahead" size='100' id="code" name="code" autofocus>
            </div>
			<!-- <div class="form-group">
                <label for="code">生产批号</label>
                <input id="batch" class="form-control typeahead">
            </div>
            <div class="form-group">
                <label for="perBox">选择包装的产品</label>
				<select id="type" class="form-control typeahead">
					<option class="form-control" value='20' >洗眼液</option>
					<option class="form-control" value='40' >精华液</option>
				</select>
            </div>
			<button role="btn" type="button" class="btn btn-danger" id="cbtn" onclick='prient()'>打印</button> 
			<div class="form-group">
                <label for="code">生成二维码数量</label>
                <input class="form-control typeahead" size='100' id="size" name="size">
            </div>
			<button role="btn" type="button" class="btn btn-danger" id="cbtn" onclick='exportTXT()'>导出二维码连接</button> -->
        </div>
    </div>
</nav>
<div id="page1" >
	<!-- <div style="width:1.5em;"><h5>福瑞达</h5></div>
		<img id="qrious">
	<div style="width:1.5em;"><h5>福瑞达</h5></div> -->
	<p id="p1">当前已扫描第<span id="sp1"></span>份<button id="dian" >点击提交录入</button></p>
	
</div>
<div class="row">
    <div class="col-lg-12 col-md-12">
        <div class="table-responsive">
            <table id="xiaoma" class="table table-striped table-bordered table-hover table-condensed">
                <thead>
                <tr>
                    <th>号码类型</th>
                    <th>码号</th>
                    <th>防伪操作 (删除一组)</th>
                </tr>
                </thead>
                <tbody id="cList">
                </tbody>
            </table>
        </div>
    </div>
</div>
<script src="http://static.runoob.com/assets/qrcode/qrcode.min.js"></script>
<script src="https://apps.bdimg.com/libs/jquery/2.1.4/jquery.min.js"></script>
<script src="http://ajax.microsoft.com/ajax/jquery.templates/beta1/jquery.tmpl.min.js"></script>
<script src="https://cdn.bootcss.com/Base64/1.0.1/base64.min.js"></script>
<script src="https://cdn.bootcss.com/FileSaver.js/2014-11-29/FileSaver.min.js"></script>
<script src="https://cdn.bootcss.com/TableExport/4.0.10/js/tableexport.min.js"></script>


<script src="../resources/js/jcpfree.js"></script>
<script src="../resources/js/qrious.js"></script>
<!--小箱td模板-->
<script id="ct" type="text/x-jquery-tmpl">
  <tr class="ctr">
    <td class="ctd">{{= hang}}</td>
    
    <td>{{= sc}}</td>
    <td><a  class="ajaxLink btn btn-danger default" data-role="delete" ><span class="glyphicon glyphicon-remove" aria-hidden="true"></span>删除</a>
  </tr>
</script>
<script>

$("#sp1").text(0);
// bjj 数据整合  =========================begin =======================


 $("#dian").click(function(){
 
      var zongjie = '';
      var fujia = 1;
   $("#cList").find("tr").each(function(){
       var onezhi = $(this).find("td").eq(1).text();
       var num4 = parseInt(fujia)%2;
       if(num4==0){
		   zongjie+=onezhi+',,,,,,';
		}else{
		  zongjie+=onezhi+',,,';
		}
      
      fujia++;
   });
    console.log(zongjie);
 });

//============================分割线=========================

$(".table").delegate(".ajaxLink", "click", function () {
    var shanzhi = $(this).closest("tr").find("td").eq(0).text();
    
     if(shanzhi=="大码号"){
       $(this).closest("tr").next().remove();
     }else{
       $(this).closest("tr").prev().remove();
     }
	$(this).closest("tr").remove();
	var rows6 = $("#cList tr").length;
			if(parseInt(rows6)%2==0){
			  num5 = parseInt(rows6)/2;
			}else{
			  num5 = (parseInt(rows6)-1)/2;
			}
			$("#sp1").text(num5);
})
function validateRepeat(os, code) {
	var result = false;
	var i = 1;
	os.each(function () {
		if ($(this).find(".ctd").html() == code) {
			result = true;
		}
	})
	return result;
}
function checkTypeValue(code,type){
	if(code == type){
		return true;
	}
	return false;
}

 
$("#code").keypress(function (event) {
	var keynum = (event.keyCode ? event.keyCode : event.which);
	if (keynum != '13') {
		return;
	}
	var code = $(this).val();
	var num5 = 0;
		var sc = code.substr(code.lastIndexOf("=")+1,code.length);
			var rows2 = $("#cList tr").length+1;
			var newzhi = '';
			var num = parseInt(rows2)%2;
			if(num==0){
			   newzhi = '运单号';
			}else{
			  newzhi = '大码号';
			}
			
			if(sc.substr(0,1)=="8"){
			   sc = sc.substr(0,11);
			
			}/* else if(sc.indexOf('http')>0 && sc.indexOf('boxCode')>0){
			   var index1 = sc.indexOf('=');
			   var length2 = sc.length - index1;
			   sc = sc.substr(index1,length2);
			} */
			var data = {"sc": sc,"code": code,"hang":newzhi}
			$("#cList").append($("#ct").tmpl(data));
			var rows3 = $("#cList tr").length;
			if(parseInt(rows3)%2==0){
			  num5 = parseInt(rows3)/2;
			}else{
			  num5 = (parseInt(rows3)-1)/2;
			}
			$("#sp1").text(num5);
		$("#code").val("");
	//zuobiao  =====================
	var rows = $("#cList tr").length;
	console.log( rows );
	if(rows == $("#type").val()){
		submint();
	}
}) 


</script>
<style type="text/css">
	th {
	    text-align: center;
	}
	td {
	    text-align: center;
	}
	#page1 {
      display: -webkit-flex;
      display: flex;
      -webkit-align-items: center;
      align-items: center;
      -webkit-justify-content: center;
      justify-content: center;
      height: 100%;
    }
</style>
</body>
</html>
