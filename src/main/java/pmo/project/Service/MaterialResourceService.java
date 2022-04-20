package pmo.project.Service;

import java.util.List;
import java.util.stream.Collectors;

import pmo.project.repo.resource.MaterialResourceRepository;
import pmo.project.repo.resource.models.MaterialResource;

public class MaterialResourceService {
	private MaterialResourceRepository _materialResourceRepo;
	
	public MaterialResourceService(MaterialResourceRepository _materialResourceRepo) {
		this._materialResourceRepo = _materialResourceRepo;
	}
	
	public MaterialResource createMaterialResource(MaterialResource resource) {
		return this._materialResourceRepo.save(resource);
	}
	
	public MaterialResource getMaterialResourceById(long id) {
		return this._materialResourceRepo.getById(id);
	}
	
	//this will be a query in real time
	public List<MaterialResource> getMaterialResourceByType(String type) {
		return this._materialResourceRepo.findAll().stream().filter(resource->resource.getType().equals(type)).collect(Collectors.toList());
	}
	
	//this will be a join query in real time
	public List<MaterialResource> getAvailableMaterialResources(String type) {
		return this._materialResourceRepo.findAll().stream().filter(resource->resource.getType().equals(type) && resource.getIsUsable() && !resource.getUtilized()).collect(Collectors.toList());
	}
}
