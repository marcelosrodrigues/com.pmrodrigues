ALTER TABLE modalidade
ADD COLUMN administradora_id BIGINT NULL;

ALTER TABLE modalidade
ADD CONSTRAINT FK_ADMINISTRADORA_MODALIDADE FOREIGN KEY (administradora_id) REFERENCES cedente (id);