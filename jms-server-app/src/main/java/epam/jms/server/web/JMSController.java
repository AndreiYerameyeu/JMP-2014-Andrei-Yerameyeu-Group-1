package epam.jms.server.web;

import static spark.Spark.get;
import static spark.Spark.setPort;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.List;

import spark.Request;
import spark.Response;
import spark.Route;
import epam.jms.server.service.ActiveMQService;
import freemarker.template.Configuration;
import freemarker.template.SimpleHash;
import freemarker.template.Template;
import freemarker.template.TemplateException;

public class JMSController {
    
    private final ActiveMQService jmsService;
    private final Configuration cfg;
    
    public static void main(String[] args) throws IOException {
        new JMSController();
    }
    
    public JMSController() throws IOException {
        jmsService = ActiveMQService.getInstance();
        cfg = createFreemarkerConfiguration();
        setPort(8082);
        initializeRoutes();
    }
    
    abstract class FreemarkerBasedRoute extends Route {
        
        final Template template;
        
        protected FreemarkerBasedRoute(final String path, final String templateName) throws IOException {
            super(path);
            template = cfg.getTemplate(templateName);
        }
        
        @Override
        public Object handle(Request request, Response response) {
            StringWriter writer = new StringWriter();
            try {
                doHandle(request, response, writer);
            } catch (Exception e) {
                e.printStackTrace();
                response.redirect("/internal_error");
            }
            return writer;
        }
        
        protected abstract void doHandle(final Request request, final Response response, final Writer writer) throws IOException, TemplateException;
        
    }
    
    private void initializeRoutes() throws IOException {
        get(new FreemarkerBasedRoute("/", "home.ftl") {
            
            @Override
            public void doHandle(Request request, Response response, Writer writer) throws IOException, TemplateException {
                List<String> messages = jmsService.receive();
                SimpleHash root = new SimpleHash();
                
                root.put("messages", messages);
                
                template.process(root, writer);
            }
        });
        
        get(new FreemarkerBasedRoute("/internal_error", "error_template.ftl") {
            
            @Override
            protected void doHandle(Request request, Response response, Writer writer) throws IOException, TemplateException {
                SimpleHash root = new SimpleHash();
                
                root.put("error", "System has encountered an error.");
                template.process(root, writer);
            }
        });
    }
    
    private Configuration createFreemarkerConfiguration() {
        Configuration retVal = new Configuration();
        retVal.setClassForTemplateLoading(JMSController.class, "/freemarker");
        return retVal;
    }
}
