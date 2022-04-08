package pmo.project;

import java.util.concurrent.Executor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
@EnableAsync
public class ExecutorConfiguration {
	
	 @Bean(name = "ProjectExecutor")
	 public Executor getProjectExecutor() {
	  ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
	  taskExecutor.setCorePoolSize(20);
	  taskExecutor.setMaxPoolSize(50);
	  taskExecutor.setQueueCapacity(20);
	  taskExecutor.initialize();
	  return taskExecutor;
	 }
	 
	 @Bean(name = "TaskExecutor")
	 public Executor getProjectTaskExecutor() {
	  ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
	  taskExecutor.setCorePoolSize(40);
	  taskExecutor.setMaxPoolSize(60);
	  taskExecutor.setQueueCapacity(30);
	  taskExecutor.initialize();
	  return taskExecutor;
	 }
	 
	 @Bean(name = "ResourceExecutor")
	 public Executor getResourceExecutor() {
	  ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
	  taskExecutor.setCorePoolSize(10);
	  taskExecutor.setMaxPoolSize(30);
	  taskExecutor.setQueueCapacity(10);
	  taskExecutor.initialize();
	  return taskExecutor;
	 }
}
