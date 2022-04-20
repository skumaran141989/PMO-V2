package pmo.project.repo.resource;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pmo.project.repo.resource.models.Labourer;

@Repository
public interface LabourerRepository extends JpaRepository<Labourer, Long> {

}

