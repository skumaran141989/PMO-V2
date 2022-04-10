package pmo.project;

import java.util.concurrent.Executor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
@EnableAsync
public class ExecutorConfiguration {
	
	 @Bean(name = "Level1")
	 public Executor getProjectExecutor() {
	  ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
	  taskExecutor.setCorePoolSize(20);
	  taskExecutor.setMaxPoolSize(50);
	  taskExecutor.setQueueCapacity(20);
	  taskExecutor.initialize();
	  return taskExecutor;
	 }
	 
	 @Bean(name = "Level2")
	 public Executor getProjectTaskExecutor() {
	  ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
	  taskExecutor.setCorePoolSize(40);
	  taskExecutor.setMaxPoolSize(60);
	  taskExecutor.setQueueCapacity(30);
	  taskExecutor.initialize();
	  return taskExecutor;
	 }
	 
	 @Bean(name = "Level3")
	 public Executor getResourceExecutor() {
	  ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
	  taskExecutor.setCorePoolSize(100);
	  taskExecutor.setMaxPoolSize(300);
	  taskExecutor.setQueueCapacity(10);
	  taskExecutor.initialize();
	  return taskExecutor;
	 }
}
