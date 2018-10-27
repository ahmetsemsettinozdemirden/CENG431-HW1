package business;

import business.person.Consultant;
import business.person.Employee;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ProjectManagementSystem {

	private int activityId;
	private int taskId;
	private int resourceId;
    private List<Project> projects;
    private List<Resource> resources;

    public ProjectManagementSystem() {
        this.projects = new ArrayList<>();
    }

    public void addProject(String name, String description, Date startDate) {
        if (name == null || name.equals(""))
            throw new IllegalArgumentException("name can not be null or empty.");
        for (Project project: projects) {
        	if (project.getName().equals(name)) {
        		throw new IllegalArgumentException("Given name already being used by another project.");
			}
		}
        projects.add(new Project(name, description, startDate));
    }

    public Project findProject(String name) {
        for (Project project: projects)
            if (project.getName().equals(name))
                return project;
        throw new ProjectNotFoundException("Project with given name: " + name + " does not exist.");
    }

    // TODO (alpay question): do we need to remove all of its tasks, activities, etc?
    public boolean removeProject(Project project) {
        return projects.remove(project);
    }

    public void addActivity(Project project, String description, Date startDate, String deliverable) {
        project.getActivities().add(new Activity(activityId++, description, startDate, deliverable));
    }

    public Activity findActivity(Project project, int id) {
        for (Activity activity: project.getActivities())
            if (activity.getId() == id)
                return activity;
        throw new ActivityNotFoundException("Activity with id: " + id + " does not exist.");
    }

    // TODO (alpay question): do we need to find and delete activity by number or by activity object?
    public boolean removeActivity(Project project, Activity activity) {
        return project.getActivities().remove(activity);
    }

    public void addTask(Activity activity, String description, int hours, Date startDate) {
		activity.getTasks().add(new Task(taskId++, description, hours, startDate));
	}

	public Task findTask(Activity activity, int id) {
		for (Task task: activity.getTasks())
			if (task.getId() == id)
				return task;
		throw new ActivityNotFoundException("Task with id: " + id + " does not exist.");
	}

	// TODO (alpay question): do we need to find and delete task by number or by task object?
	public boolean removeTask(Activity activity, Task task) {
    	return activity.getTasks().remove(task);
	}

	// create and delete resource
    public void createEmployee(String name) {
        resources.add(new Employee(resourceId++, name));
    }

    public void createConsultant(String name) {
        resources.add(new Consultant(resourceId++, name));
    }

    public boolean deleteResource(int id) {
        for (Resource resource: resources)
            if (resource.getId() == id)
                return resources.remove(resource);
        throw new ActivityNotFoundException("Task with id: " + id + " does not exist.");
    }

    // TODO: add, find and remove a resource in a project


    // TODO: assign a resource to a task in a project
    // TODO: unassign a resource from a task in a project
    // TODO: calculating project, activity, and task duration by hours
    // TODO: finding number of distinct employees and consultants assigned to a project, activity, and task

    // TODO: last user project
    // TODO: add or delete resource (Person), we can use ResourceFactory and ResourceSerializer



    public static class ProjectNotFoundException extends RuntimeException {
        public ProjectNotFoundException(String error) {
        	super(error);
        }
    }

    public static class ActivityNotFoundException extends RuntimeException {
        public ActivityNotFoundException(String error) {
            super(error);
        }
    }

}
