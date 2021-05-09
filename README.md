# 💻 Back end Redação Conectada

A api foi desenvolvida para a disciplina Engenharia de Software da Universidade Federal de Campina Grande (UFCG).

A implantação da api foi feita utilizando o heroku.

**Atenção**: Você precisa ter o java jdk 11 instalado na sua máquina bem como o `PATH_VARIABLE` do java configurado e
uma IDE de sua preferência.

## 🔌 Configurações para inicialização

- **O back end está setado em modo de TESTE, sua aplicação funcionará localmente, e será utilizado o banco de dados em
  memória (H2).**
- **Caso esteja utilizando a IDE Intelij, é necessário ter o plugin "Lombok" instalado.**
- **Caso esteja utilizando a IDE Eclipse, é necessário realizar uma configuração adicional para o lombok, mais
  informações [aqui](https://projectlombok.org/setup/eclipse).**

Execute o comando: `mvn install` para instalar as dependências do maven.

Após baixar as dependências, execute a aplicação e a api estará rodando em http://localhost:8080.

Acesso ao banco de dados: `http://localhost:8080/h2-console`.

## 🧾 Documentação

A documentação da api foi gerada através do Swagger e pode ser acessada em `http://localhost:8080/swagger-ui.html#`

## 📌 Endpoints

Os seguintes endpoints estão configurados:

**ATENÇÃO:**

- `/h2-console`
- `/swagger-ui.html#`
- [POST] `/users/**`
- [GET]`/essays/**`
- [POST] `/oauth/token`

Estão **liberadas** para acesso **sem autenticação**. Caso queira acessar as **demais** rotas é necessário **realizar
autenticação** no sistema.

### Auth

- `/oauth/token` - POST - Realiza o login do usuário na aplicação.

**OBS¹:** No momento de fazer a requisição, configure a "Authoriztion" como `Basic Auth` e repasse
o `username=redacaoconectada` e a `password=redacaoconectada123`.

**OBS²:** O corpo da requisição deve ser do tipo `x-www-form-urlencoded`, crie os campos "username",
"password" e "grant_type".

- Em "username", coloque seu valor como `maria@gmail.com`.
- Em "password", coloque seu valor como `123456`.
- Em "grant_type", coloque seu valor como `password`. (Isso indica o método de autenticação do sistema).

**OBS³:** O usuário "maria" é professora da aplicação, dessa forma tem acesso a quase todas as rotas. Caso queira ver
outros usuário veja o arquivo em `src/main/resources/data.sql`.

### Users

**APENAS USUÁRIOS AUTENTICADOS PODEM ACESSAR AS ROTAS**

- `/users` - GET - Exibe todos os usuários paginados cadastrados na API em ordem alfabética (nome do usuário).
- `/users/{id}` - GET - Exibe as informações de um usuário específico bem como quais são suas
  "roles".
- `/users/essays` - GET - Exibe as redações de um usuário específico através do seu nome do usuário.
- `/users` - POST - Cria um usuário.
- `/users/update` - PUT - Atualiza as informações de um usuário específico.
- `/users/comment/{id}` - DELETE - Remove o comentário de um usuário específico.
- `/users/comment` - POST - Cria um comentário para um usuário específico.
- `/users/changeRole/{id}` - POST - Solicita a mudança de um usuário aluno para professor.

**Exemplo de dados para criar um Usuário (JSON)**

```json
{
  "cpf": "087.417.930-05",
  "name": "any_name",
  "email": "any_email@gmail.com",
  "password": "any_password",
  "birthdate": "1999-07-13T03:00:00Z",
  "graduation": "any_graduation",
  "schoolName": "any_school_name",
  "state": "any_state",
  "city": "any_city"
}
```

**Exemplo de dados para atualizar um Usuário (JSON)**

```json
{
  "newUserName": "any_name",
  "image": "any_image_link"
}
```

**Exemplo de dados para criar um Comentário para um Usuário (JSON)**

```json
{
  "body": "any_comment",
  "idEssay": 1,
  "idUser": 1,
  "upVote": 0
}
```

**Exemplo de dados para Solicitar mudança de um Usuário aluno para professor (JSON)**

```json
{
  "idUser": 1,
  "proof_img": "any_proof_img",
  "school_name_as_teacher": "any_school_name",
  "school_registration": "any_school_registration"
}
```

### Admins

**APENAS ADMINISTRADORES PODEM ACESSAR AS ROTAS**

- `/admin` - GET - Exibe todos os usuários paginados cadastrados na API em ordem alfabética
  (nome do usuário).
- `/admin/{id}` - GET - Exibe as informações de um usuário específico.
- `/admin/users/requests` - GET - Existe todas as solicitações feitas pelos usuários para mudança de função aluno para
  professor.
- `/admin/approve/{id}` - PUT - Realiza a mudança de um usuário aluno para usuário professor.
- `/admin/dennied/{id}` - PUT - Recusa a solicitação de mudança de um usuário aluno para usuário professor.

### Essays

- `/essays` - GET - Exibe todos as redações paginadas cadastradas na API em ordem por id.
- `/essays/{id}` - GET - Exibe as informações de uma redação específica.
- `/essays/users/{id}` - GET - Exibe as redações de um usuário específico.
- `/essays/{id}/comments` - GET - Exibe os comentários de uma redação específica.
- `/essays` - POST - Cria uma redação. **AUTENTICAÇÃO NECESSÁRIA**
- `/essays/{id}/upvote` - PUT - Realiza upvote de uma redação específica **AUTENTICAÇÃO NECESSÁRIA**
- `/essays/{id}/downvote` - PUT - Remove um upvote de uma redação específica **AUTENTICAÇÃO NECESSÁRIA**
- `/essays/{id}` - PUT - Atualiza uma redação específica. **AUTENTICAÇÃO NECESSÁRIA**
- `/essays/{id}` - DELETE - Remove uma redação específica. **AUTENTICAÇÃO NECESSÁRIA**

**Exemplo de dados para criar uma Redação (JSON)**

```json
{
  "body": "any_body_content",
  "idUser": 1,
  "isAnon": false,
  "title": "Any_title_name"
}
```

**Exemplo de dados para atualizar uma Redação (JSON)**

```json
{
  "body": "any_update_body_content",
  "idUser": 1,
  "isAnon": true,
  "title": "any_update_title_name"
}
```

### Corrections

**APENAS USUÁRIOS AUTENTICADOS PODEM ACESSAR AS ROTAS**

- `/corrections` - GET - Exibe todos as correções paginadas cadastradas na API em ordem por id.
- `/corrections/{id}` - GET - Exibe as informações de uma correção específica.
- `/essays` - POST - Cria uma correção para uma redação.

**Exemplo de dados para criar uma correção (JSON)**

```json
{
  "competences": {
    "competence1Comments": "any_comments_about_competence_1",
    "competence1Grade": 100,
    "competence2Comments": "any_comments_about_competence_2",
    "competence2Grade": 200,
    "competence3Comments": "any_comments_about_competence_3",
    "competence3Grade": 200,
    "competence4Comments": "any_comments_about_competence_4",
    "competence4Grade": 150,
    "competence5Comments": "any_comments_about_competence_5",
    "competence5Grade": 100,
    "gradesSum": 750
  },
  "createdDate": "2021-05-09T13:40:36.109Z",
  "idEssay": 1,
  "idTeacherUser": 2
}
```

## 🛠️ Ferramentas

1. Java
2. Springboot
3. Spring Data JPA
4. Spring Validation
5. Spring Cloud
6. Spring Security
7. Spring Security Oauth2
8. Swagger (Documentação)
9. H2 database
10. postgresql
11. Lombok
12. Devtools

## 💾 Dados para povoar a api

Na pasta `src/main/resources` existe um arquivo **data.sql** com alguns dados já adicionados, para inserir novos dados,
basta incluí-los no arquivo. 