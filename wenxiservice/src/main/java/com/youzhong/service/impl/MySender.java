package com.youzhong.service.impl;

import com.youzhong.mes.ObjectMessage;
import com.youzhong.mes.TextMessage;
import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

@Service
public class MySender {

    @Autowired
    private JmsTemplate jmsTemplate;
    @Autowired
    private ActiveMQTopic activeMQTopic;

    //推送文本消息
    public void sendTextMes(String topicName,String text){
       activeMQTopic.setPhysicalName(topicName);
        TextMessage textMessage = new TextMessage(text);
        jmsTemplate.send(activeMQTopic,textMessage);
    }

    public void sendObjectMes(String topicName, Object object){
        activeMQTopic.setPhysicalName(topicName);
        ObjectMessage objectMessage = new ObjectMessage(object);
        jmsTemplate.send(topicName, objectMessage);
    }

}
