package business;

import java.util.Date;
import java.util.List;

public class ProjectManagementSystem {

	// TODO: Serialize these and when program started, load back last id values
	private int activityId = 0;
	private int taskId = 0;

    private List<Project> projects;

    public void addProject(String name, String description, Date startDate) {
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

    public boolean removeProject(Project project) {
    	// TODO (alpay question): do we need to remove all of its tasks, activities, etc?
        return projects.remove(project);
    }

    public void addActivity(Project project, String description, Date startDate, String deliverable) {
        project.getActivities().add(new Activity(activityId, description, startDate, deliverable));
        activityId++;
    }

    public Activity findActivity(Project project, int number) {
        for (Activity activity: project.getActivities())
            if (activity.getNumber() == number)
                return activity;
        throw new ActivityNotFoundException("Activity with id: " + number + " does not exist.");
    }

    // TODO (alpay question): do we need to find and delete activity by number or by activity object?
    public boolean removeActivity(Project project, Activity activity) {
        return project.getActivities().remove(activity);
    }

    public void addTask(Activity activity, String description, Date startDate) {
		activity.getTasks().add(new Task(taskId, description, startDate));
		taskId++;
	}

	public Task findTask(Activity activity, int number) {
		for (Task task: activity.getTasks())
			if (task.getNumber() == number)
				return task;
		throw new ActivityNotFoundException("Task with id: " + number + " does not exist.");
	}

	// TODO (alpay question): do we need to find and delete task by number or by task object?
	public boolean removeTask(Activity activity, Task task) {
    	return activity.getTasks().remove(task);
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
