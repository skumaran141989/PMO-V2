package pmo.project.models;

public class TaskDependency {
     private Task _task;
     private int _weight;
     
     public TaskDependency(Task task, int weight) {
    	 _task = task;
    	 _weight = weight;
     }
     
     public void setTask(Task task) {
    	 _task = task;
     }
     
     public Task getTask() {
     	return _task;
     } 
     
     public int getWeight() {
      	return _weight;
     } 
}
