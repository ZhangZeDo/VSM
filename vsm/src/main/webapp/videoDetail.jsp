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
                    <td><p style="font-size: 18px;font-weight: bold"><%=video.getVideoTitle()%></p></td>
                </tr>
                <tr style="padding-top: 2px">
                    <td><p style="font-size: 13px"><%=video.getTypeName()%> &nbsp;&nbsp;<%=video.getCreatedDatetime().toLocaleString()%></p></td>
                </tr>
                <tr style="padding-top: 2px">
                    <td><p style="font-size: 13px"><%=video.getVideoClicks()%>的播放量</p></td>
                </tr>
            </table>
        </div>
        <div style="height: 500px;margin-top: -20px">
            <video width="100%" height="500px" controls autoplay>
                <source src=<%=video.getVideoUrl()%> type="video/mp4">
            </video>
        </div>
        <div style="margin-top: 15px">
            <a href="">点赞</a>
            <a href="" style="padding-left: 10px">打赏</a>
            <a href="" style="float: right">举报</a>
        </div>
        <hr width="100%" style="margin-top: 5px" />
        <div style="margin-top: 10px">
            <%=video.getVideoDescription()%>
        </div>
        <div>
            <p style="font-size: 20px">评论</p>
            <div style="height: 15px">
                <input type="text" placeholder="发表评论" style="width: 500px;height: 25px">
                &nbsp;&nbsp;
                <button type="button">发表</button>
            </div>
            <div style="margin-top:  20px">
                <table>
                    <tr style="padding-top: 5px;">
                        <td>人名:</td>
                        <td>评论的内容</td>
                    </tr>
                    <tr style="padding-top: 5px;">
                        <td>人名:</td>
                        <td>评论的内容</td>
                    </tr>
                    <tr style="padding-top: 5px;">
                        <td>人名:</td>
                        <td>评论的内容</td>
                    </tr>
                </table>
            </div>

        </div>
    </div>
    <div style="margin-top:20px;margin-left:2%;width: 30%;float: right">
        <div style="padding-top: 20px">
            <p style="font-size: 18px">相关推荐</p>
        </div>
        <div style="padding-top: -40px">
            <table>
                <tr style="padding-top: 5px">
                    <br>
                        <img src="https://ss2.baidu.com/-vo3dSag_xI4khGko9WTAnF6hhy/image/h%3D300/sign=0c78105b888ba61ec0eece2f713597cc/0e2442a7d933c8956c0e8eeadb1373f08202002a.jpg" width="300px" height="150" />
                        <br>标题
                    </td>
                </tr>
                <tr style="padding-top: 5px">
                    <td>
                        <img src="https://ss2.baidu.com/-vo3dSag_xI4khGko9WTAnF6hhy/image/h%3D300/sign=0c78105b888ba61ec0eece2f713597cc/0e2442a7d933c8956c0e8eeadb1373f08202002a.jpg" width="300px" height="150" />
                        <br>标题
                    </td>
                </tr>
                <tr style="padding-top: 5px">
                    <td>
                        <img src="https://ss2.baidu.com/-vo3dSag_xI4khGko9WTAnF6hhy/image/h%3D300/sign=0c78105b888ba61ec0eece2f713597cc/0e2442a7d933c8956c0e8eeadb1373f08202002a.jpg" width="300px" height="150" />
                        <br>标题
                    </td>
                </tr>
            </table>
        </div>
    </div>
</div>
</body>
</html>
