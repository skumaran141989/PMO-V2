# PMO-Project
This is an initial version of the PMO applicatoon that can be used for allocating resources and completing a project.


### Note:
##### 1) Each project will have group of tasks.
##### 2) Each task is defined by resources and time taken
##### 3) Project will be created and store in project repo.
##### 4) Task creation will be recrusive based on the reosurces available and time remaning for the project.
##### 5) We assume that all the tasks at the same level can completed parallel. Hence time remaining is same for all the tasks at the same level,
##### 6) Intent of having event handlers for each event is to create tasks at the same level in distribted fashion - provided we trust on eventual consistency- not in use for now
##### 7) We can create more Handlers for each event.
##### 8) Please take a look at CalendarResourceRepo [https://github.com/skumaran141989/PMO-Project/blob/master/src/pmo/project/repo/CalendarResourceRepo.java]

--Still Work pending.
