package pmo.project.Service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pmo.project.repo.resource.MaterialResourceRepository;
import pmo.project.repo.resource.models.MaterialResource;

@Service
public class MaterialResourceService {
	@Autowired
	private MaterialResourceRepository _materialResourceRepo;
	
	@Transactional("readWriteTM")
	public MaterialResource createMaterialResource(MaterialResource resource) {
		return this._materialResourceRepo.save(resource);
	}
	
	@Transactional("readWriteTM")
	public MaterialResource updateMaterialResource(MaterialResource resource, long id) {
		resource.setId(id);
		return this._materialResourceRepo.save(resource);
	}
	
	@Transactional("readWriteTM")
	public void deleteMaterialResource(long id) {
		this._materialResourceRepo.deleteById(id);
	}
	
	@Transactional("readOnlyTM")
	public MaterialResource getMaterialResourceById(long id) {
		return this._materialResourceRepo.getById(id);
	}
	
	@Transactional("readOnlyTM")
	public List<MaterialResource> getMaterialResourceByType(String type) {
		return this._materialResourceRepo.findAll().stream().filter(resource->resource.getType().equals(type)).collect(Collectors.toList());
	}
	
	@Transactional("readOnlyTM")
	public List<MaterialResource> getAvailableMaterialResources(String type) {
		return this._materialResourceRepo.findAll().stream().filter(resource->resource.getType().equals(type) && resource.getIsUsable() && !resource.getUtilized()).collect(Collectors.toList());
	}
}
