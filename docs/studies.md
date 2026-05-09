# 08-05-2026 - Evolução da API de Usuários

### Anotações Core (@)
- **@PutMapping("/{id}")**: Mapeia requisições HTTP PUT. Utilizado para atualizações completas. O `/{id}` é uma **URI Template Variable**, permitindo identificar qual recurso será alterado (Ex: `/users/10`).
- **@Valid**: Aciona o motor de validação (Hibernate Validator). Ele verifica as restrições (como `@NotBlank`) no DTO antes do método ser chamado.
- **@RequestBody**: Converte o JSON recebido no corpo da requisição para um objeto Java (neste caso, o `UserRequest`).

### Manipulação de Optional: findById + map
O `userRepository.findById(id)` retorna um `Optional<User>`.
- **.map(user -> { ... })**: Uma abordagem funcional. Se o usuário existir, o código dentro das chaves é executado. Se estiver vazio, o `map` é ignorado.
- **Vantagem**: Evita o uso explícito de `if (user != null)`, tornando o código mais limpo e "Null-Safe".

### Otimização do DELETE
Anteriormente, usávamos o `findById` para deletar. Analisando a performance:
- **Abordagem findById**: Gera um `SELECT *`, carregando todos os campos (nome, senha, email) na memória para depois deletar.
- **Abordagem existsById**: Executa uma consulta otimizada para verificar apenas a existência (1 ou 0).

**Código Otimizado (204 No Content):**
```java
@DeleteMapping("/{id}")
public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
    if (!userRepository.existsById(id)) {
        return ResponseEntity.notFound().build();
    }
    userRepository.deleteById(id);
    return ResponseEntity.noContent().build();
}
```

### Lógica de Unicidade no Update (PUT)
Um dos maiores desafios no método de atualização é validar campos únicos (como o e-mail) sem impedir que o usuário atualize outros dados.

**O Problema:** Se verificarmos apenas se o e-mail existe no banco, o sistema impedirá o usuário de mudar o próprio nome, pois o banco encontrará o e-mail atual dele e retornará um erro de duplicidade.

**A Solução:**
```java
if(!user.getEmail().equals(request.email()) && userRepository.existsByEmail(request.email())){
    return ResponseEntity.status(HttpStatus.CONFLICT)
            .body("Email already exists in another account");
}
```

# 09-05-2026 - Evolução da API de Usuários
## 🏗️ Evolução Arquitetural: Camada de Serviço e Tratamento Global

Nesta etapa, o projeto deixou de ser um "Script CRUD" e passou a seguir padrões de mercado (Enterprise Patterns).

### 1. Camada de Serviço (Service Layer)
A lógica de negócio foi movida do `UserController` para o `UserService`.
- **Por que?** O Controller deve ser "burro", servindo apenas como porta de entrada e saída (HTTP). O Service é onde as regras (como a de e-mail único) vivem.
- **Vantagem:** Facilita a reutilização de código e a escrita de testes unitários.

### 2. Tratamento de Exceções Global (`@RestControllerAdvice`)
Implementamos a classe `RestExceptionHandler` para interceptar erros em toda a aplicação.

- **Antes:** Cada método no Controller precisava de um bloco `try-catch` para lidar com erros, gerando código repetitivo (*boilerplate*).
- **Depois:** O Service lança uma `RuntimeException` e o Spring a captura automaticamente, devolvendo o status HTTP correto (`404`, `409`, etc).

### 3. Lógica de Curto-Circuito no Update
Refinamos a validação de e-mail no método PUT:
```java
if (!user.getEmail().equals(request.email()) && userRepository.existsByEmail(request.email())) {
    throw new RuntimeException("Email already exists");
}