CREATE TABLE faixa_etaria (
  id        BIGINT AUTO_INCREMENT PRIMARY KEY,
  inicio    INT         NOT NULL,
  termino   INT         NULL,
  descricao VARCHAR(50) NOT NULL,
  CONSTRAINT ck_faixa_etaria CHECK (termino IS NULL OR termino > inicio ),
  UNIQUE KEY uk_faixa_etaria (inicio, termino)
);

INSERT INTO faixa_etaria (inicio, termino, descricao) VALUES (0, 18, '0 a 18 anos'),
  (19, 23, '19 a 23 anos'),
  (24, 28, '24 a 28 anos'),
  (29, 33, '29 a 33 anos'),
  (34, 38, '34 a 38 anos'),
  (39, 43, '39 a 43 anos'),
  (44, 48, '44 a 48 anos'),
  (49, 53, '49 a 53 anos'),
  (54, 58, '54 a 58 anos'),
  (59, NULL, '59 anos ou mais');

CREATE TABLE preco_plano_saude (
  id              BIGINT AUTO_INCREMENT PRIMARY KEY,
  plano_id        BIGINT         NOT NULL,
  faixa_etaria_id BIGINT         NOT NULL,
  preco           DECIMAL(15, 9) NOT NULL,
  KEY fk_faixa_etaria_preco (faixa_etaria_id),
  CONSTRAINT fk_faixa_etaria_preco FOREIGN KEY (faixa_etaria_id) REFERENCES faixa_etaria (id),
  KEY fk_plano_precos (plano_id),
  CONSTRAINT fk_plano_precos FOREIGN KEY (plano_id) REFERENCES plano (id),
  UNIQUE KEY uk_preco_plano_saude (plano_id, faixa_etaria_id)
);


