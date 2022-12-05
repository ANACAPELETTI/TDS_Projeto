CREATE TABLE pessoa (
	codigo BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	nome VARCHAR(50) NOT NULL,
	ativo BOOLEAN NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO pessoa (nome, ativo) values ('Ana Capeletti',      true );
INSERT INTO pessoa (nome, ativo) values ('Jeferson Martin',    true );
INSERT INTO pessoa (nome, ativo) values ('Giuvane Conti',      true );
INSERT INTO pessoa (nome, ativo) values ('Vitor Alex',         true );
INSERT INTO pessoa (nome, ativo) values ('Evandro Nakajima',   true );
INSERT INTO pessoa (nome, ativo) values ('Davi Marcondes',     true );
INSERT INTO pessoa (nome, ativo) values ('Giani Ito',          true );
INSERT INTO pessoa (nome, ativo) values ('Diego Thomaz',       true );
INSERT INTO pessoa (nome, ativo) values ('Leiliane Rezende',   true );