CREATE TABLE `pessoa` (
  `codigo` bigint(20) NOT NULL AUTO_INCREMENT,
  `nome` varchar(255) NOT NULL,
  `cpfcnpj` varchar(255) NOT NULL,
  `senha` varchar(255) NOT NULL,
  `logradouro` varchar(255) DEFAULT NULL,
  `complemento` varchar(255) DEFAULT NULL, 
  `numero` varchar(255) DEFAULT NULL,
  `bairro` varchar(255) DEFAULT NULL,
  `cep` varchar(255) DEFAULT NULL,
  `telefone` varchar(255) DEFAULT NULL,
  `celular` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `ativo` bit(1) NOT NULL,
  `codigo_cidade` bigint(20) NOT NULL,
  PRIMARY KEY (`codigo`),
  KEY `FK5kw2bqykvdrlxsudppb1h7v3c` (`codigo_cidade`),
  CONSTRAINT `FK5kw2bqykvdrlxsudppb1h7v3c` FOREIGN KEY (`codigo_cidade`) REFERENCES `cidade` (`codigo`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
