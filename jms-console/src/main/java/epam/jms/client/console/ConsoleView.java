package epam.jms.client.console;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import epam.jms.client.console.connection.ActiveMQService;

public class ConsoleView {
    
    private static final Logger LOG = LoggerFactory.getLogger(ConsoleView.class);
    
    public static void main(String[] args) {
        ActiveMQService service = ActiveMQService.getInstance();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            while (true) {
                String msg = reader.readLine();
                service.send(msg);
            }
        } catch (IOException e) {
            LOG.error("Can't read from console", e);
        }
        
        
    }
    
}
