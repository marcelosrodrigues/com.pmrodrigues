CREATE TABLE beneficiario (
  pessoa_id   BIGINT NOT NULL,
  contrato_id BIGINT,
  PRIMARY KEY (pessoa_id, contrato_id),
  KEY fk_pessoafisica_beneficiario (pessoa_id),
  CONSTRAINT fk_pessoafisica_beneficiario FOREIGN KEY (`pessoa_id`) REFERENCES `pessoafisica` (`id`),
  KEY fk_contrato_beneficiario (contrato_id),
  CONSTRAINT fk_contrato_beneficiario FOREIGN KEY (contrato_id) REFERENCES contrato (id)
);