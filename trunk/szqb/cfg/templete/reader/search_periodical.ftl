<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link type="text/css" rel="stylesheet" href="ext/3.2.1/resources/css/ext-all.css"/>
<link href="ext/carousel/carousel.css" rel="stylesheet" />

<script type="text/javascript" src="ext/3.2.1/adapter/ext/ext-base.js"></script>
<script type="text/javascript" src="ext/3.2.1/ext-all.js"></script>
<script src="ext/carousel/carousel.js"></script>
<script type="text/javascript">
	Ext.onReady(function(){
	 
		var p = new Ext.Panel({
	        labelWidth: 75,
	        width:710,
	        height:430,
	        style:'margin:5px;',
	        bodyStyle:'padding:5px 5px 0;font:16px;',
	        frame:true,
	        title:'审核',
	        layout:'absolute',
	        defaultType: 'label',
	        items:[{
		         text:'发布时间：',
		         x:10,
		         y:15
        	},{
		         text:'${periodical.publishTime}',
		         x:80,
		         y:15
        	},{
		         text:'出版刊号：',
		         x:10,
		         y:55
        	},{
		         text:'${periodical.number}',
		         x:80,
		         y:55
        	},{
		         text:'所属读物：',
		         x:10,
		         y:95
        	},{
		         text:'${periodical.reader.readerName}',
		         x:80,
		         y:95
        	},{
		         text:'首页图片：',
		         x:0,
		         y:135
        	},{
		         text:'缩略图片：',
		         x:305,
		         y:135
        	},{
				 xtype: 'box',
				 width:300,
				 heigth:200, 
				 x:0,
				 y:155,
				 style: {
            		marginBottom: '10px'
        		 },
				 autoEl: {  
				    tag: 'img',
				    src: "${periodical.indexPic}"
				 }  
			},{
				 xtype: 'box',
				 width:140,
				 heigth:205, 
				 x:305,
				 y:155,
				 autoEl: {  
					tag: 'img',
					src: "${periodical.tabloidPic}"
				 }  
			},{
				xtype: 'box',
				applyTo:'simple-example',
				x:450,
				y:0
			}]

	    });
	    p.render(Ext.getBody());
	    new Ext.ux.Carousel('simple-example');
	    
	});

</script>
<title>期刊查看</title>
</head>
<body style="width:800px">
	<div id="simple-example" style="padding: 5px; height: 355px; width: 238px; background-color: #E6E6E0">
		<#list pages as page>
		    	<img src="${page.imgUrl}" title="第${page_index+1}版" style="height: 355px; width: 238px;">
		</#list>
    </div>
</body>
</html>