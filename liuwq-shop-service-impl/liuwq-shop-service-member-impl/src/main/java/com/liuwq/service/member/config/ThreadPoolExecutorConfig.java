package com.liuwq.service.member.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

@Configuration
public class ThreadPoolExecutorConfig implements AsyncConfigurer {

    /**
     * 设置ThreadPoolExecutor的核心线程数
     */
    private static final int CORE_POOL_SIZE = 2;

    /**
     * 设置ThreadPoolExecutor的最大线程数
     */
    private static final int MAX_POOL_SIZE = 2;

    /**
     * 设置ThreadPoolExecutor的BlockingQueue容量
     */
    private static final int QUEUE_CAPACITY = 10;

    @Override
    public Executor getAsyncExecutor() {
        ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor();
        threadPoolTaskExecutor.setCorePoolSize(CORE_POOL_SIZE);
        threadPoolTaskExecutor.setMaxPoolSize(MAX_POOL_SIZE);
        threadPoolTaskExecutor.setQueueCapacity(QUEUE_CAPACITY);
        threadPoolTaskExecutor.initialize();
        return threadPoolTaskExecutor;
    }
}
