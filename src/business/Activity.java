package business;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Activity implements Serializable, Hourly, Resource.Resourcable {

    private int id;
    private String description;
    private String deliverable;
    private Date startDate;
    private List<Task> tasks;

    public Activity(int id, String description, Date startDate, String deliverable) {
        setId(id);
        setDescription(description);
        setStartDate(startDate);
        setDeliverable(deliverable);
        this.tasks = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        if (id < 0)
            throw new IllegalArgumentException("id can not be less than 0.");
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        if (description == null || description.equals(""))
            throw new IllegalArgumentException("description can not be null or empty.");
        this.description = description;
    }

    public String getDeliverable() {
        return deliverable;
    }

    public void setDeliverable(String deliverable) {
        if (deliverable == null || deliverable.equals(""))
            throw new IllegalArgumentException("deliverable can not be null or empty.");
        this.deliverable = deliverable;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        if (startDate == null)
            throw new IllegalArgumentException("startDate can not be null.");
        this.startDate = startDate;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    @Override
    public int getHours() {
        int hours = 0;
        for (Task task: tasks) {
            hours += task.getHours();
        }
        return hours;
    }

    @Override
    public List<Resource> getResources() {
        List<Resource> resources = new ArrayList<>();
        for (Task task: tasks) {
            if (task.getResource() != null) {
                resources.add(task.getResource());
            }
        }
        return resources;
    }

    @Override
    public String toString() {
        return "Id: " + this.getId() +
                ", description: " + this.getDescription() +
                ", deliverable: " + this.getDeliverable() +
                ", hours: " + this.getHours() +
                ", start date: " + this.getStartDate().toString() +
                ", tasks count: " + this.getTasks().size() +
                " (" + this.getClass().getSimpleName() + ")";
    }
}