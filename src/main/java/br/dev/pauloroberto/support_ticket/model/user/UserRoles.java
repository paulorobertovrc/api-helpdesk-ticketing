package br.dev.pauloroberto.support_ticket.model.user;

public enum UserRoles {
    ADMIN("Admin"),
    MODERATOR("Moderator"),
    USER("User");

    private final String description;

    UserRoles(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public static UserRoles fromDescription(String description) {
        for (UserRoles userRole : UserRoles.values()) {
            if (userRole.getDescription().equals(description)) {
                return userRole;
            }
        }
        return null;
    }
}
