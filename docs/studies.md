# 08-05-2026
> ### Anotações (@)
> - @PutMapping("/{id}) 
> > Indica que o método que será escrito responde
> ao HTT PUT (usado para atualização completa de dados).
> >
> > o "/{id}" faz com que a URL (/users) receba um id que será uma espécia de parâmetro para o verbo HTTP correspondente.
> >
> > Nesse caso a URL ficaria: 
> >
> > - /users/ID
> > 
> > Chamamos o "/{id}" de "Templeta Variable"
> >
> - @Valid
> > Ativa a validação do Hibernate Validator.
> > Se o UserRequest tiver regras como @Email ou @NotBlank, o Spring validará os dados primeiro que o UserRequest.
> 
> - @RequestBody
> > Diz ao Spring que os dados recebidos, virão no corpo da requisição (formato JSON)
> 
> ### FindById + map
> - userRepository.findById(id)
> > userRepository.findById(id) retorna um "Optional", ou seja, pode conter um valor ou vazio.
> - .map(user -> {})
> > Após a consulta no banco de dados (userRepository), se o valor retornado for diferente de vazio o .map() será executado, se não, o .orElse() será executado.
> > Caso o .map(user -> {}) seja executado, ele irá atribuir um ResponseEntity (resposta HTTP) ao objeto user.
> > - Código sem o .map():
> > ```java
> > User user = userRepository.findById(id).orElse(null);
> >
> > if (user != null) {
> > // faz toda a lógica de atualizar
> > return ResponseEntity.ok(userRepository.save(user));
> > } else {
> > return ResponseEntity.notFound().build();
> > }