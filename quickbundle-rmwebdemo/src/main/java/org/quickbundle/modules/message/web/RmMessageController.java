package org.quickbundle.modules.message.web;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

import org.quickbundle.base.cache.RmSqlCountCache;
import org.quickbundle.base.web.page.RmPageVo;
import org.quickbundle.modules.message.IRmMessageConstants;
import org.quickbundle.modules.message.service.RmMessageService;
import org.quickbundle.modules.message.vo.RmMessageVo;
import org.quickbundle.tools.helper.RmJspHelper;
import org.quickbundle.tools.helper.RmSqlHelper;
import org.quickbundle.tools.helper.RmVoHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 消息管理的Controller, 使用Restful风格的Urls:
 * 
 * List page     : GET /rmmessage/
 * Create page   : GET /rmmessage/create
 * Create action : POST /rmmessage/create
 * Update page   : GET /rmmessage/update/{id}
 * Update action : POST /rmmessage/update
 * Delete action : GET /rmmessage/delete/{id}
 * 
 * @author 白小勇
 */
@Controller
@RequestMapping(value = "/rmmessage")
public class RmMessageController implements IRmMessageConstants{

	@Autowired
	private RmMessageService rmMessageService;

	@RequestMapping(value = "")
	public String list(Model model, HttpServletRequest request) {
        String queryCondition = getQueryCondition(request);  //从request中获得查询条件
        RmPageVo pageVo = RmJspHelper.transctPageVo(request, getCount(queryCondition));
        String orderStr = RmJspHelper.getOrderStr(request);  //得到排序信息
        List<RmMessageVo> beans = rmMessageService.list(queryCondition, orderStr, pageVo.getStartIndex(), pageVo.getPageSize(), false);  //按条件查询全部,带排序
        //如果采用下边这句，就是不带翻页的，同时需要删掉list页面的page.jsp相关语句
        //beans = service.queryByCondition(queryCondition, orderStr);  //查询全部,带排序
//        RmJspHelper.saveOrderStr(orderStr, request);  //保存排序信息
        model.addAttribute(REQUEST_QUERY_CONDITION, queryCondition);
        model.addAttribute(REQUEST_BEANS, beans);  //把结果集放入request
        model.addAttribute(REQUEST_WRITE_BACK_FORM_VALUES, RmVoHelper.getMapFromRequest((HttpServletRequest) request));  //回写表单
		return "message/listRmMessage";
	}

//	@RequestMapping(value = "create", method = RequestMethod.GET)
//	public String createForm(Model model) {
//		model.addAttribute("task", new RmMessageVo());
//		model.addAttribute("action", "create");
//		return "task/taskForm";
//	}
//
//	@RequestMapping(value = "create", method = RequestMethod.POST)
//	public String create(@Valid RmMessageVo newTask, RedirectAttributes redirectAttributes) {
//		User user = new User(getCurrentUserId());
//		newTask.setUser(user);
//
//		taskService.saveTask(newTask);
//		redirectAttributes.addFlashAttribute("message", "创建任务成功");
//		return "redirect:/task/";
//	}
//
//	@RequestMapping(value = "update/{id}", method = RequestMethod.GET)
//	public String updateForm(@PathVariable("id") Long id, Model model) {
//		model.addAttribute("task", taskService.getTask(id));
//		model.addAttribute("action", "update");
//		return "task/taskForm";
//	}
//
//	@RequestMapping(value = "update", method = RequestMethod.POST)
//	public String update(@Valid @ModelAttribute("preloadTask") RmMessageVo task, RedirectAttributes redirectAttributes) {
//		taskService.saveTask(task);
//		redirectAttributes.addFlashAttribute("message", "更新任务成功");
//		return "redirect:/task/";
//	}
//
//	@RequestMapping(value = "delete/{id}")
//	public String delete(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
//		taskService.deleteTask(id);
//		redirectAttributes.addFlashAttribute("message", "删除任务成功");
//		return "redirect:/task/";
//	}
//
//	/**
//	 * 使用@ModelAttribute, 实现Struts2 Preparable二次部分绑定的效果,先根据form的id从数据库查出Task对象,再把Form提交的内容绑定到该对象上。
//	 * 因为仅update()方法的form中有id属性，因此本方法在该方法中执行.
//	 */
//	@ModelAttribute("preloadTask")
//	public RmMessageVo getTask(@RequestParam(value = "id", required = false) Long id) {
//		if (id != null) {
//			return taskService.getTask(id);
//		}
//		return null;
//	}
//
//	/**
//	 * 取出Shiro中的当前用户Id.
//	 */
//	private Long getCurrentUserId() {
//		ShiroUser user = (ShiroUser) SecurityUtils.getSubject().getPrincipal();
//		return user.id;
//	}
	
