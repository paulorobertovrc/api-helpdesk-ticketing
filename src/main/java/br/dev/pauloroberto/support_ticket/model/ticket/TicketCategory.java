package br.dev.pauloroberto.support_ticket.model.ticket;

public enum TicketCategory {
    BUG("Bug"),
    FEATURE("Feature"),
    SUPPORT("Support"),
    OTHER("Other");

    private final String description;

    TicketCategory(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public static TicketCategory fromDescription(String description) {
        for (TicketCategory ticketCategory : TicketCategory.values()) {
            if (ticketCategory.getDescription().equals(description)) {
                return ticketCategory;
            }
        }
        return null;
    }
}
