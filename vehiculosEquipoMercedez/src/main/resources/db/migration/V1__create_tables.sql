CREATE TABLE marcas (
    marca_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    codigo_marca VARCHAR(10) NOT NULL UNIQUE,
    nombre_marca VARCHAR(50) NOT NULL UNIQUE
);

CREATE TABLE vehiculos (
    vehiculo_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    codigo_vehiculo VARCHAR(10) NOT NULL UNIQUE,
    nombre_vehiculo VARCHAR(50) NOT NULL,
    stock INT NOT NULL,
    marca_id BIGINT,
    CONSTRAINT fk_vehiculo_marca FOREIGN KEY (marca_id) REFERENCES marcas(marca_id)
);