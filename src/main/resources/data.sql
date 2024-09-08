INSERT INTO tb_category (
    id, created_at, description, name, updated_at)
VALUES
    (1, CURRENT_TIMESTAMP, 'Dispositivos móveis e smartphones.', 'Smartphones', CURRENT_TIMESTAMP),
    (2, CURRENT_TIMESTAMP, 'Computadores e notebooks.', 'Computadores', CURRENT_TIMESTAMP),
    (3, CURRENT_TIMESTAMP, 'Acessórios e periféricos eletrônicos.', 'Acessórios', CURRENT_TIMESTAMP);

INSERT INTO tb_product (
    id, created_at, description, name, price, quantity, updated_at, tb_category_id)
VALUES
    (1, CURRENT_TIMESTAMP, 'Smartphone com 128GB e tela de 6.1 polegadas.', 'iPhone 13', 4999.99, 10, CURRENT_TIMESTAMP, 1),
    (2, CURRENT_TIMESTAMP, 'Smartphone Android com câmera de 108MP e 5G.', 'Samsung Galaxy S21', 3999.99, 8, CURRENT_TIMESTAMP, 1),
    (3, CURRENT_TIMESTAMP, 'Smartphone com 64GB de armazenamento e câmera tripla.', 'Xiaomi Mi 11', 2999.99, 15, CURRENT_TIMESTAMP, 1),
    (4, CURRENT_TIMESTAMP, 'Smartphone com câmera de 48MP e 128GB.', 'OnePlus 9', 3499.99, 7, CURRENT_TIMESTAMP, 1),
    (5, CURRENT_TIMESTAMP, 'Smartphone com tela dobrável de 7.6 polegadas.', 'Samsung Galaxy Z Fold 3', 8999.99, 5, CURRENT_TIMESTAMP, 1),
    (6, CURRENT_TIMESTAMP, 'Smartphone com 128GB de armazenamento e design ultrafino.', 'Google Pixel 6', 3999.99, 12, CURRENT_TIMESTAMP, 1),
    (7, CURRENT_TIMESTAMP, 'Smartphone com tela Super AMOLED de 6.5 polegadas.', 'Samsung Galaxy A52', 1999.99, 20, CURRENT_TIMESTAMP, 1);

INSERT INTO tb_product (
    id, created_at, description, name, price, quantity, updated_at, tb_category_id)
VALUES
    (8, CURRENT_TIMESTAMP, 'Notebook com processador Intel i7 e 16GB de RAM.', 'Dell XPS 13', 7999.99, 6, CURRENT_TIMESTAMP, 2),
    (9, CURRENT_TIMESTAMP, 'Notebook com AMD Ryzen 5 e 512GB SSD.', 'HP Pavilion 15', 5499.99, 9, CURRENT_TIMESTAMP, 2),
    (10, CURRENT_TIMESTAMP, 'Desktop gamer com placa de vídeo RTX 3060.', 'Acer Predator Orion 3000', 9999.99, 4, CURRENT_TIMESTAMP, 2),
    (11, CURRENT_TIMESTAMP, 'Notebook com tela de 15.6 polegadas e Intel i5.', 'Lenovo ThinkPad E15', 5299.99, 7, CURRENT_TIMESTAMP, 2),
    (12, CURRENT_TIMESTAMP, 'PC All-in-One com tela de 23.8 polegadas Full HD.', 'Dell Inspiron 24 5000', 6599.99, 5, CURRENT_TIMESTAMP, 2),
    (13, CURRENT_TIMESTAMP, 'MacBook com M1 e 512GB de SSD.', 'MacBook Air M1', 7999.99, 8, CURRENT_TIMESTAMP, 2),
    (14, CURRENT_TIMESTAMP, 'Chromebook com processador Intel eMMC de 64GB.', 'ASUS Chromebook Flip C434', 2999.99, 11, CURRENT_TIMESTAMP, 2);

INSERT INTO tb_product (
    id, created_at, description, name, price, quantity, updated_at, tb_category_id)
VALUES
    (15, CURRENT_TIMESTAMP, 'Fone de ouvido com cancelamento de ruído ativo.', 'Sony WH-1000XM4', 1499.99, 15, CURRENT_TIMESTAMP, 3),
    (16, CURRENT_TIMESTAMP, 'Caixa de som Bluetooth à prova d’água.', 'JBL Flip 5', 599.99, 25, CURRENT_TIMESTAMP, 3),
    (17, CURRENT_TIMESTAMP, 'Relógio inteligente com monitoramento cardíaco.', 'Apple Watch Series 7', 3299.99, 10, CURRENT_TIMESTAMP, 3),
    (18, CURRENT_TIMESTAMP, 'Teclado mecânico com iluminação RGB.', 'HyperX Alloy Origins', 799.99, 18, CURRENT_TIMESTAMP, 3),
    (19, CURRENT_TIMESTAMP, 'Carregador portátil com 20.000mAh.', 'Xiaomi Mi Power Bank 3', 199.99, 30, CURRENT_TIMESTAMP, 3),
    (20, CURRENT_TIMESTAMP, 'Mouse sem fio com sensor de alta precisão.', 'Logitech MX Master 3', 599.99, 22, CURRENT_TIMESTAMP, 3);
