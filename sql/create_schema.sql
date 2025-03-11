CREATE TABLE productos (
                           id INT AUTO_INCREMENT PRIMARY KEY,
                           nombre VARCHAR(150) UNIQUE NOT NULL,
                           descripcion TEXT,
                           sku VARCHAR(50) UNIQUE NOT NULL,
                           precio DECIMAL(10,2) NOT NULL CHECK (precio >= 0),
                           stock_actual INT NOT NULL CHECK (stock_actual >= 0),
                           unidad_medida VARCHAR(50) NOT NULL,
                           estado BOOLEAN DEFAULT TRUE,
                           creado_en TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                           actualizado_en TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);