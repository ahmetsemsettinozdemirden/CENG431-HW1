package presentation;

import business.Activity;
import business.Project;
import business.ProjectPortfolioManager;
import business.Resource;
import business.person.Person;
import data.ProjectPortfolioManagerSerializer;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class CLI {

    private enum State {

        MAIN_MENU,

        PROJECTS_MENU,
        PROJECT_SELECTED,
        ACTIVITY_SELECTED,

        RESOURCES_MENU,
        RESOURCE_SELECTED,

    }

    private ProjectPortfolioManagerSerializer projectPortfolioManagerSerializer;
    private ProjectPortfolioManager projectPortfolioManager;
    private Scanner scanner;
    private SimpleDateFormat simpleDateFormat;

    private State currentState;
    private Project selectedProject;
    private Activity selectedActivity;
    private Resource selectedResource;

    public CLI(ProjectPortfolioManager projectPortfolioManager, ProjectPortfolioManagerSerializer projectPortfolioManagerSerializer) {
        setProjectPortfolioManagerSerializer(projectPortfolioManagerSerializer);
        setProjectPortfolioManager(projectPortfolioManager);
        this.scanner = new Scanner(System.in);
        this.simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd-HH");
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
                    case RESOURCES_MENU:
                        resourcesMenu();
                        break;
                    case RESOURCE_SELECTED:
                        resourceSelectedMenu();
                        break;
                }
            } catch (Exception e) {
                System.out.println("error: " + e.getMessage() + "\n");
            }
        }
    }

    private void mainMenu() {

        System.out.print("----------------------------\n" +
                "     Project Portfolio Manager\n" +
                "1) Projects\n" +
                "2) Resources\n" +
                "3) Save&Exit\n");

        System.out.print("choose menu item: ");
        switch (scanner.nextInt()) {
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
                System.out.println("Invalid choice.\n");
        }
    }

    private void projectsMenu() throws ParseException {

        System.out.print("----------------------------\n" +
                "     Projects\n" +
                "1) List projects\n" +
                "2) Select project\n" +
                "3) Add project\n" +
                "4) Remove project\n" +
                "5) Back to MainMenu\n"); // TODO: duration by hours of projects

        System.out.print("choose menu item: ");
        switch (scanner.nextInt()) {
            case 1:
                if (projectPortfolioManager.getProjects().isEmpty()) {
                    System.out.println("No projects.");
                } else {
                    System.out.println("All projects are listed below:");
                    for (Project project : projectPortfolioManager.getProjects()) {
                        System.out.print("* " + project.getName() + "\n");
                    }
                }
                break;
            case 2:
                System.out.print("Enter the name of project: ");
                String projectName = scanner.next();
                selectedProject = projectPortfolioManager.findProject(projectName);
                currentState = State.PROJECT_SELECTED;
                break;
            case 3:
                System.out.print("Enter the name of new project: ");
                String newProjectName = scanner.next();
                System.out.print("Enter the description of new project: ");
                String newProjectDescription = scanner.next();
                System.out.print("Enter the start date of new project(in format yyyy-MM-dd-HH): ");
                Date newProjectStartDate = simpleDateFormat.parse(scanner.next());
                projectPortfolioManager.addProject(newProjectName, newProjectDescription, newProjectStartDate);
                System.out.print("Project successfully created.\n");
                break;
            case 4:
                System.out.print("Enter the name of project: ");
                String removeProjectName = scanner.next();
                Project removeProject = projectPortfolioManager.findProject(removeProjectName);
                projectPortfolioManager.removeProject(removeProject);
                System.out.print("Project successfully deleted.\n");
                break;
            case 5:
                currentState = State.MAIN_MENU;
                break;
            default:
                System.out.println("Invalid choice.\n");
        }
    }

    private void projectSelectedMenu() throws ParseException {

        System.out.print("----------------------------\n" +
                "     '" + selectedProject.getName() + "' is selected.\n" +
                "1) List activities\n" +
                "2) Select activity\n" +
                "3) Add activity\n" +
                "4) Remove activity\n" +
                "5) List all assigned resources\n" +
                "6) Update name\n" +
                "7) Update description\n" +
                "8) Back to Projects Menu\n");

        System.out.print("choose menu item: ");
        switch (scanner.nextInt()) {
            case 1:
                if (selectedProject.getActivities().isEmpty()) {
                    System.out.println("No activities.\n");
                } else {
                    System.out.println("All activities are listed below:");
                    for (Activity activity: selectedProject.getActivities()) {
                        System.out.print("* " + activity + "\n");
                    }
                }
                break;
            case 2:
                System.out.print("Enter the id of activity: ");
                int activityId = scanner.nextInt();
                selectedActivity = projectPortfolioManager.findActivity(selectedProject, activityId);
                currentState = State.ACTIVITY_SELECTED;
                break;
            case 3:
                System.out.print("Enter the description of new activity: ");
                String newActivityDescription = scanner.next();
                System.out.print("Enter the start date of new project(in format yyyy-MM-dd-HH): ");
                Date newActivityStartDate = simpleDateFormat.parse(scanner.next());
                System.out.print("Enter the description of new activity: ");
                String newActivityDeliverable = scanner.next();
                projectPortfolioManager.addActivity(selectedProject, newActivityDescription, newActivityStartDate, newActivityDeliverable);
                System.out.print("Activity successfully created.\n");
                break;
            case 4:
                System.out.print("Enter the id of activity: ");
                int removeActivityId = scanner.nextInt();
                projectPortfolioManager.removeActivity(selectedProject, projectPortfolioManager.findActivity(selectedProject, removeActivityId));
                System.out.print("Activity successfully deleted.\n");
                break;
            case 5:
                System.out.print("Enter the name of new project: ");
                String newProjectName = scanner.next();
                System.out.print("Enter the description of new project: ");
                String newProjectDescription = scanner.next();
                System.out.print("Enter the start date of new project(in format yyyy-MM-dd-HH): ");
                Date newProjectStartDate = simpleDateFormat.parse(scanner.next());
                projectPortfolioManager.addProject(newProjectName, newProjectDescription, newProjectStartDate);
                System.out.print("Project successfully created.\n");
                break;
            case 6:
                System.out.print("Enter the name: ");
                String updateProjectName = scanner.next();
                selectedProject.setName(updateProjectName);
                System.out.print("Project name successfully updated.\n");
                break;
            case 7:
                System.out.print("Enter the description: ");
                String updateProjectDescription = scanner.next();
                selectedProject.setDescription(updateProjectDescription);
                System.out.print("Project description successfully updated.\n");
                break;
            case 8:
                currentState = State.PROJECTS_MENU;
                break;
            default:
                System.out.println("Invalid choice.\n");
        }
    }

    private void activitySelectedMenu() {

    }

    private void resourcesMenu() {

        System.out.print("----------------------------\n" +
                "     Resources\n" +
                "1) List resources\n" +
                "2) Select resource\n" +
                "3) Add resource\n" +
                "4) Remove resource\n" +
                "5) Back to MainMenu\n");

        System.out.print("choose menu item: ");
        switch (scanner.nextInt()) {
            case 1:
                if (projectPortfolioManager.getResources().isEmpty()) {
                    System.out.println("No resources.\n");
                } else {
                    System.out.println("All resources are listed below:");
                    for (Resource resource: projectPortfolioManager.getResources()) {
                        System.out.print("* " + resource + "\n");
                    }
                }
                break;
            case 2:
                System.out.print("Enter the id of resource: ");
                int resourceId = scanner.nextInt();
                selectedResource = projectPortfolioManager.findResource(resourceId);
                currentState = State.RESOURCE_SELECTED;
                break;
            case 3:
                System.out.print("----------------------------\n" +
                        "     Resource Types\n" +
                        "1) Employee\n" +
                        "2) Consultant\n" +
                        "Enter the type of project: ");
                int resourceType = scanner.nextInt();
                switch (resourceType) {
                    case 1:
                        System.out.print("Enter the name of new employee: ");
                        String newEmployeeName = scanner.next();
                        projectPortfolioManager.createEmployee(newEmployeeName);
                        System.out.print("Employee successfully created.\n");
                        break;
                    case 2:
                        System.out.print("Enter the name of new consultant: ");
                        String newConsultantName = scanner.next();
                        projectPortfolioManager.createConsultant(newConsultantName);
                        System.out.print("Consultant successfully created.\n");
                        break;
                    default:
                        System.out.println("Invalid choice.\n");
                        return;
                }
                break;
            case 4:
                System.out.print("Enter the id of Resource: ");
                int removeResourceId = scanner.nextInt();
                projectPortfolioManager.removeResource(removeResourceId);
                System.out.print("Resource successfully deleted.\n");
                break;
            case 5:
                currentState = State.MAIN_MENU;
                break;
            default:
                System.out.println("Invalid choice.\n");
        }
    }

    private void resourceSelectedMenu() {

        System.out.print("----------------------------\n" +
                "     '" + selectedResource + "' is selected.\n");

        if (selectedResource.getClass().equals(Person.class)) {

            Person selectedPerson = (Person) selectedResource;

            System.out.print("1) Update name\n" +
                    "2) Back to Resources Menu\n");

            System.out.print("choose menu item: ");
            switch (scanner.nextInt()) {
                case 1:
                    System.out.print("Enter the name: ");
                    String name = scanner.next();
                    selectedPerson.setName(name);
                    System.out.print("Person name successfully updated.\n");
                    break;
                case 2:
                    currentState = State.RESOURCES_MENU;
                    break;
                default:
                    System.out.println("Invalid choice.\n");
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
