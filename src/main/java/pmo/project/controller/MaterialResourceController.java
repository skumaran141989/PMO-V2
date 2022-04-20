package pmo.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import pmo.project.handlers.CreateMaterialResourceHandler;
import pmo.project.handlers.response.HandlerResponse;
import pmo.project.repo.resource.models.MaterialResource;

@RestController("/pmo/material/")
public class MaterialResourceController {
	
	@Autowired
	CreateMaterialResourceHandler _createMaterialResourceHandler;
	
	@PostMapping
	public HandlerResponse<Boolean> createMaterialResource(MaterialResource request) {
		return this._createMaterialResourceHandler.process(request);
	}	
}
