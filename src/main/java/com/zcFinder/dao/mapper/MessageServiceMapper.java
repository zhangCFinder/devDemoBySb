package com.zcFinder.dao.mapper;

import com.zcFinder.dao.model.Message;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by zhangc on 2020/5/8.
 */
@Repository
public interface MessageServiceMapper {

	Message getMessageById(@Param("msgId") Long msgId);

	List<Message> getAllMessages();

	Integer saveMessage(Message message);

	Integer updateMessage(Message message);

	Integer updateMessageText(Message message);

	void deleteMessage(Long msgId);
}
