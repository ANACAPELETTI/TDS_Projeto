CREATE TABLE usuario (
	codigo BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	id_pessoa BIGINT(20) NOT NULL,
	ra VARCHAR(20) NOT NULL,
	email VARCHAR(50) NOT NULL,
	senha VARCHAR(150) NOT NULL,
	FOREIGN KEY (id_pessoa) REFERENCES pessoa(codigo)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE cargo (
	codigo BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	descricao VARCHAR(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE usuario_cargo (
	id_usuario BIGINT(20) NOT NULL,
	id_cargo BIGINT(20) NOT NULL,
	PRIMARY KEY (id_usuario, id_cargo),
	FOREIGN KEY (id_usuario) REFERENCES usuario(codigo),
	FOREIGN KEY (id_cargo) REFERENCES cargo(codigo)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 12345

INSERT INTO usuario (id_pessoa, ra, email, senha) values (1, '2153726',   'ana@gmail.com',      '$2a$10$CnbpJnu9M0WjQutNBsfWpe4C8YX.sewhLnEHuEi6IzmsFcv.mBpyy'); 
INSERT INTO usuario (id_pessoa, ra, email, senha) values (2, '2153826',   'jef@gmail.com',      '$2a$10$CnbpJnu9M0WjQutNBsfWpe4C8YX.sewhLnEHuEi6IzmsFcv.mBpyy');
INSERT INTO usuario (id_pessoa, ra, email, senha) values (3, '1521394',   'giuvane@gmail.com',  '$2a$10$CnbpJnu9M0WjQutNBsfWpe4C8YX.sewhLnEHuEi6IzmsFcv.mBpyy');
INSERT INTO usuario (id_pessoa, ra, email, senha) values (4, '5465258',   'vitor@gmail.com',    '$2a$10$CnbpJnu9M0WjQutNBsfWpe4C8YX.sewhLnEHuEi6IzmsFcv.mBpyy');
INSERT INTO usuario (id_pessoa, ra, email, senha) values (5, '7584952',   'evandro@gmail.com',  '$2a$10$CnbpJnu9M0WjQutNBsfWpe4C8YX.sewhLnEHuEi6IzmsFcv.mBpyy');
INSERT INTO usuario (id_pessoa, ra, email, senha) values (6, '6376265',   'davi@gmail.com',     '$2a$10$CnbpJnu9M0WjQutNBsfWpe4C8YX.sewhLnEHuEi6IzmsFcv.mBpyy');
INSERT INTO usuario (id_pessoa, ra, email, senha) values (7, '2752975',   'giani@gmail.com',    '$2a$10$CnbpJnu9M0WjQutNBsfWpe4C8YX.sewhLnEHuEi6IzmsFcv.mBpyy');
INSERT INTO usuario (id_pessoa, ra, email, senha) values (8, '1734546',   'diego@gmail.com',    '$2a$10$CnbpJnu9M0WjQutNBsfWpe4C8YX.sewhLnEHuEi6IzmsFcv.mBpyy');
INSERT INTO usuario (id_pessoa, ra, email, senha) values (9, '4829340',   'leiliane@gmail.com', '$2a$10$CnbpJnu9M0WjQutNBsfWpe4C8YX.sewhLnEHuEi6IzmsFcv.mBpyy');

INSERT INTO cargo (descricao) values ('Aluno');
INSERT INTO cargo (descricao) values ('Professor');
INSERT INTO cargo (descricao) values ('Coordenador');

INSERT INTO usuario_cargo (id_usuario, id_cargo) values (1, 1);
INSERT INTO usuario_cargo (id_usuario, id_cargo) values (2, 1);
INSERT INTO usuario_cargo (id_usuario, id_cargo) values (3, 2);
INSERT INTO usuario_cargo (id_usuario, id_cargo) values (4, 2);
INSERT INTO usuario_cargo (id_usuario, id_cargo) values (5, 2);
INSERT INTO usuario_cargo (id_usuario, id_cargo) values (6, 2);
INSERT INTO usuario_cargo (id_usuario, id_cargo) values (7, 2);
INSERT INTO usuario_cargo (id_usuario, id_cargo) values (8, 2);
INSERT INTO usuario_cargo (id_usuario, id_cargo) values (9, 2);
INSERT INTO usuario_cargo (id_usuario, id_cargo) values (7, 3);
INSERT INTO usuario_cargo (id_usuario, id_cargo) values (8, 3);
INSERT INTO usuario_cargo (id_usuario, id_cargo) values (9, 3);

CREATE TABLE permissoes (
	codigo BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	descricao VARCHAR(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO permissoes (descricao) values ('ROLE_CADASTRAR_TRABALHO');
INSERT INTO permissoes (descricao) values ('ROLE_EDITA_TRABALHO');
INSERT INTO permissoes (descricao) values ('ROLE_REMOVE_TRABALHO');
INSERT INTO permissoes (descricao) values ('ROLE_PESQUISAR_TRABALHO');
INSERT INTO permissoes (descricao) values ('ROLE_CADASTRAR_PESSOA');
INSERT INTO permissoes (descricao) values ('ROLE_EDITA_PESSOA');
INSERT INTO permissoes (descricao) values ('ROLE_REMOVER_PESSOA');
INSERT INTO permissoes (descricao) values ('ROLE_PESQUISAR_PESSOA');
INSERT INTO permissoes (descricao) values ('ROLE_CADASTRAR_USUARIO');
INSERT INTO permissoes (descricao) values ('ROLE_REMOVER_USUARIO');
INSERT INTO permissoes (descricao) values ('ROLE_REDEFINE_SENHA');
INSERT INTO permissoes (descricao) values ('ROLE_PESQUISAR_USUARIO');

CREATE TABLE cargo_permissoes (
	id_cargo BIGINT(20) NOT NULL,
	id_permissoes BIGINT(20) NOT NULL,
	PRIMARY KEY (id_cargo, id_permissoes),
	FOREIGN KEY (id_cargo) REFERENCES cargo(codigo),
	FOREIGN KEY (id_permissoes) REFERENCES permissoes(codigo)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Aluno
INSERT INTO cargo_permissoes (id_cargo, id_permissoes) values (1, 4);

-- Professores
INSERT INTO cargo_permissoes (id_cargo, id_permissoes) values (2, 1);
INSERT INTO cargo_permissoes (id_cargo, id_permissoes) values (2, 2);
INSERT INTO cargo_permissoes (id_cargo, id_permissoes) values (2, 3);
INSERT INTO cargo_permissoes (id_cargo, id_permissoes) values (2, 4);
INSERT INTO cargo_permissoes (id_cargo, id_permissoes) values (2, 11);

-- Coordenadores
INSERT INTO cargo_permissoes (id_cargo, id_permissoes) values (3, 1);
INSERT INTO cargo_permissoes (id_cargo, id_permissoes) values (3, 2);
INSERT INTO cargo_permissoes (id_cargo, id_permissoes) values (3, 3);
INSERT INTO cargo_permissoes (id_cargo, id_permissoes) values (3, 4);
INSERT INTO cargo_permissoes (id_cargo, id_permissoes) values (3, 5);
INSERT INTO cargo_permissoes (id_cargo, id_permissoes) values (3, 6);
INSERT INTO cargo_permissoes (id_cargo, id_permissoes) values (3, 7);
INSERT INTO cargo_permissoes (id_cargo, id_permissoes) values (3, 8);
INSERT INTO cargo_permissoes (id_cargo, id_permissoes) values (3, 9);
INSERT INTO cargo_permissoes (id_cargo, id_permissoes) values (3, 10);
INSERT INTO cargo_permissoes (id_cargo, id_permissoes) values (3, 11);
INSERT INTO cargo_permissoes (id_cargo, id_permissoes) values (3, 12);