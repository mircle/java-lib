<%@ page contentType="text/html; charset=UTF-8" language="java" %><%@page import="org.quickbundle.modules.quickquery.RmQuickQueryCache"%><%@page import="org.quickbundle.modules.quickquery.rmquickquery.vo.RmQuickQueryVo"%><%@ page import="java.util.List,java.util.Map,java.util.HashMap"%><%@ page import="org.quickbundle.project.RmProjectHelper "%><%
	//////////////////进行查询，并将结果生成select///////////////
	boolean only_left_like = false;//是否只左边匹配
	int maxRows = 20;//一次最多查询出的条数
	
	//获取传入的参数
	String qq = request.getParameter("qq");     //调用此方法的字段名字
	String q = request.getParameter("q"); //查询的内容 
	if(qq != null && qq.length() > 0 && q != null && q.length() > 0){
		RmQuickQueryVo quickQuery = RmQuickQueryCache.getSingleton().getQuickQueryByBs_keyword(qq);
	    try {
			String sql = quickQuery.buildSqlQqs(q, null);
			String[][] aOptionValue = RmProjectHelper.getCommonServiceInstance().paseToArrays(sql);
			StringBuffer returnString = new StringBuffer();
			int maxSize = 10;
			int minSize = 2;
			int size = aOptionValue.length > maxSize ? maxSize : aOptionValue.length;
			size = size < minSize?minSize:size;
			
	    	if (aOptionValue != null &&aOptionValue.length>0) {
	    		returnString.append("<select name='querySelect' id='querySelect' size='"+ size +"' style='width:204px;' onfocus=\"javascript:setQueryField(this);\" onKeyUp=\"javascript:closeTishiByEnter();\" onDblClick=\"javascript:closeTishiByClick();\" onchange=\"javascript:setQueryField(this);\" >\n");
	            for (int i = 0; i < aOptionValue.length; i++) {
	                String tempKey = aOptionValue[i][0];
	                String tempValue = aOptionValue[i][1];
	                returnString.append("<option value='"+ tempKey +"' >"+ tempValue +"</option>\n");
	            }
	            returnString.append("</select>\n<br>");
	            //returnString.append("<img src='"+ request.getContextPath() +"/images/icon/delete.gif' id='close_icon' align=left border=0 style='cursor:hand;' onclick='javascript:closeTishi();' title='关闭'>");
	            
	            out.print(returnString.toString());
	        }else{
				out.print("<font color=red style='background:white;'>抱歉，无匹配记录！</font>");
	        	out.print("<select name='querySelect' style='display:none;'>");
				out.print("</select>");
				
	        }
		}catch (Exception e) {
			out.print("<select name='querySelect'>");
			out.print("<option value=''>查询出错！</option>");
			out.print("</select>");
			
		    e.printStackTrace();
		}
	}
%>