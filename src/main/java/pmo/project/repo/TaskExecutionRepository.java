package pmo.project.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import pmo.project.repo.models.TaskExecution;

@Repository
public interface TaskExecutionRepository extends JpaRepository<TaskExecution, Long> {

	@Query("select te._taskId from TaskExecution te join Task t on te._taskId=t._blockingTaskId where te._id=?1 and te._taskId=?2")
	List<TaskExecution> findTaskExecutionReport(long executionId, long taskId);
}
