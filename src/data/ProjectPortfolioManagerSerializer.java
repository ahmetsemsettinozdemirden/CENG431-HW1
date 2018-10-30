package data;

import business.ProjectPortfolioManager;

import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ProjectPortfolioManagerSerializer {

    // TODO: take care of Exceptions
    private final String folderName = "appdata";
    private final String fileNamePrefix = "Projects-";
    private final DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");


    public ProjectPortfolioManager loadLatest() throws IOException, ClassNotFoundException {

        File appdata = new File("appdata");
        if (!appdata.exists() || !appdata.isDirectory())
            return null; // TODO: throw exception -> `appdata should exist and be a folder`

        File[] projectsFiles = appdata.listFiles();
        if (projectsFiles == null)
            throw new FileNotFoundException();

        FileInputStream fileInputStream = new FileInputStream(findLatestFile(projectsFiles));
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
        ProjectPortfolioManager projectPortfolioManager = (ProjectPortfolioManager) objectInputStream.readObject();
        objectInputStream.close();
        return projectPortfolioManager;
    }

    public void save(ProjectPortfolioManager projectPortfolioManager) throws Exception {
        File file = new File(folderName + "/" + fileNamePrefix + dateFormat.format(new Date()) +  " .txt");
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        objectOutputStream.writeObject(projectPortfolioManager);
        objectOutputStream.flush();
        objectOutputStream.close();
    }

    private File findLatestFile(File[] projectsFiles) throws FileNotFoundException {
        String fileName = fileNamePrefix + dateFormat.format(new Date()) +  " .txt";

        for (File file: projectsFiles) {
            if (file.getName().equals(fileName)) {
                return file;
            }
        }

        throw new FileNotFoundException();
    }

}
