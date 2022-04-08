package pmo.project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;

import pmo.project.repo.*;

@SpringBootApplication
public class PMOApplication {

	public static void main(String[] args) {
		SpringApplication.run(PMOApplication.class, args);
	}
	
	@Bean
	@Scope("singleton")
	public HumanResourceRepo getHumanResourceRepo() {		
		return new HumanResourceRepo();
	}
	
	@Bean
	@Scope("singleton")
	public MaterialResourceRepo getMaterialResourceRepo() {	
		return new MaterialResourceRepo();
	}
	
	@Bean
	@Scope("singleton")
	public ProjectManagementRepo getProjectManagementRepo() {	
		return new ProjectManagementRepo();
	}
	
	@Bean
	@Scope("singleton")
	public TaskManagementRepo getTaskManagementRepo() {	
		return new TaskManagementRepo();
	}
}