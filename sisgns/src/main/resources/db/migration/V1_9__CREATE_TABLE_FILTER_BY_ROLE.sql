CREATE TABLE filtro_de_dados (
  id        BIGINT        NOT NULL AUTO_INCREMENT PRIMARY KEY,
  nome      VARCHAR(1000) NOT NULL,
  perfil_id BIGINT        NOT NULL,
  CONSTRAINT fk_perfil FOREIGN KEY (perfil_id) REFERENCES perfil (id),
  INDEX fk_perfil (perfil_id)
);