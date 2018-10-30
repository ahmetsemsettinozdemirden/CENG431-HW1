package business;

import java.util.ArrayList;
import java.util.List;

public abstract class Resource {

    public interface Resourcable {

        List<Resource> getResources();

    }

    private int id;
    private List<Task> tasks;

    public Resource(int id) {
        setId(id);
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

    public List<Task> getTasks() {
        return tasks;
    }

}
