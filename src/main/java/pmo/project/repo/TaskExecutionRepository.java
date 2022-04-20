package pmo.project.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pmo.project.repo.models.TaskExecution;

@Repository
public interface TaskExecutionRepository extends JpaRepository<TaskExecution, Long> {

}
