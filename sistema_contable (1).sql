-- phpMyAdmin SQL Dump
-- version 5.1.0
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 22-07-2021 a las 03:23:29
-- Versión del servidor: 10.4.19-MariaDB
-- Versión de PHP: 8.0.6

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `sistema_contable`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `bodegas`
--

CREATE TABLE `bodegas` (
  `ID_BOD` varchar(10) NOT NULL,
  `NOM_BOD` varchar(15) NOT NULL,
  `TIP_BOD` varchar(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `clientes`
--

CREATE TABLE `clientes` (
  `ID_CLI` varchar(10) NOT NULL,
  `NOM_CLI` varchar(15) NOT NULL,
  `APE_CLI` varchar(15) NOT NULL,
  `DIR_CLI` varchar(50) DEFAULT NULL,
  `TEL_CLI` varchar(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `detalle_bodegas`
--

CREATE TABLE `detalle_bodegas` (
  `ID_DET_BOD` varchar(10) NOT NULL,
  `ID_PRO_BOD` varchar(10) NOT NULL,
  `CAN_PRO_BOD` int(11) NOT NULL,
  `ID_BOD_PER` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `detalle_factura`
--

CREATE TABLE `detalle_factura` (
  `ID_DET_FAC` varchar(10) NOT NULL,
  `ID_PRO_FAC` varchar(10) NOT NULL,
  `CAN_VEN_PRO` int(11) NOT NULL,
  `ID_FAC_PER` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `facturas`
--

CREATE TABLE `facturas` (
  `ID_FAC` varchar(10) NOT NULL,
  `ID_CLI_FAC` varchar(10) NOT NULL,
  `ID_USU_VEN_FAC` varchar(10) NOT NULL,
  `FEC_FAC` date NOT NULL,
  `TOT_FAC` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `productos`
--

CREATE TABLE `productos` (
  `ID_PRO` varchar(10) NOT NULL,
  `NOM_PRO` varchar(10) NOT NULL,
  `DES_PRO` varchar(10) DEFAULT NULL,
  `PRE_UNI_PRO` int(11) NOT NULL,
  `EST_PRO` varchar(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuarios`
--

CREATE TABLE `usuarios` (
  `ID_USU` varchar(10) NOT NULL,
  `NOM_USU` varchar(15) NOT NULL,
  `APE_USU` varchar(15) NOT NULL,
  `CLA_USU` varchar(10) NOT NULL,
  `TEL_USU` varchar(10) DEFAULT NULL,
  `DIR_USU` varchar(50) DEFAULT NULL,
  `ROL_USU` varchar(1) NOT NULL,
  `EST_USU` varchar(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `usuarios`
--

INSERT INTO `usuarios` (`ID_USU`, `NOM_USU`, `APE_USU`, `CLA_USU`, `TEL_USU`, `DIR_USU`, `ROL_USU`, `EST_USU`) VALUES
('1803834371', 'Alex', 'Tigselema', '12345', '0967205537', 'Ambato', 'A', 'Y'),
('1805283452', 'Jose', 'Pazmiño', '12345', '0995668401', 'Ambato', 'A', 'Y'),
('2000114724', 'Kevin', 'Suarez', '12345', '0990599319', 'Galapagos', 'V', 'Y');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `bodegas`
--
ALTER TABLE `bodegas`
  ADD PRIMARY KEY (`ID_BOD`);

--
-- Indices de la tabla `clientes`
--
ALTER TABLE `clientes`
  ADD PRIMARY KEY (`ID_CLI`);

--
-- Indices de la tabla `detalle_bodegas`
--
ALTER TABLE `detalle_bodegas`
  ADD PRIMARY KEY (`ID_DET_BOD`),
  ADD KEY `ID_PRO_BOD` (`ID_PRO_BOD`,`ID_BOD_PER`),
  ADD KEY `ID_BOD_PER` (`ID_BOD_PER`);

--
-- Indices de la tabla `detalle_factura`
--
ALTER TABLE `detalle_factura`
  ADD PRIMARY KEY (`ID_DET_FAC`),
  ADD KEY `ID_PRO_FAC` (`ID_PRO_FAC`,`ID_FAC_PER`),
  ADD KEY `ID_FAC_PER` (`ID_FAC_PER`);

--
-- Indices de la tabla `facturas`
--
ALTER TABLE `facturas`
  ADD PRIMARY KEY (`ID_FAC`),
  ADD KEY `ID_CLI_FAC` (`ID_CLI_FAC`,`ID_USU_VEN_FAC`),
  ADD KEY `ID_USU_VEN_FAC` (`ID_USU_VEN_FAC`);

--
-- Indices de la tabla `productos`
--
ALTER TABLE `productos`
  ADD PRIMARY KEY (`ID_PRO`);

--
-- Indices de la tabla `usuarios`
--
ALTER TABLE `usuarios`
  ADD PRIMARY KEY (`ID_USU`);

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `detalle_bodegas`
--
ALTER TABLE `detalle_bodegas`
  ADD CONSTRAINT `detalle_bodegas_ibfk_1` FOREIGN KEY (`ID_BOD_PER`) REFERENCES `bodegas` (`ID_BOD`),
  ADD CONSTRAINT `detalle_bodegas_ibfk_2` FOREIGN KEY (`ID_PRO_BOD`) REFERENCES `productos` (`ID_PRO`);

--
-- Filtros para la tabla `detalle_factura`
--
ALTER TABLE `detalle_factura`
  ADD CONSTRAINT `detalle_factura_ibfk_1` FOREIGN KEY (`ID_FAC_PER`) REFERENCES `facturas` (`ID_FAC`),
  ADD CONSTRAINT `detalle_factura_ibfk_2` FOREIGN KEY (`ID_PRO_FAC`) REFERENCES `productos` (`ID_PRO`);

--
-- Filtros para la tabla `facturas`
--
ALTER TABLE `facturas`
  ADD CONSTRAINT `facturas_ibfk_1` FOREIGN KEY (`ID_USU_VEN_FAC`) REFERENCES `usuarios` (`ID_USU`),
  ADD CONSTRAINT `facturas_ibfk_2` FOREIGN KEY (`ID_CLI_FAC`) REFERENCES `clientes` (`ID_CLI`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
