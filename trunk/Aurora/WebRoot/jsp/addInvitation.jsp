<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<body>
	<form method="get" action="" id="sendInvitation">
			<input type='hidden' id='visitTime' name='visitTime' /> 
               <p>
               预约名称：
             		<input type='text' name='invTitle'/>
             	</p>              	
               
		  您希望邀请： 
			<input type="button"  class="add-order-a" value="从文件导入" onclick="Index.showUploadFileDialog();"/>
			<div class='stub'></div>
			
			<div class="add-order-a"><a href="javascript:Next.addCustomerLine();">添加</a></div>
			<div class="add-order-a">地点：<input type='text' name='location'/></div>
			<div class="add-order-a">
				<span>接待人员：</span>
				<select id='guiderSelect'>
					<option>请选择</option>
				</select>
				<div class='guiderBlock' style='display:none'>
					<input type='text' id='guider' value='' readonly/>
					<span onclick="Next.removeGuider(this);" style="color:red;">删除</span>
				</div>
			</div>
			<div  class="add-order-a">
				<span>技术人员：</span>
				<select id='technicianSelect'>
					<option>请选择</option>
				</select>
                   <div class='technicianBlock' style='display:none'>
					<input type='text' id='technician' readonly/>
					<span onclick="Next.removeTechnician(this);" style="color:red;">删除</span>
				</div>
			</div>
			<div>
			<span>选择模板：</span>
			<select id='mmsTemplate' name='template'>
			</select>
			<p/>
			<textarea rows="5" cols="70" id='mmsContent' name='content'></textarea>
			<div>
				<span>插入标签：</span>
				<input type="button" value="姓名"/>
				<input type="button" value="时间"/>
				<input type="button" value="地点"/>
			</div>
			</div>
	</form>
</body>
