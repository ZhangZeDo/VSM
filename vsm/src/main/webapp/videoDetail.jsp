<%@ page import="com.zzd.model.TVideo" %>
<%@ page import="com.zzd.dto.VideoDTO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%
    VideoDTO video = (VideoDTO) request.getAttribute("video");
%>
<div style="margin-left: 15%;margin-top:20px;width: 70%">
    <div style="margin-top:20px;width: 68%;float: left">
        <div id="title" style="height: 100px;padding-left: 20px">
            <table>
                <tr>
                    <td><p style="font-size: 18px"><%=video.getVideoTitle()%></p></td>
                </tr>
                <tr style="padding-top: 2px">
                    <td><p style="font-size: 13px"><%=video.getVideoType()%> &nbsp;&nbsp;<%=video.getCreatedDatetime().toLocaleString()%></p></td>
                </tr>
                <tr style="padding-top: 2px">
                    <td><p style="font-size: 13px"><%=video.getVideoClicks()%></p></td>
                </tr>
            </table>
        </div>
        <div style="height: 500px;margin-top: -20px">
            <video width="100%" height="500px" controls autoplay>
                <source src="https://www.runoob.com/try/demo_source/movie.mp4" type="video/mp4">
            </video>
        </div>
    </div>
    <div style="background-color: #54c72e;margin-top:20px;margin-left:2%;width: 30%;float: right">
122
    </div>
</div>
</body>
</html>
