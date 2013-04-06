<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@page import="org.quickbundle.tools.helper.RmStringHelper"%>
<%@page import="org.quickbundle.third.jasperreport.PrintServiceApp"%>
<%@page import="java.io.File"%>
<%@page import="net.sf.jasperreports.engine.JRRuntimeException"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.HashMap"%>
<%@page import="net.sf.jasperreports.engine.JasperPrint"%>
<%@page import="net.sf.jasperreports.engine.JasperReport"%>
<%@page import="net.sf.jasperreports.engine.util.JRLoader"%>
<%@page import="net.sf.jasperreports.engine.JasperFillManager"%>
<%@page import="org.quickbundle.third.jasperreport.WebappDataSource"%>
<%@page import="net.sf.jasperreports.engine.JRException"%>
<%@page import="java.io.PrintWriter"%>
<%@page import="java.io.ObjectOutputStream"%>
<%
if("1".equals(request.getParameter("isSetSession"))) {
	session.setAttribute("toPrintStr", request.getParameter("toPrintStr"));
} else {
	try {
		JasperPrint jasperPrint = PrintServiceApp.getJasperPrint(session.getAttribute("toPrintStr") != null ? session.getAttribute("toPrintStr").toString() : "");
		if (jasperPrint != null) {
			response.setContentType("application/octet-stream");
			ServletOutputStream ouputStream = response.getOutputStream();
			ObjectOutputStream oos = new ObjectOutputStream(ouputStream);
			oos.writeObject(jasperPrint);
			oos.flush();
			oos.close();
			ouputStream.flush();
			ouputStream.close();
		}
		out.clear();
		out = pageContext.pushBody();
	} catch (JRException e) {
		e.printStackTrace();
	}
}
%>