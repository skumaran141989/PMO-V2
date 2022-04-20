package pmo.project.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pmo.project.repo.models.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

}
