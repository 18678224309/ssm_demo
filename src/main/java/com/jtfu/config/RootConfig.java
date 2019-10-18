package com.jtfu.config;


import com.alibaba.druid.pool.DruidDataSource;
import com.baomidou.mybatisplus.core.MybatisConfiguration;
import com.baomidou.mybatisplus.extension.handlers.EnumTypeHandler;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PerformanceInterceptor;
import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import org.apache.ibatis.logging.stdout.StdOutImpl;
import org.apache.ibatis.plugin.Interceptor;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import javax.annotation.Resource;
import javax.annotation.Resources;
import javax.sql.DataSource;
import java.io.IOException;
import java.util.Properties;


@ComponentScan(basePackages = {"com.jtfu"})
@MapperScan("com.jtfu.mapper")
@PropertySource("classpath:db.properties")
public class RootConfig {

    @Value("${db.url}")
    String Url;
    @Value("${db.driver}")
    String Driver;
    @Value("${db.passowrd}")
    String Password;
    @Value("${db.username}")
    String Username;
    @Bean
    public DataSource dataSource(){
        DruidDataSource dataSource=new DruidDataSource();
        dataSource.setUrl(Url);
        dataSource.setDriverClassName(Driver);
        dataSource.setPassword(Password);
        dataSource.setUsername(Username);
        return dataSource;
    }

    /*@Bean
    public SqlSessionFactoryBean sessionFactoryBean(){
        SqlSessionFactoryBean sqlSessionFactoryBean=new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource());
        return sqlSessionFactoryBean;
    }*/

    @Bean
    public MybatisSqlSessionFactoryBean sessionFactoryBean()throws IOException {
        MybatisSqlSessionFactoryBean factoryBean=new MybatisSqlSessionFactoryBean();
        MybatisConfiguration configuration=new MybatisConfiguration();
        configuration.setDefaultEnumTypeHandler(EnumTypeHandler.class);
        configuration.setLogImpl(StdOutImpl.class);
        configuration.setCacheEnabled(true);
        factoryBean.setConfiguration(configuration);
        factoryBean.setDataSource(dataSource());
        factoryBean.setPlugins(new Interceptor[]{paginationInterceptor()});
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        String packageXMLConfigPath = PathMatchingResourcePatternResolver.CLASSPATH_ALL_URL_PREFIX + "/mapper/*/*.xml";
        System.err.println(packageXMLConfigPath);
        factoryBean.setMapperLocations(resolver.getResources(packageXMLConfigPath));
        return factoryBean;
    }

 @Bean
    public PaginationInterceptor paginationInterceptor(){
        PaginationInterceptor paginationInterceptor=new PaginationInterceptor();
        paginationInterceptor.setDialectType("mysql");
        return paginationInterceptor;
    }
     /*  @Bean
    public PerformanceInterceptor performanceInterceptor(){
        //格式化sql语句
        PerformanceInterceptor performanceInterceptor =new PerformanceInterceptor();
        Properties properties =new Properties();
        properties.setProperty("format", "false");
        performanceInterceptor.setProperties(properties);
        return performanceInterceptor;
    }*/
}
