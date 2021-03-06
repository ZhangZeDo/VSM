<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Insert title here</title>
    <script src="https://cdn.staticfile.org/jquery/1.10.2/jquery.min.js"></script>
</head>
<body>
<div id="container" align="center">
    <div class="logo">联系我们</div>
    <form action="/contactUs" method="post">
        <div class="public-input">
            <input class="username has-border" name="userName" type="text" placeholder="请输入用户名">
        </div>
        <textarea rows="8" cols="55" name="remark" placeholder="请输入联系内容"></textarea>
        <br><br>
        <div class="btnLogin public-input">
            <input class="btnLogin public-input" type="submit" value="确认">
        </div>
    </form>
</div>
</body>
<style>
    html,body,div,form,input,span,a{
        padding: 0;
        margin: 0;
    }
    body{
        font-size: 0.75em;
        background-color: #3bc0c3;
    }
    #container{
        width: 33%;
        height: 500px;
        border: 5px solid #2344a6;
        margin-top: 10%;
        margin-left: 33%;

        position: absolute;
    }
    #container .logo{
        width: 100%;
        height: 55px;
        background: #f8fbfb;
        text-align: center;
        line-height: 55px;
        font-size: 18px;
        font-weight: bold;
        color: #484848;
        border-bottom: 1px solid #eff4f4;
    }
    #container form{
        width: 81.818181812%;
        margin: 20px auto;
        color: #e2e2e2;
    }
    #container .public-input{
        width: 100%;
        height: 40px;
        border: 1px solid #e2e2e2;
        margin-bottom: 20px;
        overflow: hidden;
    }
    #container form input[type="text"],
    #container form input[type="password"]{
        width: 100%;
        height: 100%;
        border: none;
    }
    #container .username{
        background-position: 3px 8px;
        background-color: #fff;
    }
    #container form input[name="username"]{
        padding-left: 20px;
    }
    #container .password{
        background-position: 3px -54px;
        background-color: #fff;
    }
    #container form input[name="password"]{
        padding-left: 20px;
    }
    #container .public-input input.has-border:focus{
        border-color: #484848;
        box-shadow: 0 0 10px #484848;
        -webkit-box-shadow: 0 0 10px #484848;
        -moz-box-shadow: 0 0 10px #484848;
    }
    #container .btnLogin{
        border: none;
        background: #2344a6;
        text-align: center;
        line-height: 40px;
        font-size: 18px;
        font-weight: bold;
        color: #fff;
        cursor: pointer;
        border-radius: 5px;
        -webkit-border-radius: 5px;
        -moz-border-radius: 5px;
    }
    #container .register{
        vertical-align:middle;
        margin-bottom: 6px;
        color: #e21f29;
    }
</style>
</html>