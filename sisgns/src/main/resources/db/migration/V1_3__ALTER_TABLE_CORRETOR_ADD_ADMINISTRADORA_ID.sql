CREATE TABLE corretores_administradora (
  corretor_id       BIGINT NOT NULL,
  administradora_id BIGINT NOT NULL,
  PRIMARY KEY (corretor_id, administradora_id),
  KEY fk_corretor_administradora (administradora_id),
  CONSTRAINT fk_corretor_administradora FOREIGN KEY (administradora_id) REFERENCES cedente (id),
  KEY fk_administradora_corretora (corretor_id),
  CONSTRAINT fk_administradora_corretora FOREIGN KEY (corretor_id) REFERENCES usuario (id)
)