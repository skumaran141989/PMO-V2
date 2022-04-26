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

import pmo.project.handlers.CreateHumanResourceHandler;
import pmo.project.handlers.DeleteHumanResourceHandler;
import pmo.project.handlers.UpdateHumanResourceHandler;
import pmo.project.handlers.response.HandlerResponse;
import pmo.project.repo.resource.models.HumanResource;

@RestController
@RequestMapping("/pmo-human")
public class HumanResourceController {
	@Autowired
	CreateHumanResourceHandler _createHumanResourceHandler;
	@Autowired
	UpdateHumanResourceHandler _updateHumanResourceHandler;
	@Autowired
	DeleteHumanResourceHandler _deleteHumanResourceHandler;
	
	@PostMapping
	public ResponseEntity<HandlerResponse<Boolean>> createHumanResource(@RequestBody  HumanResource request) {
		return ResponseEntity.ok(this._createHumanResourceHandler.process(request));
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<HandlerResponse<Boolean>> updateHumanResource(@RequestBody  HumanResource request, @PathVariable(required = true, value="id") Long id) {
		request.setId(id);
		return ResponseEntity.ok(this._updateHumanResourceHandler.process(request));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<HandlerResponse<Boolean>> deleteHumanResource(@PathVariable(required = true, value="id") Long id) {
		return ResponseEntity.ok(this._deleteHumanResourceHandler.process(id));
	}
}
