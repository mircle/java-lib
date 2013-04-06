package org.quickbundle.modules.message.rest;

import java.util.List;

import org.quickbundle.modules.message.service.RmMessageService;
import org.quickbundle.modules.message.vo.RmMessageVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Task的Restful API的Controller.
 * 
 * @author calvin
 */
@Controller
@RequestMapping(value = "/api/v1/task")
public class RmMessageRestController {

	@Autowired
	private RmMessageService rmMessageService;

//	@Autowired
//	private Validator validator;

	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<RmMessageVo> list() {
		return rmMessageService.list(null, null);
	}
//
//	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
//	@ResponseBody
//	public ResponseEntity<?> get(@PathVariable("id") Long id) {
//		RmMessageVo task = taskService.getTask(id);
//		if (task == null) {
//			return new ResponseEntity(HttpStatus.NOT_FOUND);
//		}
//		return new ResponseEntity(task, HttpStatus.OK);
//	}
//
//	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
//	@ResponseBody
//	public ResponseEntity<?> create(@RequestBody RmMessageVo task, UriComponentsBuilder uriBuilder) {
//		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
//		Set<ConstraintViolation<RmMessageVo>> failures = validator.validate(task);
//		if (!failures.isEmpty()) {
//			return new ResponseEntity(BeanValidators.extractPropertyAndMessage(failures), HttpStatus.BAD_REQUEST);
//		}
//
//		//保存任务
//		taskService.saveTask(task);
//
//		//按照Restful风格约定，创建指向新任务的url, 也可以直接返回id或对象.
//		Long id = task.getId();
//		URI uri = uriBuilder.path("/api/v1/task/" + id).build().toUri();
//		HttpHeaders headers = new HttpHeaders();
//		headers.setLocation(uri);
//
//		return new ResponseEntity(headers, HttpStatus.CREATED);
//	}
//
//	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
//	public ResponseEntity<?> update(@RequestBody RmMessageVo task) {
//		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
//		Set<ConstraintViolation<RmMessageVo>> failures = validator.validate(task);
//		if (!failures.isEmpty()) {
//			return new ResponseEntity(BeanValidators.extractPropertyAndMessage(failures), HttpStatus.BAD_REQUEST);
//		}
//
//		//保存
//		taskService.saveTask(task);
//
//		//按Restful约定，返回204状态码, 无内容. 也可以返回200状态码.
//		return new ResponseEntity(HttpStatus.NO_CONTENT);
//	}
//
//	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
//	@ResponseStatus(HttpStatus.NO_CONTENT)
//	public void delete(@PathVariable("id") Long id) {
//		taskService.deleteTask(id);
//	}
}
