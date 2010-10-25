<%@ page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<?xml version="1.0" encoding="utf-8"?>
<!DOCTYPE wml PUBLIC "-//WAPFORUM//DTD WML 1.1//EN" "http://www.wapforum.org/DTD/wml_1.1.xml">
<wml><card title="预约 | 杭州移动信息化体验馆欢迎您">
	<p>
		<a href="q?p=1&v=1">首页</a>
		<a href="q?p=2&v=1">展馆</a>
		<a href="q?p=3&v=1">预约</a>
	</p>
	<p><%if(request.getAttribute("comment") !=null && (Boolean)request.getAttribute("comment")) { %>
		感谢您的热心参与，我们会仔细考虑您的意见。<br/>
		<a href="q?p=1&v=2">&laquo;返回首页</a>
	<%}%></p>
	<p>
	留下您宝贵的意见:<br/>
	或者通过<a href="wtai://wp/mc;10086">打电话</a>告诉我们 <br/>
	姓名:<br/>
	<input name="name" emptyok="false" format="*x" />  
	<br /> 
	手机号:<br/>
	<input emptyok="false" name="phonenumber" />
	<br />
	意见或建议:<br/>
	<input name="content" />
	<br />
	<anchor>提交
	    <go href="app" method="post">  
	        <postfield name="name" value="$(name)" />  
	        <postfield name="phonenumber" value="$(phonenumber)" /> 
	        <postfield name="content" value="$(content)" /> 
	        <postfield name="p" value="9" />
	        <postfield name="v" value="1" />
	    </go>  
	</anchor>
	</p>
	<p>&lt;<a href="q?p=3&v=2">彩版</a>|<span class="bk">简版</span>|<a href="q?p=3&v=3">G3版</a>&gt;<br/>
	<p><a href="q?p=7&v=1">公司介绍</a>|<a href="q?p=8&v=1">服务条款</a>|<a href="q?p=9&v=1">意见反馈</a></p>
	&copy; 2010 中国移动杭州分公司
</card></wml>	