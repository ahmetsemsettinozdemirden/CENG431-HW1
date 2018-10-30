package data;

import business.ProjectPortfolioManager;

import java.io.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class ProjectPortfolioManagerSerializer {

    // TODO: take care of Exceptions
    private final String folderName = "appdata";
    private final String fileNamePrefix = "Projects-";
    private final DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");


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
        File file = new File(folderName + "/" + fileNamePrefix + dateFormat.format(new Date()) +  ".txt");
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        objectOutputStream.writeObject(projectPortfolioManager);
        objectOutputStream.flush();
        objectOutputStream.close();
    }

    private File findLatestFile(File[] projectsFiles) {

        List<Date> fileDates = new ArrayList<>();

        for (File file: projectsFiles) {
            String name = file.getName().replace(fileNamePrefix, "").replace(".txt", "");
            try {
                fileDates.add(dateFormat.parse(name));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        fileDates.sort(Collections.reverseOrder());

        for (File file: projectsFiles) {
            if (file.getName().equals(returnFileName(fileDates.get(0)))) {
                return file;
            }
        }

        // if no file detected, create new ProjectPortfolioManager object and save it to new txt file
        // then return that created file

        File file = new File(folderName + "/" + fileNamePrefix + dateFormat.format(new Date()) +  ".txt");

        try {
            if(file.createNewFile()) {
                System.out.println("New project file created..");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            save(new ProjectPortfolioManager());
        } catch (Exception e) {
            System.out.print("Error occurred while saving! " + e.getMessage() + "\n");
        }
        return file;
    }

    private String returnFileName (Date date) {
        return fileNamePrefix + dateFormat.format(date) +  ".txt";
    }

}
