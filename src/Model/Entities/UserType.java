package Model.Entities;

public enum UserType {
    ADMIN(1),
    USER(0),;

    private final int id;
    private UserType(int i) {
        this.id = i;
    }

    public int getPrivilegeId() {
        return id;
    }
}
