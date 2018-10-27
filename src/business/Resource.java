package business;

public abstract class Resource {

    private int id;

    public Resource(int id) {
        setId(id);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        if (id < 0)
            throw new IllegalArgumentException("id can not be less than 0.");
        this.id = id;
    }

}
