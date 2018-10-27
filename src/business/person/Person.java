package business.person;

import business.Resource;

public abstract class Person extends Resource {

    private String name;

    public Person(int id, String name) {
        super(id);
        setName(name);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null || name.equals(""))
            throw new IllegalArgumentException("name can not be null or empty.");
        this.name = name;
    }

}
