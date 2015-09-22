CREATE TABLE estado (
  id   BIGINT       NOT NULL,
  nome VARCHAR(255) NOT NULL,
  uf   CHAR(2)      NOT NULL UNIQUE,
  PRIMARY KEY (id)
);


INSERT INTO ESTADO (UF, NOME, ID) VALUES ('AC', 'Acre', 1),
  ('AL', 'Alagoas', 2),
  ('AP', 'Amapá', 3),
  ('AM', 'Amazonas', 4),
  ('BA', 'Bahia', 5),
  ('CE', 'Ceará', 6),
  ('ES', 'Espírito Santo', 7),
  ('GO', 'Goiás', 8),
  ('MA', 'Maranhão', 9),
  ('MT', 'Mato Grosso', 10),
  ('MS', 'Mato Grosso do Sul', 11),
  ('MG', 'Minas Gerais', 12),
  ('PA', 'Pará', 13),
  ('PB', 'Paraíba', 14),
  ('PR', 'Paraná', 15),
  ('PE', 'Pernambuco', 16),
  ('PI', 'Piauí', 17),
  ('RJ', 'Rio de Janeiro', 18),
  ('RN', 'Rio Grande do Norte', 19),
  ('RS', 'Rio Grande do Sul', 20),
  ('RO', 'Rondônia', 21),
  ('RR', 'Roraima', 22),
  ('SC', 'Santa Catarina', 23),
  ('SP', 'São Paulo', 24),
  ('SE', 'Sergipe', 25),
  ('TO', 'Tocantins', 26),
  ('DF', 'Distrito Federal', 27);
  
