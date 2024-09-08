package product_inventory_management.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import product_inventory_management.model.category.CategoryEntity;
import product_inventory_management.model.product.ProductEntity;
import product_inventory_management.repository.category.ICategoryRepository;
import product_inventory_management.repository.product.IProductRepository;

import java.math.BigDecimal;

@Component
public class DataLoader {

    @Bean
    CommandLineRunner initDatabase(IProductRepository productRepository, ICategoryRepository categoryRepository) {
        return args -> {
            CategoryEntity smartphones = new CategoryEntity(1L, "Smartphones", "Smartphones");
            CategoryEntity computers = new CategoryEntity(2L, "Computadores", "Computadores");
            CategoryEntity accessories = new CategoryEntity(3L, "Acessórios", "Acessórios");
            categoryRepository.save(smartphones);
            categoryRepository.save(computers);
            categoryRepository.save(accessories);

            ProductEntity product1 = new ProductEntity(1L, "iPhone 13", new BigDecimal("4999.99"), "Smartphone com 128GB e tela de 6.1 polegadas.", 10, smartphones);
            ProductEntity product2 = new ProductEntity(2L, "Samsung Galaxy S21", new BigDecimal("3999.99"), "Smartphone Android com câmera de 108MP e 5G.", 8, smartphones);
            ProductEntity product3 = new ProductEntity(3L, "Xiaomi Mi 11", new BigDecimal("2999.99"), "Smartphone com 64GB de armazenamento e câmera tripla.", 15, smartphones);
            ProductEntity product4 = new ProductEntity(4L, "OnePlus 9", new BigDecimal("3499.99"), "Smartphone com câmera de 48MP e 128GB.", 7, smartphones);
            ProductEntity product5 = new ProductEntity(5L, "Samsung Galaxy Z Fold 3", new BigDecimal("8999.99"), "Smartphone com tela dobrável de 7.6 polegadas.", 5, smartphones);
            ProductEntity product6 = new ProductEntity(6L, "Google Pixel 6", new BigDecimal("3999.99"), "Smartphone com 128GB de armazenamento e design ultrafino.", 12, smartphones);
            ProductEntity product7 = new ProductEntity(7L, "Samsung Galaxy A52", new BigDecimal("1999.99"), "Smartphone com tela Super AMOLED de 6.5 polegadas.", 20, smartphones);
            productRepository.save(product1);
            productRepository.save(product2);
            productRepository.save(product3);
            productRepository.save(product4);
            productRepository.save(product5);
            productRepository.save(product6);
            productRepository.save(product7);

            ProductEntity product8 = new ProductEntity(8L, "Dell XPS 13", new BigDecimal("7999.99"), "Notebook com processador Intel i7 e 16GB de RAM.", 6, computers);
            ProductEntity product9 = new ProductEntity(9L, "HP Pavilion 15", new BigDecimal("5499.99"), "Notebook com AMD Ryzen 5 e 512GB SSD.", 9, computers);
            ProductEntity product10 = new ProductEntity(10L, "Acer Predator Orion 3000", new BigDecimal("9999.99"), "Desktop gamer com placa de vídeo RTX 3060.", 4, computers);
            ProductEntity product11 = new ProductEntity(11L, "Lenovo ThinkPad E15", new BigDecimal("5299.99"), "Notebook com tela de 15.6 polegadas e Intel i5.", 7, computers);
            ProductEntity product12 = new ProductEntity(12L, "Dell Inspiron 24 5000", new BigDecimal("6599.99"), "PC All-in-One com tela de 23.8 polegadas Full HD.", 5, computers);
            ProductEntity product13 = new ProductEntity(13L, "MacBook Air M1", new BigDecimal("7999.99"), "MacBook com M1 e 512GB de SSD.", 8, computers);
            ProductEntity product14 = new ProductEntity(14L, "ASUS Chromebook Flip C434", new BigDecimal("2999.99"), "Chromebook com processador Intel eMMC de 64GB.", 11, computers);
            productRepository.save(product8);
            productRepository.save(product9);
            productRepository.save(product10);
            productRepository.save(product11);
            productRepository.save(product12);
            productRepository.save(product13);
            productRepository.save(product14);

            ProductEntity product15 = new ProductEntity(15L, "Sony WH-1000XM4", new BigDecimal("1499.99"), "Fone de ouvido com cancelamento de ruído ativo.", 15, accessories);
            ProductEntity product16 = new ProductEntity(16L, "JBL Flip 5", new BigDecimal("599.99"), "Caixa de som Bluetooth à prova d’água.", 25, accessories);
            ProductEntity product17 = new ProductEntity(17L, "Apple Watch Series 7", new BigDecimal("3299.99"), "Relógio inteligente com monitoramento cardíaco.", 10, accessories);
            ProductEntity product18 = new ProductEntity(18L, "HyperX Alloy Origins", new BigDecimal("799.99"), "Teclado mecânico com iluminação RGB.", 18, accessories);
            ProductEntity product19 = new ProductEntity(19L, "Xiaomi Mi Power Bank 3", new BigDecimal("199.99"), "Carregador portátil com 20.000mAh.", 30, accessories);
            ProductEntity product20 = new ProductEntity(20L, "Logitech MX Master 3", new BigDecimal("599.99"), "Mouse sem fio com sensor de alta precisão.", 22, accessories);
            productRepository.save(product15);
            productRepository.save(product16);
            productRepository.save(product17);
            productRepository.save(product18);
            productRepository.save(product19);
            productRepository.save(product20);
        };
    }
}

