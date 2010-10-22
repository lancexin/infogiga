<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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

		<title>meau</title>

		<LINK href="page/css/admin.css" type="text/css" rel="stylesheet">
		<SCRIPT language=javascript>
	function expand(el)
	{
		childObj = document.getElementById("child" + el);

		if (childObj.style.display == 'none')
		{
			childObj.style.display = 'block';
		}
		else
		{
			childObj.style.display = 'none';
		}
		return;
	}
	</SCRIPT>

	</head>

	<body>
		<TABLE height="100%" cellSpacing=0 cellPadding=0 width=170
			background=images/menu_bg.jpg border=0>
			<TR>
				<TD vAlign=top align=middle>
					<TABLE cellSpacing=0 cellPadding=0 width="100%" border=0>

						<TR>
							<TD height=10></TD>
						</TR>
					</TABLE>
					
					<c:forEach items="${list}" var="itema" varStatus="count">
						
						<TABLE cellSpacing=0 cellPadding=0 width=150 border=0>
						<TR height=22>
							<TD style="PADDING-LEFT: 30px" background=images/menu_bt.jpg>
								<A class=menuParent onclick=expand(${count.index}) href="javascript:void(0);">${itema.name }</A>
							</TD>
						</TR>
						<TR height=4>
							<TD></TD>
						</TR>
					</TABLE>
					<TABLE id=child${count.index} style="DISPLAY: none" cellSpacing=0 cellPadding=0
						width=150 border=0>
						<c:forEach items="${itema.itemList}" var="itemb">
							<TR height=20>
								<TD align=middle width=30>
									<IMG height=9 src="images/menu_icon.gif" width=9>
								</TD>
								<TD>
									<A class=menuChild
										href="${itemb.url }"
										target=main>${itemb.name }</A>
								</TD>
							</TR>
						</c:forEach>
						
						
						<TR height=4>
							<TD colSpan=2></TD>
						</TR>
					</TABLE>
					</c:forEach>
					
					
					
				</TD>
				<TD width=1 bgColor=#d1e6f7></TD>
			</TR>
		</TABLE>
	</body>
</html>
