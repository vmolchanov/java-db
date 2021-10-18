package dbService.dataSets;

public class UsersDataSet {
    private long id;

    private String email;

    public UsersDataSet(long id, String email) {
        this.id = id;
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public long getId() {
        return id;
    }
}
