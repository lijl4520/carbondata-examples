package com.lijl.carbon.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @Author Lijl
 * @ClassName ExecutorConfig
 * @Description 线程池配置类
 * @Date 2020/9/10 9:59
 * @Version 1.0
 */
@Configuration
@EnableAsync
@Slf4j
public class ExecutorConfig {

    @Bean("asyncServiceExecutor")
    public Executor asyncServiceExecutor(){
        log.info("start asyncServiceExecutor");
        ThreadPoolTaskExecutor executor = new VisiableThreadPoolTaskExecutor();
        executor.setCorePoolSize(5);
        executor.setMaxPoolSize(5);
        executor.setQueueCapacity(99999);
        executor.setThreadNamePrefix("async-service-");
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        executor.initialize();
        return executor;
    }
}
