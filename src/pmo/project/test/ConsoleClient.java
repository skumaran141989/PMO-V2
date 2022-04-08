package pmo.project.test;

import pmo.project.repo.*;
import pmo.project.resource.models.*;

public class ConsoleClient {
	
	public static void main(String[] args) {
		ProjectManagementRepo _projectManagementRepo = new ProjectManagementRepo();
		MaterialResourceRepo _materialResourceRepo = new MaterialResourceRepo();
		HumanResourceRepo _humanResourceRepo = new HumanResourceRepo();
		
		for(int i=0;i< 200;i++)
			_materialResourceRepo.save(new Brick("A", "Red", "Brand-A", 24, 13.50));
		
		for(int i=0;i< 10;i++)
			_materialResourceRepo.save(new Cement(0.33, "Brand-C", 500, 100.50));
		
		for(int i=0;i< 2000;i++)
			_materialResourceRepo.save(new Tiles("A", "Red", "Brand-A", 65, 8.50));
		
		for(int i=0;i< 20;i++)
			_materialResourceRepo.save(new Cement(0.33, "Brand-H", 500, 56.50));
		
		for(int i=0;i< 5;i++)
			_humanResourceRepo.save(new Supervisor(3, true, "Name-"+i, "Last-"+i, i, "988674567"+i, 678.6));   
		
		for(int i=5;i<9;i++)
			_humanResourceRepo.save(new Labourer("tiles-fitting, carpenter", 1, "Name-"+i, "Last-"+i, i, "999441288"+i, 678.6));   
	}
}
