
INSERT INTO rentalcompany.tb_customer (public_id, name, document, email, phone, street, number, complement, neighborhood, city, state, zip_code, is_suspended, has_pendency)
VALUES

    ('a1b2c3d4-e5f6-7890-1234-567890abcdef', 'Carlos Eduardo Montenegro', '12345678901', 'carlos.edu@emailteste.com',
     '11987654321', 'Rua das Flores', '123', 'Apto 101', 'Jardim Primavera', 'São Paulo', 'SP', '01234-567', false, true),


    ('f0e9d8c7-b6a5-4321-fedc-ba9876543210', 'Soluções em TI Ltda', '12345678000199', 'contato@solucoesti.com', '21912345678',
     'Avenida Principal', '1500', 'Sala 302', 'Centro Empresarial', 'Rio de Janeiro', 'RJ', '20000-123', false, true);


INSERT INTO rentalcompany.tb_vehicle (
public_id, vehicle_model_name, vehicle_type, vehicle_year, door_quantity, fuel_type, license_plate, color,
mileage, vehicle_status, renavam, vin, fipe_code, daily_rate_in_cents, fipe_value_in_cents, image_url, creation_date, update_date
)
VALUES

    ('a1a2a3a4-b5b6-c7c8-d9d0-e1e2e3e4e5f6', 'Jeep Compass', 'SUV', 2023, 4, 'FLEX' 'RST1A23', 'Branco', '15000',
     'RESERVED', '12345678901', '9BWZZZ377VT123456', '011193-4', 16700, 15500000,
     'https://www.webmotors.com.br/imagens/prod/379646/JEEP_COMPASS_2.0_HURRICANE_4_TURBO_GASOLINA_BLACKHAWK_AT9_37964616111535530.webp',
     CURRENT_TIMESTAMP, NULL),

    ('b1b2b3b4-c5c6-d7d8-e9e0-f1f2f3f4f5g6', 'Toyota Corolla', 'SEDAN', 2024, 4, 'HIBRIDO', 'XYZ9B87', 'Vermelho', '5000',
     'RESERVED', '98765432109', '9BDLMN654RT987654','002187-3', 21400, 18050000,
     'https://www.tsusho.com.br/pub/modelos/corolla/cores-2024/vermelho_granada.png',CURRENT_TIMESTAMP, NULL);


INSERT INTO rentalcompany.tb_reserve (public_id, customer_name, customer_document, vehicle_model_name, vehicle_year,
vehicle_license_plate, amount_in_cents, rental_date, return_date, reservation_status, payment_status, creation_date,
update_date
)
VALUES

    ('c1c2c3c4-d5d6-e7e8-f9f0-a1a2a3a4a5b6', 'Carlos Eduardo Montenegro', '12345678901', 'Jeep Compass', 2023, 'RST1A23',
     75000, '2025-09-10', '2025-09-15', 'ACTIVE', 'PENDING', CURRENT_TIMESTAMP, NULL),

    ('d1d2d3d4-e5e6-f7f8-g9g0-h1h2h3h4h5i6', 'Soluções em TI Ltda', '12345678000199', 'Toyota Corolla', 2024, 'XYZ9B87',
     120000, '2025-10-01', '2025-10-11', 'ACTIVE', 'PENDING', CURRENT_TIMESTAMP, NULL);