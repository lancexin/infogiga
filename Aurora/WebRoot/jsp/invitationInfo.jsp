<%@ page language="java" import="java.util.*,cn.infogiga.bean.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
%>
<body>
	<table>
       	<tr>
           	<td class="select-order-a">预约时间：</td>
               <td class="select-order-b" id='showInvitationTime'></td>
               <td></td>
               <td><img src="img/status0.png" />正在发送</td>
           </tr>
       	<tr>
           	<td>预约名称：</td>
               <td id='showInvitationTitle'></td>
               <td></td>
               <td><img src="img/status1.png" />发送成功</td>
           </tr>
          
           <tr>
           	<td>邀请人员：</td>
               <td></td>
               <td></td>
               <td></td>
           </tr>
            <tr  class="select-order-e">
                <td></td>
                <td>                	
                	<table id='customerListTable'>
                   		<tr class='customerListLine'>
                       		<td class="select-order-c">
	                       		<a class="select-order-f"  href="javascript:;"></a>
	                       		<img src="" />
                       		</td>
                           	<td class="select-order-c">
	                       		<a class="select-order-f"  href="javascript:;"></a>
	                       		<img src="" />
                       		</td>
                       		<td class="select-order-c">
	                       		<a class="select-order-f"  href="javascript:;"></a>
	                       		<img src="" />
                       		</td>
                       		<td class="select-order-c">
	                       		<a class="select-order-f"  href="javascript:;"></a>
	                       		<img src="" />
                       		</td>
                       </tr>
                   </table>
                
                </td>
                <td></td>
                <td></td>
            </tr>
               
            <tr  class="select-order-e">
           	<td>接待人员：</td>
               <td>
               <a href="javascript:;" class="select-order-d" id='showGuider'></a>
               </td>
           </tr>
             
            <tr class="select-order-e">
           	<td>维护人员：</td>
               <td><a href="javascript:;" class="select-order-d" id='showTechnician'></a>
            </td>
           </tr>
              
       </table>
</body>
