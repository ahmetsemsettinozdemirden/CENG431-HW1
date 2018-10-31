package presentation;

import business.Activity;
import business.Project;
import business.ProjectPortfolioManager;
import business.Resource;
import business.Task;
import business.person.Person;
import data.ProjectPortfolioManagerSerializer;

import java.text.ParseException;
import java.util.Date;
import java.util.Scanner;

public class CLI {

    private enum State {

        MAIN_MENU,

        PROJECTS_MENU,
        PROJECT_SELECTED,
        ACTIVITY_SELECTED,
        TASK_SELECTED,

        RESOURCES_MENU,
        RESOURCE_SELECTED,

    }

    private ProjectPortfolioManagerSerializer projectPortfolioManagerSerializer;
    private ProjectPortfolioManager projectPortfolioManager;
    private Scanner scanner;

    private State currentState;
    private Project selectedProject;
    private Activity selectedActivity;
    private Task selectedTask;
    private Resource selectedResource;

    public CLI(ProjectPortfolioManager projectPortfolioManager, ProjectPortfolioManagerSerializer projectPortfolioManagerSerializer) {
        setProjectPortfolioManagerSerializer(projectPortfolioManagerSerializer);
        setProjectPortfolioManager(projectPortfolioManager);
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        this.currentState = State.MAIN_MENU;
        while (true) {
            try {
                switch (currentState) {
                    case MAIN_MENU:
                        mainMenu();
                        break;
                    case PROJECTS_MENU:
                        projectsMenu();
                        break;
                    case PROJECT_SELECTED:
                        projectSelectedMenu();
                        break;
                    case ACTIVITY_SELECTED:
                        activitySelectedMenu();
                        break;
                    case TASK_SELECTED:
                        taskSelectedMenu();
                        break;
                    case RESOURCES_MENU:
                        resourcesMenu();
                        break;
                    case RESOURCE_SELECTED:
                        resourceSelectedMenu();
                        break;
                }
                Thread.sleep(1000);
            } catch (Exception e) {
                System.out.println("[ERROR] " + e.getMessage() + "\n");
            }
        }
    }

    private void mainMenu() {

        System.out.print("--------------------------------\n" +
                "     Project Portfolio Manager\n" +
                "1) Projects\n" +
                "2) Resources\n" +
                "3) Save&Exit\n");

        System.out.print("choose menu item: ");
        switch (Integer.parseInt(scanner.nextLine())) {
            case 1:
                currentState = State.PROJECTS_MENU;
                break;
            case 2:
                currentState = State.RESOURCES_MENU;
                break;
            case 3:
                try {
                    projectPortfolioManagerSerializer.save(projectPortfolioManager);
                } catch (Exception e) {
                    System.out.print("Error occurred while saving! " + e.getMessage() + "\n");
                }
                System.exit(0);
                break;
            default:
                System.out.print("Invalid choice.\n");
        }
    }

    private void projectsMenu() throws ParseException {

        System.out.print("--------------------------------\n" +
                "     Projects\n" +
                "1) List projects\n" +
                "2) Select project\n" +
                "3) Add project\n" +
                "4) Remove project\n" +
                "5) Back to MainMenu\n"); // TODO: duration by hours of projects

        System.out.print("choose menu item: ");
        switch (Integer.parseInt(scanner.nextLine())) {
            case 1:
                if (projectPortfolioManager.getProjects().isEmpty()) {
                    System.out.print("No projects.\n");
                } else {
                    System.out.println("All projects are listed below:");
                    for (Project project : projectPortfolioManager.getProjects()) {
                        System.out.print("* " + project.getName() + "\n");
                    }
                }
                break;
            case 2:
                System.out.print("Enter the name of project: ");
                String projectName = scanner.nextLine();
                selectedProject = projectPortfolioManager.findProject(projectName);
                currentState = State.PROJECT_SELECTED;
                break;
            case 3:
                System.out.print("Enter the name of new project: ");
                String newProjectName = scanner.nextLine();
                System.out.print("Enter the description of new project: ");
                String newProjectDescription = scanner.nextLine();
                Date newProjectStartDate = new Date();
                projectPortfolioManager.addProject(newProjectName, newProjectDescription, newProjectStartDate);
                System.out.print("Project successfully created.\n");
                break;
            case 4:
                System.out.print("Enter the name of project: ");
                String removeProjectName = scanner.nextLine();
                Project project = projectPortfolioManager.findProject(removeProjectName);
                projectPortfolioManager.removeProject(project);
                System.out.print("Project successfully deleted.\n");
                break;
            case 5:
                currentState = State.MAIN_MENU;
                break;
            default:
                System.out.print("Invalid choice.\n");
        }
    }

