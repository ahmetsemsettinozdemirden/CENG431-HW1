import business.ProjectPortfolioManager;
import data.ProjectPortfolioManagerSerializer;
import presentation.CLI;

public class App {

    public static void main(String[] args) {

        ProjectPortfolioManagerSerializer projectPortfolioManagerSerializer = new ProjectPortfolioManagerSerializer();
        ProjectPortfolioManager projectPortfolioManager;

        try {
            projectPortfolioManager = projectPortfolioManagerSerializer.loadLatest();
        } catch (Exception e) {
            System.out.print("load file error: " + e.getMessage());
            return;
        }

        CLI cli = new CLI(projectPortfolioManager, projectPortfolioManagerSerializer);
        cli.start();
    }

}
