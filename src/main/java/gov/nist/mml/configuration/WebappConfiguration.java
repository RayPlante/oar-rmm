/**
 * This software was developed at the National Institute of Standards and Technology by employees of
 * the Federal Government in the course of their official duties. Pursuant to title 17 Section 105
 * of the United States Code this software is not subject to copyright protection and is in the
 * public domain. This is an experimental system. NIST assumes no responsibility whatsoever for its
 * use by other parties, and makes no guarantees, expressed or implied, about its quality,
 * reliability, or any other characteristic. We would appreciate acknowledgement if the software is
 * used. This software can be redistributed and/or modified freely provided that any derivative
 * works bear some notice that they are derived from it, and any modified versions bear some notice
 * that they have been modified.
 * @author: Deoyani Nandrekar-Heinis
 */
package gov.nist.mml.configuration;

import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.data.rest.webmvc.config.RepositoryRestMvcConfiguration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.handler.SimpleUrlHandlerMapping;


@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "gov.nist.mml")
@Import(RepositoryRestMvcConfiguration.class)
public class WebappConfiguration extends WebMvcConfigurerAdapter{
	/*private Logger logger = LoggerFactory.getLogger(WebappConfiguration.class);
	public WebappConfiguration(){
		logger.info("Hello Config");
	}*/
	
//	//map static resources by extension
//    @Bean
//    public SimpleUrlHandlerMapping resourceServletMapping() {
//        SimpleUrlHandlerMapping mapping = new SimpleUrlHandlerMapping();
//
//        //make sure static resources are mapped first since we are using
//        //a slightly different approach
//        mapping.setOrder(0);
//        Properties urlProperties = new Properties();
//         urlProperties.put("/**/*.html", "defaultServletHttpRequestHandler");
//       mapping.setMappings(urlProperties);
//        return mapping;
//    }
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		
	    registry.addResourceHandler("swagger-ui.html")
	      .addResourceLocations("classpath:/META-INF/resources/");
	 
	    registry.addResourceHandler("apidoc.html")
	      .addResourceLocations("classpath:/");
	    
	    registry.addResourceHandler("/webjars/**")
	      .addResourceLocations("classpath:/META-INF/resources/webjars/");
	    
	}
	
	
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**");
	}
	
}
