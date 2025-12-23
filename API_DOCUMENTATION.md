# MovieFlix - Documentação da API

## Sumário

- [Visão Geral](#visão-geral)
- [Base URL](#base-url)
- [Formato de Dados](#formato-de-dados)
- [Endpoints de Filmes](#endpoints-de-filmes)
- [Endpoints de Categorias](#endpoints-de-categorias)
- [Endpoints de Streamings](#endpoints-de-streamings)
- [Endpoints de Autenticação](#endpoints-de-autenticação)
- [Códigos de Status](#códigos-de-status)
- [Tratamento de Erros](#tratamento-de-erros)

## Visão Geral

A API MovieFlix fornece endpoints RESTful para gerenciar um catálogo de filmes, categorias e plataformas de streaming.

## Base URL

```
http://localhost:8080
```

## Formato de Dados

- Todas as requisições e respostas utilizam formato JSON
- Datas seguem o formato ISO 8601: `YYYY-MM-DD`
- Encoding: UTF-8

## Endpoints de Filmes

### 1. Criar Filme

Cria um novo filme no catálogo.

**Endpoint:** `POST /movieflix/movie`

**Request Body:**
```json
{
  "title": "Matrix",
  "description": "Um hacker descobre a verdade sobre sua realidade e seu papel na guerra contra seus controladores",
  "releaseDate": "1999-03-31",
  "rating": 8.7,
  "categories": [
    {"id": 1},
    {"id": 2}
  ],
  "streamings": [
    {"id": 1},
    {"id": 2}
  ]
}
```

**Response:** `200 OK`
```json
{
  "id": 1,
  "title": "Matrix",
  "description": "Um hacker descobre a verdade sobre sua realidade e seu papel na guerra contra seus controladores",
  "releaseDate": "1999-03-31",
  "rating": 8.7,
  "createdAt": "2025-01-15T10:30:00",
  "updatedAt": "2025-01-15T10:30:00",
  "categories": [
    {
      "id": 1,
      "name": "Ficção Científica"
    },
    {
      "id": 2,
      "name": "Ação"
    }
  ],
  "streamings": [
    {
      "id": 1,
      "name": "Netflix"
    },
    {
      "id": 2,
      "name": "Amazon Prime"
    }
  ]
}
```

**Campos Obrigatórios:**
- `title` (String, máximo 255 caracteres)

**Campos Opcionais:**
- `description` (String, texto longo)
- `releaseDate` (Date, formato: YYYY-MM-DD)
- `rating` (Double, 0.0 a 10.0)
- `categories` (Array de objetos com id)
- `streamings` (Array de objetos com id)

---

### 2. Listar Todos os Filmes

Retorna todos os filmes cadastrados.

**Endpoint:** `GET /movieflix/movie`

**Response:** `200 OK`
```json
[
  {
    "id": 1,
    "title": "Matrix",
    "description": "Um hacker descobre a verdade sobre sua realidade",
    "releaseDate": "1999-03-31",
    "rating": 8.7,
    "createdAt": "2025-01-15T10:30:00",
    "updatedAt": "2025-01-15T10:30:00",
    "categories": [
      {
        "id": 1,
        "name": "Ficção Científica"
      }
    ],
    "streamings": [
      {
        "id": 1,
        "name": "Netflix"
      }
    ]
  },
  {
    "id": 2,
    "title": "Inception",
    "description": "Um ladrão que rouba segredos corporativos através do uso da tecnologia de compartilhamento de sonhos",
    "releaseDate": "2010-07-16",
    "rating": 8.8,
    "createdAt": "2025-01-15T11:00:00",
    "updatedAt": "2025-01-15T11:00:00",
    "categories": [
      {
        "id": 1,
        "name": "Ficção Científica"
      }
    ],
    "streamings": [
      {
        "id": 1,
        "name": "Netflix"
      }
    ]
  }
]
```

---

### 3. Buscar Filme por ID

Retorna os detalhes de um filme específico.

**Endpoint:** `GET /movieflix/movie/{id}`

**Parâmetros de URL:**
- `id` (Long) - ID do filme

**Response:** `200 OK`
```json
{
  "id": 1,
  "title": "Matrix",
  "description": "Um hacker descobre a verdade sobre sua realidade",
  "releaseDate": "1999-03-31",
  "rating": 8.7,
  "createdAt": "2025-01-15T10:30:00",
  "updatedAt": "2025-01-15T10:30:00",
  "categories": [
    {
      "id": 1,
      "name": "Ficção Científica"
    }
  ],
  "streamings": [
    {
      "id": 1,
      "name": "Netflix"
    }
  ]
}
```

**Possíveis Respostas:**
- `200 OK` - Filme encontrado
- `404 Not Found` - Filme não encontrado

---

### 4. Atualizar Filme

Atualiza as informações de um filme existente.

**Endpoint:** `PUT /movieflix/movie/{id}`

**Parâmetros de URL:**
- `id` (Long) - ID do filme

**Request Body:**
```json
{
  "title": "Matrix Reloaded",
  "description": "Neo continua sua jornada no mundo real e na Matrix",
  "releaseDate": "2003-05-15",
  "rating": 7.2,
  "categories": [
    {"id": 1},
    {"id": 2}
  ],
  "streamings": [
    {"id": 1}
  ]
}
```

**Response:** `200 OK`
```json
{
  "id": 1,
  "title": "Matrix Reloaded",
  "description": "Neo continua sua jornada no mundo real e na Matrix",
  "releaseDate": "2003-05-15",
  "rating": 7.2,
  "createdAt": "2025-01-15T10:30:00",
  "updatedAt": "2025-01-15T14:30:00",
  "categories": [
    {
      "id": 1,
      "name": "Ficção Científica"
    },
    {
      "id": 2,
      "name": "Ação"
    }
  ],
  "streamings": [
    {
      "id": 1,
      "name": "Netflix"
    }
  ]
}
```

**Possíveis Respostas:**
- `200 OK` - Filme atualizado com sucesso
- `404 Not Found` - Filme não encontrado

---

### 5. Deletar Filme

Remove um filme do catálogo.

**Endpoint:** `DELETE /movieflix/movie/{id}`

**Parâmetros de URL:**
- `id` (Long) - ID do filme

**Response:** `204 No Content`
```json
"Filme deletado com sucesso!"
```

**Possíveis Respostas:**
- `204 No Content` - Filme deletado com sucesso
- `404 Not Found` - Filme não encontrado
  ```json
  "Filme não encontrado"
  ```

---

### 6. Buscar Filmes por Categoria

Retorna todos os filmes de uma categoria específica.

**Endpoint:** `GET /movieflix/movie/search`

**Query Parameters:**
- `category` (Long) - ID da categoria

**Exemplo:** `GET /movieflix/movie/search?category=1`

**Response:** `200 OK`
```json
[
  {
    "id": 1,
    "title": "Matrix",
    "description": "Um hacker descobre a verdade sobre sua realidade",
    "releaseDate": "1999-03-31",
    "rating": 8.7,
    "createdAt": "2025-01-15T10:30:00",
    "updatedAt": "2025-01-15T10:30:00",
    "categories": [
      {
        "id": 1,
        "name": "Ficção Científica"
      }
    ],
    "streamings": [
      {
        "id": 1,
        "name": "Netflix"
      }
    ]
  }
]
```

---

## Endpoints de Categorias

### 1. Criar Categoria

Cria uma nova categoria de filmes.

**Endpoint:** `POST /movieflix/category`

**Request Body:**
```json
{
  "name": "Ficção Científica"
}
```

**Response:** `201 Created`
```json
{
  "id": 1,
  "name": "Ficção Científica"
}
```

**Campos Obrigatórios:**
- `name` (String, máximo 100 caracteres)

---

### 2. Listar Todas as Categorias

Retorna todas as categorias cadastradas.

**Endpoint:** `GET /movieflix/category`

**Response:** `200 OK`
```json
[
  {
    "id": 1,
    "name": "Ficção Científica"
  },
  {
    "id": 2,
    "name": "Ação"
  },
  {
    "id": 3,
    "name": "Comédia"
  },
  {
    "id": 4,
    "name": "Drama"
  }
]
```

---

### 3. Buscar Categoria por ID

Retorna os detalhes de uma categoria específica.

**Endpoint:** `GET /movieflix/category/{id}`

**Parâmetros de URL:**
- `id` (Long) - ID da categoria

**Response:** `200 OK`
```json
{
  "id": 1,
  "name": "Ficção Científica"
}
```

**Possíveis Respostas:**
- `200 OK` - Categoria encontrada
- `404 Not Found` - Categoria não encontrada

---

### 4. Deletar Categoria

Remove uma categoria do sistema.

**Endpoint:** `DELETE /movieflix/category/{id}`

**Parâmetros de URL:**
- `id` (Long) - ID da categoria

**Response:** `204 No Content`

---

## Endpoints de Streamings

### 1. Criar Streaming

Cria uma nova plataforma de streaming.

**Endpoint:** `POST /movieflix/streaming`

**Request Body:**
```json
{
  "name": "Netflix"
}
```

**Response:** `201 Created`
```json
{
  "id": 1,
  "name": "Netflix"
}
```

**Campos Obrigatórios:**
- `name` (String, máximo 10 caracteres)

---

### 2. Listar Todos os Streamings

Retorna todas as plataformas de streaming cadastradas.

**Endpoint:** `GET /movieflix/streaming`

**Response:** `200 OK`
```json
[
  {
    "id": 1,
    "name": "Netflix"
  },
  {
    "id": 2,
    "name": "Amazon Prime"
  },
  {
    "id": 3,
    "name": "Disney+"
  },
  {
    "id": 4,
    "name": "HBO Max"
  }
]
```

---

### 3. Buscar Streaming por ID

Retorna os detalhes de uma plataforma específica.

**Endpoint:** `GET /movieflix/streaming/{id}`

**Parâmetros de URL:**
- `id` (Long) - ID da plataforma

**Response:** `200 OK`
```json
{
  "id": 1,
  "name": "Netflix"
}
```

**Possíveis Respostas:**
- `200 OK` - Streaming encontrado
- `404 Not Found` - Streaming não encontrado

---

### 4. Deletar Streaming

Remove uma plataforma de streaming do sistema.

**Endpoint:** `DELETE /movieflix/streaming/{id}`

**Parâmetros de URL:**
- `id` (Long) - ID da plataforma

**Response:** `204 No Content`

---

## Endpoints de Autenticação

### 1. Registrar Usuário

Registra um novo usuário no sistema.

**Endpoint:** `POST /movieflix/auth/register`

**Request Body:**
```json
{
  "name": "João Silva",
  "email": "joao@email.com",
  "password": "senha123"
}
```

**Response:** `201 Created`
```json
{
  "id": 1,
  "name": "João Silva",
  "email": "joao@email.com",
  "password": "senha123"
}
```

**Campos Obrigatórios:**
- `name` (String, máximo 255 caracteres)
- `email` (String, máximo 255 caracteres)
- `password` (String, máximo 255 caracteres)

> **Nota de Segurança:** A senha está sendo retornada na resposta apenas por não haver implementação de segurança. Em produção, isso deve ser alterado.

---

## Códigos de Status

| Código | Descrição |
|--------|-----------|
| 200 OK | Requisição bem-sucedida |
| 201 Created | Recurso criado com sucesso |
| 204 No Content | Requisição bem-sucedida sem conteúdo de retorno |
| 404 Not Found | Recurso não encontrado |
| 400 Bad Request | Requisição inválida |
| 500 Internal Server Error | Erro interno do servidor |

---

## Tratamento de Erros

Em caso de erro, a API pode retornar diferentes formatos dependendo do tipo de erro.

### Exemplo de Erro 404
```json
{
  "timestamp": "2025-01-15T10:30:00",
  "status": 404,
  "error": "Not Found",
  "path": "/movieflix/movie/999"
}
```

---

## Fluxo de Trabalho Recomendado

1. **Criar Categorias**: Primeiro, crie as categorias de filmes que você vai usar
2. **Criar Streamings**: Em seguida, cadastre as plataformas de streaming
3. **Criar Filmes**: Agora você pode criar filmes associando-os às categorias e streamings criados
4. **Registrar Usuários**: Cadastre usuários que irão interagir com o sistema

### Exemplo Completo de Fluxo

```bash
# 1. Criar categoria
curl -X POST http://localhost:8080/movieflix/category \
  -H "Content-Type: application/json" \
  -d '{"name": "Ficção Científica"}'

# 2. Criar streaming
curl -X POST http://localhost:8080/movieflix/streaming \
  -H "Content-Type: application/json" \
  -d '{"name": "Netflix"}'

# 3. Criar filme
curl -X POST http://localhost:8080/movieflix/movie \
  -H "Content-Type: application/json" \
  -d '{
    "title": "Matrix",
    "description": "Um hacker descobre a verdade sobre sua realidade",
    "releaseDate": "1999-03-31",
    "rating": 8.7,
    "categories": [{"id": 1}],
    "streamings": [{"id": 1}]
  }'

# 4. Buscar todos os filmes
curl -X GET http://localhost:8080/movieflix/movie
```

---

## Observações Importantes

1. **IDs nas Relações**: Ao criar ou atualizar filmes, você deve fornecer apenas o `id` das categorias e streamings existentes
2. **Validação**: Certifique-se de criar categorias e streamings antes de associá-los a filmes
3. **Timestamps Automáticos**: Os campos `createdAt` e `updatedAt` são gerenciados automaticamente pelo sistema
4. **Segurança**: A implementação de autenticação e autorização está planejada para versões futuras

---

## Testando a API

### Usando cURL

Todos os exemplos nesta documentação utilizam cURL e podem ser executados diretamente no terminal.

### Usando Postman

1. Importe a coleção de endpoints
2. Configure a base URL como `http://localhost:8080`
3. Use os exemplos de request body fornecidos acima

### Usando Navegador

Para requisições GET simples, você pode usar diretamente o navegador:
- `http://localhost:8080/movieflix/movie`
- `http://localhost:8080/movieflix/category`
- `http://localhost:8080/movieflix/streaming`

---

**Última Atualização:** Janeiro 2025  
**Versão da API:** 0.0.1-SNAPSHOT
