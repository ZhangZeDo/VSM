<%@ page import="com.zzd.dto.VideoDTO" %>
<%@ page import="com.zzd.model.TVideo" %>
<%@ page import="java.util.List" %>
<%@ page import="com.zzd.model.TComment" %>
<%@ page import="com.zzd.dto.CommentDTO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<script>

    function openReward(){
        document.getElementById("win1").style.display="";
    }
    function closeReward(){
        document.getElementById("win1").style.display="none";
    }
    function openReport(){
        document.getElementById("win2").style.display="";
    }
    function closeReport(){
        document.getElementById("win2").style.display="none";
    }
</script>
<body>
<%
    VideoDTO video = (VideoDTO) request.getAttribute("video");
    List<TVideo> bigPraisesVideo = (List<TVideo>)session.getAttribute("bigPraisesVideo");
%>
<div id="win1" class="win" style="display:none">
    <form action="/addReward" method="post">
        <span style="font-size: 20px;"> 感谢您的打赏 </span> <br />
        <label class="label"> 请输入打赏金额: </label> <input type="text" name="rewardNum" /> <br />
        <label class="label"> 请输入想说的话: </label> <textarea name="remark"></textarea> <br />
        <input type="hidden" value="<%=video.getId()%>" name="videoId">
        <input type="hidden" value="<%=video.getUserId()%>" name="rewardId">
        <input type="reset" value="重输" class="input1" />
        <input type="button" value="退出" class="input3" onclick="closeReward();" />
        <input type="submit" value="提交" class="input2" />
    </form>
</div>
<div id="win2" class="win" style="display:none">
    <form action="/addReport" method="post">
        <span style="font-size: 20px;"> 欢迎您的监督 </span> <br />
        <label class="label"> 请输入想说的话: </label> <textarea name="remark"></textarea> <br />
        <input type="hidden" value="1" name="reportType">
        <input type="hidden" value="<%=video.getId()%>" name="reportId">
        <input type="reset" value="重输" class="input1" />
        <input type="button" value="退出" class="input3" onclick="closeReport();" />
        <input type="submit" value="提交" class="input2" />
    </form>
</div>
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
            <a href="/addParises?id=<%=video.getId()%>">点赞</a>
            <a href="javascript:openReward();" style="padding-left: 10px">打赏</a>
            <a href="javascript:openReport();" style="float: right">举报</a>
        </div>
        <hr width="100%" style="margin-top: 5px" />
        <div style="margin-top: 10px">
            <%=video.getVideoDescription()%>
        </div>
        <div>
            <p style="font-size: 20px">评论</p>
            <div style="height: 15px">
                <form action="/addComment" method="post">
                    <input type="hidden" value="<%=video.getId()%>" name="id">
                    <input type="text" name="comment" placeholder="发表评论" style="width: 500px;height: 25px">
                    &nbsp;&nbsp;
                    <button type="button" onclick="submit">发表</button>
                </form>
            </div>
            <div style="margin-top:  20px">
                <table>
                    <%
                        for (CommentDTO comment : video.getCommentList()) {
                    %>
                        <tr style="padding-top: 5px;">
                            <td style="float: left"><%=comment.getUserName()%>: </td>
                            <td style="float: left"><%=comment.getComment()%></td>
                        </tr>
                    <%
                        }
                    %>
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
                <%
                    for (TVideo tVideo : bigPraisesVideo) {
                %>
                    <tr style="padding-top: 5px">
                        <br>
                        <img src="<%=tVideo.getCoverUrl()%>" width="300px" height="150" />
                        <br><%=tVideo.getVideoTitle()%>
                    </td>
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
    .win {
        POSITION:absolute; left:55%; top:60%;
        width:400px; height:250px;
        margin-left:-300px;margin-top:-200px; border:1px solid #888;
        background-color: #d6cfcb; text-align: center;
        line-height: 60px; z-Index:3;
    }
</style>
