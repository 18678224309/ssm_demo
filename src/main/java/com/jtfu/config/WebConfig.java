package com.jtfu.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import org.springframework.context.annotation.ImportResource;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import javax.annotation.Resource;
import java.util.Properties;

@Configuration
@EnableWebMvc
@ComponentScan("com.jtfu.controller")
/*@ImportResource("classpath:springmvc.xml")*/
public class WebConfig extends WebMvcConfigurerAdapter implements WebMvcConfigurer {
 
    //配置视图解析器
   /* @Bean
    public ViewResolver viewResolver(){
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/WEB-INF/page/");
        resolver.setSuffix(".html");
        resolver.setExposeContextBeansAsAttributes(true);
        resolver.setOrder(1);
        return  resolver;
    }*/
    /*@Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("http://localhost:8080/ssm_Demo1/login.html");
    }*/
 
    /*配置静态资源的处理*/
    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }



    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        registry.enableContentNegotiation(new MappingJackson2JsonView());
        registry.freeMarker().cache(false);
    }

    @Bean
    public FreeMarkerConfigurer freeMarkerConfigurer() {
        FreeMarkerConfigurer configurer = new FreeMarkerConfigurer();
        configurer.setTemplateLoaderPath("/WEB-INF/page/");
        configurer.setDefaultEncoding("utf-8");
        Properties properties=new Properties();
        properties.setProperty("template_update_delay","0");
        properties.setProperty("datetime_format","yyyy-MM-dd HH:mm:ss");
        properties.setProperty("locale","zh_CN");
        properties.setProperty("number_format","#");
        configurer.setFreemarkerSettings(properties);
        return configurer;
    }
    @Bean
    public ViewResolver FreeMarkerViewResolver(){
        FreeMarkerViewResolver resolver = new FreeMarkerViewResolver();
        resolver.setSuffix(".html");
        // resolver.setExposeContextBeansAsAttributes(true);
        resolver.setContentType("text/html; charset=UTF-8");
       // resolver.setOrder(2);
        return  resolver;
    }


}