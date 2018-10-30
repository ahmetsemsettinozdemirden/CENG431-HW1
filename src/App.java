import business.Project;
import business.ProjectPortfolioManager;
import data.ProjectSerializer;
import presentation.CLI;

import java.util.ArrayList;
import java.util.List;

public class App {

    public static void main(String[] args) {
        ProjectPortfolioManager projectPortfolioManager = new ProjectPortfolioManager();
        ProjectSerializer projectSerializer = new ProjectSerializer();

        List<Project> projectList = new ArrayList<>();
        try {
            projectList =  projectSerializer.loadLatestProjects();
        } catch (Exception e) {
            e.printStackTrace();
        }

        for (Project project: projectList) {
            projectPortfolioManager.addProject(project.getName(), project.getDescription(), project.getStartDate());
        }

        CLI cli = new CLI(projectPortfolioManager, projectSerializer);
        cli.start();
    }

}
