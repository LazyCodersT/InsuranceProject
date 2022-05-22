package Model.Entities;

public class InsuranceUser {
    private User user;
    private Insurance insurance;

    public InsuranceUser() {}

    public InsuranceUser(User user, Insurance insurance) {
        this.user = user;
        this.insurance = insurance;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Insurance getInsurance() {
        return insurance;
    }

    public void setInsurance(Insurance insurance) {
        this.insurance = insurance;
    }
}
