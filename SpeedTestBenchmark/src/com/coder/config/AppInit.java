package com.coder.config;


import java.util.EnumSet;

import javax.servlet.DispatcherType;
import javax.servlet.FilterChain;
import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

@Configuration
public class AppInit extends AbstractAnnotationConfigDispatcherServletInitializer {

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        addEncodingFilter(servletContext);
        super.onStartup(servletContext);
    }



private void addEncodingFilter(ServletContext servletContext) {
        CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
        characterEncodingFilter.setEncoding("UTF-8");
        characterEncodingFilter.setForceEncoding(true);

        EnumSet<DispatcherType> dispatcherTypes
                = EnumSet.of(DispatcherType.REQUEST, DispatcherType.FORWARD, DispatcherType.ERROR);

        FilterRegistration.Dynamic characterEncoding
                = servletContext.addFilter("characterEncoding", characterEncodingFilter);
        characterEncoding.addMappingForUrlPatterns(dispatcherTypes, true, "/*");
        characterEncoding.setAsyncSupported(true);
        

    }



@Override
protected Class<?>[] getRootConfigClasses() {
	// TODO Auto-generated method stub
	return null;
}



@Override
protected Class<?>[] getServletConfigClasses() {
	   return new Class[] {WebXmlConfig.class};
}



@Override
protected String[] getServletMappings() {
	
	return new String[] {"/"};
}
}