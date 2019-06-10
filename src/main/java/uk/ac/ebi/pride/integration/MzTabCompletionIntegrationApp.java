package uk.ac.ebi.pride.integration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author Rui Wang
 * @version $Id$
 */
public class MzTabCompletionIntegrationApp {
    public static final Logger logger = LoggerFactory.getLogger(MzTabCompletionIntegrationApp.class);

    private MzTabCompletionIntegrationApp() {}


    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:META-INF/spring/integration/mztab-integration-context.xml",
                                                                                    "classpath:META-INF/spring/integration/redis-context.xml",
                                                                                    "classpath:META-INF/spring/integration/common-context.xml");
        context.registerShutdownHook();
    }
}
