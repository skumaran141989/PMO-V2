package pmo.project.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pmo.project.repo.models.Project;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {

}
