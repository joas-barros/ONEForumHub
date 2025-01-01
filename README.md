# ForumHub

ForumHub é uma API REST criada em Java que simula o funcionamento de um fórum online. Este projeto foi desenvolvido como parte da formação backend com Java Spring da Oracle Next Education em parceira com a alura.

## Tecnologias Utilizadas

- **Java 17**
- **Spring Boot 3**
- **Maven**
- **Flyway**
- **PostgreSQL**
- **Hibernate**
- **Spring Data JPA**
- **Spring Security**
- **JWT (JSON Web Token)**
- **Lombok**
- **Swagger**

## Funcionalidades

### Gerenciamento de Usuários
- **Cadastro de Usuários:** Permite o registro de novos usuários no sistema.
- **Login de Usuários:** Autenticação baseada em JWT, permitindo que os usuários façam login com segurança.
- **Listagem de Usuários:** Exibe uma lista de todos os usuários cadastrados.

### Gerenciamento de Tópicos
- **CRUD de Tópicos:** Os usuários podem criar, listar, editar e excluir tópicos de discussão.
- **Respostas a Tópicos:** Permite que usuários respondam a tópicos com suas dúvidas e soluções.

### Segurança
- **Autenticação JWT:** Implementa um sistema de autenticação seguro e eficiente com base em tokens JWT.

### Documentação
- **Swagger:** Disponibiliza uma documentação interativa da API para facilitar o acesso e os testes das rotas.

## Configuração do Ambiente

### Requisitos
- Java 17
- Maven
- PostgreSQL

### Passos para Configuração
1. Clone o repositório:
   ```bash
   git clone https://github.com/joas-barros/ONEForumHub.git
   cd ONEForumHub
   ```
2. Configure o banco de dados PostgreSQL:
   - Crie um banco de dados chamado `forumhub`.
   - Atualize as configurações no arquivo `application.properties` ou `application.yml`.

3. Rode as migrações do Flyway:
   ```bash
   mvn flyway:migrate
   ```

4. Compile e execute a aplicação:
   ```bash
   mvn spring-boot:run
   ```

5. Acesse a documentação da API via Swagger:
   - URL: `http://localhost:8080/swagger-ui.html`

## Estrutura do Projeto

```plaintext
src/
├── main/
│   ├── java/
│   │   └── br.alura.ForumHub/
│   │       ├── controller/
│   │       ├── dto/
│   │       ├── infra/
│   │       ├── model/
│   │       ├── repository/
│   │       └── service/
│   └── resources/
│       ├── application.yml
│       └── db/migration/
├── test/
└── pom.xml
```
## Principais Endpoints

Para realizar todas as funcionalidades presentes na API basta acessar os seguintes endpoints:

- **POST /login:**
    - Gera um Token JWT para ser enviado junto com as demais requisições da API. O Token JWT se responsabiliza por autenticar as requisições.

- **GET /usuarios:**
    - Lista todos os usuários cadastrados no fórum.

- **PUT /usuarios:**
    - Atualiza os dados de nome e senha do usuário logado.

- **POST /usuarios:**
    - Realiza o cadastro de um novo usuário no fórum.

- **DELETE /usuarios:**
    - Torna o usuário inativo no banco de dados (delete lógico).

- **GET /usuarios/topicos:**
    - Lista todos os tópicos postados pelo usuário logado ordenados pela date de publicação do tópico em páginas de 10 itens.

- **GET /topicos/{id}:**
    - Detalha um tópico específico pelo seu ID.

- **PUT /topicos/{id}:**
    - Atualizar/editar as informações publicadas no tópico selecionando o mesmo pelo ID.

- **DELETE /topicos/{id}:**
    - Remove um tópico específico do fórum selecionando o mesmo pelo ID se esse tópico foi publicado pelo mesmo usuário logado.

- **GET /topicos/:**
    - Lista todos os tópicos postados no fórum ordenados pela date de publicação do tópico em páginas de 10 itens.

- **POST /topicos/:**
    - Realiza a publicação de um tópico no fórum.

- **PUT /respostas/{id}:**
    - Atualiza uma resposta específica por ID.

- **POST /respostas/{id}:**
    - Realiza a publicação de uma resposta em um tópico que é reconhecido pelo seu ID. Sendo que cada vez que uma resposta é postada, o status do tópico é atualizado"

- **DELETE /respostas/{id}:**
    - Deleta uma resposta específica por ID. Caso a última resposta do tópico seja deletada, o status do tópico é atualizado para `NAO_RESPONDIDO`.

As especificações sobre os dados que são passados e recebidos por cada endpoint estão presentes na documentação do swagger.

## Contribuição
Contribuições são bem-vindas! Siga os passos abaixo para colaborar:
1. Faça um fork do repositório.
2. Crie uma branch para sua funcionalidade/bugfix: `git checkout -b minha-feature`.
3. Envie suas modificações: `git push origin minha-feature`.
4. Abra um pull request.

## Licença
Este projeto está licenciado sob a Licença MIT. Veja o arquivo `LICENSE` para mais detalhes.

<div>
    <p>
        <img src="Badge-Spring.png" height="300" tittle="site"> 
    </p>
</div>