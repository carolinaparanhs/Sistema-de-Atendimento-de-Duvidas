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

### DER – Diagrama Entidade Relacionamento

```mermaid
erDiagram
    ALUNO {
        int id_aluno PK
        varchar nome
        varchar email
        varchar senha
    }

    PROFESSOR {
        int id_professor PK
        varchar nome
        varchar email
        varchar senha
    }

    DUVIDA {
        int id_duvida PK
        int id_aluno FK
        int id_professor FK
        varchar titulo
        text descricao
        enum prioridade
        enum status_atendimento
        timestamp data_criacao
        timestamp data_resolucao
    }

    ALUNO ||--o{ DUVIDA : "cria"
    PROFESSOR ||--o{ DUVIDA : "atende"



