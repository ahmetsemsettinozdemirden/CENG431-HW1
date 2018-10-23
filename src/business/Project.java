package business;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Project implements Serializable {

    private String name;
    private String description;
    private Date startDate;
    private List<Activity> activities;

    public Project(String name, String description, Date startDate) {
        setName(name);
        setDescription(description);
        setStartDate(startDate);
        setActivities(new ArrayList<>());
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

    public void setActivities(List<Activity> activities) {
        if (activities == null)
            throw new IllegalArgumentException("activities can not be null.");
        this.activities = activities;
    }

}