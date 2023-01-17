package br.dev.pauloroberto.support_ticket.domain.model.ticket;

public enum TicketStatus {
    OPEN("Open"),
    IN_PROGRESS("In Progress"),
    SOLVED("Solved"),
    CLOSED("Closed"),
    DELETED("Deleted");

    private final String description;

    TicketStatus(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public static TicketStatus fromDescription(String description) {
        for (TicketStatus ticketStatus : TicketStatus.values()) {
            if (ticketStatus.getDescription().equals(description)) {
                return ticketStatus;
            }
        }
        return null;
    }
}
