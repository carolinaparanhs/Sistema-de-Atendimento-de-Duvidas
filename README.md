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
        string nome
        string email
        string senha
    }

    DUVIDA {
        int id_duvida PK
        int id_aluno FK
        string titulo
        string descricao
        string prioridade
        string status_atendimento
        datetime data_criacao
        datetime data_resolucao
    }

    PROFESSOR {
        int id_professor PK
        string nome
        string email
        string senha
    }

    ALUNO ||--o{ DUVIDA : "possui"
    DUVIDA }o--|| PROFESSOR : "atendida por"


