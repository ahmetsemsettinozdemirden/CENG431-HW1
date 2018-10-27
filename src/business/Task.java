package business;

import java.io.Serializable;
import java.util.Date;

public class Task implements Serializable {

    private int id;
    private String description;
    private int hours;
    private Date startDate;
    private Resource resource;

    public Task(int id, String description, int hours, Date startDate) {
        setId(id);
        setDescription(description);
        setHours(hours);
        setStartDate(startDate);
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

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        if (hours < 0)
            throw new IllegalArgumentException("hours can not be less than 0.");
        this.hours = hours;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        if (startDate == null)
            throw new IllegalArgumentException("startDate can not be null.");
        this.startDate = startDate;
    }

    public Resource getResource() {
        return resource;
    }

    public void setResource(Resource resource) {
        if (resource == null)
            throw new IllegalArgumentException("resource can not be null.");
        this.resource = resource;
    }

}
