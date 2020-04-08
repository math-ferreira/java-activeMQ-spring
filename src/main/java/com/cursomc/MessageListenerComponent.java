package com.cursomc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Component
public class MessageListenerComponent implements ApplicationRunner {

    @Autowired private JmsTemplate jmsTemplate;
    @Autowired private JmsTemplate jmsTemplateTopic;


    @JmsListener(destination = "MATSSF-QUEUE-EXAMPLE")
    public void onReceiverQueue(String str) {
        System.out.println("Message received: " + str);
    }

    @JmsListener(destination = "topic.sample", containerFactory = "jmsFactoryTopic")
    public void onReceiverTopic(String str) {
        System.out.println(str);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
    	// Test send message to Queue and Topic
        jmsTemplate.convertAndSend("MATSSF-QUEUE-EXAMPLE", "{user: 'matssf', usando: 'fila'}");
        jmsTemplateTopic.convertAndSend("topic.sample", "{user: 'matssf', usando: 'tópico'}");
    }

}