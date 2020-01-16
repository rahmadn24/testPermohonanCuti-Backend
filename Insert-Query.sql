INSERT INTO position(position_id, created_by, created_date, position_name, update_by, update_date, level, position_leave_id)
    VALUES (nextval('hibernate_sequence'), 'System', now(), 'employee', 'System', now(), 1, null),
    (nextval('hibernate_sequence'), 'System', now(), 'staff', 'System', now(), 2, null),
    (nextval('hibernate_sequence'), 'System', now(), 'supervisor', 'System', now(), 3, null);

INSERT INTO position_leave(position_leave_id, created_by, created_date, leave_approve, 
            update_by, update_date, position_id)
    VALUES (nextval('hibernate_sequence'), 'System', now(), 12, 'System', now(), (select position_id from position where position_name = 'employee')),
    (nextval('hibernate_sequence'), 'System', now(), 13, 'System', now(), (select position_id from position where position_name = 'staff')),
    (nextval('hibernate_sequence'), 'System', now(), 15, 'System', now(), (select position_id from position where position_name = 'supervisor'));
