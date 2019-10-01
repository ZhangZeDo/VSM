<%@ page import="com.zzd.model.TVideoType" %>
<%@ page import="java.util.List" %>
<%@ page import="com.zzd.model.TVideo" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>VSM</title>
    <link rel="stylesheet" type="text/css" href="../css/reset.css"/>
    <link rel="stylesheet" type="text/css" href="../css/common.css"/>
    <link rel="stylesheet" type="text/css" href="../css/thems.css">
    <link rel="stylesheet" href="//cdn.staticfile.org/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="//cdn.staticfile.org/jquery/2.1.1/jquery.min.js"></script>
    <script src="//cdn.staticfile.org/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="../js/jquery-1.8.3.min.js"></script>
</head>
<body>
<%
    List<TVideo> bigClickVideo = (List<TVideo>)session.getAttribute("bigClickVideo");
    List<TVideo> bigPraisesVideo = (List<TVideo>)session.getAttribute("bigPraisesVideo");
    List<TVideoType> videoTypes = (List<TVideoType>)session.getAttribute("videoTypes");
    List<TVideo> videos = (List<TVideo>)request.getAttribute("videos");

%>
<div class="header" style="background-color: #c71012;height: 15%">
    <div style="padding-top: 40px;padding-left: 50px">
        <p align="left" style="font-size: 25px">
            短视频分享VS
        </p>
        <div style="float: right;padding-right: 20px">
            <table>
                <tr>
                    <td style="padding-right: 15px"><a href="/getMyVideos" style="color: #DEDEDE">我的视频</a></td>
                    <td style="padding-right: 15px"><a href="userDetail.jsp" style="color: #DEDEDE">个人中心</a></td>
                </tr>
            </table>
        </div>
    </div>
</div>
<div class="topMenu" style="background-color: #DEDEDE;height: 5%">
    <div style="padding-top: 8px;padding-left: 16%;">
        <table cellpadding="0" cellspacing="0" style="font-size:15px;">
            <tr>
                <th scope="col"><a href="">首页</a>&nbsp;&nbsp;&nbsp;&nbsp;</th>
                <%
                    for (TVideoType videoType : videoTypes) {
                %>
                <th scope="col"><a href=""><%=videoType.getVideoTypeName()%></>&nbsp;&nbsp;&nbsp;&nbsp;</th>
                <%
                    }
                %>
                <th scope="col">
                    <input type="text" placeholder="请输入搜索标题" name="search" >
                </th>
            </tr>
        </table>
    </div>
</div>
<div class="mainContent" style="height: auto">
    <div style="margin-left: 15%;margin-top: 20px ; width: 70%; height: auto;">
        <table style="padding-top:25px">
            <tr>
                <td rowspan="2" style="padding-left: 15px">
                    <div id="myCarousel" class="carousel slide">
                        <ol class="carousel-indicators">
                            <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
                            <li data-target="#myCarousel" data-slide-to="1"></li>
                            <li data-target="#myCarousel" data-slide-to="2"></li>
                        </ol>
                        <!-- 轮播（Carousel）项目 -->
                        <div class="carousel-inner">
                            <div class="item active">
                                <a href="/videoDetail?id=<%=bigPraisesVideo.get(0).getId()%>"><img src="<%=bigPraisesVideo.get(0).getCoverUrl()%>" width="400px" height="265px"/></a>
                            </div>
                            <div class="item">
                                <a href="/videoDetail?id=<%=bigPraisesVideo.get(1).getId()%>"><img src="<%=bigPraisesVideo.get(1).getCoverUrl()%>" width="400px" height="265px"/></a>
                            </div>
                            <div class="item">
                                <a href="/videoDetail?id=<%=bigPraisesVideo.get(1).getId()%>"><img src="<%=bigPraisesVideo.get(1).getCoverUrl()%>" width="400px" height="265px"/></a>
                            </div>
                        </div>
                        <!-- 轮播（Carousel）导航 -->
                        <a class="left carousel-control" href="#myCarousel" role="button" data-slide="prev">
                            <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
                            <span class="sr-only">Previous</span>
                        </a>
                        <a class="right carousel-control" href="#myCarousel" role="button" data-slide="next">
                            <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
                            <span class="sr-only">Next</span>
                        </a>
                    </div>
                </td>
                <td style="padding-left: 15px"><a href="/videoDetail?id=<%=bigClickVideo.get(0).getId()%>"><img src="<%=bigClickVideo.get(0).getCoverUrl()%>" width="200px" height="125px" /></a></td>
                <td style="padding-left: 15px"><a href="/videoDetail?id=<%=bigClickVideo.get(0).getId()%>"><img src="<%=bigClickVideo.get(0).getCoverUrl()%>" width="200px" height="125px" /></a></td>
                <td style="padding-left: 15px"><a href="/videoDetail?id=<%=bigClickVideo.get(0).getId()%>"><img src="<%=bigClickVideo.get(0).getCoverUrl()%>" width="200px" height="125px" /></a></td>
            </tr>
            <tr>
                <td style="padding-left: 15px;padding-top: 15px"><a href="/videoDetail?id=<%=bigClickVideo.get(0).getId()%>"><img src="<%=bigClickVideo.get(0).getCoverUrl()%>" width="200px" height="125px" /></a></td>
                <td style="padding-left: 15px;padding-top: 15px"><a href="/videoDetail?id=<%=bigClickVideo.get(0).getId()%>"><img src="<%=bigClickVideo.get(0).getCoverUrl()%>" width="200px" height="125px" /></a></td>
                <td style="padding-left: 15px;padding-top: 15px"><a href="/videoDetail?id=<%=bigClickVideo.get(0).getId()%>"><img src="<%=bigClickVideo.get(0).getCoverUrl()%>" width="200px" height="125px" /></a></td>
            </tr>
        </table>
    </div>
    <br>
    <div style="padding-top: 4px;">
        <p style="font-size: 20px ;margin-left: 16%;float: left">全部</p>
    </div>
    <br>
    <div style="margin-left: 15%;margin-top: 20px ; width: 70%; height: auto;">
        <table style="padding-top:25px">
            <tr>
                <td style="padding-left: 12px"><img src="../cover/cover1.jpg" width="200px" height="125" /><br>标题</td>
                <td style="padding-left: 12px"><img src="../cover/cover1.jpg" width="200px" height="125" /><br>标题</td>
                <td style="padding-left: 12px"><img src="../cover/cover1.jpg" width="200px" height="125" /><br>标题</td>
                <td style="padding-left: 12px"><img src="../cover/cover1.jpg" width="200px" height="125" /><br>标题</td>
                <td style="padding-left: 12px"><img src="../cover/cover1.jpg" width="200px" height="125" /><br>标题</td>
            </tr>
            <tr>
                <td style="padding-left: 12px;padding-top: 10px"><img src="../cover/cover1.jpg" width="200px" height="125" /><br>标题</td>
                <td style="padding-left: 12px;padding-top: 10px"><img src="../cover/cover1.jpg" width="200px" height="125" /><br>标题</td>
                <td style="padding-left: 12px;padding-top: 10px"><img src="../cover/cover1.jpg" width="200px" height="125" /><br>标题</td>
                <td style="padding-left: 12px;padding-top: 10px"><img src="../cover/cover1.jpg" width="200px" height="125" /><br>标题</td>
                <td style="padding-left: 12px;padding-top: 10px"><img src="../cover/cover1.jpg" width="200px" height="125" /><br>标题</td>
            </tr>
        </table>
    </div>
</div>
</body>
</html>
