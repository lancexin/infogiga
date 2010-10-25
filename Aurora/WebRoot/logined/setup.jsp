<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<script type="text/javascript">
<!--
	$(document).ready(function(){
		$("#head-right-menu-middle-container").append('<a href="javascript:;" id="setup">系统设置</a>');
		
		  $("#setup").click(function() {
				$("#head-right-menu-middle-container").stop().animate(
					{"backgroundPosition":"220px 4px"},
					{"duration":"500"});
				right_menu_choosed($(this));
				$(".add-continer").hide();
				$("#setup-dialog").show();
				
				$(".setup-tool-bar .setup-tool-bar-el2").removeClass("setup-tool-bar-el-choosed");
				$(".setup-tool-bar-el-left").removeClass("setup-el-left-choosed");
				$(".setup-tool-bar-el-right").removeClass("setup-el-right-choosed");
				
				$(".setup-tool-bar .setup-tool-bar-el2:first").addClass("setup-tool-bar-el-choosed");
				$(".setup-tool-bar .setup-tool-bar-el2:first").find(".setup-tool-bar-el-left").addClass("setup-el-left-choosed");
				$(".setup-tool-bar .setup-tool-bar-el2:first").find(".setup-tool-bar-el-right").addClass("setup-el-right-choosed");
				
				$(".setup-right-tab").hide();
				$("#"+$(".setup-tool-bar .setup-tool-bar-el2:first").attr("id")+"-dialog").show();
				$("#setup-add-top-exit").click(function(){
					$("#setup-dialog").hide();
				});
				
				$("#setup-button-exit").click(function(){
					$("#setup-dialog").hide();
				});
		   });
	});
//-->
</script>
<div class="add-continer" id="setup-dialog" style="display:none;">
	<div class="add-top">
    	<div class="add-top-left"></div>
        <div class="add-top-center">
          <div class="add-top-title">
          		<img src="Theme/Default/images/zq2admin_40.png" align="top"/>
                <span class="add-top-title-new">设置</span>
          </div>
          <div class="add-top-exit" id="setup-add-top-exit"><img src="Theme/Default/images/zq2admin_11.png"/></div>
        </div>
        <div class="add-top-right"></div>
    </div>
    
    <div class="add-main" >
		<div class="setup-left">
        	<div class="setup-tool-middle"></div>
        	<div class="setup-tool-bar">
            	<div class="setup-tool-bar-middle"></div>
            	<div class="setup-tool-bar-el" id="setup-tab-a">
                	<div class="setup-tool-bar-el-left"></div>
                    <div class="setup-tool-bar-el-right" id="setup-tab-a-right">
                    	
                    </div>
                </div>
                
              	<div class="setup-tool-bar-middle setup-tab-b"></div>
                <div class="setup-tool-bar-el"  id="setup-tab-f">
                	<div class="setup-tool-bar-el-left"></div>
                    <div class="setup-tool-bar-el-right" id="setup-tab-f-right">
                    	
                    </div>
                </div>
                
                <div class="setup-tool-bar-middle"></div>
                <div class="setup-tool-bar-el"  id="setup-tab-d">
                	<div class="setup-tool-bar-el-left"></div>
                    <div class="setup-tool-bar-el-right" id="setup-tab-d-right">
                    	
                    </div>
                </div>
            
                
                
                  <div class="setup-tool-bar-middle"></div>
                <div class="setup-tool-bar-el"  id="setup-tab-c">
                	<div class="setup-tool-bar-el-left"></div>
                    <div class="setup-tool-bar-el-right" id="setup-tab-c-right">
                    	
                    </div>
                </div>
                
               <div class="setup-tool-bar-middle setup-tab-b"></div>
                <div class="setup-tool-bar-el" id="setup-tab-e">
                	<div class="setup-tool-bar-el-left"></div>
                    <div class="setup-tool-bar-el-right" id="setup-tab-e">
                    	
                    </div>
                </div>
                <div class="setup-tool-bar-middle setup-tab-b"></div>
                <div class="setup-tool-bar-middle setup-tab-b"></div>
            </div>
        	 <div class="setup-tool-middle"></div>
        </div>
        <div class="setup-right" id="setup-right-box">
       

		</div>
            
            
   </div>
        
        
    
    <div class="add-bottom">
    	<div class="add-bottom-left"></div>
        <div class="add-bottom-center">
        	
        	<div class="add-bottom-button-right" id="setup-button-exit"><span class="add-bottom-button-next">退出</span></div>
      </div>
        <div class="add-bottom-right"></div>
    </div>

</div>