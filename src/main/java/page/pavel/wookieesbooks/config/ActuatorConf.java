package page.pavel.wookieesbooks.config;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * Spring Boot Actuator Controller
 */
@Controller
public class ActuatorConf {

    /**
     * Actuator Config
     */
    @ResponseBody
    @RequestMapping(path = "/actuator")
    public String home(HttpServletRequest request) {

        String contextPath = request.getContextPath();
        String host = request.getServerName();

        String endpointBasePath = "/actuator";

        StringBuilder sb = new StringBuilder();

        sb.append("<h2>Sprig Boot Actuator</h2>");
        sb.append("<ul>");

        String url = "http://" + host + ":8080" + contextPath + endpointBasePath;

        sb.append("<li><a href='" + url + "'>" + url + "</a></li>");

        sb.append("</ul>");

        return sb.toString();
    }
}
