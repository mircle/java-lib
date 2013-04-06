<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%><%@page import="net.sf.json.JSONObject"%><%@page import="org.quickbundle.project.common.vo.RmCommonVo"%><%@page import="java.util.List"%><%@page import="org.quickbundle.project.RmProjectHelper"%><%@page import="org.quickbundle.modules.quickquery.rmquickquery.vo.RmQuickQueryVo"%><%@page import="org.quickbundle.modules.quickquery.RmQuickQueryCache"%><%
	String qq = request.getParameter("qq");     //调用此方法的字段名字
	String q = request.getParameter("q"); //查询的内容
	RmQuickQueryVo quickQuery = RmQuickQueryCache.getSingleton().getQuickQueryByBs_keyword(qq);
	String sql = quickQuery.buildSqlQqp(q);
	List<RmCommonVo> lvo = RmProjectHelper.getCommonServiceInstance().doQuery(sql);
	JSONObject result = new JSONObject(); 
	if(lvo != null && lvo.size() > 0) {
		result = JSONObject.fromObject(lvo.get(0));
	}
	out.print(result.toString());
%>