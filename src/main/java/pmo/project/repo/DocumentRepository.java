package pmo.project.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pmo.project.repo.models.Document;

@Repository
public interface DocumentRepository extends JpaRepository<Document, Long> {

}
