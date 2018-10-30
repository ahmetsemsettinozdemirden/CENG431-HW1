package business.person;

public class Consultant extends Person {

    public Consultant(int id, String name) {
        super(id, name);
    }

    @Override
    public String toString() {
        return "Id: " + this.getId() + ", name: " + this.getName() + " (" + this.getClass().getName() + ")";
    }
}
