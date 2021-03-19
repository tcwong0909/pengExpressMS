package com.tcwong.pengms.listen;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 * Description 线程池配置
 *
 * @author tcwong
 * @date 2020/8/24 Since 1.8
 */
@EnableAsync
@Configuration
public class ExecutorConfig implements AsyncConfigurer {

    @Override
    public Executor getAsyncExecutor() {
        ThreadPoolTaskExecutor threadPoolExecutor = new ThreadPoolTaskExecutor();
        // 核心线程数超时
        threadPoolExecutor.setAllowCoreThreadTimeOut(true);
        // 设置任务队列30，超过时 增加线程
        threadPoolExecutor.setQueueCapacity(30);
        // 核心线程数和服务器核数一致
        threadPoolExecutor.setCorePoolSize(Runtime.getRuntime().availableProcessors());
        // 最大线程数，设置IO阻塞系数 0.7
        threadPoolExecutor.setMaxPoolSize(
                (int) (Runtime.getRuntime().availableProcessors() / (1 - 0.7)));
        // 线程名
        threadPoolExecutor.setThreadNamePrefix("asyncPengMS-");
        // 拒绝策略,执行任务
        threadPoolExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        // 初始化线程池
        threadPoolExecutor.initialize();
        return threadPoolExecutor;
    }
}