    /**
     * 功能: 从request中获得查询条件
     *
     * @param request
     * @return
     */
    private String getQueryCondition(ServletRequest request) {
        String queryCondition = null;
        if(request.getAttribute(REQUEST_QUERY_CONDITION) != null) {  //如果request.getAttribute中有，就不取request.getParameter
            queryCondition = request.getAttribute(REQUEST_QUERY_CONDITION).toString();
        } else {
    		List<String> lQuery = new ArrayList<String>();
    		lQuery.add(RmSqlHelper.buildQueryStr(TABLE_NAME + ".bs_keyword", request.getParameter("bs_keyword"), RmSqlHelper.TYPE_CHAR_LIKE));
    		
    		lQuery.add(RmSqlHelper.buildQueryStr(TABLE_NAME + ".record_id", request.getParameter("record_id"), RmSqlHelper.TYPE_CHAR_LIKE));
    		
    		lQuery.add(RmSqlHelper.buildQueryStr(TABLE_NAME + ".order_number", request.getParameter("order_number_from"), RmSqlHelper.TYPE_NUMBER_GREATER_EQUAL));
    		lQuery.add(RmSqlHelper.buildQueryStr(TABLE_NAME + ".order_number", request.getParameter("order_number_to"), RmSqlHelper.TYPE_NUMBER_LESS_EQUAL));

    		lQuery.add(RmSqlHelper.buildQueryStr(TABLE_NAME + ".title", request.getParameter("title"), RmSqlHelper.TYPE_CHAR_LIKE));
    		
    		lQuery.add(RmSqlHelper.buildQueryStr(TABLE_NAME + ".old_name", request.getParameter("old_name"), RmSqlHelper.TYPE_CHAR_LIKE));
    		
    		lQuery.add(RmSqlHelper.buildQueryStr(TABLE_NAME + ".save_name", request.getParameter("save_name"), RmSqlHelper.TYPE_CHAR_LIKE));
    		
    		lQuery.add(RmSqlHelper.buildQueryStr(TABLE_NAME + ".save_size", request.getParameter("save_size_from"), RmSqlHelper.TYPE_NUMBER_GREATER_EQUAL));
    		lQuery.add(RmSqlHelper.buildQueryStr(TABLE_NAME + ".save_size", request.getParameter("save_size_to"), RmSqlHelper.TYPE_NUMBER_LESS_EQUAL));
    		
    		lQuery.add(RmSqlHelper.buildQueryStr(TABLE_NAME + ".mime_type", request.getParameter("mime_type"), RmSqlHelper.TYPE_CHAR_EQUAL));
    		
    		lQuery.add(RmSqlHelper.buildQueryStr(TABLE_NAME + ".encoding", request.getParameter("encoding"), RmSqlHelper.TYPE_CHAR_LIKE));
    		
    		lQuery.add(RmSqlHelper.buildQueryStr(TABLE_NAME + ".description", request.getParameter("description"), RmSqlHelper.TYPE_CHAR_LIKE));
    		
    		lQuery.add(RmSqlHelper.buildQueryStr(TABLE_NAME + ".author", request.getParameter("author"), RmSqlHelper.TYPE_CHAR_LIKE));
    		
    		lQuery.add(RmSqlHelper.buildQueryStr(TABLE_NAME + ".modify_date", request.getParameter("modify_date_from"), RmSqlHelper.TYPE_DATE_GREATER_EQUAL));
    		lQuery.add(RmSqlHelper.buildQueryStr(TABLE_NAME + ".modify_date", request.getParameter("modify_date_to"), RmSqlHelper.TYPE_DATE_LESS_EQUAL));
    		
    		queryCondition = RmSqlHelper.appendQueryStr(lQuery.toArray(new String[0]));
        }
        return queryCondition;
    }
    
    /**
     * 得到缓存中查询条件对应的count(*)记录数，如返回-1则执行查询
     * 
     * @param queryCondition
     * @return
     */
    private int getCount(String queryCondition) {
    	int count = RmSqlCountCache.getCount(TABLE_NAME, queryCondition);
    	if(count < 0) {
//    		count = rmMessageService.getRecordCount(queryCondition);
    		RmSqlCountCache.putCount(TABLE_NAME, queryCondition, count);
    	}
    	return count;
    }
    
}
