<%@ page import="com.zzd.model.TVideo" %>
<%@ page import="java.util.List" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>VSM</title>
    <link rel="stylesheet" type="text/css" href="../css/reset.css"/>
    <link rel="stylesheet" type="text/css" href="../css/common.css"/>
    <link rel="stylesheet" type="text/css" href="../css/thems.css">
    <script type="text/javascript" src="../js/jquery-1.8.3.min.js"></script>
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
                <li>
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
                <li class="now">
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
            <span class="name">在线视频列表</span>
        </div>
        <div class="space_hx">&nbsp;</div>
        <!--列表-->
        <table cellpadding="0" cellspacing="0" class="list_hy">
            <tr>
                <th scope="col">上传人id</th>
                <th scope="col">视频标题</th>
                <th scope="col">视频描述</th>
                <th scope="col">视频点击量</th>
                <th scope="col">视频状态</th>
                <th scope="col">操作</th>
            </tr>
            <%
                List<TVideo> videos = (List<TVideo>) request.getAttribute("videos");
                for (TVideo video : videos) {
            %>
            <tr>
                <td><%=video.getUserId()%></td>
                <td><%=video.getVideoTitle()%></td>
                <td><%=video.getVideoDescription()%></td>
                <td><%=video.getVideoClicks()%></td>
                <%
                    if (video.getStatus()==1){
                %>
                <td>上架中...</td>
                <td>
                    <a href="/detailVideo?id=<%=video.getId()%>" class="btn">详情</a>
                    <a href="/lowerVideo?id=<%=video.getId()%>" class="btn">下架</a>
                </td>
                <%
                }
                else{
                %>
                <td>已下架...</td>
                <td>
                    <a href="/detailVideo?id=<%=video.getId()%>" class="btn">详情</a>
                    <a href="/upperVideo?id=<%=video.getId()%>" class="btn">上架</a>
                </td>
                <%
                    }
                %>
            </tr>
            <%
                }
            %>
        </table>
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
