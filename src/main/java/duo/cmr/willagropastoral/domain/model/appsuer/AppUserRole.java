package duo.cmr.willagropastoral.domain.model.appsuer;

public enum AppUserRole {
    USER,
    ADMIN,
    LEADER;

    public String getName() {
        return "ROLE_" + this.name();
    }
}
