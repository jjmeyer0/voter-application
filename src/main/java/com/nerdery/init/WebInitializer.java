package com.nerdery.init;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

public class WebInitializer implements WebApplicationInitializer {
    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        AnnotationConfigWebApplicationContext ctx = new AnnotationConfigWebApplicationContext();
        ctx.register(PersistenceConfiguration.class);
        ctx.setServletContext(servletContext);

        ServletRegistration.Dynamic dynamicServlet = servletContext.addServlet("dispatcher", new DispatcherServlet(ctx));
        dynamicServlet.addMapping("/");
        dynamicServlet.setLoadOnStartup(1);
    }
}
