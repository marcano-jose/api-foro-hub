-- V1__create-table-topicos.sql
-- Descripción: Script de migración SQL para la creación de la tabla `topicos` en una base de datos MySQL.
-- Esta tabla está diseñada para la gestión de tópicos de la API `api-foro-hub` y cuenta con una
-- restricción de unicidad compuesta en los campos `titulo` y `mensaje`.

CREATE TABLE `topicos` (
    `id` bigint NOT NULL AUTO_INCREMENT,
    `titulo` varchar(255) NOT NULL,
    `mensaje` text NOT NULL,
    `fecha_creacion` timestamp NOT NULL,
    `status` tinyint(1) NOT NULL, -- Representa un valor booleano (1 para activo, 0 para inactivo)
    `autor` varchar(20) NOT NULL,
    `curso` varchar(20) NOT NULL,
    PRIMARY KEY (`id`),
    -- Restricción única compuesta para título y mensaje, usando un prefijo de 255 caracteres para 'mensaje'
    UNIQUE KEY `UQ_titulo_mensaje` (`titulo`,`mensaje`(255))
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Fin del script de creación de tabla 'topicos'
