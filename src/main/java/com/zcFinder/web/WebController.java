package com.zcFinder.web;

import com.zcFinder.common.util.MultipleDataSource.DataSourceContextHolder;
import com.zcFinder.dao.model.Message;
import com.zcFinder.service.MessageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by zhangc on 2020/5/8.
 */
@RestController
@RequestMapping("/Index")
@Scope("prototype")
@Slf4j
public class WebController {

	@Autowired
	private MessageService messageService;


	@GetMapping("/message")

	public List<Message> getAllMessages(){
		DataSourceContextHolder.switchDataSource("master");
		List<Message> messages = messageService.getAllMessages();
//		redisUtil.getRedisTemplate().opsForValue().set();
		return messages;
	}

	@GetMapping(value = "/message/{msgId}")
	public Message getMessageById(@PathVariable Long msgId) {
		DataSourceContextHolder.switchDataSource("slave");
		Message messages = messageService.getMessageById(msgId);
		return messages;
	}
	@GetMapping(value = "/requestInfo")
	public String getRequestInfo(HttpServletRequest request) {
		ServletContext application = request.getServletContext();
		return (String) application.getAttribute("user");
	}


	@PostMapping(value = "/message")
	public Integer create(Message message) {
		Integer resultLineNum = messageService.saveMessage(message);
		return resultLineNum;
	}

	@PutMapping(value = "/message")
	public Integer modify(Message message) {
		Integer resultLineNum = messageService.updateMessage(message);
		return resultLineNum;
	}

	@PatchMapping(value = "/message")
	public Integer patch(Message message) {
		Integer resultLineNum = messageService.updateMessageText(message);
		return resultLineNum;
	}

	@DeleteMapping(value = "/message/{msgId}")
	public void patch(@PathVariable Long msgId) {
		messageService.deleteMessage(msgId);
	}
}
