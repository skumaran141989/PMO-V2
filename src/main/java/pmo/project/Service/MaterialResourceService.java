package pmo.project.Service;


import java.util.List;
import java.util.stream.Collectors;

import pmo.project.repo.MaterialResourceRepo;
import pmo.project.repo.resource.models.MaterialResource;

public class MaterialResourceService {
	private MaterialResourceRepo _materialResourceRepo;
	
	public MaterialResourceService(MaterialResourceRepo _materialResourceRepo){
		this._materialResourceRepo = _materialResourceRepo;
	}
	
	public long createMaterialResource(MaterialResource resource){
		return this._materialResourceRepo.save(resource);
	}
	
	public MaterialResource getMaterialResourceById(long id){
		return this._materialResourceRepo.get(id);
	}
	
	//this will be a query in real time
	public List<MaterialResource> getMaterialResourceByType(String type) {
		return this._materialResourceRepo.getAll().values().stream().filter(resource->resource.getType() == type).collect(Collectors.toList());
	}
	
	//this will be a join query in real time
	public List<MaterialResource> getAvailableMaterialResources(String type) {
		return this._materialResourceRepo.getAll().values().stream().filter(resource->resource.getType() == type && resource.getIsUsable() && !resource.getUtilized()).collect(Collectors.toList());
	}
}
