# MovieFlix - Guia de Referência Rápida

Este documento fornece uma referência rápida para desenvolvedores trabalhando no projeto MovieFlix.

## 🚀 Início Rápido

### Setup em 3 Passos

```bash
# 1. Clone o repositório
git clone https://github.com/Durannd/MovieFlix.git && cd MovieFlix

# 2. Configure o PostgreSQL
# Certifique-se de que o PostgreSQL está rodando na porta 5432
# com usuário: postgres, senha: postgres, database: postgres

# 3. Execute a aplicação
./mvnw spring-boot:run
```

Aplicação disponível em: `http://localhost:8080`

## 📍 Endpoints Principais

### Movies
```
POST   /movieflix/movie                    # Criar filme
GET    /movieflix/movie                    # Listar todos
GET    /movieflix/movie/{id}               # Buscar por ID
PUT    /movieflix/movie/{id}               # Atualizar
DELETE /movieflix/movie/{id}               # Deletar
GET    /movieflix/movie/search?category=1  # Buscar por categoria
```

### Categories
```
POST   /movieflix/category        # Criar categoria
GET    /movieflix/category        # Listar todas
GET    /movieflix/category/{id}   # Buscar por ID
DELETE /movieflix/category/{id}   # Deletar
```

### Streamings
```
POST   /movieflix/streaming       # Criar streaming
GET    /movieflix/streaming       # Listar todos
GET    /movieflix/streaming/{id}  # Buscar por ID
DELETE /movieflix/streaming/{id}  # Deletar
```

### Auth
```
POST   /movieflix/auth/register   # Registrar usuário
```

## 📦 Estrutura do Projeto

```
moviefliex/
├── controller/          # Endpoints REST
│   ├── MovieController
│   ├── CategoryController
│   ├── StreamingController
│   └── AuthController
│
├── service/            # Lógica de negócio
│   ├── MovieService
│   ├── CategoryService
│   ├── StreamingService
│   └── UserService
│
├── repository/         # Acesso ao banco
│   ├── MovieRepository
│   ├── CategoryRepository
│   ├── StreamingRepository
│   └── UserRepository
│
├── entity/            # Entidades JPA
│   ├── Movie
│   ├── Category
│   ├── Streaming
│   └── User
│
├── mapper/            # Conversão DTO ↔ Entity
│   ├── MovieMapper
│   ├── CategoryMapper
│   ├── StreamingMapper
│   └── UserMapper
│
└── controller/
    ├── request/       # DTOs de entrada
    └── response/      # DTOs de saída
```

## 🔄 Fluxo de Dados

```
Cliente → Controller → Service → Repository → Database
          ↓ Request      ↓ Entity    ↓ Entity      ↑
          ↑ Response     ↑ Entity    ↑ Entity      ↓
```

## 🎯 Exemplos Rápidos

### 1. Criar e Consultar Filme

```bash
# Criar categoria
curl -X POST http://localhost:8080/movieflix/category \
  -H "Content-Type: application/json" \
  -d '{"name": "Ação"}'

# Criar streaming
curl -X POST http://localhost:8080/movieflix/streaming \
  -H "Content-Type: application/json" \
  -d '{"name": "Netflix"}'

# Criar filme
curl -X POST http://localhost:8080/movieflix/movie \
  -H "Content-Type: application/json" \
  -d '{
    "title": "Matrix",
    "description": "Um filme incrível",
    "releaseDate": "1999-03-31",
    "rating": 8.7,
    "categories": [{"id": 1}],
    "streamings": [{"id": 1}]
  }'

# Buscar filme
curl http://localhost:8080/movieflix/movie/1
```

### 2. JSON de Request/Response

**Request - Criar Filme:**
```json
{
  "title": "Inception",
  "description": "Sonhos dentro de sonhos",
  "releaseDate": "2010-07-16",
  "rating": 8.8,
  "categories": [{"id": 1}],
  "streamings": [{"id": 1}]
}
```

**Response - Filme Criado:**
```json
{
  "id": 1,
  "title": "Inception",
  "description": "Sonhos dentro de sonhos",
  "releaseDate": "2010-07-16",
  "rating": 8.8,
  "createdAt": "2025-01-15T10:30:00",
  "updatedAt": "2025-01-15T10:30:00",
  "categories": [
    {"id": 1, "name": "Ficção Científica"}
  ],
  "streamings": [
    {"id": 1, "name": "Netflix"}
  ]
}
```

## 🗄️ Schema do Banco

```sql
-- Tabelas principais
movie           (id, title, description, release_date, rating, created_at, updated_at)
category        (id, name)
streaming       (id, name)
users           (id, name, email, password)

-- Tabelas de relacionamento
movie_category  (movie_id, category_id)
movie_streaming (movie_id, streaming_id)
```

## 🛠️ Comandos Maven

```bash
# Compilar
./mvnw compile

# Executar aplicação
./mvnw spring-boot:run

# Executar testes
./mvnw test

# Gerar JAR
./mvnw clean package

# Limpar build
./mvnw clean

# Pular testes
./mvnw clean package -DskipTests
```

