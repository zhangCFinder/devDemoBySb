package com.zcFinder.service.impl;

import com.zcFinder.dao.mapper.MessageServiceMapper;
import com.zcFinder.dao.model.Message;
import com.zcFinder.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by zhangc on 2020/5/8.
 */
@Service
public class MessageServiceImpl implements MessageService {

	@Autowired
	private MessageServiceMapper messageServiceMapper;
	@Override
	public Message getMessageById(Long msgID) {
		Message message= messageServiceMapper.getMessageById(msgID);
		return message;
	}

	@Override
	public List<Message> getAllMessages() {
		List<Message> list = messageServiceMapper.getAllMessages();
		return list;
	}

	@Override
	public Integer saveMessage(Message message) {
		Integer resultLineNum = messageServiceMapper.saveMessage(message);
		return resultLineNum;
	}

	@Override
	public Integer updateMessage(Message message) {
		Integer resultLineNum = messageServiceMapper.updateMessage(message);
		return resultLineNum;
	}

	@Override
	public Integer updateMessageText(Message message) {
		Integer resultLineNum = messageServiceMapper.updateMessageText(message);
		return resultLineNum;
	}

	@Override
	public void deleteMessage(Long msgId) {
		messageServiceMapper.deleteMessage(msgId);
		System.out.println("删除信息成功");
	}
}
