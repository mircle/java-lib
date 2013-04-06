<%@page import="java.util.Arrays"%>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@page import="org.quickbundle.tools.helper.RmVoHelper"%>
<%@page import="org.quickbundle.base.web.page.RmPageVo"%>
<%@ page import="org.quickbundle.modules.quickquery.rmquickquerydata.vo.RmQuickQueryDataVo" %>
<%@ page import="org.quickbundle.modules.quickquery.rmquickquerydata.util.IRmQuickQueryDataConstants" %>
<%  //判断是否只读
	boolean isReadOnly = false;
	if("1".equals(request.getAttribute(IRmQuickQueryDataConstants.REQUEST_IS_READ_ONLY))) {
		isReadOnly = true;
	}
%>
<%  //取出List
	List<RmQuickQueryDataVo> lResult = null;  //定义结果列表的List变量
	if(request.getAttribute(IRmQuickQueryDataConstants.REQUEST_BEANS) != null) {  //如果request中的beans不为空
		lResult = (List)request.getAttribute(IRmQuickQueryDataConstants.REQUEST_BEANS);  //赋值给resultList
	}
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<%@ include file="/jsp/include/rmGlobal.jsp" %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><bean:message key="qb.web_title"/></title>
<script type="text/javascript">
	var rmActionName = "RmQuickQueryDataConditionAction";
	var rmJspPath = "/condition";
	function findCheckbox_onClick() {  //从多选框到修改页面
		var ids = findSelections("checkbox_template","id");  //取得多选框的选择项
		if(ids == null) {  //如果ids为空
	  		alert("请选择一条记录!")
	  		return false;
		}
		if(ids.length > 1) {  //如果ids有2条以上的纪录
	  		alert("只能选择一条记录!")
	  		return false;
		}
		form.action="<%=request.getContextPath()%>/" + rmActionName + ".do?cmd=find&id=" + ids;
		form.submit();
	}
	function deleteMulti_onClick(){  //从多选框物理删除多条记录
 		var ids = findSelections("checkbox_template","id");  //取得多选框的选择项
		if(ids == null)	{  //如果ids为空
			alert("请选择记录!")
			return false;
		}
		if(!confirm("是否彻底删除该数据？")) {  //如果用户在确认对话框按"确定"
			return false;
		}
    	form.action="<%=request.getContextPath()%>/" + rmActionName + ".do?cmd=deleteMulti&ids=" + ids;
    	form.submit();
	}
	function simpleQuery_onClick(){  //简单的模糊查询
    	form.action="<%=request.getContextPath()%>/" + rmActionName + ".do?cmd=simpleQuery";
    	form.submit();
  	}
	
	function toAdd_onClick() {  //到增加记录页面
		form.action="<%=request.getContextPath()%>/modules/quickquery/rmquickquerydata" + rmJspPath + "/insertRmQuickQueryData.jsp";
		form.submit();
	}
	function refresh_onClick() {  //刷新本页
		form.submit();
	}
	function detail_onClick(thisId) {  //实现转到详细页面
		form.id.value = thisId;  //赋值thisId给隐藏值id
		form.action="<%=request.getContextPath()%>/" + rmActionName + ".do?cmd=detail";
		form.submit();
	}
	function initData_onClick(){  //自动初始化参照索引数据
 		var ids = findSelections("checkbox_template","old_resource_id");  //取得多选框的选择项
		if(ids == null)	{  //如果ids为空
			alert("请选择记录!")
			return false;
		}
		if(!confirm("是否自动初始化该数据？")) {  //如果用户在确认对话框按"确定"
			return false;
		}
    	form.action="<%=request.getContextPath()%>/" + rmActionName + ".do?cmd=initData&ids=" + ids;
    	form.submit();
	}
</script>
</head>
<body>
<form name="form" method="post">

