# Contribuindo para o MovieFlix

Primeiramente, obrigado por considerar contribuir para o MovieFlix! 🎉

Este documento fornece diretrizes para contribuir com o projeto. Seguindo estas diretrizes, você ajuda a manter o projeto organizado e facilita o processo de revisão e aceitação de contribuições.

## 📋 Sumário

- [Código de Conduta](#código-de-conduta)
- [Como Posso Contribuir?](#como-posso-contribuir)
- [Processo de Desenvolvimento](#processo-de-desenvolvimento)
- [Padrões de Código](#padrões-de-código)
- [Estrutura de Commits](#estrutura-de-commits)
- [Testes](#testes)
- [Documentação](#documentação)
- [Reportando Bugs](#reportando-bugs)
- [Sugerindo Melhorias](#sugerindo-melhorias)

## 📜 Código de Conduta

Ao participar deste projeto, você concorda em manter um ambiente respeitoso e colaborativo. Esperamos que todos os participantes:

- Usem linguagem acolhedora e inclusiva
- Respeitem pontos de vista diferentes
- Aceitem críticas construtivas
- Foquem no que é melhor para a comunidade
- Mostrem empatia com outros membros da comunidade

## 🤝 Como Posso Contribuir?

Existem várias formas de contribuir com o MovieFlix:

### 1. Reportando Bugs

Se você encontrou um bug, por favor crie uma issue com:
- Descrição clara do problema
- Passos para reproduzir o bug
- Comportamento esperado vs. comportamento atual
- Screenshots (se aplicável)
- Informações do ambiente (versão do Java, SO, etc.)

### 2. Sugerindo Melhorias

Para sugerir uma melhoria ou nova funcionalidade:
- Verifique se já não existe uma issue similar
- Descreva claramente a funcionalidade proposta
- Explique por que ela seria útil
- Se possível, forneça exemplos de uso

### 3. Contribuindo com Código

Antes de começar a desenvolver:
1. Procure por issues abertas ou crie uma nova
2. Comente na issue que você pretende trabalhar nela
3. Aguarde feedback antes de começar (evita trabalho duplicado)

### 4. Melhorando a Documentação

Documentação nunca é demais! Você pode ajudar:
- Corrigindo erros de digitação
- Melhorando explicações
- Adicionando exemplos
- Traduzindo documentação

## 🔧 Processo de Desenvolvimento

### 1. Fork e Clone

```bash
# Fork o projeto no GitHub e clone seu fork
git clone https://github.com/SEU_USUARIO/MovieFlix.git
cd MovieFlix

# Adicione o repositório original como remote
git remote add upstream https://github.com/Durannd/MovieFlix.git
```

### 2. Crie uma Branch

```bash
# Atualize sua branch main
git checkout main
git pull upstream main

# Crie uma branch para sua feature/bugfix
git checkout -b feature/nome-da-feature
# ou
git checkout -b fix/nome-do-bug
```

### 3. Faça suas Alterações

- Escreva código limpo e legível
- Siga os padrões de código do projeto
- Adicione testes quando aplicável
- Atualize a documentação se necessário

### 4. Teste suas Alterações

```bash
# Execute os testes
./mvnw test

# Execute a aplicação localmente
./mvnw spring-boot:run

# Verifique se tudo funciona corretamente
```

### 5. Commit e Push

```bash
# Adicione suas mudanças
git add .

# Faça commit seguindo o padrão
git commit -m "tipo: descrição breve"

# Envie para seu fork
git push origin feature/nome-da-feature
```

### 6. Abra um Pull Request

1. Acesse seu fork no GitHub
2. Clique em "Pull Request"
3. Preencha o template de PR com:
   - Descrição clara das mudanças
   - Issue relacionada (se houver)
   - Checklist de verificação
   - Screenshots (se aplicável)

## 💻 Padrões de Código

### Java

- **Java Version**: 24
- **Encoding**: UTF-8
- **Indentação**: 4 espaços (não tabs)
- **Line Width**: Máximo 120 caracteres

### Convenções de Nomenclatura

#### Classes e Interfaces
```java
// PascalCase
public class MovieService { }
public interface MovieRepository { }
```

#### Métodos e Variáveis
```java
// camelCase
public Movie findMovieById(Long movieId) { }
private String userName;
```

#### Constantes
```java
// UPPER_SNAKE_CASE
private static final String DEFAULT_CATEGORY = "Sem Categoria";
```

### Estrutura de Classes

```java
@Service
@RequiredArgsConstructor
public class MovieService {
    // 1. Constantes
    private static final int MAX_RATING = 10;
    
    // 2. Dependências injetadas
    private final MovieRepository movieRepository;
    private final CategoryService categoryService;
    
    // 3. Métodos públicos
    public Movie save(Movie movie) {
        // implementação
    }
    
    // 4. Métodos privados
    private void validateMovie(Movie movie) {
        // implementação
    }
}
```

### Anotações do Lombok

Use Lombok para reduzir boilerplate:

```java
@Data                // getter, setter, toString, equals, hashCode
@NoArgsConstructor   // construtor sem argumentos
@AllArgsConstructor  // construtor com todos os argumentos
@Builder             // padrão builder
@RequiredArgsConstructor  // para injeção de dependências
```

### JPA e Entidades

```java
@Entity
@Table(name = "nome_tabela")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MinhaEntidade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String campo;
}
```

## 📝 Estrutura de Commits

Siga o padrão [Conventional Commits](https://www.conventionalcommits.org/):

### Formato

```
tipo(escopo): descrição breve

[corpo opcional]

[rodapé opcional]
```

### Tipos

- `feat`: Nova funcionalidade
- `fix`: Correção de bug
- `docs`: Mudanças na documentação
- `style`: Formatação, falta de ponto e vírgula, etc
- `refactor`: Refatoração de código
- `test`: Adição ou correção de testes
- `chore`: Manutenção, atualização de dependências

### Exemplos

```bash
# Feature
git commit -m "feat(movie): add rating filter endpoint"

# Bug fix
git commit -m "fix(auth): correct password validation"

# Documentation
git commit -m "docs(readme): update installation instructions"

# Refactoring
git commit -m "refactor(service): extract validation to separate method"
```

## 🧪 Testes

### Escrevendo Testes

- Todo código novo deve incluir testes
- Use JUnit 5 para testes unitários
- Use Spring Boot Test para testes de integração
- Mire em pelo menos 80% de cobertura de código

### Estrutura de Testes

```java
@SpringBootTest
class MovieServiceTest {
    
    @Autowired
    private MovieService movieService;
    
    @Test
    @DisplayName("Deve salvar filme com sucesso")
    void shouldSaveMovieSuccessfully() {
        // Given (Arrange)
        Movie movie = Movie.builder()
            .title("Matrix")
            .build();
        
        // When (Act)
        Movie savedMovie = movieService.save(movie);
        
        // Then (Assert)
        assertNotNull(savedMovie.getId());
        assertEquals("Matrix", savedMovie.getTitle());
    }
}
```

### Executando Testes

```bash
# Todos os testes
./mvnw test

# Testes de uma classe específica
./mvnw test -Dtest=MovieServiceTest

# Com relatório de cobertura
./mvnw test jacoco:report
```

## 📚 Documentação

### Documentando Código

Use Javadoc para métodos públicos:

```java
/**
 * Busca um filme por ID.
 *
 * @param id o identificador único do filme
 * @return Optional contendo o filme se encontrado
 * @throws IllegalArgumentException se id for null
 */
public Optional<Movie> findById(Long id) {
    // implementação
}
```

### Atualizando Documentação

Se suas mudanças afetam:
- **API**: Atualize `API_DOCUMENTATION.md`
- **Banco de Dados**: Atualize `DATABASE.md`
- **Setup/Instalação**: Atualize `README.md`

## 🐛 Reportando Bugs

Ao reportar um bug, inclua:

### Título
Descrição clara e concisa do problema

### Descrição
- O que aconteceu
- O que deveria acontecer
- Passos para reproduzir

### Ambiente
- Versão do Java
- Sistema Operacional
- Versão do PostgreSQL
- Versão do Maven

### Exemplo

```markdown
## Bug: Filme não é atualizado quando categoria não existe

### Descrição
Ao tentar atualizar um filme com uma categoria inexistente, a API retorna 200 OK mas não atualiza o filme.

### Passos para reproduzir
1. Criar um filme com categoria ID 1
2. Tentar atualizar o filme com categoria ID 999 (não existe)
3. Verificar que o filme não foi atualizado

### Comportamento esperado
A API deveria retornar 404 ou 400 indicando que a categoria não existe.

### Ambiente
- Java 24
- Ubuntu 22.04
- PostgreSQL 14
- Maven 3.9
```

## 💡 Sugerindo Melhorias

Para sugerir uma melhoria:

### Título
Descrição clara da funcionalidade

### Problema
Descreva o problema que esta funcionalidade resolveria

### Solução Proposta
Como você imagina que funcione

### Alternativas
Outras soluções que você considerou

### Exemplo

```markdown
## Feature: Busca de filmes por título

### Problema
Atualmente só é possível buscar filmes por ID ou categoria. Usuários precisam buscar por título.

### Solução Proposta
Adicionar endpoint GET /movieflix/movie/search?title={nome}

### Detalhes Técnicos
- Busca case-insensitive
- Suporte a busca parcial (LIKE %titulo%)
- Paginação de resultados

### Alternativas
- Implementar busca full-text com PostgreSQL
- Usar Elasticsearch para buscas mais avançadas
```

## 🔍 Checklist do Pull Request

Antes de submeter um PR, verifique:

- [ ] Meu código segue os padrões do projeto
- [ ] Fiz uma revisão do meu próprio código
- [ ] Comentei partes complexas do código
- [ ] Atualizei a documentação
- [ ] Minhas mudanças não geram novos warnings
- [ ] Adicionei testes que provam que minha correção/feature funciona
- [ ] Testes novos e existentes passam localmente
- [ ] Minhas mudanças não quebram funcionalidades existentes

## 🏗️ Estrutura de Branch

```
main
  ├── feature/adicionar-busca-por-titulo
  ├── feature/implementar-paginacao
  ├── fix/corrigir-validacao-email
  └── docs/atualizar-readme
```

### Nomenclatura de Branches

- `feature/nome-da-feature` - Novas funcionalidades
- `fix/nome-do-bug` - Correções de bugs
- `docs/nome-da-doc` - Atualizações de documentação
- `refactor/nome` - Refatorações
- `test/nome` - Adição/melhoria de testes

## 📞 Dúvidas?

Se você tiver dúvidas sobre como contribuir:

1. Verifique a documentação existente
2. Procure em issues fechadas
3. Abra uma issue com a tag `question`
4. Entre em contato com os mantenedores

## 🎉 Reconhecimento

Todos os contribuidores serão reconhecidos no projeto. Suas contribuições são valiosas e apreciadas!

---

**Obrigado por contribuir com o MovieFlix!** 🎬

Desenvolvido com ❤️ pela comunidade
