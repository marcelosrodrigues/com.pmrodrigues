CREATE TABLE regra_comissionamento (
  id                BIGINT AUTO_INCREMENT PRIMARY KEY,
  nome              VARCHAR(50) NOT NULL,
  ordem             INT         NOT NULL,
  administradora_id BIGINT      NOT NULL
);

ALTER TABLE regra_comissionamento
ADD CONSTRAINT FOREIGN KEY fk_administradora_regra_comissionamento (administradora_id) REFERENCES cedente (id);

ALTER TABLE comissionamento
DROP FOREIGN KEY fk_plano_comissionamento;

ALTER TABLE comissionamento
DROP COLUMN plano_id;

ALTER TABLE comissionamento
DROP COLUMN nome;

ALTER TABLE comissionamento
DROP COLUMN ordem;

ALTER TABLE comissionamento
ADD COLUMN regra_id BIGINT NOT NULL;

ALTER TABLE comissionamento
ADD CONSTRAINT FOREIGN KEY fk_regra_comissionamento (regra_id) REFERENCES regra_comissionamento (id);

CREATE TABLE comissionamento_por_operadora (
  operadora_id BIGINT NOT NULL,
  id           BIGINT NOT NULL,
  PRIMARY KEY (operadora_id, id),
  CONSTRAINT FOREIGN KEY fk_comissionamento_por_operadora (operadora_id) REFERENCES operadora (id),
  CONSTRAINT FOREIGN KEY fk_comissionamento_regra (id) REFERENCES comissionamento (id)
);

CREATE TABLE comissionamento_por_plano (
  plano_id BIGINT NOT NULL,
  id       BIGINT NOT NULL,
  PRIMARY KEY (plano_id, id),
  CONSTRAINT FOREIGN KEY fk_comissionamento_por_plano (plano_id) REFERENCES plano (id),
  CONSTRAINT FOREIGN KEY fk_comissionamento_regra (id) REFERENCES comissionamento (id)
);

