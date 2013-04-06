<%@page import="org.quickbundle.tools.helper.RmDateHelper"%>
<%@page import="org.quickbundle.project.IGlobalConstants"%>
<%@page import="org.quickbundle.tools.support.statistic.RmStatisticHandler"%>
<%@ page contentType="text/html; charset=UTF-8" language="java" %><%try {
    RmStatisticHandler sh = (RmStatisticHandler) request.getAttribute(IGlobalConstants.REQUEST_STATISTIC_HANDLER);
	response.setContentType("application/msexcel");
    response.setHeader("Content-disposition", "attachment; filename=" + RmDateHelper.getJoinedSysDateTime() + "statistic.xls");
	sh.toExportExcel(response.getOutputStream());
} catch(Exception e) {
	e.printStackTrace();
}%>