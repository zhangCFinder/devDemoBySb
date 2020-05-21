package com.zcFinder.service;

import com.zcFinder.dao.model.Message;

import java.util.List;

/**
 * Created by zhangc on 2020/5/8.
 */
public interface MessageService {
	Message getMessageById(Long msgID);

	List<Message> getAllMessages();

	Integer saveMessage(Message message);

	Integer updateMessage(Message message);

	Integer updateMessageText(Message message);

	void deleteMessage(Long msgId);
}
