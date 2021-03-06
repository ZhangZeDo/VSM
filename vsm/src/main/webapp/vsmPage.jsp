<%@ page import="com.zzd.model.TVideoType" %>
<%@ page import="java.util.List" %>
<%@ page import="com.zzd.model.TVideo" %>
<%@ page import="com.zzd.model.TUser" %>
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
    <script type="text/javascript" language="JavaScript">
        function searchenter(event) {
            event = event || window.event;
            var title = document.getElementById('title').value;
            if (event.keyCode == 13) {
                if (title == '') {
                    return false;
                }
                window.location.href = "queryVideoByTitle?title="+title ;
            }
        }
    </script>
</head>
<body>
<%
    TUser user = (TUser)session.getAttribute("User");
    List<TVideo> bigClickVideo = (List<TVideo>)session.getAttribute("bigClickVideo");
    List<TVideo> bigPraisesVideo = (List<TVideo>)session.getAttribute("bigPraisesVideo");
    List<TVideoType> videoTypes = (List<TVideoType>)session.getAttribute("videoTypes");
    List<TVideo> videos = (List<TVideo>)request.getAttribute("videos");
    String theme = (String) request.getAttribute("theme");
%>
<script>
    function openPerson(){
        document.getElementById("win3").style.display="";
    }
    function closePerson(){
        document.getElementById("win3").style.display="none";
    }
</script>
<div class="header" style="background-color: #c71012;height: 15%">
    <div style="padding-top: 40px;padding-left: 50px">
        <p align="left" style="font-size: 25px">
            短视频分享VS
        </p>
        <div style="float: right;padding-right: 20px">
            <table>
                <tr>
                    <td style="padding-right: 15px">欢迎你: <%=user.getUserName()%> </td>
                    <td style="padding-right: 15px"><a href="/getMyVideos" style="color: #DEDEDE">我的视频</a></td>
                    <td style="padding-right: 15px"><a href="/loginOut" style="color: #DEDEDE">退出登录</a></td>
                </tr>
            </table>
        </div>
    </div>
</div>
<div class="topMenu" style="background-color: #DEDEDE;height: 5%">
    <div style="padding-top: 8px;padding-left: 16%;">
        <table cellpadding="0" cellspacing="0" style="font-size:15px;">
            <tr>
                <th scope="col"><a href="listVideoByType?id=全部">首页</a>&nbsp;&nbsp;&nbsp;&nbsp;</th>
                <%
                    for (TVideoType videoType : videoTypes) {
                %>
                <th scope="col"><a href="listVideoByType?id=<%=videoType.getId()%>"><%=videoType.getVideoTypeName()%></>&nbsp;&nbsp;&nbsp;&nbsp;</th>
                <%
                    }
                %>
                <th scope="col">
                    <input type="text" placeholder="请输入搜索标题" name="title" id="title" onkeyup="searchenter(event);">
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
                                <a href="/videoDetail?id=<%=bigPraisesVideo.get(0).getId()%>"><img src="<%=bigPraisesVideo.get(0).getCoverUrl()%>" width="400px" height="300px"/></a>
                            </div>
                            <div class="item">
                                <a href="/videoDetail?id=<%=bigPraisesVideo.get(1).getId()%>"><img src="<%=bigPraisesVideo.get(1).getCoverUrl()%>" width="400px" height="300px"/></a>
                            </div>
                            <div class="item">
                                <a href="/videoDetail?id=<%=bigPraisesVideo.get(2).getId()%>"><img src="<%=bigPraisesVideo.get(2).getCoverUrl()%>" width="400px" height="300px"/></a>
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
                <td style="padding-left: 15px"><a href="/videoDetail?id=<%=bigClickVideo.get(1).getId()%>"><img src="<%=bigClickVideo.get(1).getCoverUrl()%>" width="200px" height="125px" /></a></td>
                <td style="padding-left: 15px"><a href="/videoDetail?id=<%=bigClickVideo.get(2).getId()%>"><img src="<%=bigClickVideo.get(2).getCoverUrl()%>" width="200px" height="125px" /></a></td>
            </tr>
            <tr>
                <td style="padding-left: 15px;padding-top: 15px"><a href="/videoDetail?id=<%=bigClickVideo.get(3).getId()%>"><img src="<%=bigClickVideo.get(3).getCoverUrl()%>" width="200px" height="125px" /></a></td>
                <td style="padding-left: 15px;padding-top: 15px"><a href="/videoDetail?id=<%=bigClickVideo.get(4).getId()%>"><img src="<%=bigClickVideo.get(4).getCoverUrl()%>" width="200px" height="125px" /></a></td>
                <td style="padding-left: 15px;padding-top: 15px"><a href="/videoDetail?id=<%=bigClickVideo.get(5).getId()%>"><img src="<%=bigClickVideo.get(5).getCoverUrl()%>" width="200px" height="125px" /></a></td>
            </tr>
        </table>
    </div>
    <br>
    <div style="padding-top: 4px;">
        <p style="font-size: 20px ;margin-left: 16%;float: left"><%=theme%></p>
    </div>
    <br>
    <div style="margin-left: 15%;margin-top: 20px ; width: 70%; height: auto;">
        <table style="padding-top:25px">
            <tr>
                <%
                    for (int i = 0; i < videos.size(); i++) {
                        if (i%5!=0){
                %>
                    <td style="padding-left: 15px;padding-top: 15px"><a href="/videoDetail?id=<%=videos.get(i).getId()%>"><img src="<%=videos.get(i).getCoverUrl()%>" width="200px" height="125px" /></a></td>
                <%
                    }else{
                %>
                    </tr><tr>
                    <td style="padding-left: 15px;padding-top: 15px"><a href="/videoDetail?id=<%=videos.get(i).getId()%>"><img src="<%=videos.get(i).getCoverUrl()%>" width="200px" height="125px" /></a></td>
            <%
                    }
                    }
                %>
            </tr>
        </table>
    </div>
</div>
</body>
</html>