    private void projectSelectedMenu() throws ParseException {

        System.out.print("--------------------------------\n" +
                "     Project Selected Menu\n" +
                "     '" + selectedProject.getName() + "' is selected.\n" +
                "1) List activities\n" +
                "2) Select activity\n" +
                "3) Add activity\n" +
                "4) Remove activity\n" +
                "5) List all assigned resources\n" +
                "6) Duration by hours\n" +
                "7) Update name\n" +
                "8) Update description\n" +
                "9) Back to Projects Menu\n");

        System.out.print("choose menu item: ");
        switch (Integer.parseInt(scanner.nextLine())) {
            case 1:
                if (selectedProject.getActivities().isEmpty()) {
                    System.out.print("No activities.\n");
                } else {
                    System.out.println("All activities are listed below:");
                    for (Activity activity: selectedProject.getActivities()) {
                        System.out.print("* " + activity + "\n");
                    }
                }
                break;
            case 2:
                System.out.print("Enter the id of activity: ");
                int activityId = Integer.parseInt(scanner.nextLine());
                selectedActivity = projectPortfolioManager.findActivity(selectedProject, activityId);
                currentState = State.ACTIVITY_SELECTED;
                break;
            case 3:
                System.out.print("Enter the description of new activity: ");
                String newActivityDescription = scanner.nextLine();
                Date newActivityStartDate = new Date();
                System.out.print("Enter the deliverable of new activity: ");
                String newActivityDeliverable = scanner.nextLine();
                projectPortfolioManager.addActivity(selectedProject, newActivityDescription, newActivityStartDate, newActivityDeliverable);
                System.out.print("Activity successfully created.\n");
                break;
            case 4:
                System.out.print("Enter the id of activity: ");
                int removeActivityId = Integer.parseInt(scanner.nextLine());
                Activity activity = projectPortfolioManager.findActivity(selectedProject, removeActivityId);
                projectPortfolioManager.removeActivity(selectedProject, activity);
                System.out.print("Activity successfully deleted.\n");
                break;
            case 5:
                if (projectPortfolioManager.getResources(selectedProject).isEmpty()) {
                    System.out.print("No Resources.\n");
                } else {
                    System.out.println("All resources are listed below:");
                    for (Resource resource: projectPortfolioManager.getResources(selectedProject)) {
                        System.out.print("* " + resource + "\n");
                    }
                }
                break;
            case 6:
                System.out.print("Duration by hours:" + projectPortfolioManager.getHours(selectedProject) + "\n");
                break;
            case 7:
                System.out.print("Enter the name: ");
                String updateProjectName = scanner.nextLine();
                selectedProject.setName(updateProjectName);
                System.out.print("Project name successfully updated.\n");
                break;
            case 8:
                System.out.print("Enter the description: ");
                String updateProjectDescription = scanner.nextLine();
                selectedProject.setDescription(updateProjectDescription);
                System.out.print("Project description successfully updated.\n");
                break;
            case 9:
                selectedProject = null;
                currentState = State.PROJECTS_MENU;
                break;
            default:
                System.out.print("Invalid choice.\n");
        }
    }

