<%@ page import="java.util.List" %>
<%@ page import="com.zzd.dto.VideoDTO" %>
<%@ page import="com.zzd.model.TVideoType" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%
    List<VideoDTO> myVideos = (List<VideoDTO>)request.getAttribute("myVideos");
    List<TVideoType> videoTypes = (List<TVideoType>)session.getAttribute("videoTypes");
%>
<div>
    <div style="background-color: #edf5d0;width: 50%;height: 100%;margin-left: 10% ;float: left" >
        <div align="center">
            <p style="font-size: 20px">我的视频</p>
        </div>
        <div style="">
            <table style="padding-left: 5%;width: 95%" >
                <%
                    for (VideoDTO myVideo : myVideos) {
                %>
                    <tr style="background-color: #DEDEDE ">
                        <td style="width: 200px"><a href="/videoDetail?id=<%=myVideo.getId()%>"><img src="<%=myVideo.getCoverUrl()%>" width="200px" height="125px" /></a></td>
                        <td>
                            <p style="font-size: 18px;font-weight: bold"><%=myVideo.getVideoTitle()%></p>
                            <p style="font-size: 13px"><%=myVideo.getTypeName()%> &nbsp;&nbsp;<%=myVideo.getCreatedDatetime().toLocaleString()%></p>
                            <p style="font-size: 13px"><%=myVideo.getVideoClicks()%>的播放量 &nbsp;&nbsp; 共收获打赏<%=myVideo.getRewardTotal()%></p>
                        </td>
                    </tr>
                <%
                    }
                %>
            </table>
        </div>
    </div>
    <div style="background-color: #edf5d0;width: 25%;height: 100%;margin-left: 62%">
        <div align="center">
            <p style="font-size: 20px;padding-top: 30px">新建上传</p>
        </div>
        <div>
            <form action="/uploadVideo" method="post" enctype="multipart/form-data">
                &nbsp;&nbsp;请选择视频类型: <select id="videoType" name="videoType" style="width: 200px">
                    <%
                        for (TVideoType videoType : videoTypes) {
                    %>
                        <option value=<%=videoType.getId()%>><%=videoType.getVideoTypeName()%></option>
                    <%
                        }
                    %>
                </select>
                <br><br>
                &nbsp;&nbsp;请输入视频标题: <input type="text" name="videoTitle" style="width: 200px ;" required>
                <br><br>
                &nbsp;&nbsp;请输入视频描述: <textarea style="float: bottom;width: 200px;height: 50px" name="videoDescription" required></textarea>
                <br><br>
                &nbsp;&nbsp;请上传视频封面:  &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp;<input type="file" name="videoCover" required/>
                <br><br>
                &nbsp;&nbsp;请上传视频地址:  &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp;<input type="file" name="videoUrl" required/>
                <br><br><br><br>
                &nbsp;&nbsp;<input type="submit" value="上传"> &nbsp;&nbsp;&nbsp;&nbsp;<input type="reset" value="重置">
            </form>
        </div>
    </div>
</div>
</body>
</html>
