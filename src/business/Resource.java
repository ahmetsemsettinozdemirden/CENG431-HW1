package business;

import java.util.ArrayList;
import java.util.List;

public abstract class Resource {

    private int resourceId;
    private List<Task> tasks;

    public Resource(int resourceId) {
        setResourceId(resourceId);
        this.tasks = new ArrayList<>();
    }

    public int getResourceId() {
        return resourceId;
    }

    public void setResourceId(int resourceId) {
        if (resourceId < 0)
            throw new IllegalArgumentException("resourceId can not be less than 0.");
        this.resourceId = resourceId;
    }

    public List<Task> getTasks() {
        return tasks;
    }

}