    private void activitySelectedMenu() throws ParseException {

        System.out.print("--------------------------------\n" +
                "     Activity Selected Menu\n" +
                "     '" + selectedActivity.getId() + "' of '" + selectedProject.getName() + "' is selected.\n" +
                "1) List tasks\n" +
                "2) Select task\n" +
                "3) Add task\n" +
                "4) Remove task\n" +
                "5) List all assigned resources\n" +
                "6) Duration by hours\n" +
                "7) Update deliverable\n" +
                "8) Update description\n" +
                "9) Back to '" + selectedProject.getName() + "' Project Menu\n");

        System.out.print("choose menu item: ");
        switch (Integer.parseInt(scanner.nextLine())) {
            case 1:
                if (selectedActivity.getTasks().isEmpty()) {
                    System.out.print("No tasks.\n");
                } else {
                    System.out.println("All tasks are listed below:");
                    for (Task task: selectedActivity.getTasks()) {
                        System.out.print("* " + task + "\n");
                    }
                }
                break;
            case 2:
                System.out.print("Enter the id of task: ");
                int taskId = Integer.parseInt(scanner.nextLine());
                selectedTask = projectPortfolioManager.findTask(selectedActivity, taskId);
                currentState = State.TASK_SELECTED;
                break;
            case 3:
                System.out.print("Enter the description of new task: ");
                String newTaskDescription = scanner.nextLine();
                System.out.print("Enter the hours of new task: ");
                int newTaskHours = Integer.parseInt(scanner.nextLine());
                Date newTaskStartDate = new Date();
                projectPortfolioManager.addTask(selectedActivity, newTaskDescription, newTaskHours, newTaskStartDate);
                System.out.print("Task successfully created.\n");
                break;
            case 4:
                System.out.print("Enter the id of task: ");
                int removeTaskId = Integer.parseInt(scanner.nextLine());
                Task task = projectPortfolioManager.findTask(selectedActivity, removeTaskId);
                projectPortfolioManager.removeTask(selectedActivity, task);
                System.out.print("Task successfully deleted.\n");
                break;
            case 5:
                if (projectPortfolioManager.getResources(selectedActivity).isEmpty()) {
                    System.out.print("No Resources.\n");
                } else {
                    System.out.println("All resources are listed below:");
                    for (Resource resource: projectPortfolioManager.getResources(selectedActivity)) {
                        System.out.print("* " + resource + "\n");
                    }
                }
                break;
            case 6:
                System.out.print("Duration by hours:" + projectPortfolioManager.getHours(selectedActivity) + "\n");
                break;
            case 7:
                System.out.print("Enter the deliverable: ");
                String updateActivityDeliverable = scanner.nextLine();
                selectedActivity.setDeliverable(updateActivityDeliverable);
                System.out.print("Activity deliverable successfully updated.\n");
                break;
            case 8:
                System.out.print("Enter the description: ");
                String updateActivityDescription = scanner.nextLine();
                selectedActivity.setDescription(updateActivityDescription);
                System.out.print("Activity description successfully updated.\n");
                break;
            case 9:
                selectedActivity = null;
                currentState = State.PROJECT_SELECTED;
                break;
            default:
                System.out.print("Invalid choice.\n");
        }
    }

    private void taskSelectedMenu() {

        System.out.print("--------------------------------\n" +
                "     Task Selected Menu\n" +
                "     '" + selectedActivity.getId() + "' of '" + selectedProject.getName() + "' is selected.\n" +
                "1) Show resource\n" +
                "2) Assign resource\n" +
                "3) Unassign resource\n" +
                "4) Duration by hours\n" +
                "5) Update description\n" +
                "6) Back to '" + selectedActivity.getDescription() + "' Activity Menu\n");

        System.out.print("choose menu item: ");
        switch (Integer.parseInt(scanner.nextLine())) {
            case 1:
                if (selectedTask.getResource() == null) {
                    System.out.print("No resource assigned.\n");
                } else {
                    System.out.print("* " + selectedTask.getResource() + "\n");
                }
                break;
            case 2:
                if (projectPortfolioManager.getResources().isEmpty()) {
                    System.out.print("No resources.\n");
                    break;
                } else {
                    System.out.println("All resources are listed below:");
                    for (Resource resource: projectPortfolioManager.getResources()) {
                        System.out.print("* " + resource + "\n");
                    }
                }
                System.out.print("Enter the id of resource: ");
                int assignResourceId = Integer.parseInt(scanner.nextLine());
                Resource assignResource = projectPortfolioManager.findResource(assignResourceId);
                projectPortfolioManager.assign(assignResource, selectedTask);
                System.out.println("Resource assigned to this task.");
                break;
            case 3:
                System.out.print("Enter the id of resource: ");
                int unassignResourceId = Integer.parseInt(scanner.nextLine());
                Resource unassignResource = projectPortfolioManager.findResource(unassignResourceId);
                projectPortfolioManager.unassign(unassignResource, selectedTask);
                System.out.println("Resource unassigned from this task.");
                break;
            case 4:
                System.out.print("Duration by hours:" + projectPortfolioManager.getHours(selectedTask) + "\n");
                break;
            case 5:
                System.out.print("Enter the description: ");
                String updateTaskDescription = scanner.nextLine();
                selectedTask.setDescription(updateTaskDescription);
                System.out.print("Task description successfully updated.\n");
                break;
            case 6:
                selectedTask = null;
                currentState = State.ACTIVITY_SELECTED;
                break;
            default:
                System.out.print("Invalid choice.\n");
        }

    }

