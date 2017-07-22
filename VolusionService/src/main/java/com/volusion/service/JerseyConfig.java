package com.volusion.service;

import javax.inject.Inject;
import javax.servlet.ServletContext;

import org.glassfish.jersey.filter.LoggingFilter;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.ServerProperties;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.filter.RequestContextFilter;

import com.volusion.service.rest.v1.OrderResource;
import com.volusion.service.rest.v1.ProductResource;

/**
 * Class to register Jersey configuration and resource/endpoint classes.
 * @author shankar meenkeri
 *
 */
public class JerseyConfig extends ResourceConfig {

    @Inject
    public JerseyConfig(ServletContext servletContext) {
        WebApplicationContext springFactory = WebApplicationContextUtils.getWebApplicationContext(servletContext);
        register(RequestContextFilter.class);
        register(springFactory.getBean(ProductResource.class));
        register(springFactory.getBean(OrderResource.class));
        register(LoggingFilter.class);
        property(ServerProperties.BV_SEND_ERROR_IN_RESPONSE, true);
        property(ServerProperties.MOXY_JSON_FEATURE_DISABLE, true);
        property(ServerProperties.JSON_PROCESSING_FEATURE_DISABLE, false);

        register(JacksonFeature.class);
        setApplicationName("volusion-service-application");
    }
}
