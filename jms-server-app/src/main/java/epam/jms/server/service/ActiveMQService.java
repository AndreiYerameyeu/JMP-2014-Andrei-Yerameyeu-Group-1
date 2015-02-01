package epam.jms.server.service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import javax.jms.Connection;
import javax.jms.DeliveryMode;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class ActiveMQService {
    
    private static final Logger LOG = LoggerFactory.getLogger(ActiveMQService.class);
    
    private static ActiveMQService instance;
    private static final Lock lock = new ReentrantLock();
    
    private static final String MAIN_TOPIC_NAME = "MainTopic";
    
    private final ActiveMQConnectionFactory connectionFactory;
    private final List<String> messages = new ArrayList<String>();
    
    private ActiveMQService() {
        connectionFactory = new ActiveMQConnectionFactory(ActiveMQConnection.DEFAULT_USER, ActiveMQConnection.DEFAULT_PASSWORD, "failover://tcp://localhost:61616");
        Connection connection;
        try {
            connection = connectionFactory.createConnection();
            
            connection.start();
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            
            Topic destination = getDestinationTopic(session);
            
            MessageConsumer consumer = session.createConsumer(destination);
            consumer.setMessageListener(new MessageListener() {
                
                @Override
                public void onMessage(Message msg) {
                    LOG.debug("Received message: " + msg);
                    TextMessage textmessage = (TextMessage)msg;
                    try {
                        messages.add(textmessage.getText());
                    } catch (JMSException e) {
                        LOG.error("Can't get Test", e);
                    }
                }
            });
            
        } catch (JMSException e) {
            LOG.error("Can't Register asynchronius listener", e);
        }
    }
    
    public static ActiveMQService getInstance() {
        if (null == instance) {
            lock.lock();
            try {
                if (null == instance) {
                    instance = new ActiveMQService();
                }
            } finally {
                lock.unlock();
            }
        }
        return instance;
    }
    
    public boolean send(Object msg) {
        if (null != msg) {
            try {
                Session session = getSession(getConnection());
                Topic topic = getDestinationTopic(session);
                MessageProducer producer = session.createProducer(topic);
                producer.setDeliveryMode(DeliveryMode.PERSISTENT);
                LOG.info("Sending " + msg.toString());
                TextMessage message = session.createTextMessage(msg.toString());
                producer.send(message);
                return true;
            } catch (JMSException e) {
                LOG.error("Can't send message " + msg, e);
                e.printStackTrace();
            }
        }
        return false;
    }
    
    public List<String> receive() {
        return messages;
    }
    
    private Connection getConnection() throws JMSException {
        return connectionFactory.createConnection();
    }
    
    private Session getSession(Connection con) throws JMSException {
        return con.createSession(false, Session.AUTO_ACKNOWLEDGE);
    }
    
    private Topic getDestinationTopic(Session session) throws JMSException {
        return session.createTopic(MAIN_TOPIC_NAME);
    }
    
}
