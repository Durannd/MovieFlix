# MovieFlix - Documentação do Banco de Dados

## Visão Geral

O MovieFlix utiliza PostgreSQL como sistema de gerenciamento de banco de dados e Flyway para versionamento e migração do schema. Este documento descreve a estrutura completa do banco de dados.

## Configuração do Banco de Dados

### Conexão

```yaml
spring:
  datasource:
    url: jdbc:postgresql://localhost:5432/postgres
    username: postgres
    password: postgres
    driver-class-name: org.postgresql.Driver
```

### Dialect JPA

```yaml
spring:
  jpa:
    database-platform: org.hibernate.dialect.PostgreSQLDialect
    show-sql: true
```

## Estrutura do Banco de Dados

### Diagrama de Relacionamento (ER)

```
┌─────────────┐         ┌──────────────────┐         ┌─────────────┐
│  Category   │         │ movie_category   │         │    Movie    │
├─────────────┤         ├──────────────────┤         ├─────────────┤
│ id (PK)     │────────<│ category_id (FK) │         │ id (PK)     │
│ name        │         │ movie_id (FK)    │>────────│ title       │
└─────────────┘         └──────────────────┘         │ description │
                                                      │ release_date│
                                                      │ rating      │
                                                      │ created_at  │
┌─────────────┐         ┌──────────────────┐         │ updated_at  │
│  Streaming  │         │ movie_streaming  │         └─────────────┘
├─────────────┤         ├──────────────────┤                │
│ id (PK)     │────────<│ streaming_id(FK) │                │
│ name        │         │ movie_id (FK)    │>───────────────┘
└─────────────┘         └──────────────────┘

┌─────────────┐
│    Users    │
├─────────────┤
│ id (PK)     │
│ name        │
│ email       │
│ password    │
└─────────────┘
```

## Tabelas

### 1. movie

Tabela principal que armazena informações sobre filmes.

| Coluna | Tipo | Restrições | Descrição |
|--------|------|------------|-----------|
| id | SERIAL | PRIMARY KEY | Identificador único do filme (auto-incremento) |
| title | VARCHAR(255) | NOT NULL | Título do filme |
| description | TEXT | NULL | Descrição/sinopse do filme |
| release_date | DATE | NULL | Data de lançamento |
| rating | NUMERIC | NULL | Avaliação do filme (0.0 - 10.0) |
| created_at | TIMESTAMP | NULL | Data/hora de criação do registro |
| updated_at | TIMESTAMP | NULL | Data/hora da última atualização |

**Índices:**
- PRIMARY KEY (id)

**Criada em:** Migração V5__create_table_movie.sql

**SQL de Criação:**
```sql
CREATE TABLE movie(
    id serial PRIMARY KEY,
    title varchar(255) NOT NULL,
    description text,
    release_date date,
    rating numeric,
    created_at timestamp,
    updated_at timestamp
);
```

---

### 2. category

Tabela que armazena categorias de filmes.

| Coluna | Tipo | Restrições | Descrição |
|--------|------|------------|-----------|
| id | SERIAL | PRIMARY KEY | Identificador único da categoria (auto-incremento) |
| name | VARCHAR(100) | NOT NULL | Nome da categoria |

**Índices:**
- PRIMARY KEY (id)

**Criada em:** Migração V2__Add_table_category.sql

**Exemplos de Categorias:**
- Ação
- Comédia
- Drama
- Ficção Científica
- Terror
- Suspense
- Romance
- Documentário

---

### 3. streaming

Tabela que armazena plataformas de streaming.

| Coluna | Tipo | Restrições | Descrição |
|--------|------|------------|-----------|
| id | SERIAL | PRIMARY KEY | Identificador único da plataforma (auto-incremento) |
| name | VARCHAR(10) | NOT NULL | Nome da plataforma de streaming |

**Índices:**
- PRIMARY KEY (id)

**Criada em:** Migração V3__Create_table_streaming.sql  
**Refatorada em:** Migração V4__Refact_table_streaming.sql

**Exemplos de Streamings:**
- Netflix
- Amazon Prime
- Disney+
- HBO Max
- Apple TV+
- Paramount+

---

### 4. movie_category

Tabela de relacionamento Many-to-Many entre filmes e categorias.

| Coluna | Tipo | Restrições | Descrição |
|--------|------|------------|-----------|
| movie_id | INTEGER | FOREIGN KEY | Referência para movie.id |
| category_id | INTEGER | FOREIGN KEY | Referência para category.id |

