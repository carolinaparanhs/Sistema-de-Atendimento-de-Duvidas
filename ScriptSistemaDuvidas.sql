


USE sistema_duvidas;

ALTER TABLE duvida
ADD COLUMN id_professor INT NULL AFTER id_aluno;

ALTER TABLE duvida
ADD CONSTRAINT fk_duvida_professor
    FOREIGN KEY (id_professor)
    REFERENCES professor(id_professor)
    ON DELETE SET NULL
    ON UPDATE CASCADE;

