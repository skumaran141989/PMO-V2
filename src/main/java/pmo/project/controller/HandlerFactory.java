package pmo.project.controller;

import pmo.project.handlers.*;
import pmo.project.handlers.abstraction.Handler;

public class HandlerFactory {
	public static Handler getProessingHanlder(String requestType)
	{
		switch(requestType) {
			case "CP":
				return new CreateProjectHandler();
			case "CT":
				return new CreateTaskHandler();	
			case "EP":
				return new ExecuteProjectHandler();
			case "GPF":
				return new GenerateProjectFeasibilityHandler();
			case "SP":
				return new SaveHumanResourceHandler();
			case "SM":
				return new SaveMaterialResourceHandler();	
			case "UP":
				return new UpdateProjectHandler();
			case "UT":
				return new UpdateTaskHandler();	
			
		}
		
		return null;
	}
}
