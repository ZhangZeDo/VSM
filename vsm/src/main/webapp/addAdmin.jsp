<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>VSM</title>
    <link rel="stylesheet" type="text/css" href="css/reset.css"/>
    <link rel="stylesheet" type="text/css" href="css/common.css"/>
    <link rel="stylesheet" type="text/css" href="css/thems.css">
    <script type="text/javascript" src="js/jquery-1.8.3.min.js"></script>
    <script type="text/javascript">
        $(function(){
            $('.sidenav li').click(function(){
                $(this).siblings('li').removeClass('now');
                $(this).addClass('now');
            });

            $('.erji li').click(function(){
                $(this).siblings('li').removeClass('now_li');
                $(this).addClass('now_li');
            });
            var main_h = $(window).height();
            $('.sidenav').css('height',main_h+'px');
        });

        $(function(){
            //自适应屏幕宽度
            window.onresize=function(){ location=location };

            var main_h = $(window).height();
            $('.hy_list').css('height',main_h-45+'px');

            var main_w = $(window).width();
            $('.xjhy').css('width',main_w-40+'px');

        });
    </script>
</head>
<body>
<div class="main">
    <div class="header">
        <div class="head">
            <div class="logo"><a href="#"><h1 style="font-size: 30px; margin-top: 20px">VSM后台管理</h1></a></div>
            <div class="curr"><span style="font-size: 15px">欢迎您,${Admin.adminName}[ <a href="/loginOut" target="_top">退出</a> ]</span></div>
        </div>
    </div>
    <div class="leftMenu">
        <div>
            <ul class="sidenav">
                <li class="now">
                    <div class="nav_m">
                        <span><a>个人中心</a></span>
                        <i>&nbsp;</i>
                    </div>
                    <ul class="erji">
                        <li>
                            <span><a href="homePage.jsp" target="main">个人信息</a></span>
                        </li>
                        <li>
                            <span><a href="changPassword.jsp" target="main">修改密码</a></span>
                        </li>
                    </ul>
                </li>
                <li>
                    <div class="nav_m">
                        <span><a>账号管理</a></span>
                        <i>&nbsp;</i>
                    </div>
                    <ul class="erji">
                        <li>
                            <span><a href="/listAdmin" target="main">人员管理</a></span>
                        </li>
                        <li>
                            <span><a href="/listUser" target="main">用户管理</a></span>
                        </li>
                        <li>
                            <span><a href="/listApplyRecord" target="main">用户申请</a></span>
                        </li>
                    </ul>
                </li>
                <li>
                    <div class="nav_m">
                        <span><a>视频管理</a></span>
                        <i>&nbsp;</i>
                    </div>
                    <ul class="erji">
                        <li>
                            <span><a href="/listVideo" target="main">在线视频</a></span>
                        </li>
                        <li>
                            <span><a href="/listVideoUpload" target="main">上传审批</a></span>
                        </li>
                        <li>
                            <span><a href="/listVideoType" target="main">视频类型</a></span>
                        </li>
                    </ul>
                </li>
                <li>
                    <div class="nav_m">
                        <span><a>记录管理</a></span>
                        <i>&nbsp;</i>
                    </div>
                    <ul class="erji">
                        <li>
                            <span><a href="/listReportRecord" target="main">举报记录</a></span>
                        </li>
                        <li>
                            <span><a href="/listViolation" target="main">违规记录</a></span>
                        </li>
                        <li>
                            <span><a href="/listComment" target="main">评论记录</a></span>
                        </li>
                        <li>
                            <span><a href="/listReward" target="main">打赏记录</a></span>
                        </li>
                    </ul>
                </li>
            </ul>
        </div>
    </div>
    <div class="mainContent">
        <div class="box_t">
            <span class="name">添加管理员账号</span>
        </div>
        <div class="space_hx">&nbsp;</div>
        <form action="/addAdmin" method="post" name="addAdminForm">
            <div class="xjhy">
                <!--高级配置-->
                <ul class="hypz gjpz clearfix">
                    <li class="clearfix">
                        <span class="title">管理员名称：</span>
                        <div class="li_r">
                            <input class="chang" name="name" type="text"/>
                            <i>*</i>
                        </div>
                    </li>
                    <li class="clearfix">
                        <span class="title">请设置你的密码：</span>
                        <div class="li_r">
                            <input class="chang" name="onePassword" type="text"/>
                            <i>*</i>
                        </div>
                    </li>
                    <li class="clearfix">
                        <span class="title">请确认你的密码：</span>
                        <div class="li_r">
                            <input class="chang" name="twoPassword" type="text"/>
                            <i>*</i>
                        </div>
                    </li>
                    <li class="clearfix">
                        <span class="title">联系方式：</span>
                        <div class="li_r">
                            <input class="chang" name="adminPhone" type="text"/>
                            <i>*</i>
                        </div>
                    </li>
                    <li class="clearfix">
                        <span class="title">个人邮箱：</span>
                        <div class="li_r">
                            <input class="chang" name="adminMail" type="text"/>
                            <i>*</i>
                        </div>
                    </li>

                    <li class="clearfix">
                        <p style="font-size: 10px ; color: #c71012">${addAdminMsg}</p>
                    </li>

                    <li class="tj_btn">
                        <a href="javascript:history.go(-1);" class="back">返回</a>
                        <a href="javascript:addAdminForm.submit();">保存</a>
                    </li>
                </ul>
                <!--高级配置-->
            </div>
        </form>
    </div>
</div>
</div>
</body>
</html>
<style>
    .main{
        position: absolute;
        top:0;
        left:0;
        right:0;
        bottom:0;
    }
    .header{
        position:absolute;
        left:0;
        top:0;
        right:0;
        height: 80px;
    }
    .leftMenu{
        position: fixed;
        left:0;
        top:80px;
        bottom:0;
        width:200px;
        height:100%;
        overflow: hidden;
    }
    .mainContent{
        position: absolute;
        left:200px;
        top:80px;
        bottom:0;
        right:0;
    }
</style>
