# Sistema-de-Atendimento-de-Dúvidas

Este projeto foi desenvolvido para a disciplina de Programação Orientada a Objetos (POO) 
com o objetivo de criar um sistema completo de *envio e atendimento de dúvidas entre alunos e professores*

## Funcionalidades do Sistema

### Para Alunos
- Cadastro de aluno
- Login com autenticação
- Envio de dúvidas com:
  - título
  - descrição
  - prioridade (Alta, Média, Baixa)
- Acompanhamento das dúvidas enviadas
- Visualização do status:
  - Aguardando
  - Em Atendimento
  - Resolvida

### Para Professores
- Cadastro de professor
- Login com autenticação
- Visualização de todas as dúvidas registradas
- Filtro por:
  - prioridade
  - status
- Resposta e atendimento da dúvida
- Alteração do status
- Finalização como “Resolvida”

###Conceitos de POO Utilizados
- Encapsulamento (atributos privados + getters/setters)
- Herança (Aluno e Professor herdam de Usuario)
- Polimorfismo
- Abstração
- Separação em camadas (MVC)

###Arquitetura do Projeto
- O projeto segue o padrão MVC:
model → Representação das entidades
dao → Comunicação com o banco de dados
controller → Regras de negócio
view → Interface gráfica (Swing)
util → Conexão com banco

###Banco de Dados
- Banco relacional com uso de:
INSERT
SELECT
UPDATE

### DER – Diagrama Entidade Relacionamento
<img width="450" height="387" alt="image" src="https://github.com/user-attachments/assets/21bd69f7-361b-4be2-9004-68b6585ebbcc" />
