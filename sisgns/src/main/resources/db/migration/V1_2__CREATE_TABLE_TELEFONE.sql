CREATE TABLE telefone (
  id       BIGINT   NOT NULL AUTO_INCREMENT,
  ddd      CHAR(3)  NOT NULL,
  telefone CHAR(10) NOT NULL,
  tipo     CHAR(1)  NOT NULL,
  PRIMARY KEY (id)
);

ALTER TABLE telefone
ADD CONSTRAINT CH_TIPO_TELEFONE CHECK ( tipo IN ('R', 'C') );

CREATE TABLE telefone_pessoa (
  pessoa_id   BIGINT NOT NULL,
  telefone_id BIGINT NOT NULL,
  PRIMARY KEY (pessoa_id, telefone_id),
  CONSTRAINT fk_telefone_pessoa FOREIGN KEY (pessoa_id) REFERENCES pessoa (id),
  CONSTRAINT fk_pessoa_telefone FOREIGN KEY (telefone_id) REFERENCES telefone (id)
);

CREATE TABLE telefone_administrador (
  administradora_id BIGINT NOT NULL,
  telefone_id       BIGINT NOT NULL,
  PRIMARY KEY (administradora_id, telefone_id),
  CONSTRAINT fk_telefone_administradora FOREIGN KEY (administradora_id) REFERENCES cedente (id),
  CONSTRAINT fk_administradora_telefone FOREIGN KEY (telefone_id) REFERENCES telefone (id)
);