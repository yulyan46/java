package client;

/**
 * Created by iulian on 07.01.2017.
 */

public class SimpleService {
    private String name;
    private String uid;
    private String description;

    public SimpleService(String name, String uid, String description) {
        this.name = name;
        this.uid = uid;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return name;
    }
}

