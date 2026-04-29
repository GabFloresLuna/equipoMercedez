CREATE TABLE marcas (
    marca_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    codigo_marca VARCHAR(10) NOT NULL UNIQUE,
    nombre_marca VARCHAR(50) NOT NULL UNIQUE
);

CREATE TABLE repuestos (
    repuesto_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    codigo_repuesto VARCHAR(10) NOT NULL UNIQUE,
    nombre_repuesto VARCHAR(50) NOT NULL UNIQUE,
    stock INT NOT NULL,
    marca_id BIGINT,
    CONSTRAINT fk_repuesto_marca FOREIGN KEY (marca_id) REFERENCES marcas(marca_id)
);