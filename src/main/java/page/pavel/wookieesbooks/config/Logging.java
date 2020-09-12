package page.pavel.wookieesbooks.config;

import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;

/**
 * Logging template
 */
public class Logging {

    private long start;
    private final Logger logger;

    public Logging(Logger logger) {
        this.logger = logger;
    }

    public void requestStart(HttpServletRequest request) {

        long start = System.currentTimeMillis();
        logger.info("!--REQUEST START--!");

        logger.info("Request URL: " + request.getRequestURL().toString());

        List<String> requestParameterNames = Collections.list((Enumeration<String>) request.getParameterNames());
        logger.info("Parameter number: " + requestParameterNames.size());

        for (String parameterName : requestParameterNames) {
            logger.info("Parameter name: " + parameterName + " - Parameter value: " + request.getParameter(parameterName));
        }

        this.start = start;
    }

    public void requestStop() {

        long end = System.currentTimeMillis();
        logger.info("Requested completed in: " + (end - start) + "ms");
        logger.info("!--REQUEST END--!");
    }

    public void requestStop(Object result) {

        logger.info("Response is: " + result.toString());

        long end = System.currentTimeMillis();
        logger.info("Requested completed in: " + (end - start) + "ms");
        logger.info("!--REQUEST END--!");
    }
}
