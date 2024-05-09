package vn.devpro.javaweb27.configurer;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import vn.devpro.javaweb27.dto.Jw27Constant;

@Configuration
public class MvcConfigurer implements WebMvcConfigurer, Jw27Constant {

	@Bean
	public ViewResolver viewResolver() {
		InternalResourceViewResolver bean = new InternalResourceViewResolver();
		bean.setViewClass(JstlView.class);
		bean.setPrefix("/WEB-INF/views/");
		bean.setSuffix(".jsp");
		return bean;
	}

	// Dang ky thu muc chua resource file (css, js, img, ...)
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/frontend/**").addResourceLocations("classpath:/frontend/");
		registry.addResourceHandler("/backend/**").addResourceLocations("classpath:/backend/");
		registry.addResourceHandler("/login/**").addResourceLocations("classpath:/login/");
		registry.addResourceHandler("/FileUploads/**").addResourceLocations("file:" + FOLDER_UPLOAD);
	}
	
}
