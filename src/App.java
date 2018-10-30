import business.Project;
import business.ProjectPortfolioManager;
import data.ProjectPortfolioManagerSerializer;
import presentation.CLI;

public class App {

    public static void main(String[] args) throws Exception {
        ProjectPortfolioManager projectPortfolioManager;
        ProjectPortfolioManagerSerializer projectPortfolioManagerSerializer = new ProjectPortfolioManagerSerializer();

        try {
            projectPortfolioManager = projectPortfolioManagerSerializer.loadLatest();
        } catch (Exception e) {
            throw new Exception();
        }

        CLI cli = new CLI(projectPortfolioManager, projectPortfolioManagerSerializer);
        cli.start();
    }

}
