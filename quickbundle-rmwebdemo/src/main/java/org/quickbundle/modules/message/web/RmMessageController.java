package org.quickbundle.modules.message.web;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.quickbundle.base.web.page.RmPageVo;
import org.quickbundle.modules.message.IRmMessageConstants;
import org.quickbundle.modules.message.service.RmMessageService;
import org.quickbundle.modules.message.vo.RmMessageVo;
import org.quickbundle.tools.helper.RmJspHelper;
import org.quickbundle.tools.helper.RmPopulateHelper;
import org.quickbundle.tools.helper.RmSqlHelper;
import org.quickbundle.tools.helper.RmVoHelper;
import org.quickbundle.tools.support.statistic.RmStatisticHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * 消息管理的Controller, 使用Restful风格的Urls:
 * 
 * List          : GET /rmmessage/
 * Insert        : GET /rmmessage/insert
 * Insert action : POST /rmmessage/insert
 * Update        : GET /rmmessage/update/{id}
 * Update action : POST /rmmessage/update
 * Delete action : GET /rmmessage/delete
 * Detail        : GET /rmmessage/detail/{id}
 * reference     : GET /rmmessage/reference
 * statistic     : GET /rmmessage/statistic
 * 
 * @author 白小勇
 */
@Controller
@RequestMapping(value = "/rmmessage")
public class RmMessageController implements IRmMessageConstants{

	@Autowired
	private RmMessageService rmMessageService;
	
	/**
	 * 简单查询，分页显示，支持表单回写
	 * 
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "")
	public String list(Model model, HttpServletRequest request) {
        String queryCondition = getQueryCondition(request);  //从request中获得查询条件
        RmPageVo pageVo = RmJspHelper.transctPageVo(request, rmMessageService.getCount(queryCondition));
        String orderStr = RmJspHelper.getOrderStr(request);  //得到排序信息
        List<RmMessageVo> beans = rmMessageService.list(queryCondition, orderStr, pageVo.getStartIndex(), pageVo.getPageSize());  //按条件查询全部,带排序
        //如果采用下边这句，就是不带翻页的，同时需要删掉list页面的page.jsp相关语句
        //beans = rmMessageService.list(queryCondition, orderStr);  //查询全部,带排序
        RmJspHelper.saveOrderStr(orderStr, request);  //保存排序信息
        model.addAttribute(REQUEST_QUERY_CONDITION, queryCondition);
        model.addAttribute(REQUEST_BEANS, beans);  //把结果集放入request
        model.addAttribute(REQUEST_WRITE_BACK_FORM_VALUES, RmVoHelper.getMapFromRequest((HttpServletRequest) request));  //回写表单
		return "message/listRmMessage";
	}
	
	/**
	 * 跳转到新增页
	 * 
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "insert", method = RequestMethod.GET)
	public String insertForm(Model model) {
		model.addAttribute("bean", new RmMessageVo());
		model.addAttribute("action", "insert");
		return "message/insertRmMessage";
	}
    
	/**
	 * 从页面表单获取信息注入vo，并插入单条记录
	 * 
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "insert", method = RequestMethod.POST)
	public String insert(HttpServletRequest request, @Valid RmMessageVo vo, RedirectAttributes redirectAttributes) {
        RmVoHelper.markCreateStamp(request,vo);  //打创建时间,IP戳
        rmMessageService.insert(vo);  //插入单条记录
        redirectAttributes.addFlashAttribute("message", "创建成功");
        return "redirect:/rmmessage/";
	}
	
	/**
	 * 从页面的表单获取单条记录id，查出这条记录的值，并跳转到修改页面
	 * 
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "update/{id}", method = RequestMethod.GET)
	public String updateForm(@PathVariable("id") String id, Model model) {
		RmMessageVo bean = rmMessageService.get(id);
        model.addAttribute(REQUEST_BEAN, bean);  //把vo放入request
        model.addAttribute("action", "update");
		return "message/insertRmMessage";
	}
	
	/**
	 * 从页面表单获取信息注入vo，并修改单条记录
	 * 
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "update", method = RequestMethod.POST)
	public String update(HttpServletRequest request, @Valid @ModelAttribute("preloadTask") RmMessageVo vo, RedirectAttributes redirectAttributes) {
        RmVoHelper.markModifyStamp(request,vo);  //打修改时间,IP戳
        int count = rmMessageService.update(vo);  //更新单条记录
        redirectAttributes.addFlashAttribute("message", "更新成功: " + count);
    	return "redirect:/rmmessage/";
	}
	
	/**
	 * 从页面的表单获取单条记录id并删除，如request.id为空则删除多条记录（request.ids）
	 * 
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "delete", method = RequestMethod.POST)
	public String delete(HttpServletRequest request, RedirectAttributes redirectAttributes) {
		int deleteCount = 0;  //定义成功删除的记录数
		String id = request.getParameter(REQUEST_ID);
		if(id != null && id.length() > 0) {
			deleteCount = rmMessageService.delete(id);
		} else {
			String[] ids = RmJspHelper.getArrayFromRequest(request, REQUEST_IDS); //从request获取多条记录id
			if (ids != null && ids.length != 0) {
				deleteCount += rmMessageService.delete(id);  //删除多条记录
			}
		}
		redirectAttributes.addFlashAttribute("message", "删除成功: " + deleteCount);
        return "redirect:/rmmessage/";
	}
	
	/**
	 * 批量保存，没有主键的insert，有主键的update
	 * 
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "insertUpdateBatch", method = RequestMethod.POST)
	public String insertUpdateBatch(Model model, HttpServletRequest request) {
    	List<RmMessageVo> lvo = RmPopulateHelper.populateAjax(RmMessageVo.class, request);
    	for(RmMessageVo vo : lvo) {
    		if(vo.getId() != null && vo.getId().trim().length() > 0) {
    			RmVoHelper.markModifyStamp(request, vo);
    		} else {
    			RmVoHelper.markCreateStamp(request, vo);
    		}
    	}
    	int[] sum_insert_update = rmMessageService.insertUpdateBatch(lvo.toArray(new RmMessageVo[0]));
    	model.addAttribute(REQUEST_OUTPUT_OBJECT, sum_insert_update);
    	return "redirect:/rmmessage/";
	}


	/**
	 * 从页面的表单获取单条记录id，并察看这条记录的详细信息
	 * 
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "detail/{id}")
	public String detail(@PathVariable("id") String id, Model model, HttpServletRequest request) {
		RmMessageVo bean = rmMessageService.get(request.getParameter(REQUEST_ID));
        model.addAttribute(REQUEST_BEAN, bean);  //把vo放入request
        if(RM_YES.equals(request.getParameter(REQUEST_IS_READ_ONLY))) {
        	model.addAttribute(REQUEST_IS_READ_ONLY, request.getParameter(REQUEST_IS_READ_ONLY));
        }
		return "message/detailRmMessage";
	}
	
	/**
	 * 参照信息查询，带简单查询，分页显示，支持表单回写
	 * 
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "reference")
	public String reference(Model model, HttpServletRequest request) {
		list(model, request);
		model.addAttribute(REQUEST_REFERENCE_INPUT_TYPE, request.getParameter(REQUEST_REFERENCE_INPUT_TYPE));  //传送输入方式,checkbox或radio
		return "message/util/referenceRmMessage";
	}

	/**
	 * 功能: 行列统计
	 * 
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "statistic")
	public String statistic(Model model, HttpServletRequest request) {
        String rowKeyField = "parent_message_id";  //定义行统计关键字
        String columnKeyField = "id";  //定义列统计关键字
        String queryCondition = getQueryCondition(request);  //从request中获得查询条件
        List<RmMessageVo> beans = rmMessageService.list(queryCondition, null);  //查询出全部结果
        RmStatisticHandler sh = new RmStatisticHandler(beans, rowKeyField, columnKeyField, "父消息ID\\主键");
        model.addAttribute(REQUEST_STATISTIC_HANDLER, sh);  //把结果集放入request
        model.addAttribute(REQUEST_WRITE_BACK_FORM_VALUES, RmVoHelper.getMapFromRequest((HttpServletRequest) request));  //回写表单
		return "message/util/statisticRmMessage_rowColumn";
	}
    
	/**
	 * 使用@ModelAttribute, 实现Struts2 Preparable二次部分绑定的效果,先根据form的id从数据库查出Task对象,再把Form提交的内容绑定到该对象上。
	 * 因为仅update()方法的form中有id属性，因此本方法在该方法中执行.
	 * 
	 * @param id
	 * @return
	 */
	@ModelAttribute("preloadTask")
	public RmMessageVo get(@RequestParam(value = "id", required = false) String id) {
		if (id != null) {
			return rmMessageService.get(id);
		}
		return null;
	}
	
