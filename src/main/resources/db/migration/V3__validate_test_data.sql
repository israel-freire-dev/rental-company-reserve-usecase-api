
-- ===================================================================================
-- Massa de Clientes para Validação
-- ===================================================================================
INSERT INTO rentalcompany.tb_customer (
    public_id, name, document, email, phone, street, number, complement,
    neighborhood, city, state, zip_code, is_suspended, has_pendency
)
VALUES

    ('11111111-aaaa-bbbb-cccc-000000000001', 'Mariana Costa', '33344455566', 'mariana.costa@test.com', '11911112222', 'Rua da Dívida', '10', null, 'Centro', 'São Paulo', 'SP', '01001-000', false, true),


    ('22222222-aaaa-bbbb-cccc-000000000002', 'Fernando Lima', '77788899900', 'fernando.lima@test.com', '21933334444', 'Rua dos Adimplentes', '20', 'Apto 50', 'Copacabana', 'Rio de Janeiro', 'RJ', '22010-000', false, false),


    ('33333333-aaaa-bbbb-cccc-000000000003', 'Pedro Almeida', '11122233344', 'pedro.almeida@test.com', '31955556666', 'Avenida dos Problemas', '300', null, 'Savassi', 'Belo Horizonte', 'MG', '30110-000', true, true),


     ('88888888-dddd-eeee-ffff-000000000008', 'Ricardo Mendes', '99988877766', 'ricardo.mendes@test.com', '81912345678', 'Rua do Bloqueio', '800', null, 'Boa Viagem', 'Recife', 'PE', '51020-000', true, false),


     ('99999999-ffff-eeee-dddd-000000000009', 'Juliana Santos', '12121212121', 'juliana.santos@test.com', '21988887777', 'Rua da Harmonia', '1234', 'Casa', 'Laranjeiras', 'Rio de Janeiro', 'RJ', '22240-000', false, false);


-- ===================================================================================
-- Massa de Veículos para Validação
-- ===================================================================================
INSERT INTO rentalcompany.tb_vehicle (
    public_id, vehicle_model_name, vehicle_type, vehicle_year, door_quantity, fuel_type,
    license_plate, color, mileage, renavam, vin, vehicle_status, fipe_code,
    daily_rate_in_cents, fipe_value_in_cents, image_url, creation_date, update_date
)
VALUES

    ('44444444-aaaa-bbbb-cccc-000000000004', 'Volkswagen Polo', 'HATCH', 2024, 4, 'FLEX', 'BRA2E24', 'Vermelho', '1200', '44455566677', '9BWAAA444RT111222', 'RESERVED', '005473-4', 15000, 9500000, '/images/mock/hatch_red.jpg', CURRENT_TIMESTAMP, NULL),


    ('55555555-aaaa-bbbb-cccc-000000000005', 'Honda Civic', 'SEDAN', 2023, 4, 'GASOLINA', 'BRA3C25', 'Preto', '25000', '88899900011', '9BDBBB555VT333444', 'AVAILABLE', '014112-0', 28000, 17000000, '/images/mock/sedan_black.jpg', CURRENT_TIMESTAMP, NULL),


    ('66666666-aaaa-bbbb-cccc-000000000006', 'Chevrolet Tracker', 'SUV', 2022, 4, 'DIESEL', 'BRA4D26', 'Azul', '45000', '22233344455', '9BWCCC666ST555666', 'UNDER_MAINTENANCE', '004523-2', 35000, 16000000, '/images/mock/suv_blue.jpg', CURRENT_TIMESTAMP, NULL),

    ('10101010-ffff-eeee-dddd-000000000010', 'Fiat Pulse', 'SUV', 2024, 4, 'FLEX', 'HAPPY01', 'Cinza', '500', '33344455566', '9BWDDD777ST888999', 'AVAILABLE', '001527-3', 20000, 13000000, '/images/mock/suv_gray.jpg', CURRENT_TIMESTAMP, NULL);



INSERT INTO rentalcompany.tb_reserve (
    public_id, customer_name, customer_document, vehicle_model_name, vehicle_year,
    vehicle_license_plate, amount_in_cents, rental_date, return_date,
    reservation_status, payment_status, creation_date, update_date
)
VALUES

    ('77777777-aaaa-bbbb-cccc-000000000007', 'Fernando Lima', '77788899900', 'Volkswagen Polo', 2024, 'BRA2E24', 60000, '2025-08-20', '2025-08-23', 'SCHEDULED', 'PENDING', CURRENT_TIMESTAMP, NULL);