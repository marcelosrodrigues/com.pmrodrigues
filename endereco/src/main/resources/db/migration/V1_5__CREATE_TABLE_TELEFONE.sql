CREATE TABLE telefone (
  id       BIGINT   NOT NULL AUTO_INCREMENT,
  ddd      CHAR(3)  NOT NULL,
  telefone CHAR(10) NOT NULL,
  tipo     CHAR(1)  NOT NULL,
  PRIMARY KEY (id)
);


ALTER TABLE telefone
ADD CONSTRAINT CH_TIPO_TELEFONE CHECK ( tipo IN ('R', 'C') );