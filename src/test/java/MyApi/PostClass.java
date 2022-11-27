package MyApi;

import java.io.Serializable;

public class PostClass implements Serializable {
    public int id;
    public String title;
    public String author;

    public PostClass(int id,String title,String author) {
        this.id = id;
        this.title = title;
        this.author = author;
    }

}
