package business;

import java.io.Serializable;
import java.util.Date;

public class Task implements Serializable {

    private int number;
    private String description;
    private Date startDate;
    private int hours;
    private int resourceId;

    public Task(int number, String description, Date startDate, int hours) {
        setNumber(number);
        setDescription(description);
        setStartDate(startDate);
        setHours(hours);
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
        return hours;
    }

    public void setHours(int hours) {
        if (hours < 0)
            throw new IllegalArgumentException("hours can not be less than 0.");
        this.hours = hours;
    }

    public int getResourceId() {
        return resourceId;
    }

    public void setResourceId(int resourceId) {
        if (resourceId < 0)
            throw new IllegalArgumentException("resource can not be less than 0.");
        this.resourceId = resourceId;
    }

}
