package pmo.project.repo.resource;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pmo.project.repo.resource.models.Tile;

@Repository
public interface TileRepository extends JpaRepository<Tile, Long> {

}

