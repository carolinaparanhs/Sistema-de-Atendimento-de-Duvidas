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

  ##DER – Diagrama Entidade Relacionamento

                      ┌────────────────────────┐
                      │        ALUNO           │
                      ├────────────────────────┤
                      │ PK id_aluno            │
                      │ nome                   │
                      │ email                  │
                      │ senha                  │
                      └───────────┬────────────┘
                                  │ 1:N
                                  │
                                  │
                     ┌────────────▼─────────────┐
                     │         DÚVIDA            │
                     ├───────────────────────────┤
                     │ PK id_duvida              │
                     │ FK id_aluno               │
                     │ titulo                    │
                     │ descricao                 │
                     │ prioridade                │
                     │ status_atendimento        │
                     │ data_criacao             │
                     │ data_resolucao           │
                     └────────────┬─────────────┘
                                  │ N:1
                                  │
                                  │
                     ┌────────────▼─────────────┐
                     │        PROFESSOR          │
                     ├───────────────────────────┤
                     │ PK id_professor           │
                     │ nome                      │
                     │ email                     │
                     │ senha                     │
                     └───────────────────────────┘

