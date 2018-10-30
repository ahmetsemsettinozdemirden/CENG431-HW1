import business.ProjectPortfolioManager;
import data.ProjectPortfolioManagerSerializer;
import presentation.CLI;

public class App {

    public static void main(String[] args) throws Exception {
        ProjectPortfolioManagerSerializer projectPortfolioManagerSerializer = new ProjectPortfolioManagerSerializer();
        ProjectPortfolioManager projectPortfolioManager = projectPortfolioManagerSerializer.loadLatest();

        CLI cli = new CLI(projectPortfolioManager, projectPortfolioManagerSerializer);
        cli.start();
    }

}
