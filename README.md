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
- **JUnit & Mockito**

## 🧠 Conceitos aplicados
- CRUD completo (Create, Read, Update, Delete)
- Arquitetura em camadas: Controller, Service, Repository e Model
- Uso de `ResponseEntity` para respostas HTTP semânticas
- Integração com banco de dados via ORM (JPA)
- Boas práticas de versionamento com Git
- Autenticação via JWT
- Testes unitários via JUnit e Mockito

## 🧩 Endpoints principais
| Método | Endpoint | Descrição |
|--------|-----------|-----------|
| POST | `/auth/register` | Registra um usuário |
| POST | `/auth/login` | Loga um usuário já existente |
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
