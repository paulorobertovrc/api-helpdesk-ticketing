ALTER TABLE helpdesk.tickets
    ADD updatedAt DATETIME DEFAULT NULL AFTER created_at,
    ADD closedAt DATETIME DEFAULT NULL AFTER updatedAt,
    ADD closedBy BIGINT DEFAULT NULL AFTER closedAt,
    ADD deleted boolean DEFAULT false,
    ADD deletedAt DATETIME DEFAULT NULL AFTER deleted,
    ADD deletedBy BIGINT DEFAULT NULL AFTER deletedAt;
