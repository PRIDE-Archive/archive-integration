package uk.ac.ebi.pride.integration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author Rui Wang
 * @version $Id$
 */
public class AllIntegrationApp {
    public static final Logger logger = LoggerFactory.getLogger(AllIntegrationApp.class);

    private AllIntegrationApp() {}


    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:META-INF/spring/integration/*.xml");
        context.registerShutdownHook();
    }
}
