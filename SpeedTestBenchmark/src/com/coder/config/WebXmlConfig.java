package com.coder.config;

import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

public class WebXmlConfig implements WebApplicationInitializer {
	private String TMP_FOLDER = "/tmp";
	private int MAX_UPLOAD_SIZE = 500 * 1024 * 1024;

	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {


		System.out.println("WebXml with ApplicationInitaizer--->1");
        
		AnnotationConfigWebApplicationContext webContext = new AnnotationConfigWebApplicationContext();
		webContext.register(SpringDSXmlConfig.class);
		

		// join to the spring configurationspring.xml
		System.out.println("WebXml with ApplicationInitaizer--->2");

		ServletRegistration.Dynamic sreg = servletContext.addServlet(// <servlet><servlet-mapping>
				"SpringDispatcher", // <servlet-name>
				new DispatcherServlet(webContext)// <servlet-class>
		);
		
		MultipartConfigElement multipartConfigElement = new MultipartConfigElement(TMP_FOLDER, MAX_UPLOAD_SIZE,
				MAX_UPLOAD_SIZE * 2, MAX_UPLOAD_SIZE / 2);

		sreg.setMultipartConfig(multipartConfigElement);
		sreg.setLoadOnStartup(1);// <load-on-startup>
		sreg.addMapping("/");// <url-pattern>

		sreg.setInitParameter("contextClass", webContext.getClass().getName());// <init-oarm
		servletContext.addListener(new ContextLoaderListener(webContext));

		// <listener-class>
		System.out.println("WebXml with ApplicationInitaizer--->3");
	}

}
