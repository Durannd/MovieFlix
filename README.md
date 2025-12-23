# MovieFlix 🎬

MovieFlix é uma API RESTful desenvolvida com Spring Boot para gerenciar um catálogo de filmes, permitindo organizar informações sobre filmes, categorias e plataformas de streaming.

## 📋 Sobre o Projeto

O MovieFlix é um sistema backend que permite gerenciar um catálogo completo de filmes com suas respectivas categorias e plataformas de streaming disponíveis. A aplicação fornece endpoints REST para realizar operações CRUD (Create, Read, Update, Delete) em filmes, categorias, streamings e usuários.

## 🚀 Tecnologias Utilizadas

- **Java 24** - Linguagem de programação
- **Spring Boot 3.5.4** - Framework principal
- **Spring Data JPA** - Persistência de dados
- **Spring Validation** - Validação de dados
- **PostgreSQL** - Banco de dados relacional
- **Flyway** - Versionamento de banco de dados
- **Lombok** - Redução de código boilerplate
- **Maven** - Gerenciamento de dependências

## 🏗️ Arquitetura do Projeto

O projeto segue uma arquitetura em camadas:

```
src/main/java/br/com/ricael/movieflix/moviefliex/
├── controller/           # Camada de controle (API REST)
│   ├── request/         # DTOs de requisição
│   └── response/        # DTOs de resposta
├── entity/              # Entidades JPA
├── repository/          # Camada de acesso a dados
├── service/             # Camada de lógica de negócio
└── mapper/              # Conversão entre entidades e DTOs
```

## 📊 Modelo de Dados

### Entidades Principais

#### Movie (Filme)
- `id` - Identificador único
- `title` - Título do filme
- `description` - Descrição/sinopse
- `releaseDate` - Data de lançamento
- `rating` - Avaliação (nota)
- `categories` - Lista de categorias (Many-to-Many)
- `streamings` - Lista de plataformas de streaming (Many-to-Many)
- `createdAt` - Data de criação
- `updatedAt` - Data de atualização

#### Category (Categoria)
- `id` - Identificador único
- `name` - Nome da categoria (ex: Ação, Comédia, Drama)

#### Streaming (Plataforma)
- `id` - Identificador único
- `name` - Nome da plataforma (ex: Netflix, Amazon Prime, Disney+)

#### User (Usuário)
- `id` - Identificador único
- `name` - Nome do usuário
- `email` - E-mail
- `password` - Senha

## 🔌 API Endpoints

### Filmes

| Método | Endpoint | Descrição |
|--------|----------|-----------|
| POST | `/movieflix/movie` | Criar novo filme |
| GET | `/movieflix/movie` | Listar todos os filmes |
| GET | `/movieflix/movie/{id}` | Buscar filme por ID |
| PUT | `/movieflix/movie/{id}` | Atualizar filme |
| DELETE | `/movieflix/movie/{id}` | Deletar filme |
| GET | `/movieflix/movie/search?category={id}` | Buscar filmes por categoria |

### Categorias

| Método | Endpoint | Descrição |
|--------|----------|-----------|
| POST | `/movieflix/category` | Criar nova categoria |
| GET | `/movieflix/category` | Listar todas as categorias |
| GET | `/movieflix/category/{id}` | Buscar categoria por ID |
| DELETE | `/movieflix/category/{id}` | Deletar categoria |

### Streamings

| Método | Endpoint | Descrição |
|--------|----------|-----------|
| POST | `/movieflix/streaming` | Criar nova plataforma |
| GET | `/movieflix/streaming` | Listar todas as plataformas |
| GET | `/movieflix/streaming/{id}` | Buscar plataforma por ID |
| DELETE | `/movieflix/streaming/{id}` | Deletar plataforma |

### Autenticação

| Método | Endpoint | Descrição |
|--------|----------|-----------|
| POST | `/movieflix/auth/register` | Registrar novo usuário |

## 📝 Exemplos de Uso

### Criar uma Categoria

```bash
curl -X POST http://localhost:8080/movieflix/category \
  -H "Content-Type: application/json" \
  -d '{
    "name": "Ação"
  }'
```

### Criar uma Plataforma de Streaming

```bash
curl -X POST http://localhost:8080/movieflix/streaming \
  -H "Content-Type: application/json" \
  -d '{
    "name": "Netflix"
  }'
```

### Criar um Filme

```bash
curl -X POST http://localhost:8080/movieflix/movie \
  -H "Content-Type: application/json" \
  -d '{
    "title": "Matrix",
    "description": "Um hacker descobre a verdade sobre sua realidade",
    "releaseDate": "1999-03-31",
    "rating": 8.7,
    "categories": [
      {"id": 1}
    ],
    "streamings": [
      {"id": 1}
    ]
  }'
```

### Listar Todos os Filmes

```bash
curl -X GET http://localhost:8080/movieflix/movie
```

### Buscar Filme por ID

