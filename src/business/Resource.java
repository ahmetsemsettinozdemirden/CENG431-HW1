package business;

public abstract class Resource {

    private int resourceId;

    public Resource(int resourceId) {
        setResourceId(resourceId);
    }

    public int getResourceId() {
        return resourceId;
    }

    private void setResourceId(int resourceId) {
        if (resourceId < 0)
            throw new IllegalArgumentException("resourceId can not be less than 0.");
        this.resourceId = resourceId;
    }

}
