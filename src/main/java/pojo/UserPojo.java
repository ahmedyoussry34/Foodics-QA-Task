package pojo;

public class UserPojo {

    private String name;
    private String job;

    public UserPojo(String name, String job) {
        this.name = name;
        this.job = job;
    }

    public UserPojo() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

}
