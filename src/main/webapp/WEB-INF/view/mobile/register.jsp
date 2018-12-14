<%@ page language="java" pageEncoding="UTF-8" %>
<%@include file="/WEB-INF/view/common/common.jsp" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link href="${ctx}/resources/mallcss/initcss.css" rel="stylesheet">
    <link href="${ctx}/resources/mallcss/common.css" rel="stylesheet">
    <style>

        .con {
            background-color: #e8e8e8;
        }

        header {
            background: #f03791;
            height: 3.7em;
            text-align: center;
            position: relative;
        }

        .Return, .Home {
            position: absolute;
            width: 3.7em;
            height: 3.7em;
        }

        .Return {
            left: 0;
        }

        .Home {
            right: 0;
        }

        .Return span, .Home span {
            background: url("${ctx}/resources/mallimages/png.png");
            background-size: 17em;
            position: absolute;
            width: 1.7em;
            height: 1.7em;
        }

        .Return span {
            background-position: -0.17em -5.5em;
            left: 1em;
            top: 1em;
        }

        .Home span {
            background-position: -0.17em -8.1em;
            left: 1em;
            top: 1em;
        }

        .Title {
            color: white;
            font-size: 1.3em;
            line-height: 2.7em;
        }

        .login section {
            padding: 0.8em 0.8em;
        }

        .input {
            background: #ffffff;
            border-radius: 4px;
            border: #dadada 1px solid;
            -webkit-box-shadow: 0 1px 2px rgba(0, 0, 0, .1) inset;
        }

        .name {
            padding: 0.3em 0.5em;
            /*overflow: hidden;*/
            position: relative;
            line-height: 2.3em;
            font-size: 1.1em;
            border-bottom: 1px solid #e3e3e3;
        }

        .name b {
            color: #85858d;
            text-align: center;
            font-size: 1.2em;
            line-height: 1.8em;
            position: absolute;
            left: 0.5em;
            top: 0.5em;
        }

        .name span {
            display: block;
            /*position: static;*/
            padding-left: 4em;

        }

        .name span input {
            border: none;
            width: 100%;
            padding: 0;
            height: 2.3em;
            font-size: 1.2em;
            outline: none;
        }

        .loginBtn {
            display: block;
            background: #f02387;
            width: 100%;
            border-radius: 1em;
            color: white;
            text-align: center;
            font-size: 1.5em;
            margin-top: 1.5em;
            padding: 0.7em 0;
            margin-bottom: 0.5em;
        }

        .hz {
            border-top: 1px white solid;
            text-align: center;
            padding: 1em;
            margin-top: 1em;
        }

        .hz h2 {
            margin-top: -1.3em;
        }

        .hz h2 span {
            background: #e8e8e8;
            color: #5e5e5e;
            font-size: 0.8em;
        }

        .hz p {
            padding: 1em;
            text-align: center;
        }

        .hz p img {
            margin-right: 0.3em;
            width: 2.1em;
        }


    </style>
    <title>完善信息</title>
</head>
<body style="background: rgb(232, 232, 232);">
<div class="con">
    <%--<header>--%>
        <%--<a href="" class="Return"><span></span></a>--%>
        <%--<span class="Title">完善信息</span>--%>
        <%--&lt;%&ndash;<a href="" class="Home"><span></span></a>&ndash;%&gt;--%>
    <%--</header>--%>
    <form id="loginForm" method="post" action="<c:url value='/mobile/register.json' />">
        <div class="login">
            <section>
                <div class="input">
                    <div class="name">
                        <b>姓名：</b><span><input name="realName" type="text"></span>
                    </div>
                    <div class="name">
                        <b>身份证</b><span><input name="idCard" type="text"></span>
                    </div>
                    <div class="name">
                        <b>手机：</b><span><input name="mobile" type="text"></span>
                    </div>
                    <div class="name">
                        <b>微信：</b><span><input name="weixin" type="text"></span>
                    </div>
                    <div class="name">
                        <b>密码：</b><span><input id="password" name="password" placeholder="" type="password"></span>
                    </div>
                    <div class="name">
                        <b>重复：</b><span><input id="rePaw" name="rePaw" placeholder="" type="password"></span>
                    </div>
                    <input type="hidden" value="${s_nickname}" name="nickName">
                    <input type="hidden" value="${s_headimgurl}" name="headimgurl">
                    <input type="hidden" value="${s_unionid}" name="wxUnionID">
                    <input type="hidden" value="${s_openid}" name="wxOpenid">
                    <input type="hidden" value="${refCode}" name="refCode">
                </div>
                <p style="color: red" id="error"></p>
                <p>
                    <label class="loginBtn"  id="loginBtn">立即提交</label>
                </p>
                <p><a style="color: blueviolet" href="<c:url value="/mobile/out.html"/>" class="register">我是老客户有编号,去登陆</a>
                </p>
            </section>
        </div>
    </form>
    <div class="hz">
        <%--<h2><a href=""><span style="color:blueviolet;">我有账号,点我登录</span></a></h2>--%>
        <%--<p>--%>
            <%--<img src="${ctx}/resources/mallimages/qq.png" alt="">--%>
            <%--<img src="${ctx}/resources/mallimages/weibo.png" alt="">--%>
        <%--</p>--%>
    </div>