<div id="div_simple" style="display:none;">
	<table class="table_query">
		<tr>
			<td width="20%">&nbsp;</td>
			<td width="35%">&nbsp;</td>
			<td width="20%">&nbsp;</td>
			<td width="25%">&nbsp;</td>
		</tr>
		<tr>
			<td align="right"><%=IRmQuickQueryDataConstants.TABLE_COLUMN_CHINESE.get("quick_query_id")%></td>
			<td>
				<input type="text" class="text_field_reference" hiddenInputId="quick_query_id" name="quick_query_id_name" inputName="<%=IRmQuickQueryDataConstants.TABLE_COLUMN_CHINESE.get("quick_query_id")%>" value="" /><input type="hidden" name="quick_query_id"><img class="refButtonClass" src="<%=request.getContextPath()%>/images/09.gif" onclick="javascript:getReference(new Array(form.quick_query_id, form.quick_query_id_name), '<%=request.getContextPath()%>/', '<%=request.getContextPath()%>/RmQuickQueryAction.do?cmd=queryReference&referenceInputType=radio');"/>
			</td>
			<td align="right"></td>
			<td></td>
		</tr>
		<tr>
			<td align="right"><%=IRmQuickQueryDataConstants.TABLE_COLUMN_CHINESE.get("old_resource_name")%></td>
			<td>
				<input type="text" class="text_field" name="old_resource_name" inputName="<%=IRmQuickQueryDataConstants.TABLE_COLUMN_CHINESE.get("old_resource_name")%>" maxLength="100"/>
			</td>
			<td align="right"></td>
			<td></td>
		</tr>
		<tr>
			<td align="right"><%=IRmQuickQueryDataConstants.TABLE_COLUMN_CHINESE.get("old_resource_code")%></td>
			<td>
				<input type="text" class="text_field" name="old_resource_code" inputName="<%=IRmQuickQueryDataConstants.TABLE_COLUMN_CHINESE.get("old_resource_code")%>" maxLength="25"/>
			</td>
			<td align="right"></td>
			<td></td>
		</tr>
		<tr>
			<td align="right"><%=IRmQuickQueryDataConstants.TABLE_COLUMN_CHINESE.get("old_resource_id_number")%></td>
			<td>
				<input type="text" class="text_field" name="old_resource_id_number" inputName="<%=IRmQuickQueryDataConstants.TABLE_COLUMN_CHINESE.get("old_resource_id_number")%>" maxLength="9"/>
			</td>
			<td align="right"></td>
			<td></td>
		</tr>
		<tr>
			<td align="right"><%=IRmQuickQueryDataConstants.TABLE_COLUMN_CHINESE.get("old_resource_id_char")%></td>
			<td>
				<input type="text" class="text_field" name="old_resource_id_char" inputName="<%=IRmQuickQueryDataConstants.TABLE_COLUMN_CHINESE.get("old_resource_id_char")%>" maxLength="25"/>
			<input type="button" class="button_ellipse" id="button_ok" onclickto="javascript:simpleQuery_onClick()" value="查询" />
				<input type="reset" class="button_ellipse" id="button_reset" value="清空" />
				<input type="button" class="button_ellipse" id="button_moreCondition" onclick="javascript:changeSearch_onClick(this);" value="更多条件" />
			</td>
			<td align="right"></td>
			<td></td>
		</tr>
		</table>
</div>
<div id="div_advanced" style="display:none;">
	<table class="table_query">
		<tr>
			<td width="20%"></td>
			<td width="35%"></td>
			<td width="20%"></td>
			<td width="25%"></td>
		</tr>
		<%-- 将需要隐藏的查询条件剪切到这里 --%>
	</table>
</div>

<table class="tableHeader">
  <tr>
    <td width="1%"><img src="<%=request.getContextPath()%>/images/bg_mcontentL.gif" /></td>
    <td class="tableHeaderMiddleTd"><b><%=IRmQuickQueryDataConstants.TABLE_NAME_CHINESE %>列表</b></td>
    <td class="tableHeaderMiddleTd" width="60%" align="right">
    	<%if(!isReadOnly) { %>
		<input type="button" class="button_ellipse" id="button_initData" value="初始化" onclickto="javascript:initData_onClick();" title="自动初始化所选的记录"/>
		<input type="button" class="button_ellipse" id="button_toAdd" value="新增" onclick="javascript:toAdd_onClick();" title="跳转到新增页面"/>
		<input type="button" class="button_ellipse" id="button_deleteMulti" value="删除" onclickto="javascript:deleteMulti_onClick();" title="删除所选的记录"/>
		<input type="button" class="button_ellipse" id="button_findCheckbox" value="修改" onclick="javascript:findCheckbox_onClick();" title="跳转到修改所选的某条记录"/>
		<%}%>
		<input type="button" class="button_ellipse" id="button_refresh" value="刷新" onclickto="javascript:refresh_onClick();" title="刷新当前页面"/>
    </td>
    <td width="1%" align="right"><img src="<%=request.getContextPath()%>/images/bg_mcontentR.gif" /></td>
  </tr>
