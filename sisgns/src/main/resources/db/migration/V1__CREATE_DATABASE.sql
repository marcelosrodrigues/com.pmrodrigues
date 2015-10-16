-- MySQL dump 10.13  Distrib 5.6.13, for Win32 (x86)
--
-- Host: 127.0.0.1    Database: sisgns
-- ------------------------------------------------------
-- Server version	5.5.34

/*!40101 SET @OLD_CHARACTER_SET_CLIENT = @@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS = @@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION = @@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE = @@TIME_ZONE */;
/*!40103 SET TIME_ZONE = '+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS = @@UNIQUE_CHECKS, UNIQUE_CHECKS = 0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS = @@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS = 0 */;
/*!40101 SET @OLD_SQL_MODE = @@SQL_MODE, SQL_MODE = 'NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES = @@SQL_NOTES, SQL_NOTES = 0 */;

--
-- Table structure for table `bairro`
--

DROP TABLE IF EXISTS `bairro`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `bairro` (
  `id`        BIGINT(20)   NOT NULL AUTO_INCREMENT,
  `nome`      VARCHAR(255) NOT NULL,
  `cidade_id` BIGINT(20)   NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_CIDADE_BAIRRO` (`cidade_id`),
  INDEX IX_BAIRRO USING BTREE (cidade_id, nome),
  CONSTRAINT `FK_CIDADE_BAIRRO` FOREIGN KEY (`cidade_id`) REFERENCES `cidade` (`id`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bairro`
--

LOCK TABLES `bairro` WRITE;
/*!40000 ALTER TABLE `bairro` DISABLE KEYS */;
/*!40000 ALTER TABLE `bairro` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `boleto`
--

DROP TABLE IF EXISTS `boleto`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `boleto` (
  `id`                 BIGINT(20) NOT NULL AUTO_INCREMENT,
  `digito_agencia`     VARCHAR(255)        DEFAULT NULL,
  `numero_agencia`     INT(11)             DEFAULT NULL,
  `banco`              VARCHAR(255)        DEFAULT NULL,
  `carteira`           INT(11)             DEFAULT NULL,
  `digito_conta`       VARCHAR(255)        DEFAULT NULL,
  `numero_conta`       INT(11)             DEFAULT NULL,
  `dataAlteracao`      DATETIME            DEFAULT NULL,
  `dataCriacao`        DATETIME            DEFAULT NULL,
  `dataEmissao`        DATETIME            DEFAULT NULL,
  `dataProcessamento`  DATETIME            DEFAULT NULL,
  `dataVencimento`     DATETIME            DEFAULT NULL,
  `localPagamento`     VARCHAR(255)        DEFAULT NULL,
  `modalidade`         INT(11)             DEFAULT NULL,
  `digito_nossonumero` VARCHAR(255)        DEFAULT NULL,
  `nossonumero`        VARCHAR(255)        DEFAULT NULL,
  `numeroDocumento`    VARCHAR(255)        DEFAULT NULL,
  `valor`              DECIMAL(19, 2)      DEFAULT NULL,
  `cedente_id`         BIGINT(20) NOT NULL,
  `sacado_id`          BIGINT(20) NOT NULL,
  `contrato_id`        BIGINT(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_boleto_cedente` (`cedente_id`),
  KEY `fk_boleto_sacado` (`sacado_id`),
  KEY `fk_boleto_contrato` (`contrato_id`),
  CONSTRAINT `fk_boleto_contrato` FOREIGN KEY (`contrato_id`) REFERENCES `contrato` (`id`),
  CONSTRAINT `fk_boleto_cedente` FOREIGN KEY (`cedente_id`) REFERENCES `cedente` (`id`),
  CONSTRAINT `fk_boleto_sacado` FOREIGN KEY (`sacado_id`) REFERENCES `pessoa` (`id`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `boleto`
--

LOCK TABLES `boleto` WRITE;
/*!40000 ALTER TABLE `boleto` DISABLE KEYS */;
/*!40000 ALTER TABLE `boleto` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cedente`
--

DROP TABLE IF EXISTS `cedente`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cedente` (
  `DTYPE`           VARCHAR(31) NOT NULL,
  `id`              BIGINT(20)  NOT NULL AUTO_INCREMENT,
  `convenio`        VARCHAR(255)         DEFAULT NULL,
  `nome`            VARCHAR(255)         DEFAULT NULL,
  `numeroDocumento` CHAR(18) NOT NULL,
  `endereco_id`     BIGINT(20)  NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_numerodocumento` (`numeroDocumento`),
  KEY `fk_endereco_cedente` (`endereco_id`),
  CONSTRAINT `fk_endereco_cedente` FOREIGN KEY (`endereco_id`) REFERENCES `endereco` (`id`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cedente`
--

LOCK TABLES `cedente` WRITE;
/*!40000 ALTER TABLE `cedente` DISABLE KEYS */;
/*!40000 ALTER TABLE `cedente` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cidade`
--

DROP TABLE IF EXISTS `cidade`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cidade` (
  `id`        BIGINT(20)   NOT NULL AUTO_INCREMENT,
  `nome`      VARCHAR(255) NOT NULL,
  `estado_id` BIGINT(20)   NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_CIDADE_ESTADO` (`estado_id`),
  INDEX IX_CIDADE USING BTREE (estado_id, nome),
  CONSTRAINT `FK_CIDADE_ESTADO` FOREIGN KEY (`estado_id`) REFERENCES `estado` (`id`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cidade`
--

LOCK TABLES `cidade` WRITE;
/*!40000 ALTER TABLE `cidade` DISABLE KEYS */;
/*!40000 ALTER TABLE `cidade` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `comissao`
--

DROP TABLE IF EXISTS `comissao`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `comissao` (
  `id`        BIGINT(20) NOT NULL AUTO_INCREMENT,
  `boleto_id` BIGINT(20) NOT NULL,
  `regra_id`  BIGINT(20) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_comissao` (`boleto_id`, `regra_id`),
  KEY `fk_comissao_regra_comissionamento` (`regra_id`),
  CONSTRAINT `fk_comissao_regra_comissionamento` FOREIGN KEY (`regra_id`) REFERENCES `comissionamento` (`id`),
  CONSTRAINT `fk_comissao_boleto` FOREIGN KEY (`boleto_id`) REFERENCES `boleto` (`id`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comissao`
--

LOCK TABLES `comissao` WRITE;
/*!40000 ALTER TABLE `comissao` DISABLE KEYS */;
/*!40000 ALTER TABLE `comissao` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `comissionamento`
--

DROP TABLE IF EXISTS `comissionamento`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `comissionamento` (
  `id`                BIGINT(20)     NOT NULL AUTO_INCREMENT,
  `plano_id`          BIGINT(20)     NOT NULL,
  `nome`              VARCHAR(45)    NOT NULL,
  `percentual`        DECIMAL(15, 3) NOT NULL,
  `ordem`             INT(11)        NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_plano_comissionamento` (`plano_id`),
  CONSTRAINT `fk_plano_comissionamento` FOREIGN KEY (`plano_id`) REFERENCES `plano` (`id`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comissionamento`
--

LOCK TABLES `comissionamento` WRITE;
/*!40000 ALTER TABLE `comissionamento` DISABLE KEYS */;
/*!40000 ALTER TABLE `comissionamento` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `contrato`
--

DROP TABLE IF EXISTS `contrato`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `contrato` (
  `id`                BIGINT(20) NOT NULL AUTO_INCREMENT,
  `dataInicio`        DATE       NOT NULL,
  `dataTermino`       DATE                DEFAULT NULL,
  `numeroContrato`    VARCHAR(255)        DEFAULT NULL,
  `administradora_id` BIGINT(20) NOT NULL,
  `cliente_id`        BIGINT(20) NOT NULL,
  `plano_id`          BIGINT(20) NOT NULL,
  `corretor_id`       BIGINT(20) NOT NULL,
  `excluido`          BIT(1)              DEFAULT b'0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_contrato` (`administradora_id`, `dataInicio`, `numeroContrato`, `dataTermino`),
  KEY `fk_contrato_corretor` (`corretor_id`),
  KEY `fk_contrato_cliente` (`cliente_id`),
  KEY `fk_contrato_plano` (`plano_id`),
  CONSTRAINT `fk_contrato_administradora` FOREIGN KEY (`administradora_id`) REFERENCES `cedente` (`id`),
  CONSTRAINT `fk_contrato_cliente` FOREIGN KEY (`cliente_id`) REFERENCES `pessoa` (`id`),
  CONSTRAINT `fk_contrato_corretor` FOREIGN KEY (`corretor_id`) REFERENCES `usuario` (`id`),
  CONSTRAINT `fk_contrato_plano` FOREIGN KEY (`plano_id`) REFERENCES `plano` (`id`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `contrato`
--

LOCK TABLES `contrato` WRITE;
/*!40000 ALTER TABLE `contrato` DISABLE KEYS */;
/*!40000 ALTER TABLE `contrato` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `endereco`
--

DROP TABLE IF EXISTS `endereco`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `endereco` (
  `id`          BIGINT(20)   NOT NULL AUTO_INCREMENT,
  `complemento` VARCHAR(255)          DEFAULT NULL,
  `numero`      VARCHAR(255) NOT NULL,
  `logradouro`  VARCHAR(255)          DEFAULT NULL,
  `cep` CHAR(9) NOT NULL,
  cidade_id     BIGINT       NOT NULL,
  bairro_id     BIGINT       NOT NULL,
  estado_id     BIGINT       NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_ENDERECO_cidade` (`cidade_id`),
  CONSTRAINT `FK_ENDERECO_cidade` FOREIGN KEY (`cidade_id`) REFERENCES `cidade` (`id`),
  KEY `FK_ENDERECO_bairro` (`bairro_id`),
  CONSTRAINT `FK_ENDERECO_bairro` FOREIGN KEY (`bairro_id`) REFERENCES `bairro` (`id`),
  KEY `FK_ENDERECO_estado` (`estado_id`),
  CONSTRAINT `FK_ENDERECO_estado` FOREIGN KEY (`estado_id`) REFERENCES `estado` (`id`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `endereco`
--

LOCK TABLES `endereco` WRITE;
/*!40000 ALTER TABLE `endereco` DISABLE KEYS */;
/*!40000 ALTER TABLE `endereco` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `estado`
--

DROP TABLE IF EXISTS `estado`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `estado` (
  `id`   BIGINT(20)   NOT NULL,
  `nome` VARCHAR(255) NOT NULL,
  `uf`   CHAR(2)      NOT NULL,
  PRIMARY KEY (`id`),
  INDEX IX_ESTADO USING BTREE (nome),
  UNIQUE KEY `UK_UF` (`uf`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `estado`
--

LOCK TABLES `estado` WRITE;
/*!40000 ALTER TABLE `estado` DISABLE KEYS */;
/*!40000 ALTER TABLE `estado` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `instrucao`
--

DROP TABLE IF EXISTS `instrucao`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `instrucao` (
  `id`        BIGINT(20) NOT NULL AUTO_INCREMENT,
  `instrucao` VARCHAR(255)        DEFAULT NULL,
  `boleto_id` BIGINT(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_instrucao_boleto` (`boleto_id`),
  CONSTRAINT `fk_instrucao_boleto` FOREIGN KEY (`boleto_id`) REFERENCES `boleto` (`id`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `instrucao`
--

LOCK TABLES `instrucao` WRITE;
/*!40000 ALTER TABLE `instrucao` DISABLE KEYS */;
/*!40000 ALTER TABLE `instrucao` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `logradouro`
--

DROP TABLE IF EXISTS `logradouro`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `logradouro` (
  `id`         BIGINT(20)   NOT NULL AUTO_INCREMENT,
  `cep`        VARCHAR(255) NOT NULL,
  `logradouro` VARCHAR(255) NOT NULL,
  `bairro_id`  BIGINT(20)   NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_BAIRRO_LOGRADOURO` (`bairro_id`),
  INDEX `IX_CEP` USING BTREE (`cep` ASC),
  CONSTRAINT `FK_BAIRRO_LOGRADOURO` FOREIGN KEY (`bairro_id`) REFERENCES `bairro` (`id`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `logradouro`
--

LOCK TABLES `logradouro` WRITE;
/*!40000 ALTER TABLE `logradouro` DISABLE KEYS */;
/*!40000 ALTER TABLE `logradouro` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `membros`
--

DROP TABLE IF EXISTS `membros`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `membros` (
  `usuario_id` BIGINT(20) NOT NULL,
  `perfil_id`  BIGINT(20) NOT NULL,
  UNIQUE KEY `uk_membro` (`usuario_id`, `perfil_id`),
  KEY `fk_perfil_membros` (`perfil_id`),
  CONSTRAINT `fk_usuario_membros` FOREIGN KEY (`usuario_id`) REFERENCES `usuario` (`id`),
  CONSTRAINT `fk_perfil_membros` FOREIGN KEY (`perfil_id`) REFERENCES `perfil` (`id`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `membros`
--

LOCK TABLES `membros` WRITE;
/*!40000 ALTER TABLE `membros` DISABLE KEYS */;
/*!40000 ALTER TABLE `membros` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `operadora`
--

DROP TABLE IF EXISTS `operadora`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `operadora` (
  `id`   BIGINT(20)   NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`id`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `operadora`
--

LOCK TABLES `operadora` WRITE;
/*!40000 ALTER TABLE `operadora` DISABLE KEYS */;
/*!40000 ALTER TABLE `operadora` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `perfil`
--

DROP TABLE IF EXISTS `perfil`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `perfil` (
  `id`   BIGINT(20)   NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_perfil` (`nome`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `perfil`
--

LOCK TABLES `perfil` WRITE;
/*!40000 ALTER TABLE `perfil` DISABLE KEYS */;
/*!40000 ALTER TABLE `perfil` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pessoa`
--

DROP TABLE IF EXISTS `pessoa`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pessoa` (
  `id`                BIGINT(20)   NOT NULL AUTO_INCREMENT,
  `email`             VARCHAR(255) NOT NULL,
  `nome`              VARCHAR(255) NOT NULL,
  `endereco_id`       BIGINT(20)   NOT NULL,
  `administradora_id` BIGINT(20)   NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_email_pessoa` (`email`),
  KEY `FK_PESSOA_ENDERECO` (`endereco_id`),
  KEY `fk_cliente_administradora_idx` (`administradora_id`),
  CONSTRAINT `fk_cliente_administradora` FOREIGN KEY (`administradora_id`) REFERENCES `cedente` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_endereco_pessoa` FOREIGN KEY (`endereco_id`) REFERENCES `endereco` (`id`),
  CONSTRAINT `FK_PESSOA_ENDERECO` FOREIGN KEY (`endereco_id`) REFERENCES `endereco` (`id`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pessoa`
--

LOCK TABLES `pessoa` WRITE;
/*!40000 ALTER TABLE `pessoa` DISABLE KEYS */;
/*!40000 ALTER TABLE `pessoa` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pessoafisica`
--

DROP TABLE IF EXISTS `pessoafisica`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pessoafisica` (
  `id`             BIGINT(20)  NOT NULL,
  `cpf`            VARCHAR(15) NOT NULL,
  `dataNascimento` DATE        NOT NULL,
  `pai_id`         BIGINT(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_CPF` (`cpf`),
  KEY `FK_PAI_PESSOA_FISICA` (`pai_id`),
  CONSTRAINT `FK_PAI_PESSOA_FISICA` FOREIGN KEY (`pai_id`) REFERENCES `pessoafisica` (`id`),
  CONSTRAINT `fk_pessoa_pessoafisica` FOREIGN KEY (`id`) REFERENCES `pessoa` (`id`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pessoafisica`
--

LOCK TABLES `pessoafisica` WRITE;
/*!40000 ALTER TABLE `pessoafisica` DISABLE KEYS */;
/*!40000 ALTER TABLE `pessoafisica` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pessoajuridica`
--

DROP TABLE IF EXISTS `pessoajuridica`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pessoajuridica` (
  `id`          BIGINT(20)  NOT NULL,
  `cnpj`        VARCHAR(17) NOT NULL,
  `endereco_id` BIGINT(20)  NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_CNPJ` (`cnpj`),
  CONSTRAINT `fk_pessoa_pessoajuridica` FOREIGN KEY (`id`) REFERENCES `pessoa` (`id`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pessoajuridica`
--

LOCK TABLES `pessoajuridica` WRITE;
/*!40000 ALTER TABLE `pessoajuridica` DISABLE KEYS */;
/*!40000 ALTER TABLE `pessoajuridica` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `plano`
--

DROP TABLE IF EXISTS `plano`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `plano` (
  `id`                BIGINT(20) NOT NULL AUTO_INCREMENT,
  `nome`              VARCHAR(255)        DEFAULT NULL,
  `operadora_id`      BIGINT(20) NOT NULL,
  `administradora_id` BIGINT(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_plano_operadora` (`operadora_id`),
  KEY `fk_plano_administradora_idx` (`administradora_id`),
  CONSTRAINT `fk_plano_administradora` FOREIGN KEY (`administradora_id`) REFERENCES `cedente` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_plano_operadora` FOREIGN KEY (`operadora_id`) REFERENCES `operadora` (`id`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `plano`
--

LOCK TABLES `plano` WRITE;
/*!40000 ALTER TABLE `plano` DISABLE KEYS */;
/*!40000 ALTER TABLE `plano` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuario`
--

DROP TABLE IF EXISTS `usuario`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `usuario` (
  `DTYPE`      VARCHAR(31)  NOT NULL,
  `id`         BIGINT(20)   NOT NULL AUTO_INCREMENT,
  `bloqueado`  TINYINT(1)            DEFAULT '0',
  `email`      VARCHAR(255) NOT NULL,
  `nome`       VARCHAR(255) NOT NULL,
  `password`   VARCHAR(255) NOT NULL,
  `tentativas` BIGINT(20)            DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_usuario` (`email`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario`
--

LOCK TABLES `usuario` WRITE;
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE = @OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE = @OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS = @OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS = @OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT = @OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS = @OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION = @OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES = @OLD_SQL_NOTES */;

-- Dump completed on 2015-09-23  9:36:58