    /**
     * 功能: 从request中获得查询条件
     *
     * @param request
     * @return
     */
    protected String getQueryCondition(HttpServletRequest request) {
        String queryCondition = null;
        if(request.getAttribute(REQUEST_QUERY_CONDITION) != null) {  //如果request.getAttribute中有，就不取request.getParameter
            queryCondition = request.getAttribute(REQUEST_QUERY_CONDITION).toString();
        } else {
			List<String> lQuery = new ArrayList<String>();
			
			lQuery.add(RmSqlHelper.buildQueryStr(TABLE_NAME + ".biz_keyword", request.getParameter("biz_keyword"), RmSqlHelper.TYPE_CHAR_LIKE));
				
			lQuery.add(RmSqlHelper.buildQueryStr(TABLE_NAME + ".sender_id", request.getParameter("sender_id"), RmSqlHelper.TYPE_CHAR_LIKE));
				
			lQuery.add(RmSqlHelper.buildQueryStr(TABLE_NAME + ".parent_message_id", request.getParameter("parent_message_id"), RmSqlHelper.TYPE_CUSTOM, "='", "'"));
				
			lQuery.add(RmSqlHelper.buildQueryStr(TABLE_NAME + ".owner_org_id", request.getParameter("owner_org_id"), RmSqlHelper.TYPE_CHAR_LIKE));
				
			lQuery.add(RmSqlHelper.buildQueryStr(TABLE_NAME + ".template_id", request.getParameter("template_id"), RmSqlHelper.TYPE_CHAR_LIKE));
				
			lQuery.add(RmSqlHelper.buildQueryStr(TABLE_NAME + ".is_affix", request.getParameter("is_affix"), RmSqlHelper.TYPE_CUSTOM, "='", "'"));
				
			lQuery.add(RmSqlHelper.buildQueryStr(TABLE_NAME + ".record_id", request.getParameter("record_id"), RmSqlHelper.TYPE_CHAR_LIKE));
				
			lQuery.add(RmSqlHelper.buildQueryStr(TABLE_NAME + ".message_xml_context", request.getParameter("message_xml_context"), RmSqlHelper.TYPE_CHAR_LIKE));
				
			queryCondition = RmSqlHelper.appendQueryStr(lQuery.toArray(new String[0]));
        }
        return queryCondition;
    }
}