</table>

<layout:collection name="beans" id="rmBean" styleClass="listCss" width="100%" indexId="rmOrderNumber" align="center" sortAction="0">
	<layout:collectionItem width="1%" title="<input type='checkbox' pdType='control' control='checkbox_template'/>" style="text-align:center;">
		<bean:define id="rmValue" name="rmBean" property="id"/>
		<bean:define id="rmDisplayName" name="rmBean" property="old_resource_name"/>
        <bean:define id="old_resource_id" name="rmBean" property="old_resource_id"/>
		<input type="checkbox" name="checkbox_template" value="<%=RmStringHelper.prt(rmValue)%>" displayName="<%=RmStringHelper.prt(rmDisplayName)%>" old_resource_id="<%=old_resource_id%>" />
	</layout:collectionItem>
	<layout:collectionItem width="3%"  title="序" style="text-align:center;">
	<%
		Integer rmOrderNumber = (Integer)pageContext.getAttribute("rmOrderNumber");
		RmPageVo pageVo = (RmPageVo)pageContext.getRequest().getAttribute(IRmQuickQueryDataConstants.RM_PAGE_VO);
		out.print((pageVo.getCurrentPage() - 1) * pageVo.getPageSize() + rmOrderNumber.intValue() + 1);
	%>
		<bean:define id="rmValue" name="rmBean" property="id"/>
		<input type="hidden" signName="hiddenId" value="<%=rmValue%>"/>
	</layout:collectionItem>
	<layout:collectionItem width="8%" title='资源原始ID' property="old_resource_id"/>
	<layout:collectionItem width="8%" title='资源原始名称' property="old_resource_id_name"/>
	<layout:collectionItem width="8%" title='<%=IRmQuickQueryDataConstants.TABLE_COLUMN_CHINESE.get("old_resource_code")%>' property="old_resource_code" sortable="true"/>
	<layout:collectionItem width="8%" title='资源索引名称' property="old_resource_name" sortable="true"/>
	<layout:collectionItem width="8%" title='索引值' sortable="true">
       <bean:define id="vo" name="rmBean"/>
   <%
   		out.print(RmStringHelper.parseToString(((RmQuickQueryDataVo)vo).getIndex_values().toArray(new String[0])));
   %>
	</layout:collectionItem>
</layout:collection>

<%-- 下边这句是翻页, 如果去掉就不带翻页了,同时注意Action中也要调整方法 --%>
<jsp:include page="/jsp/include/page.jsp" />

<input type="hidden" name="id" value="">
<input type="hidden" name="queryCondition" value="">
<%=isReadOnly ? "<input type=\"hidden\" name=\"" + IRmQuickQueryDataConstants.REQUEST_IS_READ_ONLY + "\" value=\"1\">" : ""%>

</form>
</body>
</html>
<script type="text/javascript">
<%  //表单回写
	if(request.getAttribute(IRmQuickQueryDataConstants.REQUEST_WRITE_BACK_FORM_VALUES) != null) {  //如果request中取出的表单回写bean不为空
		out.print(RmVoHelper.writeBackMapToForm((java.util.Map)request.getAttribute(IRmQuickQueryDataConstants.REQUEST_WRITE_BACK_FORM_VALUES)));  //输出表单回写方法的脚本
	}
%>
autoPatchParentIdName("<%=IRmQuickQueryDataConstants.DEFAULT_CONDITION_KEY_ARRAY[0]%>");
</script>