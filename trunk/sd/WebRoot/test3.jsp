<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'test.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link type="text/css" rel="stylesheet" href="ext/3.2.1/resources/css/ext-all.css"/>
	<link type="text/css" rel="stylesheet" href="ext/common/css/common.css"/>

	
	<script type="text/javascript" src="ext/3.2.1/adapter/ext/ext-base.js"></script>
	<script type="text/javascript" src="ext/3.2.1/ext-all-debug.js"></script>
	
	

	<script type="text/javascript">
	
		Ext.onReady(function(){
				
			 var store = new Ext.data.JsonStore({
				    	url:"json?adminList&type=json",
				    	root:"data",
				    	totalProperty:"totalCount",//返回的总页数
				        fields: [
				           {name: 'adminId'},
				           {name: 'nickName'},
				           {name: 'userName'},
				           {name: 'passWord'},
				           {name: 'powerId'},
				           {name: 'powerName'},
				           {name: 'status'},
				           {name: 'addTime'}
				        ],
				        sortInfo: {
				            field: 'adminId', direction: 'ASC'
				        }
				    });
				    
				    store.load({params:{start:0,limit:20}});
				    
				    var dataGrid = new Ext.grid.GridPanel({
				    	store: store,
				       	region: 'center',
				       	border:false,
				        columns: [
				            {id:'adminId',hidden:true,sortable: true, dataIndex: 'adminId'},
				            {header: "昵称", sortable: true, dataIndex: 'nickName'},
				            {header: "用户名", sortable: true, dataIndex: 'userName'},
				            {header: "密码", sortable: true, dataIndex: 'passWord'},
				            {header: "身份", sortable: true, dataIndex: 'powerName'},
				          	{header: "添加时间", sortable: true, dataIndex: 'addTime'},
				            {header: "状态",sortable: true, dataIndex: 'status',renderer:function(value, metaData, record, rowIndex, colIndex, store){
				            	if(value == 0){
				            		return "停用";
				            	}else{
				            		return "启用";
				            	}
				            } },
				        ],
				        stripeRows: true,
				        autoExpandColumn: 'adminId',
				        loadMask: true,
				        bbar: new Ext.PagingToolbar({
					        pageSize: 20,
					        store: store,
					        displayInfo: true,
					        displayMsg: '第{0}到{1}条数据 共{2}条',
					        emptyMsg: "没有记录"
					    })
				    }); 
				   				
				    
				    //main panel
				    var panel = new Ext.Panel({
				    	title:"管理员",
				    	id:"dddddddd",
				    	closable:true,
				    	layout: 'border',
				    	items:[dataGrid]
				    });
				
				
				
				var w = new Ext.Window({
			    	title: '管理员添加',
					width: 900,
					height:600,
					layout: 'fit',
					plain:true,
					closable :true,
					collapsible : true ,
					bodyStyle:'padding:5px;',
					buttonAlign:'center',
					closeAction:"hide",
					items:panel
			    });
				
				w.show();
		});
		
		
		
	</script>

  </head>
  
  <body>
   
  </body>
</html>
