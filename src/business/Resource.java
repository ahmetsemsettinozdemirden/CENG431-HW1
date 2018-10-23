package business;

import java.util.List;

public interface Resource {

    int getResourceId();
    void setResourceId(int resourceId);

    List<Task> getTasks();

}
