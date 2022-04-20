package pmo.project.repo.resource;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pmo.project.repo.resource.models.Brick;

@Repository
public interface BrickRepository extends JpaRepository<Brick, Long> {

}

