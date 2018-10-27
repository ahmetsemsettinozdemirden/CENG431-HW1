package business;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Activity implements Serializable {

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
}
