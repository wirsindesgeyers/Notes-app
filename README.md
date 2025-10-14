# 📝 Notes API

API RESTful desenvolvida em **Java com Spring Boot**, com o objetivo de gerenciar notas de forma simples, aplicando arquitetura **MVC** e boas práticas de desenvolvimento **back-end**.

## 🚀 Tecnologias Utilizadas
- **Java 21**
- **Spring Boot**
- **Spring Data JPA**
- **PostgreSQL**
- **Maven**
- **Flyway**
- **Spring Security**

## 🧠 Conceitos aplicados
- CRUD completo (Create, Read, Update, Delete)
- Arquitetura em camadas: Controller, Service, Repository e Model
- Uso de `ResponseEntity` para respostas HTTP semânticas
- Integração com banco de dados via ORM (JPA)
- Boas práticas de versionamento com Git

## 🧩 Endpoints principais
| Método | Endpoint | Descrição |
|--------|-----------|-----------|
| POST | `/notes` | Cria uma nova nota |
| GET | `/notes` | Lista todas as notas |
| GET | `/notes/{id}` | Busca uma nota específica |
| PUT | `/notes/{id}` | Atualiza uma nota existente |
| DELETE | `/notes/{id}` | Remove uma nota |

## 🧰 Exemplo de JSON
```json
{
  "title": "Estudar Spring Boot",
  "content": "Revisar camada Service e Repository"
}