## 🔍 Debugging

### Verificar Status da Aplicação

```bash
# Verificar se está rodando
curl http://localhost:8080/actuator/health

# Ver logs
tail -f logs/application.log
```

### Conectar ao Banco

```bash
# Via linha de comando
psql -U postgres -d postgres

# Comandos úteis no psql
\dt              # Listar tabelas
\d movie         # Descrever tabela movie
SELECT * FROM movie LIMIT 5;
```

## 📝 Padrões de Código

### Controller
```java
@RestController
@RequestMapping("/movieflix/movie")
@RequiredArgsConstructor
public class MovieController {
    private final MovieService movieService;
    
    @GetMapping("/{id}")
    public ResponseEntity<MovieResponse> findById(@PathVariable Long id) {
        return movieService.findById(id)
            .map(MovieMapper::toMovieResponse)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }
}
```

### Service
```java
@Service
@RequiredArgsConstructor
public class MovieService {
    private final MovieRepository movieRepository;
    
    public Optional<Movie> findById(Long id) {
        return movieRepository.findById(id);
    }
}
```

### Entity
```java
@Entity
@Table(name = "movie")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String title;
}
```

## 🎨 Adicionando Nova Feature

### 1. Criar Endpoint de Busca por Título

```java
// Controller
@GetMapping("/search/title")
public ResponseEntity<List<MovieResponse>> findByTitle(
    @RequestParam String title) {
    return ResponseEntity.ok(
        movieService.findByTitle(title)
            .stream()
            .map(MovieMapper::toMovieResponse)
            .toList()
    );
}

// Service
public List<Movie> findByTitle(String title) {
    return movieRepository.findByTitleContainingIgnoreCase(title);
}

// Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {
    List<Movie> findByTitleContainingIgnoreCase(String title);
}
```

## 🔐 Configurações

### application.yaml
```yaml
spring:
  datasource:
    url: jdbc:postgresql://localhost:5432/postgres
    username: postgres
    password: postgres
  
  jpa:
    show-sql: true           # Mostrar SQL no console
    database-platform: org.hibernate.dialect.PostgreSQLDialect
  
  flyway:
    enabled: true            # Ativar migrations
```

### Alterar Porta
```yaml
server:
  port: 8081  # Adicionar em application.yaml
```

## 📚 Recursos Adicionais

| Documento | Descrição |
|-----------|-----------|
| [README.md](README.md) | Visão geral do projeto |
| [API_DOCUMENTATION.md](API_DOCUMENTATION.md) | Documentação completa da API |
| [DATABASE.md](DATABASE.md) | Schema e migrations do banco |
| [CONTRIBUTING.md](CONTRIBUTING.md) | Guia de contribuição |

## 🐛 Troubleshooting

### Aplicação não inicia
```bash
# Verificar se PostgreSQL está rodando
sudo systemctl status postgresql

# Verificar porta 8080 livre
lsof -i :8080

# Limpar e recompilar
./mvnw clean install
```

### Erro de Migration
```bash
# Resetar Flyway (CUIDADO: apaga dados)
DROP TABLE flyway_schema_history;

# Recriar banco
DROP DATABASE postgres;
CREATE DATABASE postgres;
```

### Erro de Conexão com Banco
```yaml
# Verificar configurações em application.yaml
spring:
  datasource:
    url: jdbc:postgresql://localhost:5432/postgres
    username: postgres
    password: postgres
```

## 🎯 Dicas de Produtividade

1. **Use Lombok**: Reduz boilerplate com `@Data`, `@Builder`
2. **Hot Reload**: Use Spring DevTools para reload automático
3. **Postman/Insomnia**: Importe coleção de endpoints
4. **Git Aliases**: Configure aliases para comandos comuns
5. **IDE Plugins**: Use plugins para Spring Boot

## 📊 Métricas de Código

### Cobertura de Testes
```bash
# Gerar relatório
./mvnw test jacoco:report

# Ver em: target/site/jacoco/index.html
```

### Análise Estática
```bash
# Adicionar ao pom.xml se necessário
./mvnw checkstyle:check
./mvnw pmd:check
```

## 🔄 Workflow Git

```bash
# Atualizar main
git checkout main
git pull origin main

# Criar feature branch
git checkout -b feature/nova-funcionalidade

# Fazer alterações, commit e push
git add .
git commit -m "feat: adicionar nova funcionalidade"
git push origin feature/nova-funcionalidade

# Criar Pull Request no GitHub
```

## 📞 Contatos

- **Issues**: [GitHub Issues](https://github.com/Durannd/MovieFlix/issues)
- **Autor**: Ricael - [@Durannd](https://github.com/Durannd)

---

**Última atualização**: Janeiro 2025  
**Versão**: 0.0.1-SNAPSHOT

> 💡 **Dica**: Mantenha este guia aberto enquanto desenvolve!
