package pmo.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import pmo.project.handlers.CreateHumanResourceHandler;
import pmo.project.handlers.response.HandlerResponse;
import pmo.project.repo.resource.models.HumanResource;

@RestController("/pmo/human/")
public class HumanResourceController {
	
	@Autowired
	CreateHumanResourceHandler _createHumanResourceHandler;
	
	@PostMapping
	public HandlerResponse<Boolean> createHumanResource(HumanResource request) {
		return this._createHumanResourceHandler.process(request);
	}
}
