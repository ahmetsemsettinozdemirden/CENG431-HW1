package business;

import java.io.Serializable;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class Task implements Serializable, Hourly, Resource.Resourcable {

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

    @Override
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

    @Override
    public List<Resource> getResources() {
        return Collections.singletonList(resource);
    }

    public void setResource(Resource resource) {
        this.resource = resource;
    }

    @Override
    public String toString() {
        String resourceText = getResource() == null ? "no resource assigned" : getResource().toString();
        return "Id: " + this.getId() +
                ", Description: " + this.getDescription() +
                ", Hours: " + this.getHours() +
                ", Start date: " + this.getStartDate().toString() +
                ", Resource: " + resourceText +
                " (" + this.getClass().getSimpleName() + ")";
    }

}
