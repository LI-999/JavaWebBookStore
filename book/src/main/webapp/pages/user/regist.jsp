<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>尚硅谷会员注册页面</title>
    <%--使用静态包含替换--%>
    <%@include file="/pages/common/header.jsp" %>

    <style type="text/css">
        .login_form {
            height: 420px;
            margin-top: 25px;
        }

    </style>

    <script type="text/javascript">
        $(function () {
            $('#sub_btn').click(function () {

                // 用户名
                var $username = $('#username').val();
                var $usernamePattern = /^\w{5,12}$/;


                if (!$usernamePattern.test($username)) {
                    console.log($('.erroMsg'));
                    $('.errorMsg').text('用户名格式错误');
                    return false;
                }

                //密码
                var $passwordPattern = /^\w{5,12}$/;
                var $password = $("#password").val();
                var $passwordRepeat = $("#repwd").val();

                if (!$passwordPattern.test($password)) {
                    $('.errorMsg').text('密码格式错误');
                    return false;
                }

                if ($password != $passwordRepeat) {
                    $('.errorMsg').text('两次密码不一致');
                    return false;
                }

                //邮箱正则表达式
                var $emailPattern = /^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+/;
                var $email = $('#email').val();
                if (!$emailPattern.test($email)) {
                    $('.errorMsg').text('邮箱格式错误');
                    return false;
                }


                //验证码
                var $code = $('#code').val();
                console.log($code);
                $code = $.trim($code);
                if ($code == null || $code == '') {
                    $('.errorMsg').text('验证码错误');
                    return false;
                }

                $('.errorMsg').text('');
            })

            //验证码图片单击刷新
            $("#code_jpg").click(function () {
                //属性整个页面 不适合放在这里当用户输入完用户名、密码、邮箱时 点击验证码会导致用户名密码邮箱被清空
                // location.reload();

                <%--alert("${url}");--%>
                <%-- header中的设置的绝对路径  ${url}--%>
                this.src = "${url}kaptcha.jpg";
                console.log(new Date());
            })

            //ajax判断用户名是否存在
            $("#username").blur(function () {
                var username = this.value;
                console.log(username);
                $.getJSON("userServlet","action=ajaxExsistsUsername&"+username,function(msg){
                    console.log(msg.exsistsUsername);
                    if(msg.exsistsUsername){
                        $('.errorMsg').text('用户名已存在');
                    }else{
                        $('.errorMsg').text('用户名可用');
                    }

                })
            })
        })
    </script>
</head>
<body>
<div id="login_header">
    <img class="logo_img" alt="" src="static/img/logo2.png">
</div>

<div class="login_banner">

    <div id="l_content">
        <span class="login_word">欢迎注册</span>
    </div>

    <div id="content">
        <div class="login_form">
            <div class="login_box">
                <div class="tit">
                    <h1>注册尚硅谷会员</h1>
                    <span class="errorMsg">
<%--									<%=request.getAttribute("Msg")==null?"":request.getAttribute("Msg")%>--%>
									${requestScope.Msg}
								</span>
                </div>
                <div class="form">
                    <form action="userServlet" method="post">
                        <input type="hidden" name="action" value="register">
                        <label>用户名称：</label>
                        <input class="itxt" type="text" placeholder="请输入用户名" autocomplete="off" tabindex="1"
                               name="username" id="username" value="${requestScope.username}"/>
                        <br/>
                        <br/>
                        <label>用户密码：</label>
                        <input class="itxt" type="password" placeholder="请输入密码" autocomplete="off" tabindex="1"
                               name="password" id="password"/>
                        <br/>
                        <br/>
                        <label>确认密码：</label>
                        <input class="itxt" type="password" placeholder="确认密码" autocomplete="off" tabindex="1"
                               name="repwd" id="repwd"/>
                        <br/>
                        <br/>
                        <label>电子邮件：</label>
                        <%--									value="<%=request.getAttribute("email")==null?"":request.getAttribute("email")%>数据回显--%>
                        <input class="itxt" type="text" placeholder="请输入邮箱地址" autocomplete="off" tabindex="1"
                               name="email" id="email" value="${requestScope.email}"/>
                        <br/>
                        <br/>
                        <label>验证码：</label>
                        <input class="itxt" type="text" style="width: 150px;" id="code" name="code"/>
                        <img id="code_jpg" alt="" src="kaptcha.jpg"
                             style="float: right; margin-right: 40px; width:90px;height:40px">
                        <br/>
                        <br/>
                        <input type="submit" value="注册" id="sub_btn"/>

                    </form>
                </div>

            </div>
        </div>
    </div>
</div>

<%--		静态包含--%>
<%@ include file="/pages/common/footer.jsp" %>
</body>
</html>