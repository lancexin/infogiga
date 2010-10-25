<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="<%=basePath%>">

		<title>设备列表</title>
		<meta http-equiv="imagetoolbar" content="no"></meta>
		<link rel="stylesheet" href="plugin/thickbox.css" type="text/css"
			media="screen" />
		<script type="text/javascript" src="plugin/jquery-1.3.min.js"></script>
		<script type="text/javascript" src="plugin/thickbox.js"></script>
		<script type="text/javascript" src="<%=path%>/page/js/TableStyle.js"></script>
		<LINK href="page/css/admin.css" type="text/css" rel="stylesheet">
		<LINK href="page/css/style.css" type="text/css" rel="stylesheet" />
		<link href="<%=basePath%>plugin/dp/dp.css" type="text/css" rel="stylesheet">
		<script type="text/javascript" src="<%=basePath%>plugin/jquery-1.3.min.js"></script>
		<script type="text/javascript" src="<%=basePath%>plugin/validate/jquery.validate.js"></script>
		<script type="text/javascript" src="<%=basePath%>plugin/dp/jquery.datepicker.js"></script>
		<script type="text/javascript" src="<%=basePath%>plugin/Common.js"></script>
	</head>
	<body>
		<div style="text-align: center">
			<div>
				<TABLE cellSpacing=0 cellPadding=0 width="100%" align=center
					border=0>
					<TR height=28>
						<TD background=images/title_bg1.jpg>
							设备列表
						</TD>
					</TR>
					<TR>
						<TD bgColor=#b1ceef height=1></TD>
					</TR>
					<TR height=20>
						<TD background=images/shadow_bg.jpg></TD>
					</TR>
				</TABLE>
			</div>
			<!-- 
			<div>
				<a href="<%=path%>/equipment/xlsExport">导出excel文件</a>
			</div>
			 -->
			<div id="context">
				<TABLE cellSpacing=0 cellPadding=0 border=0 width="100%"
					align=center>
						<TR height=100><TD>
							<table width="100%" id="tb" name="tb">
								<tr>
									<th>序号</th>
									<th>设备名称</th>
									<th>MAC地址</th>
									<th>ip地址</th>
									<th>硬盘编码</th>
									<th>设备码</th>
									<th>营业厅</th>
									<th>所属系统</th>
									<th>添加时间</th>
									<th>状态</th>
									<th>操作</th>
								</tr>
								<c:forEach items="${list}" var="equip" varStatus="obj">
									<tr
										<c:if test="${obj.index%2 == 0}">
											bgcolor="#E3EFFB"
										</c:if>
									>
										<td>
											${obj.index+1}
										</td>
										<td>
											${equip.equiName==null?"-":equip.equiName}
										</td>
										<td>
											${equip.mac==null?"-":equip.mac}
										</td>
										<td>
											${equip.ip==null?"-":equip.ip}
										</td>
										<td>
											${equip.harddisk==null?"-":equip.harddisk}
										</td>
										<td>
											${equip.code==null?"-":equip.code}
										</td>
										
										<td>
											${equip.team==null?"-":(equip.team.teamName==null?"-":equip.team.teamName)}
										</td>
										<td>
											${equip.sysinfo.systemName}
										</td>
										<td>
											${equip.addTime==null?"-":equip.addTime}
										</td>
										<td>
											${equip.status==1?"启用":"停用"}
										</td>
										<td>
											<c:if test="${flag eq '1'}">
												<a href="update?equipment&id=${equip.equipmentId}"
												title="修改相关信息"
												>[修改]</a>
											</c:if>
										</td>
									</tr>
								</c:forEach>
							</table>
							<div style="text-align: center">
							总共有
							<font color=red>${page.totalPage}</font>页当前第[${page.nowPage+1}]页
							<a href="<%=basePath%>list?equipment&flag=${flag}&totleSize=${page.totalSize}&nowPage=0&prames=${prames}">第一页</a>|
							<a
								href="<%=basePath%>list?equipment&flag=${flag}&totleSize=${page.totalSize}&nowPage=${page.nowPage-1}&prames=${prames}">上一页</a>|
							<a
								href="<%=basePath%>list?equipment&flag=${flag}&totleSize=${page.totalSize}&nowPage=${page.nowPage+1}&prames=${prames}">下一页</a>|
							<a
								href="<%=basePath%>list?equipment&flag=${flag}&totleSize=${page.totalSize}&nowPage=${page.totalPage-1}&prames=${prames}">最后一页</a>|
							<input type="text" size=3 id="t">
							<input type="button" id="b" value="GO" onclick="load(t.value)">
							</div>
							
							<script type="text/javaScript">
								function load(val){
									//alert(val);
									if((/^\d+$/g.test(val))){
										window.location="<%=basePath%>list?equipment&flag=${flag}&totleSize=${page.totalSize}&nowPage="+(val-1)+"&prames=${prames}";	
									}else{
										alert("请键入一个有效的数字！");
									}
								}
							</script>
						</TD>
					</TR>
				</TABLE>
			</div>
			<div id="query">
				<a href="query?equipment&height=200&width=300" class="thickbox" title="">高级查询</a>
			</div>
		</div>
	</body>
</html>