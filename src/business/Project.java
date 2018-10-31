package business;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Project implements Serializable, Hourly, Resource.Resourcable {

    private String name;
    private String description;
    private Date startDate;
    private List<Activity> activities;

    public Project(String name, String description, Date startDate) {
        setName(name);
        setDescription(description);
        setStartDate(startDate);
        this.activities = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null || name.equals(""))
            throw new IllegalArgumentException("name can not be null or empty.");
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        if (description == null || description.equals(""))
            throw new IllegalArgumentException("description can not be null or empty.");
        this.description = description;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        if (startDate == null)
            throw new IllegalArgumentException("startDate can not be null.");
        this.startDate = startDate;
    }

    public List<Activity> getActivities() {
        return activities;
    }

    @Override
    public int getHours() {
        int hours = 0;
        for (Activity activity: activities) {
            hours += activity.getHours();
        }
        return hours;
    }

    @Override
    public List<Resource> getResources() {
        List<Resource> resources = new ArrayList<>();
        for (Activity activity: activities) {
            resources.addAll(activity.getResources());
        }
        return resources;
    }

    @Override
    public String toString() {
        return "Project name: " + this.getName() +
                ", description: " + this.getDescription() +
                ", hours: " + this.getHours() +
                ", start date: " + this.getStartDate().toString() +
                ", activities count: " + this.getActivities().size() +
                " (" + this.getClass().getSimpleName() + ")";
    }
}