DROP TABLE IF EXISTS product;

CREATE TABLE product (
    id INT AUTO_INCREMENT PRIMARY KEY,
    productCode VARCHAR(20) NOT NULL,
    productBrand VARCHAR(50) NOT NULL,
    quantityInHand INT,
    unitPrice DOUBLE
);