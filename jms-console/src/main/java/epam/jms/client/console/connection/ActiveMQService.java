package epam.jms.client.console.connection;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import javax.jms.Connection;
import javax.jms.DeliveryMode;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.pool.PooledConnectionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class ActiveMQService {
    
    private static final Logger LOG = LoggerFactory.getLogger(ActiveMQService.class);
    
    private static ActiveMQService instance;
    private static final Lock lock = new ReentrantLock();
    
    private static final String MAIN_TOPIC_NAME = "MainTopic";
    
    private final PooledConnectionFactory connectionFactory;
    
    private ActiveMQService() {
        connectionFactory = new PooledConnectionFactory(new ActiveMQConnectionFactory(ActiveMQConnection.DEFAULT_USER, ActiveMQConnection.DEFAULT_PASSWORD, "failover://tcp://localhost:61616"));
        connectionFactory.initConnectionsPool();
        connectionFactory.setMaxConnections(5);
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
