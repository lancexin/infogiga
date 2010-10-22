<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String value = (String)request.getAttribute("value");
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>员工登录</title>
    
	<LINK href="page/css/admin.css" type="text/css" rel="stylesheet">

  </head>
  
  <body>
    <FORM name=form1 action="empLogin" method=post style="border:0;padding:0px;margin:0px;">
		<TABLE height="100%" cellSpacing=0 cellPadding=0 width="100%"
			bgColor=#002779 border=0>
			<TR>
				<TD align=middle>
					<TABLE cellSpacing=0 cellPadding=0 width=468 border=0>
						<TR>
							<TD>
								<IMG height=23 src="images/login_1.jpg" width=468>
							</TD>
						</TR>
						<TR>
							<TD>
								<IMG height=147 src="images/login_2.jpg" width=468>
							</TD>
						</TR>
					</TABLE>
					
					<TABLE cellSpacing=0 cellPadding=0 width=468 bgColor=#ffffff
						border=0>
						<TR>
							<TD width=16>
								<IMG height=122 src="images/login_3.jpg" width=16>
							</TD>
							<TD align=middle>
								<TABLE cellSpacing=0 cellPadding=0 width=230 border=0>
									
									<TR height=5>
										<TD width=5></TD>
										<TD width=56></TD>
										<TD></TD>
									</TR>
									<TR height=36>
										<TD></TD>
										<TD>
											工号
										</TD>
										<TD>
											<INPUT
												style="BORDER-RIGHT: #000000 1px solid; BORDER-TOP: #000000 1px solid; BORDER-LEFT: #000000 1px solid; BORDER-BOTTOM: #000000 1px solid"
												maxLength=30 value="" size=24 name="username">
										</TD>
									</TR>
									<TR height=36>
										<TD>
											&nbsp;
										</TD>
										<TD>
											密码
										</TD>
										<TD>
											<INPUT
												style="BORDER-RIGHT: #000000 1px solid; BORDER-TOP: #000000 1px solid; BORDER-LEFT: #000000 1px solid; BORDER-BOTTOM: #000000 1px solid"
												type=password value="" maxLength=30 size=24 
												name="password">
										</TD>
									</TR>
									<TR height=5>
										<TD colSpan=3></TD>
									</TR>
									<TR>
										<TD>
											&nbsp;
										</TD>
										<TD>
											&nbsp;
										</TD>
										<TD>
											<INPUT type=image height=18 width=70
												src="images/bt_login.gif">
										</TD>
									</TR>
									
								</TABLE>
							</TD>
							<TD width=16>
								<IMG onclick="document.form1.submit()" height=122 src="images/login_4.jpg" width=16>
							</TD>
						</TR>
					</TABLE>
					
					<TABLE cellSpacing=0 cellPadding=0 width=468 border=0>
						<TR>
							<TD>
								<IMG height=16 src="images/login_5.jpg" width=468>
							</TD>
						</TR>
					</TABLE>
					<TABLE cellSpacing=0 cellPadding=0 width=468 border=0>
						<TR>
							<TD align=right>
								<A href="javascript:;" target=_blank><IMG height=26
										src="images/login_6.gif" width=165 border=0>
								</A>
							</TD>
						</TR>
					</TABLE>
				</TD>
			</TR>
		</TABLE>
		</FORM>
		 <%
	  if(value != null){
	  %>
	   <script type="text/javascript">
	  	alert("<%=value%>");
	   </script>
	  <%}%>
  </body>
</html>