**Chaves Estrangeiras:**
- `fk_movie_category_movie`: movie_id → movie(id)
- `fk_movie_category_category`: category_id → category(id)

**Criada em:** Migração V6__create_movie_category.sql

**SQL de Criação:**
```sql
CREATE TABLE movie_category(
    movie_id INTEGER,
    category_id INTEGER,
    
    CONSTRAINT fk_movie_category_movie 
        FOREIGN KEY (movie_id) REFERENCES movie(id),
    CONSTRAINT fk_movie_category_category 
        FOREIGN KEY (category_id) REFERENCES category(id)
);
```

**Nota:** Um filme pode ter múltiplas categorias e uma categoria pode estar associada a múltiplos filmes.

---

### 5. movie_streaming

Tabela de relacionamento Many-to-Many entre filmes e plataformas de streaming.

| Coluna | Tipo | Restrições | Descrição |
|--------|------|------------|-----------|
| movie_id | INTEGER | FOREIGN KEY | Referência para movie.id |
| streaming_id | INTEGER | FOREIGN KEY | Referência para streaming.id |

**Chaves Estrangeiras:**
- movie_id → movie(id)
- streaming_id → streaming(id)

**Criada em:** Migração V7__create_movie_streaming.sql

**Nota:** Um filme pode estar disponível em múltiplas plataformas de streaming e uma plataforma pode ter múltiplos filmes.

---

### 6. users

Tabela que armazena informações de usuários do sistema.

| Coluna | Tipo | Restrições | Descrição |
|--------|------|------------|-----------|
| id | SERIAL | PRIMARY KEY | Identificador único do usuário (auto-incremento) |
| name | VARCHAR(255) | NOT NULL | Nome completo do usuário |
| email | VARCHAR(255) | NOT NULL | E-mail do usuário |
| password | VARCHAR(255) | NOT NULL | Senha do usuário |

**Índices:**
- PRIMARY KEY (id)

**Criada em:** Migração V8__create_user_table.sql

**SQL de Criação:**
```sql
CREATE TABLE users(
    id serial PRIMARY KEY,
    name varchar(255) NOT NULL,
    email varchar(255) NOT NULL,
    password varchar(255) NOT NULL
);
```

**Nota de Segurança:** ⚠️ Atualmente, as senhas são armazenadas em texto plano. Em produção, deve-se implementar hash de senha (BCrypt, Argon2, etc.).

---

## Relacionamentos

### Relacionamento Movie ↔ Category (Many-to-Many)

- Um filme pode ter várias categorias
- Uma categoria pode estar associada a vários filmes
- Implementado através da tabela `movie_category`

**Exemplo:**
```
Filme "Matrix" → Categorias: "Ficção Científica", "Ação"
Filme "Inception" → Categorias: "Ficção Científica", "Suspense"
```

### Relacionamento Movie ↔ Streaming (Many-to-Many)

- Um filme pode estar disponível em várias plataformas
- Uma plataforma pode ter vários filmes
- Implementado através da tabela `movie_streaming`

**Exemplo:**
```
Filme "Matrix" → Streamings: "Netflix", "Amazon Prime"
Filme "Inception" → Streamings: "Netflix", "HBO Max"
```

---

## Migrations (Flyway)

As migrações são executadas automaticamente na inicialização da aplicação.

### Ordem de Execução

1. **V2__Add_table_category.sql**
   - Cria a tabela `category`

2. **V3__Create_table_streaming.sql**
   - Cria a tabela `streaming` (versão inicial)

3. **V4__Refact_table_streaming.sql**
   - Refatora a tabela `streaming`

4. **V5__create_table_movie.sql**
   - Cria a tabela `movie`

5. **V6__create_movie_category.sql**
   - Cria a tabela de relacionamento `movie_category`

6. **V7__create_movie_streaming.sql**
   - Cria a tabela de relacionamento `movie_streaming`

7. **V8__create_user_table.sql**
   - Cria a tabela `users`

### Localização dos Arquivos

```
src/main/resources/db/migration/
├── V2__Add_table_category.sql
├── V3__Create_table_streaming.sql
├── V4__Refact_table_streaming.sql
├── V5__create_table_movie.sql
├── V6__create_movie_category.sql
├── V7__create_movie_streaming.sql
└── V8__create_user_table.sql
```

### Configuração do Flyway

```yaml
spring:
  flyway:
    enabled: true
```

