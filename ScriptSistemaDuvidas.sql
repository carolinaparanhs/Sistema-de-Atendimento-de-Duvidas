DROP DATABASE IF EXISTS sistema_duvidas;

CREATE DATABASE sistema_duvidas
  CHARACTER SET utf8mb4
  COLLATE utf8mb4_unicode_ci;

USE sistema_duvidas;

CREATE TABLE aluno (
    id_aluno INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(150) NOT NULL,
    email VARCHAR(150) UNIQUE NOT NULL,
    senha VARCHAR(100) NOT NULL
);

CREATE TABLE professor (
    id_professor INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(150) NOT NULL,
    email VARCHAR(150) UNIQUE NOT NULL,
    senha VARCHAR(100) NOT NULL
);

CREATE TABLE duvida (
    id_duvida INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    id_aluno INT NOT NULL,
    id_professor INT NULL,

    titulo VARCHAR(200) NOT NULL,
    descricao TEXT NOT NULL,

    prioridade ENUM('Alta','Media','Baixa') NOT NULL,
    status_atendimento ENUM('Aguardando','Em Atendimento','Resolvido') 
        NOT NULL DEFAULT 'Aguardando',

    data_criacao TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    data_resolucao TIMESTAMP NULL,

    CONSTRAINT fk_duvida_aluno 
        FOREIGN KEY (id_aluno)
        REFERENCES aluno(id_aluno)
        ON DELETE CASCADE
        ON UPDATE CASCADE,

    CONSTRAINT fk_duvida_professor
        FOREIGN KEY (id_professor)
        REFERENCES professor(id_professor)
        ON DELETE SET NULL
        ON UPDATE CASCADE
);