    private void resourcesMenu() {

        System.out.print("--------------------------------\n" +
                "     Resources\n" +
                "1) List resources\n" +
                "2) Select resource\n" +
                "3) Add resource\n" +
                "4) Remove resource\n" +
                "5) Back to MainMenu\n");

        System.out.print("choose menu item: ");
        switch (Integer.parseInt(scanner.nextLine())) {
            case 1:
                if (projectPortfolioManager.getResources().isEmpty()) {
                    System.out.print("No resources.\n");
                } else {
                    System.out.println("All resources are listed below:");
                    for (Resource resource: projectPortfolioManager.getResources()) {
                        System.out.print("* " + resource + "\n");
                    }
                }
                break;
            case 2:
                System.out.print("Enter the id of resource: ");
                int resourceId = Integer.parseInt(scanner.nextLine());
                selectedResource = projectPortfolioManager.findResource(resourceId);
                currentState = State.RESOURCE_SELECTED;
                break;
            case 3:
                System.out.print("--------------------------------\n" +
                        "     Resource Types\n" +
                        "1) Employee\n" +
                        "2) Consultant\n" +
                        "Enter the type of project: ");
                int resourceType = Integer.parseInt(scanner.nextLine());
                switch (resourceType) {
                    case 1:
                        System.out.print("Enter the name of new employee: ");
                        String newEmployeeName = scanner.nextLine();
                        projectPortfolioManager.createEmployee(newEmployeeName);
                        System.out.print("Employee successfully created.\n");
                        break;
                    case 2:
                        System.out.print("Enter the name of new consultant: ");
                        String newConsultantName = scanner.nextLine();
                        projectPortfolioManager.createConsultant(newConsultantName);
                        System.out.print("Consultant successfully created.\n");
                        break;
                    default:
                        System.out.print("Invalid choice.\n");
                        return;
                }
                break;
            case 4:
                System.out.print("Enter the id of Resource: ");
                int removeResourceId = Integer.parseInt(scanner.nextLine());
                projectPortfolioManager.removeResource(removeResourceId);
                System.out.print("Resource successfully deleted.\n");
                break;
            case 5:
                currentState = State.MAIN_MENU;
                break;
            default:
                System.out.print("Invalid choice.\n");
        }
    }

    private void resourceSelectedMenu() {

        System.out.print("--------------------------------\n" +
                "     Task Selected Menu\n" +
                "     '" + selectedResource + "' is selected.\n");

        if (selectedResource instanceof Person) {

            Person selectedPerson = (Person) selectedResource;

            System.out.print("1) Update name\n" +
                    "2) Back to Resources Menu\n");

            System.out.print("choose menu item: ");
            switch (Integer.parseInt(scanner.nextLine())) {
                case 1:
                    System.out.print("Enter the name: ");
                    String name = scanner.nextLine();
                    selectedPerson.setName(name);
                    System.out.print("Person name successfully updated.\n");
                    break;
                case 2:
                    currentState = State.RESOURCES_MENU;
                    break;
                default:
                    System.out.print("Invalid choice.\n");
            }

        } else {
            System.out.print("  No actions are defined for this type!");
            currentState = State.RESOURCES_MENU;
        }
    }

    private void setProjectPortfolioManager(ProjectPortfolioManager projectPortfolioManager) {
        if (projectPortfolioManager == null)
            throw new IllegalArgumentException("projectPortfolioManager can not be null.");
        this.projectPortfolioManager = projectPortfolioManager;
    }

    private void setProjectPortfolioManagerSerializer(ProjectPortfolioManagerSerializer projectPortfolioManagerSerializer) {
        if (projectPortfolioManagerSerializer == null)
            throw new IllegalArgumentException("projectPortfolioManagerSerializer can not be null.");
        this.projectPortfolioManagerSerializer = projectPortfolioManagerSerializer;
    }

}
