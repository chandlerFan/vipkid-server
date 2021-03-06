package com.quxueyuan.server.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.quxueyuan.server.config.filter.CloseApiFilter;
import com.quxueyuan.server.config.filter.SellerLoginFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.web.servlet.MultipartProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import javax.servlet.MultipartConfigElement;

/**
 * @author liuwei
 * @time 2018/6/26 10:30
 * @description 应用配置类
 */
@Configuration
@EnableTransactionManagement
public class AppConfig {

    @Autowired
    private MultipartProperties multipartProperties;
    @Bean
    public ThreadPoolTaskExecutor taskExecutor() {
        ThreadPoolTaskExecutor pool = new ThreadPoolTaskExecutor();
        // 线程池维护线程的最少数量
        pool.setCorePoolSize(5);
        // 线程池维护线程的最大数量
        pool.setMaxPoolSize(2000);
        // 当调度器shutdown被调用时等待当前被调度的任务完成
        pool.setWaitForTasksToCompleteOnShutdown(true);
        return pool;
    }
    /**
     * 设置tomcat上传文件最大为10M
     * tomcat的默认上传文件大小为1M
     * 超出则会抛异常
     * @return
     */
    @Bean
    @ConditionalOnMissingBean
    public MultipartConfigElement multipartConfigElement() {
        this.multipartProperties.setMaxFileSize("10Mb");
        return this.multipartProperties.createMultipartConfig();
    }

    @Bean
    public FilterRegistrationBean sellerLoginFilterRegistration() {
        FilterRegistrationBean registration = new FilterRegistrationBean(new SellerLoginFilter());
//        registration.addUrlPatterns("/*");//过滤器会过滤所有/*的请求
        registration.addUrlPatterns("/seller/*");//过滤器会过滤所有/seller/*的请求
        return registration;
    }

    @Bean
    public FilterRegistrationBean closeApiFilterRegistration() {
        FilterRegistrationBean registration = new FilterRegistrationBean(new CloseApiFilter());
        registration.addUrlPatterns("/api/reference/*");
        registration.addUrlPatterns("/foreign/referenceList");
        registration.addUrlPatterns("/foreign/reference");
        return registration;
    }

//    @Bean
//    public DozerBeanMapper dozerBeanMapper() {
//        DozerBeanMapper dozerBeanMapper = new DozerBeanMapper();
//        List<String> mappingFiles = new ArrayList<>();
//        mappingFiles.add("classpath:config/mapping.xml");
//        dozerBeanMapper.setMappingFiles(mappingFiles);
//        return dozerBeanMapper;
//    }

    /**
     * 解决前端ajax请求跨域问题
     * @return
     */
    private CorsConfiguration buildConfig() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.addAllowedOrigin("*"); // 1
        corsConfiguration.addAllowedHeader("*"); // 2
        corsConfiguration.addAllowedMethod("*"); // 3
        return corsConfiguration;
    }

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", buildConfig()); // 4
        return new CorsFilter(source);
    }


//    @Autowired
//    private PlatformInterceptor interceptor;

    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper().disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
    }

//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(interceptor);
//    }
}
