package com.coder.config;


import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

@Configuration
@EnableWebMvc
@EnableScheduling
public class SpringMvcXmlConfig implements WebMvcConfigurer{ 

	//<bean class="---InternalResourceViewResolver <prefix><suffix><viewClass
		@Bean(name="viewResolver")//<bean>
		public InternalResourceViewResolver getViewResolver(){
			System.out.println("SpringMVCXML COnifg--->prepare view resolver");
			InternalResourceViewResolver rv=new InternalResourceViewResolver();// class
				rv.setPrefix("/WEB-INF/views/");//<prefix
				rv.setSuffix(".jsp");//<suffix
				rv.setViewClass(JstlView.class);//viewClass
			return rv;
		}

		@Bean(name = "multipartResolver")
		public CommonsMultipartResolver multipartResolver() {
		    CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
		    multipartResolver.setMaxUploadSize(500000000);
		    return multipartResolver;
		}
		
		@Bean
		public MessageSource messageSource() {
		    ResourceBundleMessageSource source = new ResourceBundleMessageSource();
		    source.setBasename("i18n/messages");
		    source.setDefaultEncoding("UTF-8");
		    return source;
		}
		
		@Override
	    public void addResourceHandlers(ResourceHandlerRegistry registry) {
	        registry
	          .addResourceHandler("/resources/**")
	          .addResourceLocations("/resources/"); 
	    }
	
}