---

## Exemplos de Consultas SQL

### Buscar todos os filmes com suas categorias

```sql
SELECT 
    m.id,
    m.title,
    m.description,
    m.release_date,
    m.rating,
    c.name as category_name
FROM movie m
LEFT JOIN movie_category mc ON m.id = mc.movie_id
LEFT JOIN category c ON mc.category_id = c.id
ORDER BY m.title;
```

### Buscar filmes por categoria específica

```sql
SELECT 
    m.id,
    m.title,
    m.description,
    m.rating
FROM movie m
INNER JOIN movie_category mc ON m.id = mc.movie_id
INNER JOIN category c ON mc.category_id = c.id
WHERE c.name = 'Ficção Científica';
```

### Buscar filmes disponíveis em uma plataforma específica

```sql
SELECT 
    m.id,
    m.title,
    m.rating
FROM movie m
INNER JOIN movie_streaming ms ON m.id = ms.movie_id
INNER JOIN streaming s ON ms.streaming_id = s.id
WHERE s.name = 'Netflix';
```

### Buscar filmes com múltiplas categorias

```sql
SELECT 
    m.id,
    m.title,
    COUNT(mc.category_id) as num_categories
FROM movie m
INNER JOIN movie_category mc ON m.id = mc.movie_id
GROUP BY m.id, m.title
HAVING COUNT(mc.category_id) > 1;
```

### Buscar categorias mais populares

```sql
SELECT 
    c.name,
    COUNT(mc.movie_id) as total_movies
FROM category c
LEFT JOIN movie_category mc ON c.id = mc.category_id
GROUP BY c.id, c.name
ORDER BY total_movies DESC;
```

---

## Manutenção e Boas Práticas

### Backup do Banco de Dados

```bash
# Backup completo
pg_dump -U postgres -d postgres > movieflix_backup.sql

# Backup apenas do schema
pg_dump -U postgres -d postgres --schema-only > movieflix_schema.sql

# Backup apenas dos dados
pg_dump -U postgres -d postgres --data-only > movieflix_data.sql
```

### Restore do Banco de Dados

```bash
# Restore completo
psql -U postgres -d postgres < movieflix_backup.sql
```

### Adicionando Nova Migration

1. Crie um arquivo seguindo o padrão: `V{número}__{descrição}.sql`
2. Coloque em: `src/main/resources/db/migration/`
3. O Flyway executará automaticamente na próxima inicialização

**Exemplo:**
```
V9__add_movie_duration.sql
```

```sql
ALTER TABLE movie ADD COLUMN duration INTEGER;
COMMENT ON COLUMN movie.duration IS 'Duração do filme em minutos';
```

---

## Considerações de Performance

### Índices Recomendados (Para Produção)

```sql
-- Índice para busca por título
CREATE INDEX idx_movie_title ON movie(title);

-- Índice para busca por data de lançamento
CREATE INDEX idx_movie_release_date ON movie(release_date);

-- Índice para busca por categoria
CREATE INDEX idx_movie_category_category_id ON movie_category(category_id);

-- Índice para busca por streaming
CREATE INDEX idx_movie_streaming_streaming_id ON movie_streaming(streaming_id);

-- Índice único para email de usuários
CREATE UNIQUE INDEX idx_users_email ON users(email);
```

### Otimizações Sugeridas

1. **Paginação**: Implementar paginação nas queries que retornam muitos registros
2. **Cache**: Considerar uso de cache para categorias e streamings (dados que mudam pouco)
3. **Índices**: Adicionar índices baseados nos padrões de consulta mais frequentes
4. **Constraints**: Adicionar UNIQUE constraint no email dos usuários
5. **Cascade**: Definir comportamento de CASCADE nas foreign keys para deleções

---

## Melhorias Futuras

### Segurança
- [ ] Hash de senhas (BCrypt)
- [ ] Constraint UNIQUE no email de usuários
- [ ] Tabela de tokens de autenticação

### Funcionalidades
- [ ] Tabela de avaliações de usuários
- [ ] Tabela de favoritos
- [ ] Tabela de watchlist
- [ ] Histórico de visualizações
- [ ] Comentários e reviews

### Performance
- [ ] Índices adicionais
- [ ] Particionamento de tabelas grandes
- [ ] Views materializadas para queries complexas

---

**Versão do Schema:** 0.0.1  
**Última Atualização:** Janeiro 2025  
**Sistema de Migração:** Flyway
