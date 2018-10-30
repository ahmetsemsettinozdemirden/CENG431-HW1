package presentation;

import business.Project;
import business.ProjectPortfolioManager;
import business.Resource;
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

        RESOURCES_MENU,

    }

    private ProjectPortfolioManagerSerializer projectPortfolioManagerSerializer;
    private ProjectPortfolioManager projectPortfolioManager;
    private State currentState;
    private Scanner scanner;
    private SimpleDateFormat simpleDateFormat;
    private Project selectedProject;

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
                    case RESOURCES_MENU:
                        resourcesMenu();
                        break;
                    case PROJECT_SELECTED:
    //                     projectSelectedMenu();
                        break;
                }
            } catch (Exception e) {
                System.out.print(e.getMessage());
            }
        }
    }

    private void mainMenu() {

        System.out.print("\n----------------------------\n" +
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
                    System.out.print("Error occured while saving! " + e.getMessage());
                }
                System.exit(0);
                break;
            default:
                System.out.println("Invalid choice.");
        }
    }

    private void projectsMenu() throws ParseException {

        System.out.print("\n----------------------------\n" +
                "     Projects\n" +
                "1) Select project\n" +
                "2) List projects\n" +
                "3) Add project\n" +
                "4) Remove project\n" +
                "5) Back to MainMenu\n");

        System.out.print("choose menu item: ");
        switch (scanner.nextInt()) {
            case 1:
                System.out.print("Enter the name of project: ");
                String projectName = scanner.next();
                selectedProject = projectPortfolioManager.findProject(projectName);
                currentState = State.PROJECT_SELECTED;
                break;
            case 2:
                if (projectPortfolioManager.getProjects().isEmpty()) {
                    System.out.println("No projects.");
                } else {
                    System.out.println("All projects are listed below:");
                    for (Project project : projectPortfolioManager.getProjects()) {
                        System.out.print("* " + project.getName());
                    }
                }
                break;
            case 3:
                System.out.print("Enter the name of project: ");
                String newProjectName = scanner.next();
                System.out.print("Enter the description of project: ");
                String newProjectDescription = scanner.next();
                System.out.print("Enter the start date of project(in format yyyy-MM-dd-HH): ");
                Date newProjectStartDate = simpleDateFormat.parse(scanner.next());
                projectPortfolioManager.addProject(newProjectName, newProjectDescription, newProjectStartDate);
                System.out.print("Project successfully created.");
                break;
            case 4:
                System.out.print("Enter the name of project: ");
                String removeProjectName = scanner.next();
                Project removeProject = projectPortfolioManager.findProject(removeProjectName);
                projectPortfolioManager.removeProject(removeProject);
                System.out.print("Project successfully deleted.");
                break;
            case 5:
                currentState = State.MAIN_MENU;
                break;
            default:
                System.out.println("Invalid choice.");
        }
    }


    private void resourcesMenu() throws ParseException {

        System.out.print("\n----------------------------\n" +
                "     Resources\n" +
                "1) Select resource\n" +
                "2) List resources\n" +
                "3) Add resource\n" +
                "4) Remove resource\n" +
                "5) Back to MainMenu\n");

        System.out.print("choose menu item: ");
        switch (scanner.nextInt()) {
            case 1:
                System.out.print("\n----------------------------\n" +
                        "     Resource Types\n" +
                        "1) Employee\n" +
                        "2) Consultant\n" +
                        "Enter the type of project: ");
                int resourceType = scanner.nextInt();

                currentState = State.PROJECT_SELECTED;
                break;
            case 2:
                if (projectPortfolioManager.getProjects().isEmpty()) {
                    System.out.println("No projects.");
                } else {
                    System.out.println("All projects are listed below:");
                    for (Project project : projectPortfolioManager.getProjects()) {
                        System.out.print("* " + project.getName());
                    }
                }
                break;
            case 3:
                System.out.print("Enter the name of project: ");
                String newProjectName = scanner.next();
                System.out.print("Enter the description of project: ");
                String newProjectDescription = scanner.next();
                System.out.print("Enter the start date of project: ");
                Date newProjectStartDate = simpleDateFormat.parse(scanner.next());
                projectPortfolioManager.addProject(newProjectName, newProjectDescription, newProjectStartDate);
                System.out.print("Project successfully created.");
                break;
            case 4:
                System.out.print("Enter the name of project: ");
                String removeProjectName = scanner.next();
                Project removeProject = projectPortfolioManager.findProject(removeProjectName);
                projectPortfolioManager.removeProject(removeProject);
                System.out.print("Project successfully deleted.");
                break;
            case 5:
                currentState = State.MAIN_MENU;
                break;
            default:
                System.out.println("Invalid choice.");
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
