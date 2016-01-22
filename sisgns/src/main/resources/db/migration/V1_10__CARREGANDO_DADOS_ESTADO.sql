CREATE TABLE uf (
  cd_uf       INTEGER,
  ds_uf_sigla CHAR(2),
  ds_uf_nome  VARCHAR(2000)
);

INSERT INTO uf
(cd_uf, ds_uf_sigla, ds_uf_nome)
VALUES (1, 'AC', 'Acre');

INSERT INTO uf
(cd_uf, ds_uf_sigla, ds_uf_nome)
VALUES (2, 'AL', 'Alagoas');

INSERT INTO uf
(cd_uf, ds_uf_sigla, ds_uf_nome)
VALUES (3, 'AM', 'Amazonas');

INSERT INTO uf
(cd_uf, ds_uf_sigla, ds_uf_nome)
VALUES (4, 'AP', 'Amap�');

INSERT INTO uf
(cd_uf, ds_uf_sigla, ds_uf_nome)
VALUES (5, 'BA', 'Bahia');

INSERT INTO uf
(cd_uf, ds_uf_sigla, ds_uf_nome)
VALUES (6, 'CE', 'Cear�');

INSERT INTO uf
(cd_uf, ds_uf_sigla, ds_uf_nome)
VALUES (7, 'DF', 'Distrito Federal');

INSERT INTO uf
(cd_uf, ds_uf_sigla, ds_uf_nome)
VALUES (8, 'ES', 'Esp�rito Santo');

INSERT INTO uf
(cd_uf, ds_uf_sigla, ds_uf_nome)
VALUES (9, 'GO', 'Goi�s');

INSERT INTO uf
(cd_uf, ds_uf_sigla, ds_uf_nome)
VALUES (10, 'MA', 'Maranh�o');

INSERT INTO uf
(cd_uf, ds_uf_sigla, ds_uf_nome)
VALUES (11, 'MG', 'Minas Gerais');

INSERT INTO uf
(cd_uf, ds_uf_sigla, ds_uf_nome)
VALUES (12, 'MS', 'Mato Grosso do Sul');

INSERT INTO uf
(cd_uf, ds_uf_sigla, ds_uf_nome)
VALUES (13, 'MT', 'Mato Grosso');

INSERT INTO uf
(cd_uf, ds_uf_sigla, ds_uf_nome)
VALUES (14, 'PA', 'Par�');

INSERT INTO uf
(cd_uf, ds_uf_sigla, ds_uf_nome)
VALUES (15, 'PB', 'Para�ba');

INSERT INTO uf
(cd_uf, ds_uf_sigla, ds_uf_nome)
VALUES (16, 'PE', 'Pernambuco');

INSERT INTO uf
(cd_uf, ds_uf_sigla, ds_uf_nome)
VALUES (17, 'PI', 'Piau�');

INSERT INTO uf
(cd_uf, ds_uf_sigla, ds_uf_nome)
VALUES (18, 'PR', 'Paran�');

INSERT INTO uf
(cd_uf, ds_uf_sigla, ds_uf_nome)
VALUES (19, 'RJ', 'Rio de Janeiro');

INSERT INTO uf
(cd_uf, ds_uf_sigla, ds_uf_nome)
VALUES (20, 'RN', 'Rio Grande do Norte');

INSERT INTO uf
(cd_uf, ds_uf_sigla, ds_uf_nome)
VALUES (21, 'RO', 'Rond�nia');

INSERT INTO uf
(cd_uf, ds_uf_sigla, ds_uf_nome)
VALUES (22, 'RR', 'Roraima');

INSERT INTO uf
(cd_uf, ds_uf_sigla, ds_uf_nome)
VALUES (23, 'RS', 'Rio Grande do Sul');

INSERT INTO uf
(cd_uf, ds_uf_sigla, ds_uf_nome)
VALUES (24, 'SC', 'Santa Catarina');

INSERT INTO uf
(cd_uf, ds_uf_sigla, ds_uf_nome)
VALUES (25, 'SE', 'Sergipe');

INSERT INTO uf
(cd_uf, ds_uf_sigla, ds_uf_nome)
VALUES (26, 'SP', 'S�o Paulo');

INSERT INTO uf
(cd_uf, ds_uf_sigla, ds_uf_nome)
VALUES (27, 'TO', 'Tocantins');

INSERT INTO estado (id, nome, uf) SELECT
                                    cd_uf,
                                    ds_uf_nome,
                                    ds_uf_sigla
                                  FROM uf
                                  WHERE cd_uf NOT IN (SELECT id
                                                      FROM estado);