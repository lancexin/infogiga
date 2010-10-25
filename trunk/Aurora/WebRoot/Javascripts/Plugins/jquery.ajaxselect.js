/**
 *@author:Cindy
*/
(function($){
	$.fn.extend({
		ajaxSelect:function(config){
			var _this = this;
			var def = {
				url:"",   //访问的url
				data:"",  //访问的参数 
				cache:true, //是否访问缓存，如果为true则只加载一次，默认为true
				value:"managerId",  //返回json填充到value的字段
				text:"name",   //返回json
				selected:"selected",//json中selected
				databind:"optionData", //是否将数据绑定在添加的option的标签上面
				lazy:true, //是否懒加载
				dirty:false //请不要动这个
			};
			
			var selectConfig = this.data("selectConfig");
			
			if(!selectConfig){
				selectConfig = $.extend(def, config);
				this.data("selectConfig",selectConfig);
			}
			
			if(selectConfig.lazy){
				if(selectConfig.cache){
					this.click(function(){
						getData();
						_this.unbind();
					});
				}else{
					this.click(function(){
						_this.empty();
						_this.append('<option value="-1">请选择...</option>');
						getData();
						_this.unbind();
					});
				}
				
			}else{
				if(selectConfig.url){
					getData();
				}
			}
			
			function getData(){
				$.post(selectConfig.url,selectConfig.data,function(html){
					$.each(html,function(i,n){
						
						var el = $('<option value="'+n[selectConfig.value]+'">'+n[selectConfig.text]+'</option>')
						if(n[selectConfig.selected]){
							el.attr("selected","selected");
						}
						
						if(selectConfig.databind){
							el.data(selectConfig.databind,n);	
						}
						_this.append(el);
					})
				},"json");
			}
		}
	});
})(jQuery);