/*INSERT INTO profile ( name, surname, email, phone, password, status, visible, created_date, role)
VALUES ('Adminjon', 'Adminov', 'admin5@gmail.com', '998915721235', 'JRSIpuUOBrlNMJrx2URi', 'ACTIVE', true, now(),
        'ROLE_ADMIN') ON CONFLICT (id) DO NOTHING;
SELECT setval('profile_id_seq', max(id))
FROM profile;
*/