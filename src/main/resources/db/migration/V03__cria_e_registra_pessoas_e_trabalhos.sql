CREATE TABLE trabalho (
	codigo BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	titulo VARCHAR(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO trabalho (titulo) values ('TCC1');
INSERT INTO trabalho (titulo) values ('TCC1 - teste');

CREATE TABLE pessoa_trabalho (
	id_pessoa BIGINT(20) NOT NULL,
	id_trabalho BIGINT(20) NOT NULL,
	PRIMARY KEY (id_pessoa, id_trabalho),
	FOREIGN KEY (id_pessoa) REFERENCES pessoa(codigo),
	FOREIGN KEY (id_trabalho) REFERENCES trabalho(codigo)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO pessoa_trabalho (id_pessoa, id_trabalho) values (1, 1);
INSERT INTO pessoa_trabalho (id_pessoa, id_trabalho) values (5, 1);
INSERT INTO pessoa_trabalho (id_pessoa, id_trabalho) values (3, 1);
INSERT INTO pessoa_trabalho (id_pessoa, id_trabalho) values (2, 2);
INSERT INTO pessoa_trabalho (id_pessoa, id_trabalho) values (3, 2);