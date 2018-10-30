package presentation;

import business.Project;
import business.ProjectPortfolioManager;
import data.ProjectPortfolioManagerSerializer;

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

    // TODO: inject serializer
    public CLI(ProjectPortfolioManager projectPortfolioManager, ProjectPortfolioManagerSerializer projectPortfolioManagerSerializer) {
        setProjectPortfolioManager(projectPortfolioManager);
        this.projectPortfolioManagerSerializer = projectPortfolioManagerSerializer;
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        this.currentState = State.MAIN_MENU;
        while (true) {
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
        }
    }

    private void mainMenu() {

        System.out.print("\n----------------------------\n" +
                "=> Project Portfolio Manager\n" +
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
                    e.printStackTrace();
                }
                System.exit(0);
                break;
            default:
                System.out.println("Invalid choice.");
        }
    }

    private void projectsMenu() {

        System.out.print("\n----------------------------\n" +
                "=> Projects\n" +
                "1) Select project\n" +
                "2) List projects\n" +
                "3) Add project\n" +
                "4) Delete project\n" +
                "5) Back to MainMenu\n");

        System.out.print("choose menu item: ");
        switch (scanner.nextInt()) {
            case 1:
                // TODO: select project
                break;
            case 2:
                for (Project project: projectPortfolioManager.getProjects()) {
                    System.out.print("* " + project.getName());
                }
                break;
            case 3:
                System.out.print("Enter the name of project: ");
                String name = scanner.next();
                System.out.print("Enter the description of project: ");
                String description = scanner.next();
                System.out.print("Enter the start date of project: ");
                Date date = new Date();
                projectPortfolioManager.addProject(name, description, date);

                System.out.print("Project successfully created.");
                break;
            case 4:
                // TODO: sa
                break;
            case 5:
                currentState = State.MAIN_MENU;
                break;
            default:
                System.out.println("Invalid choice.");
        }
    }

    private void resourcesMenu() {

        System.out.print("\n----------------------------\n" +
                "=> Resources\n" +
                "1) Select resource\n" +
                "2) List resources\n" +
                "3) Add resource\n" +
                "4) Delete resource\n" +
                "5) Back to MainMenu\n");
    }

    private void setProjectPortfolioManager(ProjectPortfolioManager projectPortfolioManager) {
        if (projectPortfolioManager == null)
            throw new IllegalArgumentException("projectPortfolioManager can not be null.");
        this.projectPortfolioManager = projectPortfolioManager;
    }

}
