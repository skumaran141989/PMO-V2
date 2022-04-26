package pmo.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pmo.project.handlers.CreateMaterialResourceHandler;
import pmo.project.handlers.DeleteMaterialResourceHandler;
import pmo.project.handlers.UpdateMaterialResourceHandler;
import pmo.project.handlers.response.HandlerResponse;
import pmo.project.repo.resource.models.MaterialResource;

@RestController
@RequestMapping("/pmo-material")
public class MaterialResourceController {
	@Autowired
	CreateMaterialResourceHandler _createMaterialResourceHandler;
	@Autowired
	UpdateMaterialResourceHandler _updateMaterialResourceHandler;
	@Autowired
	DeleteMaterialResourceHandler _deleteMaterialResourceHandler;
	
	@PostMapping
	public ResponseEntity<HandlerResponse<Boolean>> createMaterialResource(@RequestBody  MaterialResource request) {
		return ResponseEntity.ok(this._createMaterialResourceHandler.process(request));
	}	
	
	@PutMapping("/{id}")
	public ResponseEntity<HandlerResponse<Boolean>> updateMaterialResource(@RequestBody  MaterialResource request, @PathVariable(required = true, value="id") long id) {
		request.setId(id);
		return ResponseEntity.ok(this._updateMaterialResourceHandler.process(request));
	}	
	
	@DeleteMapping("/{id}")
	public ResponseEntity<HandlerResponse<Boolean>> deleteMaterialResource(@PathVariable(required = true, value="id") long id) {
		return ResponseEntity.ok(this._deleteMaterialResourceHandler.process(id));
	}
}
