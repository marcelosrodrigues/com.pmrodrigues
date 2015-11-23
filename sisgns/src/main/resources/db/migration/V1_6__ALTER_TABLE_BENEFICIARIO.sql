ALTER TABLE beneficiario
DROP FOREIGN KEY fk_pessoafisica_beneficiario;

ALTER TABLE beneficiario
DROP COLUMN pessoa_id,
ADD COLUMN id BIGINT NOT NULL AUTO_INCREMENT
AFTER contrato_id,
ADD COLUMN nome VARCHAR(250) NOT NULL
AFTER id,
ADD COLUMN dataNascimento DATE NOT NULL
AFTER nome,
ADD COLUMN pai_id BIGINT NULL
AFTER dataNascimento,
DROP PRIMARY KEY,
ADD PRIMARY KEY (id),
ADD INDEX fk_dependentes_pai (pai_id ASC),
DROP INDEX fk_pessoafisica_beneficiario;

ALTER TABLE beneficiario
ADD CONSTRAINT fk_dependentes_pai FOREIGN KEY (pai_id) REFERENCES beneficiario (id);