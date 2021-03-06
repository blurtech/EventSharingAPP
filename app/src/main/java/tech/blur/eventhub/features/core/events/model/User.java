package tech.blur.eventhub.features.core.events.model;

public class User {

    private String id;
    private String login;
    private String name;
    private String password;
    private String birhday; //type?
    private int sex;

    public User(String id, String login,String password, String name, String birhday, int sex) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.name = name;
        this.birhday = birhday;
        this.sex = sex;
    }

    public User(String login,String password, String name, String birhday, int sex) {
        this.login = login;
        this.password = password;
        this.name = name;
        this.birhday = birhday;
        this.sex = sex;
    }

    public String getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public String getName() {
        return name;
    }

    public String getBirhday() {
        return birhday;
    }

    public int getSex() {
        return sex;
    }
}
