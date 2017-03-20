package com.spirit.project.sysmgr.ui.config;

import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.web.servlet.ErrorPage;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		/**
		 * 404、500页面配置
		 */
		registry.addViewController("/error/404").setViewName("common/error/404");
		registry.addViewController("/error/500").setViewName("common/error/500");
		
		/**
		 * 系统管理页面配置
		 */
		registry.addViewController("/user").setViewName("user/userlist");
		registry.addViewController("/edituser").setViewName("user/userdetail");
		registry.addViewController("/authority").setViewName("authority/authoritymanager");
		registry.addViewController("/role").setViewName("role/rolelist");
		registry.addViewController("/editrole").setViewName("role/roledetail");
		registry.addViewController("/servicemodule").setViewName("servicemodule/servicemodulelist");
		registry.addViewController("/editservicemodule").setViewName("servicemodule/servicemoduledetail");
		registry.addViewController("/menu").setViewName("resource/resourcemanager");
	}
	
	@Bean
    public EmbeddedServletContainerCustomizer containerCustomizer(){
        return new EmbeddedServletContainerCustomizer(){
            @Override
            public void customize(ConfigurableEmbeddedServletContainer container) {
                container.addErrorPages(new ErrorPage(HttpStatus.NOT_FOUND, "/error/404"));
                container.addErrorPages(new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR, "/error/500"));
                container.addErrorPages(new ErrorPage(java.lang.Throwable.class,"/error/500"));
            }
        };
    }
}
