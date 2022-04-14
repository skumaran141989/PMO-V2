package pmo.project.repo.models;

public class TaskDependency {
     private Task _task;
     private int _weight;
     
     public TaskDependency(Task task, int weight) {
    	 this._task = task;
    	 this._weight = weight;
     }
     
     public void setTask(Task task) {
    	 this._task = task;
     }
     
     public Task getTask() {
     	return this._task;
     } 
     
     public int getWeight() {
      	return this._weight;
     } 
}
