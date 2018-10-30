package presentation;

import business.ProjectManagementSystem;

import java.util.Scanner;

public class CLI {

    private enum State {

        MAIN_MENU,

        PROJECTS_MENU,
        PROJECT_SELECTED,

        RESOURCES_MENU,

    }

    private ProjectManagementSystem projectManagementSystem;
    private State currentState;
    private Scanner scanner;

    // TODO: inject serializer
    public CLI(ProjectManagementSystem projectManagementSystem) {
        setProjectManagementSystem(projectManagementSystem);
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

        System.out.print("  Project Management System\n" +
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
                // TODO: save
                System.exit(0);
                break;
            default:
                System.out.println("Invalid choice.");
        }
    }

    private void projectsMenu() {

        System.out.print("  Projects\n" +
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
                // TODO
                break;
            case 3:
                // TODO: sa
                break;
            case 4:
                // TODO: sa
                break;
            case 5:
                // TODO: sa
                break;
            default:
                System.out.println("Invalid choice.");
        }
    }

    private void resourcesMenu() {

        System.out.print("  Resources\n" +
                "1) Select resource\n" +
                "2) List resources\n" +
                "3) Add resource\n" +
                "4) Delete resource\n" +
                "5) Back to MainMenu\n");
    }

    private void setProjectManagementSystem(ProjectManagementSystem projectManagementSystem) {
        if (projectManagementSystem == null)
            throw new IllegalArgumentException("projectManagementSystem can not be null.");
        this.projectManagementSystem = projectManagementSystem;
    }

}
