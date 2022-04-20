package pmo.project.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pmo.project.repo.models.TaskRequirement;

@Repository
public interface TaskRequirementRepository extends JpaRepository<TaskRequirement, Long> {

}
