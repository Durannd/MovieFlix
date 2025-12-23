# 🎬 MovieFlix API

Uma API RESTful para gerenciamento de filmes, categorias e plataformas de streaming, desenvolvida com Spring Boot e PostgreSQL.

## 🚀 Tecnologias

- **Java 24**
- **Spring Boot 3.5.4**
- **Spring Data JPA**
- **Spring Validation**
- **PostgreSQL**
- **Flyway**
- **Lombok**
- **Maven**

## 📋 Features (Funcionalidades)

- ✅ Gerenciamento de Filmes (CRUD completo)
- ✅ Gerenciamento de Categorias
- ✅ Gerenciamento de Plataformas de Streaming
- ✅ Busca de filmes por categoria
- ✅ Relacionamento Many-to-Many entre filmes, categorias e streamings
- ✅ Sistema de registro de usuários

## 🏗️ Arquitetura

O projeto segue uma arquitetura em camadas:

```
src/main/java/br/com/ricael/movieflix/moviefliex/
├── controller/          # Controllers REST
├── service/            # Lógica de negócio
├── repository/         # Camada de acesso a dados (JPA)
├── entity/             # Entidades do domínio
├── mapper/             # Conversão DTO ↔ Entity
├── request/            # DTOs de requisição
└── response/           # DTOs de resposta
```

## 🔧 Configuração e Instalação

### Pré-requisitos

- Java 24 ou superior
- PostgreSQL 12+ instalado e rodando
- Maven 3.6+

### Configuração do Banco de Dados

1. Crie um banco de dados PostgreSQL:
```sql
CREATE DATABASE postgres;
```

2. Configure as credenciais no arquivo `src/main/resources/application.yaml`:
```yaml
spring:
  datasource:
    url: jdbc:postgresql://localhost:5432/postgres
    username: postgres
    password: postgres
```

As tabelas serão criadas automaticamente pelo Flyway na primeira execução.

### Executando a Aplicação

Execute o projeto usando Maven:

```bash
./mvnw spring-boot:run
```

A aplicação estará disponível em: `http://localhost:8080`

## 🌐 Principais Endpoints

### Filmes

| Método | Endpoint | Descrição |
|--------|----------|-----------|
| `GET` | `/movieflix/movie` | Listar todos os filmes |
| `GET` | `/movieflix/movie/{id}` | Buscar filme por ID |
| `GET` | `/movieflix/movie/search?category={id}` | Buscar filmes por categoria |
| `POST` | `/movieflix/movie` | Criar novo filme |
| `PUT` | `/movieflix/movie/{id}` | Atualizar filme |
| `DELETE` | `/movieflix/movie/{id}` | Deletar filme |

### Categorias

| Método | Endpoint | Descrição |
|--------|----------|-----------|
| `GET` | `/movieflix/category` | Listar todas as categorias |
| `GET` | `/movieflix/category/{id}` | Buscar categoria por ID |
| `POST` | `/movieflix/category` | Criar categoria |
| `DELETE` | `/movieflix/category/{id}` | Deletar categoria |

### Streamings

| Método | Endpoint | Descrição |
|--------|----------|-----------|
| `GET` | `/movieflix/streaming` | Listar todos os streamings |
| `GET` | `/movieflix/streaming/{id}` | Buscar streaming por ID |
| `POST` | `/movieflix/streaming` | Criar streaming |
| `DELETE` | `/movieflix/streaming/{id}` | Deletar streaming |

### Autenticação

| Método | Endpoint | Descrição |
|--------|----------|-----------|
| `POST` | `/movieflix/auth/register` | Registrar novo usuário |

## 📊 Modelo de Dados

### Movie

```java
{
  "id": Long,
  "title": String,
  "description": String,
  "releaseDate": LocalDate,
  "rating": Double,
  "categories": List<Category>,
  "streamings": List<Streaming>,
  "createdAt": LocalDateTime,
  "updatedAt": LocalDateTime
}
```

### Category

```java
{
  "id": Long,
  "name": String
}
```

### Streaming

```java
{
  "id": Long,
  "name": String
}
```

### User

```java
{
  "id": Long,
  "name": String,
  "email": String,
  "password": String
}
```

## 🗄️ Migrations do Banco de Dados

O projeto utiliza Flyway para versionamento do banco de dados. As migrations são executadas automaticamente na inicialização:

- **V2__Add_table_category.sql** - Cria tabela de categorias
- **V3__Create_table_streaming.sql** - Cria tabela de streamings
- **V4__Refact_table_streaming.sql** - Refatoração da tabela streaming
- **V5__create_table_movie.sql** - Cria tabela de filmes
- **V6__create_movie_category.sql** - Cria tabela de relacionamento filme-categoria
- **V7__create_movie_streaming.sql** - Cria tabela de relacionamento filme-streaming
- **V8__create_user_table.sql** - Cria tabela de usuários

## 🎯 Destaques Técnicos

- 🏛️ **Arquitetura RESTful** seguindo boas práticas
- 📦 **Padrão DTO** para transferência de dados
- 🔄 **Mappers** para conversão Entity-DTO
- 🔗 **Relacionamentos JPA** bem definidos (Many-to-Many)
- 📈 **Migrations automáticas** com Flyway
- ✨ **Redução de boilerplate** com Lombok
- ✅ **Validação** de dados com Spring Validation

## 🛠️ Melhorias Futuras

- [ ] Implementar autenticação e autorização (Spring Security + JWT)
- [ ] Adicionar paginação nas listagens
- [ ] Implementar busca por título do filme
- [ ] Adicionar testes unitários e de integração
- [ ] Sistema de avaliações de usuários
- [ ] API de recomendação de filmes
- [ ] Documentação com Swagger/OpenAPI

## 👨‍💻 Autor

**Ricael Durand** - <a href="https://github.com/Durannd">GitHub</a>

## 📝 Licença

Este projeto foi desenvolvido para fins educacionais e de portfólio.

---

⭐ Se você achou este projeto útil, considere dar uma estrela!
