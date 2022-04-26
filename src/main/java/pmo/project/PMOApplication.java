package pmo.project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import pmo.project.handlers.*;
import pmo.project.handlers.query.QueryProjectFeasibilityHandler;

@SpringBootApplication
@EnableAutoConfiguration
public class PMOApplication {

	public static void main(String[] args) {
		SpringApplication.run(PMOApplication.class, args);
	}
	
	@Bean
	public CreateHumanResourceHandler getCreateHumanResourceHandler() {
		return new CreateHumanResourceHandler();
	}
	
	@Bean
	public UpdateHumanResourceHandler getUpdateHumanResourceHandler() {
		return new UpdateHumanResourceHandler();
	}
	
	@Bean
	public DeleteHumanResourceHandler getDeleteHumanResourceHandler() {
		return new DeleteHumanResourceHandler();
	}
	
	@Bean
	public CreateMaterialResourceHandler getCreateMaterialResourceHandler() {
		return new CreateMaterialResourceHandler();
	}
	
	@Bean
	public UpdateMaterialResourceHandler getUpdateMaterialResourceHandler() {
		return new UpdateMaterialResourceHandler();
	}
	
	@Bean
	public DeleteMaterialResourceHandler getDeleteMaterialResourceHandler() {
		return new DeleteMaterialResourceHandler();
	}
	
	@Bean
	public CreateProjectHandler getCreateProjectHandler() {
		return new CreateProjectHandler();
	}
	
	@Bean
	public UpdateProjectHandler getUpdateProjectHandler() {
		return new UpdateProjectHandler();
	}
	
	@Bean
	public DeleteProjectHandler getDeleteProjectHandler() {
		return new DeleteProjectHandler();
	}
	
	@Bean
	public QueryProjectFeasibilityHandler getQueryProjectFeasibilityHandler() {
		return new QueryProjectFeasibilityHandler();
	}
	
	@Bean
	public CreateTaskHandler getCreateTaskHandler() {
		return new CreateTaskHandler();
	}
	
	@Bean
	public UpdateTaskHandler getUpdateTaskHandler() {
		return new UpdateTaskHandler();
	}
	
	@Bean
	public DeleteTaskHandler getDeleteTaskHandler() {
		return new DeleteTaskHandler();
	}
}