</div>

<jsp:include page="footer.jsp"/>
<script src="https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>
<script src="https://cdn.bootcss.com/jquery.form/4.2.1/jquery.form.min.js"></script>
<script src="https://cdn.bootcss.com/jquery-validate/1.16.0/jquery.validate.min.js"></script>
<script src="https://cdn.bootcss.com/jquery-validate/1.16.0/additional-methods.min.js"></script>
<script>
var idCardNoUtil = {
	    provinceAndCitys: { 11: "北京",12: "天津",13: "河北",14: "山西",15: "内蒙古",21: "辽宁",22: "吉林",
	        23: "黑龙江",31: "上海",32: "江苏",33: "浙江",34: "安徽",35: "福建",36: "江西",37: "山东",41: "河南",
	        42: "湖北",43: "湖南",44: "广东",45: "广西",46: "海南",50: "重庆",51: "四川",52: "贵州",53: "云南",
	        54: "西藏",61: "陕西",62: "甘肃",63: "青海",64: "宁夏",65: "新疆",71: "台湾",
	        81: "香港",82: "澳门",91: "国外"},
		powers: ["7", "9", "10", "5", "8", "4", "2", "1", "6", "3", "7", "9", "10", "5", "8", "4", "2"],
		parityBit: ["1", "0", "X", "9", "8", "7", "6", "5", "4", "3", "2"],
		genders: { male: "男", female: "女" },
	    checkAddressCode: function(addressCode) {
	        var check = /^[1-9]\d{5}$/.test(addressCode);
	        if (!check) return false;
	        if (idCardNoUtil.provinceAndCitys[parseInt(addressCode.substring(0, 2))]) {
	            return true;
	        } else {
	            return false;
	        }
	    },
	    checkBirthDayCode: function(birDayCode) {
	        var check = /^[1-9]\d{3}((0[1-9])|(1[0-2]))((0[1-9])|([1-2][0-9])|(3[0-1]))$/.test(birDayCode);
	        if (!check) return false;
	        var yyyy = parseInt(birDayCode.substring(0, 4), 10);
	        var mm = parseInt(birDayCode.substring(4, 6), 10);
	        var dd = parseInt(birDayCode.substring(6), 10);
	        var xdata = new Date(yyyy, mm - 1, dd);
	        if (xdata > new Date()) {
	            return false; //生日不能大于当前日期
	        } else if ((xdata.getFullYear() == yyyy) && (xdata.getMonth() == mm - 1) && (xdata.getDate() == dd)) {
	            return true;
	        } else {
	            return false;
	        }
	    },
	    getParityBit: function(idCardNo) {
	        var id17 = idCardNo.substring(0, 17);
	        var power = 0;
	        for (var i = 0; i < 17; i++) {
	            power += parseInt(id17.charAt(i), 10) * parseInt(idCardNoUtil.powers[i]);
	        }
	        var mod = power % 11;
	        return idCardNoUtil.parityBit[mod];
	    },
	    checkParityBit: function(idCardNo) {
	        var parityBit = idCardNo.charAt(17).toUpperCase(); 
	        if (idCardNoUtil.getParityBit(idCardNo) == parityBit) {
	            return true;
	        } else {
	            return false;
	        }
	    },
	    checkIdCardNo: function(idCardNo) {
	        //15位和18位身份证号码的基本校验
	        var check = /^\d{15}|(\d{17}(\d|x|X))$/.test(idCardNo);
	        if (!check) return false;
	        //判断长度为15位或18位
	        if (idCardNo.length == 15) {
	            return idCardNoUtil.check15IdCardNo(idCardNo);
	        } else if (idCardNo.length == 18) {
	            return idCardNoUtil.check18IdCardNo(idCardNo);
	        } else {
	            return false;
	        }
	    },
	    //校验15位的身份证号码
	    check15IdCardNo: function(idCardNo) {
	        //15位身份证号码的基本校验
	        var check = /^[1-9]\d{7}((0[1-9])|(1[0-2]))((0[1-9])|([1-2][0-9])|(3[0-1]))\d{3}$/.test(idCardNo);
	        if (!check) return false;
	        //校验地址码
	        var addressCode = idCardNo.substring(0, 6);
	        check = idCardNoUtil.checkAddressCode(addressCode);
	        if (!check) return false;
	        var birDayCode = '19' + idCardNo.substring(6, 12);
	        //校验日期码
	        return idCardNoUtil.checkBirthDayCode(birDayCode);
	    },
	    //校验18位的身份证号码
	    check18IdCardNo: function(idCardNo) {
	        //18位身份证号码的基本格式校验
	        var check = /^[1-9]\d{5}[1-9]\d{3}((0[1-9])|(1[0-2]))((0[1-9])|([1-2][0-9])|(3[0-1]))\d{3}(\d|x|X)$/.test(idCardNo);
	        if (!check) return false;
	        //校验地址码
	        var addressCode = idCardNo.substring(0, 6);
	        check = idCardNoUtil.checkAddressCode(addressCode);
	        if (!check) return false;
	        //校验日期码
	        var birDayCode = idCardNo.substring(6, 14);
	        check = idCardNoUtil.checkBirthDayCode(birDayCode);
	        if (!check) return false;
	        //验证校检码
	        return idCardNoUtil.checkParityBit(idCardNo);
	    },
	    formateDateCN: function(day) {
	        var yyyy = day.substring(0, 4);
	        var mm = day.substring(4, 6);
	        var dd = day.substring(6);
	        return yyyy + '-' + mm + '-' + dd;
	    },
	    //获取信息
	    getIdCardInfo: function(idCardNo) {
	        var idCardInfo = {
	            gender: "", //性别
	            birthday: "" // 出生日期(yyyy-mm-dd)
	        };
	        if (idCardNo.length == 15) {
	            var aday = '19' + idCardNo.substring(6, 12);
	 
	            idCardInfo.birthday = idCardNoUtil.formateDateCN(aday);
	 
	            if (parseInt(idCardNo.charAt(14)) % 2 == 0) {
	                idCardInfo.gender = idCardNoUtil.genders.female;
	            } else {
	                idCardInfo.gender = idCardNoUtil.genders.male;
	            }
	        } else if (idCardNo.length == 18) {
	            var aday = idCardNo.substring(6, 14);
	 
	            idCardInfo.birthday = idCardNoUtil.formateDateCN(aday);
	 
	            if (parseInt(idCardNo.charAt(16)) % 2 == 0) {
	                idCardInfo.gender = idCardNoUtil.genders.female;
	            } else {
	                idCardInfo.gender = idCardNoUtil.genders.male;
	            }
	        }
	        return idCardInfo;
	    },
	    getId15: function(idCardNo) {
	        if (idCardNo.length == 15) {
	            return idCardNo;
	        } else if (idCardNo.length == 18) {
	            return idCardNo.substring(0, 6) + idCardNo.substring(8, 17);
	        } else {
	            return null;
	        }
	    },
	    getId18: function(idCardNo) {
	        if (idCardNo.length == 15) {
	            var id17 = idCardNo.substring(0, 6) + '19' + idCardNo.substring(6);
	            var parityBit = idCardNoUtil.getParityBit(id17);
	            return id17 + parityBit;
	        } else if (idCardNo.length == 18) {
	            return idCardNo;
	        } else {
	            return null;
	        }
	    }
	};
    $(function () {
        !function (w) {
            if (w.utils)return;
            var Utils = function () {
            }
            Utils.prototype = {
                Constructor: Utils,
                phoneValidate: function (e) {
                //    var sr = /^(0[0-9]{2,3}\-)?([2-9][0-9]{6,7})+(\-[0-9]{1,4})?$/g;
                	var sr=/^(13[0-9]|14[5-9]|15[012356789]|166|17[0-8]|18[0-9]|19[8-9])[0-9]{8}$/;  
                	return this.mobilePhone(e) || sr.test(e);
                },
                mobilePhone: function (e) {
                    var mr = /^((\(\d{3}\))|(\d{3}\-))?1[3-9][0-9]\d{8}$/g;
                    return mr.test(e);
                },
                pawValidate: function (e) {
                    var paw = $("#password").val();
                    return e == paw;
                }
            }
            w["utils"] = new Utils();
        }(window);

        if (jQuery.validator) {
            jQuery.validator.addMethod("telephone", function (value, element) {
                return this.optional(element) || window.utils.phoneValidate(value);
            }, "请正确填写电话号码");
            jQuery.validator.addMethod("idCard", function (value, element) {
                return this.optional(element) || idCardNoUtil.checkIdCardNo(value);
            }, "请正确填写身份证号码");
            jQuery.validator.addMethod("mobile", function (value, element) {
                return this.optional(element) || window.utils.mobilePhone(value);
            }, "请正确填写手机号码");
            jQuery.validator.addMethod("rePaw", function (value, element) {
                return this.optional(element) || window.utils.pawValidate(value);
            }, "两次密码不同");
        }
        var btn = null;
        $("#loginForm").validate(
            {
                submitHandler: function (form) {
                    $(form)
                        .ajaxSubmit(
                            {
                                beforeSubmit: function () {
                                    btn.html("loading");
                                },
                                success: function (responseText, statusText, xhr, $form) {//btn.button("reset");
                                    var m = $.parseJSON(xhr.responseText);
                                    if (m.flag == "0") {
                                        var l = xhr.getResponseHeader("Location");
                                        window.location = l;
                                    } else {
                                        alert(m.message);
                                        btn.html("提交注册");
                                    }

                                },
                                error: function (xhr, textStatus, errorThrown) {
                                    alert(xhr.responseText);
                                    var m = $.parseJSON(xhr.responseText);
//                                        $("#alert").empty().html(m.message).removeClass("invisible");
//                                        btn.button("reset");
//                                        $(".captchaImg").click();
//                                        $("#accountName").focus();
                                    alert(m.message);
                                    btn.html("提交注册");
//                                        window.location.reload();
                                }
                            });
                },
                rules: {
                    realName: {
                        required: true
                    },
                    idCard:{
                    	required: true,
                    	idCard: true
                    },
                    mobile: {
                        required: true,
                        mobile: true
                    },
                    weixin: {
                        required: true
                    },
                    password: {
                        required: true
                    },
                    rePaw: {
                        required: true,
                        rePaw: true
                    }
                },
                onkeyup: false,
                messages: {
                    realName: {
                        required: "请输入代理姓名"
                    },
                    idCard:{
                    	required: "请输入身份证号"
                    },
                    mobile: {
                        required: "请输入手机号码"
//                        mobile: "手机号码格式不正确"
                    },
                    weixin: {
                        required: "请输入微信号"
                    },
                    password: {
                        required: "请输入密码"
                    },
                    rePaw: {
                        required: "请输入密码",
                        rePaw: "两次密码不一样"
                    }
                },
                focusInvalid: true,
                errorClass: "text-danger",
                validClass: "valid",
                errorElement: "small",
                errorPlacement: function (error, element) {
//                    error.appendTo(element.closest(
//                        "div.row").children(
//                        "div.text-error"));
                    $("#error").html(error);

                }
            });
        $("#loginBtn").click(function () {
            btn = $(this);
            $("#loginForm").submit();
        });
    })
</script>
</body>
</html>