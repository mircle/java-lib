<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ page import="org.quickbundle.tools.helper.RmVoHelper" %>
<%@ page import="org.quickbundle.tools.helper.RmStringHelper" %>
<%@ page import="org.quickbundle.modules.quickquery.rmquickquerydata.vo.RmQuickQueryDataVo" %>
<%@ page import="org.quickbundle.modules.quickquery.rmquickquerydata.util.IRmQuickQueryDataConstants" %>
<%  //取出本条记录
	RmQuickQueryDataVo resultVo = null;  //定义一个临时的vo变量
	resultVo = (RmQuickQueryDataVo)request.getAttribute(IRmQuickQueryDataConstants.REQUEST_BEAN);  //从request中取出vo, 赋值给resultVo
	RmVoHelper.replaceToHtml(resultVo);  //把vo中的每个值过滤
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<%@ include file="/jsp/include/rmGlobal.jsp" %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><bean:message key="qb.web_title"/></title>
<script type="text/javascript">
	var rmActionName = "RmQuickQueryDataAction";
	function find_onClick(){  //直接点到修改页面
		form.action="<%=request.getContextPath()%>/" + rmActionName + ".do?cmd=find";
		form.submit();
	}
	function delete_onClick(){  //直接点删除单条记录
		if(!getConfirm()) {  //如果用户在确认对话框中点"取消"
			return false;
		}
		form.action="<%=request.getContextPath()%>/" + rmActionName + ".do?cmd=delete";
		form.submit();
	}  
</script>
</head>
<body>
<form name="form" method="post">
<br/>
<table class="mainTable">
	<tr>
		<td align="right" width="20%">&nbsp;</td>
		<td width="35%">&nbsp;</td>
		<td align="right" width="20%">&nbsp;</td>
		<td width="25%">&nbsp;</td>
	</tr>
	<tr>
		<td align="right"><%=IRmQuickQueryDataConstants.TABLE_COLUMN_CHINESE.get("quick_query_id")%>：</td>
		<td><%=RmStringHelper.prt(resultVo.getQuick_query_id())%>&nbsp;</td>
		<td align="right">&nbsp;</td>
		<td>&nbsp;</td>
	</tr>
	<tr>
		<td align="right"><%=IRmQuickQueryDataConstants.TABLE_COLUMN_CHINESE.get("old_resource_name")%>：</td>
		<td><%=RmStringHelper.prt(resultVo.getOld_resource_name())%>&nbsp;</td>
		<td align="right">&nbsp;</td>
		<td>&nbsp;</td>
	</tr>
	<tr>
		<td align="right"><%=IRmQuickQueryDataConstants.TABLE_COLUMN_CHINESE.get("old_resource_code")%>：</td>
		<td><%=RmStringHelper.prt(resultVo.getOld_resource_code())%>&nbsp;</td>
		<td align="right">&nbsp;</td>
		<td>&nbsp;</td>
	</tr>
	<tr>
		<td align="right"><%=IRmQuickQueryDataConstants.TABLE_COLUMN_CHINESE.get("old_resource_id_number")%>：</td>
		<td><%=RmStringHelper.prt(resultVo.getOld_resource_id_number())%>&nbsp;</td>
		<td align="right">&nbsp;</td>
		<td>&nbsp;</td>
	</tr>
	<tr>
		<td align="right"><%=IRmQuickQueryDataConstants.TABLE_COLUMN_CHINESE.get("old_resource_id_char")%>：</td>
		<td><%=RmStringHelper.prt(resultVo.getOld_resource_id_char())%>&nbsp;</td>
		<td align="right">&nbsp;</td>
		<td>&nbsp;</td>
	</tr>
	</table>

<input type="hidden" name="id" value="<%=RmStringHelper.prt(resultVo.getId())%>">

<table align="center">
	<tr>
		<td><br>
			<input type="button" class="button_ellipse" id="button_update" value="修改" onclick="javascript:find_onClick();">
			<input type="button" class="button_ellipse" id="button_delete" value="删除" onclickto="javascript:delete_onClick();">
			<input type="button" class="button_ellipse" id="button_back" value="返回"  onclick="javascript:history.go(-1);" >
		</td>
	</tr>
</table>

<%-- 开始子表信息，带页签集成多个子表 --%>
<script type="text/javascript">
var childTableTabs  =  new Array(

	null
);
</script>
<br/><br/>
<table class="table_div_content">
	<tr>
		<td>
			<div id="childTableTabsDiv"></div>
		</td>
	</tr>
</table>
<%-- 结束子表信息 --%>

</form>
</body>
</html>