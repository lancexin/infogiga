<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link type="text/css" rel="stylesheet" href="ext/3.2.1/resources/css/ext-all.css"/>
<script type="text/javascript" src="ext/3.2.1/adapter/ext/ext-base.js"></script>
<script type="text/javascript" src="ext/3.2.1/ext-all-debug.js"></script>
<script type="text/javascript">
	Ext.onReady(function(){
		 var form = new Ext.form.FormPanel({
			baseCls: 'x-plain',
			layout:'absolute',
			
			defaultType: 'textfield',
			items: [{
				x: 15,
				y: 5,
				xtype:'label',
				text: '用户名:'
			},{
				x: 75,
				y: 0,
				name: 'userName',
				value:'admin'
			},{
				x: 15,
				y: 35,
				xtype:'label',
				text: '密码:'
			},{
				x: 75,
				y: 30,
				name: 'passWord',
				inputType:"password",
				value:'admin'
			}]
		});
	
		var window = new Ext.Window({
			title: '用户登录',
			width: 250,
			height:140,
			layout: 'fit',
			plain:true,
			closable :false,
			collapsible : true ,
			bodyStyle:'padding:5px;',
			buttonAlign:'center',
			items: form,
			buttons: [{
				text: '确定',
				handler:function(){
					form.getForm().submit({
						url:'login?type=json',
						waitMsg:"请等待",
                        method:'post',
						success:function(form,action){
							document.location = "/sd/admin";
						},
						failure:function(form,action){
							if(action.result.msg){
								Ext.Msg.alert('错误',action.result.msg);
							}else{
								Ext.Msg.alert('错误',"无法连接服务器,请稍后再试");
							}
                             
                        }
					});
				}
			},{
				text: '重置',
				handler:function(){
					form.getForm().getEl().dom.reset();
				}
			}]
		});
		window.show();
	});
</script>
<title>用户登录</title>
</head>

<body>
	<div id="form-login"></div>
</body>
</html>
