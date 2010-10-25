<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'addCalendar.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link href="Theme/Default/main.css" rel="stylesheet" type="text/css" />
	<link href="Theme/Default/dailog.css" rel="stylesheet" type="text/css" />
	<link href="Theme/Default/calendar.css" rel="stylesheet" type="text/css" />
	<link href="Theme/Default/dp.css" rel="stylesheet" type="text/css" />
	<link href="Theme/Default/alert.css" rel="stylesheet" type="text/css" />
	<link href="Theme/Shared/blackbird.css" rel="stylesheet" type="text/css" />
	
	<script src="Javascripts/jquery.min.js" type="text/javascript"></script>  
    <script src="Javascripts/Common.js" type="text/javascript"></script>    
    <script src="Javascripts/lib/blackbird.js" type="text/javascript"></script> 
    <script src="Javascripts/Plugins/jquery.datepicker.js" type="text/javascript"></script>

    <script src="Javascripts/Plugins/jquery.alert.js" type="text/javascript"></script>    
    <script src="Javascripts/Plugins/jquery.ifrmdailog.js" defer="defer" type="text/javascript"></script>
    <script src="Javascripts/Plugins/jquery.calendar.js" type="text/javascript"></script>  
    <script type="text/javascript">
    $(function(){
    	$("#addDate").datepicker({
    		picker:"#addDate"
    	});
    
    });
    
    </script> 
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>

<table cellspacing="0" cellpadding="0">
    <tbody>
      <tr>
        <td colspan="3" class="bubble-mid">
        	<div id="bubbleContent1" style="overflow: hidden;">
            	             <div class="cb-root">
                <table cellspacing="0" cellpadding="0" class="cb-table">
                  <tbody>
                    <tr>
                      <th class="cb-key">开始时间：</th>
                      <td class="cb-value"><div id="bbit-cal-buddle-timeshow"><input style="width:100px" type="text" name="addDate" id="addDate">
                      <select id="addTime" name="addTime">
                      	<option>00:00</option><option>00:30</option>
                      	<option>01:00</option><option>01:30</option>
                      	<option>02:00</option><option>02:30</option>
                      	<option>03:00</option><option>03:30</option>
                      	<option>04:00</option><option>04:30</option>
                      	<option>05:00</option><option>05:30</option>
                      	<option>06:00</option><option>06:30</option>
                      	<option>07:00</option><option>07:30</option>
                      	<option>08:00</option><option>08:30</option>
                      	<option>09:00</option><option>09:30</option>
                      	<option>10:00</option><option>10:30</option>
                      	<option>11:00</option><option>11:30</option>
                      	<option>12:00</option><option>12:30</option>
                      	<option>13:00</option><option>13:30</option>
                      	<option>14:00</option><option>14:30</option>
                      	<option>15:00</option><option>15:30</option>
                      	<option>16:00</option><option>16:30</option>
                      	<option>17:00</option><option>17:30</option>
                      	<option>18:00</option><option>18:30</option>
                      	<option>19:00</option><option>19:30</option>
                      	<option>20:00</option><option>20:30</option>
                      	<option>21:00</option><option>21:30</option>
                      	<option>22:00</option><option>22:30</option>
                      	<option>23:00</option><option>23:30</option>
                      	<option>24:00</option>
                      	
                      </select>
                      
                      </div></td>
                    </tr>
                    
                    <tr>
                      <th class="cb-key">预约名称：</th>
                      <td class="cb-value">
                        <div class="textbox-fill-wrapper">
                          <div class="textbox-fill-mid">
                            <input class="textbox-fill-input" id="bbit-cal-what">
                          </div>
                        </div>
                      </td>
                    </tr>
                    
                    <tr>
                      <th class="cb-key">邀请人员：</th>
                      <td class="cb-value">
                        <input type="button" value="从文件导入"/><input class="add-a" type="button" value="添加"/>
                      </td>
                    </tr>
                    
                  	<tr>
                    	<td colspan="2">
                        	<table>
                            	<tr>
                                	<td>姓名</td>
                                	<td>手机号</td>
                                    <td>公司名称</td>
                                    <td>选项</td>
                                </tr>
                                
                                <tr>
                                	<td  ><input  class="add-b" type="text"/></td>
                                	<td  ><input  class="add-b" type="text"/></td>
                                    <td  ><input  class="add-b" type="text"/></td>
                                    <td  ><a href="javascript:;">删除</a></td>
                                </tr>
                            </table>
                        </td>
                    </tr>
                    
                     <tr>
                      <th class="cb-key">接待人员：</th>
                      <td class="cb-value">
                        <select>
                        	<option>请选择...</option>
                            <option>唐僧</option>
                            <option>猪八戒</option>
                        </select>
                      </td>
                    </tr>
                    
                    <tr>
                      <th class="cb-key">维护人员：</th>
                      <td class="cb-value">
                        <select>
                        	<option>请选择...</option>
                            <option>唐僧</option>
                            <option>猪八戒</option>
                        </select>
                      </td>
                    </tr>
                    
                    <tr>
                      <th class="cb-key">选择模板：</th>
                      <td class="cb-value">
                        <select>
                        	<option>请选择...</option>
                            <option>模板一</option>
                            <option>模板二</option>
                        </select>
                       </td>
                    </tr>
                    
                  </tbody>
                </table>
                <input type="hidden" id="bbit-cal-start" value="2010-03-02 12:30">
                <input type="hidden" id="bbit-cal-end" value="2010-03-02 13:30">
                <input type="hidden" id="bbit-cal-allday" value="0">
                <input type="button" value="创建日程" id="bbit-cal-quickAddBTN">
               </div>
            
            
            </div>
        </td>
      </tr>
     
    </tbody>
  </table>
  </body>
</html>
