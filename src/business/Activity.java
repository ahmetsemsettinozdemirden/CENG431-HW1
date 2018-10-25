package business;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Activity implements Serializable {

    private int number;
    private String description;
    private Date startDate;
    private String deliverable;
    private List<Task> tasks;

    public Activity(int number, String description, Date startDate, String deliverable) {
        setNumber(number);
        setDescription(description);
        setStartDate(startDate);
        setDeliverable(deliverable);
        this.tasks = new ArrayList<>();
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        if (number < 0)
            throw new IllegalArgumentException("number can not be less than 0.");
        this.number = number;
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

    public int getHours() {
        int hours = 0;
        for (Task task: tasks)
            hours += task.getHours();
        return hours;
    }

    public String getDeliverable() {
        return deliverable;
    }

    public void setDeliverable(String deliverable) {
        if (deliverable == null || deliverable.equals(""))
            throw new IllegalArgumentException("deliverable can not be null or empty.");
        this.deliverable = deliverable;
    }

    public List<Task> getTasks() {
        return tasks;
    }

}
