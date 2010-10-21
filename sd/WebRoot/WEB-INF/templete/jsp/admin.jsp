<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base>
    
    <title>M-Store后台管理系统</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link type="text/css" rel="stylesheet" href="ext/3.2.1/resources/css/ext-all.css"/>
	<script type="text/javascript" src="ext/3.2.1/adapter/ext/ext-base.js"></script>
	<script type="text/javascript" src="ext/3.2.1/ext-all-debug.js"></script>
	<script type="text/javascript" src="ext/lovcombo/js/Ext.ux.form.LovCombo.js"></script>
	<link type="text/css" rel="stylesheet" href="ext/css/main.css"/>
	<link type="text/css" rel="stylesheet" href="ext/lovcombo/css/Ext.ux.form.LovCombo.css"/>
	
	
	
	<script type="text/javascript">
	
	Ext.onReady(function(){
  
        Ext.state.Manager.setProvider(new Ext.state.CookieProvider());
        
        Ext.QuickTips.init(); //为组件提供提示信息功能，form的主要提示信息就是客户端验证的错误信息。  
        
       // Ext.form.Field.prototype.msgTarget='side';//  提示的方式，枚举值为  
	  //  qtip-当鼠标移动到控件上面时显示提示  
	  //  title-在浏览器的标题显示，但是测试结果是和qtip一样的  
	  //  under-在控件的底下显示错误提示  
	  //  side-在控件右边显示一个错误图标，鼠标指向图标时显示错误提示. 默认值.  
	  //  id-[element id]错误提示显示在指定id的HTML元件中  
        
        var centerTab = new Ext.TabPanel({
	    	animCollapse : true,
	    	animScroll : true,   
	    	activeTab: 0,
	    	items: [{
                contentEl: 'center1',
                title: 'Welcome',
                autoScroll: true
            }]
        });
        
        
        
         var viewport = new Ext.Viewport({
			layout: 'border',
			items:[{//头部
				region: 'north',
				contentEl: 'north',
				height: 56,
				margins: '0 0 0 0',
				border : false
		    },{//菜单
				region: 'west',
				id: 'west-panel', // see Ext.getCmp() below
				collapsible: true,
				title: '管理菜单',
				xtype: 'treepanel',
				width: 200,
			    minSize: 175,
			    maxSize: 400,
			    collapsible: true,
			    margins: '0 0 0 1',
				autoScroll: true,
				split: true,
				dataUrl:"menu?type=json",
				root: {
					nodeType: 'async',
					draggable: false,
					id: 'source'
				},
				rootVisible: false,
				listeners: {
				    click: function(n) {
						if(n.leaf){//如果是子菜单这则做如下操作
							//Ext.Msg.alert('Navigation Tree Click', '你点击了子菜单: ' + n.attributes.text +' url:'+n.attributes.url);
							Ext.Ajax.request({
							    url: n.attributes.url,
							    success: function(xhr) {
							        eval(xhr.responseText);
							        centerTab.add(newComponent);
							        centerTab.setActiveTab(newComponent);
							    },
							    failure: function() {
							        Ext.Msg.alert("Grid create failed", "Server communication failure");
							    }
							});							
						}
			    	}
				}
		    },centerTab,{//底部
				region: 'south',
				split: true,
				height: 25,
				contentEl: 'south',
				margins: '0 0 0 0'
		    }]
		 });

    });
	</script>
  </head>
  
 <body>
 
	<div id="north">
		<TABLE cellSpacing=0 cellPadding=0 width="100%"
			background="images/header_bg.jpg" border=0>
			<TR height=56>
				<TD width=260>
					<IMG height=56 src="images/header_left.jpg" width=260>
				</TD>
				<TD style="FONT-WEIGHT: bold; COLOR: #fff; PADDING-TOP: 20px"
					align=middle>
				</TD>
				<TD align=right width=268>
					<IMG height=56 src="images/header_right.jpg" width=268>
				</TD>
			</TR>
		</TABLE>
	</div>
   
    <div id="center1" class="x-hide-display">
        <p><b>欢迎进入M-Store管理平台</b></p>
    </div>
	
	<div id="south">
		<p>Password By 杭州讯杰科技 2010-8-4</p>
	</div>
	
	<iframe style=""></iframe>
</body>
</html>