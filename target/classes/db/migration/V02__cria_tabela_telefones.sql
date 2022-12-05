CREATE TABLE telefone (
	codigo BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	id_pessoa BIGINT(20) NULL,
	descricao VARCHAR(50) NOT NULL,
	FOREIGN KEY (id_pessoa) REFERENCES pessoa(codigo)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO telefone (id_pessoa, descricao) values (1,  '63 3215-6383');
INSERT INTO telefone (id_pessoa, descricao) values (2,  '45 3269-8903');
INSERT INTO telefone (id_pessoa, descricao) values (3,  '45 3268-8807');
INSERT INTO telefone (id_pessoa, descricao) values (4,  '45 3268-8802');
INSERT INTO telefone (id_pessoa, descricao) values (5,  '45 3268-3700');
INSERT INTO telefone (id_pessoa, descricao) values (6,  '45 3268-8809');
INSERT INTO telefone (id_pessoa, descricao) values (7,  '45 3268-8802');
INSERT INTO telefone (id_pessoa, descricao) values (8,  '45 3268-8802');
INSERT INTO telefone (id_pessoa, descricao) values (9,  '45 3268-8802');
INSERT INTO telefone (id_pessoa, descricao) values (9,  '45 3228-8903');