var newComponent = (function() {

	//Ext.QuickTips.init();
	//main grid
    var store = new Ext.data.JsonStore({
    	url:"json?softdownloadstatList&type=json",
    	root:"data",
    	totalProperty:"totalCount",//返回的总页数
        fields: [
            {name: 'statId'},
	        {name: 'equipmentName'},
	        {name: 'hallName'},
	        {name: 'softName'},
	        {name: 'phonetypeName'},
	        {name: 'phonebrandName'},
	        {name: 'employeeName'},
	        {name: 'downloadtypeName'},
	        {name: 'phoneNumber'},
	        {name: 'addTime'}
        ],
        sortInfo: {
            field: 'statId', direction: 'ASC'
        }
    });
    
    store.load({params:{start:0,limit:20}});
    var record_start = 0;
    var dataGrid = new Ext.grid.GridPanel({
    	store: store,
       	region: 'center',
       	border:false,
        columns: [
        	new Ext.grid.RowNumberer({
        		renderer:function(value,metadata,record,rowIndex){
			　　　return　record_start　+　1　+　rowIndex;
			　　}
        	}), 
            {id:'statId',hidden:true,sortable: true, dataIndex: 'statId'},
            {header: "员工姓名", sortable: true, dataIndex: 'employeeName'},
            {header: "设备名称", sortable: true, dataIndex: 'equipmentName'},
            {header: "营业厅", sortable: true, dataIndex: 'hallName'},
            {header: "软件名称", sortable: true, dataIndex: 'softName'},
            {header: "手机厂商", sortable: true, dataIndex: 'phonebrandName'},
            {header: "手机型号", sortable: true, dataIndex: 'phonetypeName'},
            {header: "手机号码", sortable: true, dataIndex: 'phoneNumber'},
            {header: "下载类型", sortable: true, dataIndex: 'downloadtypeName'},
            {header: "下载时间", sortable: true, dataIndex: 'addTime'}
        ],
        stripeRows: true,
        autoExpandColumn: 'statId',
        loadMask: true,
        bbar: new Ext.PagingToolbar({
	        pageSize: 20,
	        store: store,
	        displayInfo: true,
	        displayMsg: '第{0}到{1}条数据 共{2}条',
	        emptyMsg: "没有记录",
		　　 doLoad　:　function(start){
				record_start　=　start;
				var o = {}, pn = this.getParams(); 
				o[pn.start]　=　start;
				o[pn.limit]　=　this.pageSize;
				this.store.load({params:o});
		　　}
	    })
    }); 
    
    //main panel
    var panel = new Ext.Panel({
    	id:"${code}",
		title:"${name}",
    	closable:true,
    	layout: 'border',
    	defaults: {
            split: true
        },
    	items:[dataGrid]
    });
    
    return panel;
})();