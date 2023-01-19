package br.dev.pauloroberto.support_ticket.domain.model.user;

public enum UserRoles {
    ROLE_ADMIN("Admin"),
    ROLE_MODERATOR("Moderator"),
    ROLE_USER("User");

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