```bash
curl -X GET http://localhost:8080/movieflix/movie/1
```

### Buscar Filmes por Categoria

```bash
curl -X GET http://localhost:8080/movieflix/movie/search?category=1
```

### Atualizar um Filme

```bash
curl -X PUT http://localhost:8080/movieflix/movie/1 \
  -H "Content-Type: application/json" \
  -d '{
    "title": "Matrix Reloaded",
    "description": "Neo continua sua jornada",
    "releaseDate": "2003-05-15",
    "rating": 7.2,
    "categories": [
      {"id": 1}
    ],
    "streamings": [
      {"id": 1}
    ]
  }'
```

### Deletar um Filme

```bash
curl -X DELETE http://localhost:8080/movieflix/movie/1
```

### Registrar Usuário

```bash
curl -X POST http://localhost:8080/movieflix/auth/register \
  -H "Content-Type: application/json" \
  -d '{
    "name": "João Silva",
    "email": "joao@email.com",
    "password": "senha123"
  }'
```

## ⚙️ Configuração e Instalação

### Pré-requisitos

- Java 24 ou superior
- Maven 3.6+
- PostgreSQL 12+

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

### Instalação e Execução

1. Clone o repositório:
```bash
git clone https://github.com/Durannd/MovieFlix.git
cd MovieFlix
```

2. Execute o projeto com Maven:
```bash
./mvnw spring-boot:run
```

Ou no Windows:
```bash
mvnw.cmd spring-boot:run
```

3. A aplicação estará disponível em: `http://localhost:8080`

### Build do Projeto

Para gerar o arquivo JAR:
```bash
./mvnw clean package
```

O arquivo será gerado em `target/moviefliex-0.0.1-SNAPSHOT.jar`

Para executar o JAR:
```bash
java -jar target/moviefliex-0.0.1-SNAPSHOT.jar
```

## 🗄️ Migrações do Banco de Dados

O projeto utiliza Flyway para versionamento do banco de dados. As migrações são executadas automaticamente na inicialização da aplicação.

Arquivos de migração localizados em: `src/main/resources/db/migration/`

- `V2__Add_table_category.sql` - Cria tabela de categorias
- `V3__Create_table_streaming.sql` - Cria tabela de streaming
- `V4__Refact_table_streaming.sql` - Refatora tabela de streaming
- `V5__create_table_movie.sql` - Cria tabela de filmes
- `V6__create_movie_category.sql` - Cria tabela de relacionamento filme-categoria
- `V7__create_movie_streaming.sql` - Cria tabela de relacionamento filme-streaming
- `V8__create_user_table.sql` - Cria tabela de usuários

## 🔒 Segurança

> **Nota**: Atualmente, as dependências de segurança (Spring Security e JWT) estão comentadas no `pom.xml`. A implementação de autenticação e autorização está planejada para versões futuras.

## 🧪 Testes

Execute os testes com:
```bash
./mvnw test
```

## 📦 Estrutura de Pacotes

```
br.com.ricael.movieflix.moviefliex
├── MoviefliexApplication.java    # Classe principal
├── controller/                    # Controladores REST
│   ├── AuthController.java       # Endpoints de autenticação
│   ├── CategoryController.java   # Endpoints de categorias
│   ├── MovieController.java      # Endpoints de filmes
│   ├── StreamingController.java  # Endpoints de streamings
│   ├── request/                  # DTOs de entrada
│   └── response/                 # DTOs de saída
├── entity/                        # Entidades JPA
│   ├── Category.java
│   ├── Movie.java
│   ├── Streaming.java
│   └── User.java
├── repository/                    # Repositórios JPA
│   ├── CategoryRepository.java
│   ├── MovieRepository.java
│   ├── StreamingRepository.java
│   └── UserRepository.java
├── service/                       # Serviços de negócio
│   ├── CategoryService.java
│   ├── MovieService.java
│   ├── StreamingService.java
│   └── UserService.java
└── mapper/                        # Conversores de objetos
    ├── CategoryMapper.java
    ├── MovieMapper.java
    ├── StreamingMapper.java
    └── UserMapper.java
```

## 🤝 Contribuindo

Contribuições são bem-vindas! Sinta-se à vontade para:

1. Fazer fork do projeto
2. Criar uma branch para sua feature (`git checkout -b feature/NovaFuncionalidade`)
3. Commit suas mudanças (`git commit -m 'Adiciona nova funcionalidade'`)
4. Push para a branch (`git push origin feature/NovaFuncionalidade`)
5. Abrir um Pull Request

## 📄 Licença

Este projeto é um projeto de demonstração desenvolvido para fins educacionais.

## 👤 Autor

- **Ricael** - [Durannd](https://github.com/Durannd)

## 📞 Suporte

Para reportar bugs ou solicitar novas funcionalidades, por favor abra uma [issue](https://github.com/Durannd/MovieFlix/issues).

---

Desenvolvido com ❤️ usando Spring Boot
