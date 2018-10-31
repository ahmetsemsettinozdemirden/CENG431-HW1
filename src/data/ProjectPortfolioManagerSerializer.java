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
            throw new AppdataFolderNotFoundException("Appdata folder not found!");

        File[] projectsFiles = appdata.listFiles();
        if (projectsFiles == null)
            throw new IOException("No backup files found");

        FileInputStream fileInputStream = new FileInputStream(findLatestFile(projectsFiles));
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
        ProjectPortfolioManager projectPortfolioManager = (ProjectPortfolioManager) objectInputStream.readObject();
        objectInputStream.close();
        return projectPortfolioManager;
    }

    public void save(ProjectPortfolioManager projectPortfolioManager) throws Exception {
        File file = new File(returnFilePath(new Date()));
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        objectOutputStream.writeObject(projectPortfolioManager);
        objectOutputStream.flush();
        objectOutputStream.close();
    }

    private File findLatestFile(File[] projectsFiles) {

        // if project backup files detected, find and return latest one
        if (projectsFiles.length > 0) {
            List<Date> fileDates = new ArrayList<>();

            for (File file: projectsFiles) {
                String name = file.getName().replace(fileNamePrefix, "").replace(".txt", "");
                try {
                    fileDates.add(dateFormat.parse(name));
                } catch (ParseException e) {
                    throw new InvalidFileNameException("Found files with invalid names at Appdata folder.");
                }
            }

            // sort files wrt. their dates
            fileDates.sort(Collections.reverseOrder());

            return new File(returnFilePath(fileDates.get(0)));
        }

        // if no file detected, create new project backup file then return that created file
        else {
            File file = new File(returnFilePath(new Date()));

            try {
                if(file.createNewFile()) {
                    System.out.println("New project backup file created..");
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
    }

    private String returnFilePath(Date date) {
        return folderName + "/" + fileNamePrefix + dateFormat.format(date) +  ".txt";
    }

    public static class AppdataFolderNotFoundException extends RuntimeException {
        public AppdataFolderNotFoundException(String error) {
            super(error);
        }
    }

    public static class InvalidFileNameException extends RuntimeException {
        public InvalidFileNameException(String error) {
            super(error);
        }
    }
}
