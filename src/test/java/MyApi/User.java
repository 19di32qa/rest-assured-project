package MyApi;

import java.io.Serializable;

public class User implements Serializable {

    public int id;
    public String name;
    public String lastName;

    public User(int id, String name, String lastName) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
    }
}
