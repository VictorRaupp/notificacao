# 📧 Microsserviço de Notificação

Microsserviço responsável pelo envio de notificações por e-mail relacionadas às tarefas do sistema de agendamento.

Desenvolvido utilizando **Java e Spring Boot**, o serviço disponibiliza uma API REST para receber informações das tarefas e enviar notificações por e-mail utilizando templates HTML.

---

## 🎯 Objetivo

O objetivo deste microsserviço é realizar o envio de notificações relacionadas às tarefas cadastradas pelos usuários da aplicação.

O serviço recebe os dados de uma tarefa através de uma API REST, processa um template HTML utilizando **Thymeleaf** e realiza o envio através de um servidor SMTP.

As informações utilizadas na notificação incluem:

- Nome da tarefa
- Descrição
- Data do evento
- E-mail do usuário
- Status da tarefa

---

## 🚀 Funcionalidades

### 📧 Envio de notificações

- Receber dados de uma tarefa através de uma API REST
- Criar mensagens de e-mail em formato HTML
- Utilizar templates Thymeleaf
- Inserir dinamicamente os dados da tarefa no e-mail
- Enviar notificações através de SMTP
- Tratar exceções relacionadas ao envio de e-mails

### 🔗 Integração entre microsserviços

O serviço foi desenvolvido para receber informações provenientes do microsserviço de gerenciamento de tarefas.

```text
Microsserviço de Tarefas
          │
          │ HTTP
          ▼
┌───────────────────────┐
│    Notificação        │
│       :8082           │
└───────────┬───────────┘
            │
            ▼
       Thymeleaf
            │
            ▼
       SMTP / E-mail
            │
            ▼
          📧
       Usuário
```

---

## 🏗️ Arquitetura

O `notificacao` faz parte de uma arquitetura baseada em microsserviços para um sistema de agendamento de tarefas.

```text
                         ┌───────────────┐
                         │    Cliente    │
                         └───────┬───────┘
                                 │
                                 ▼
                         ┌───────────────┐
                         │      BFF      │
                         │    :8084      │
                         └───────┬───────┘
                                 │
                                 ▼
                    ┌────────────────────────┐
                    │   Agendador de Tarefas │
                    │         :8081          │
                    └───────────┬────────────┘
                                │
                                │ POST /email
                                ▼
                    ┌────────────────────────┐
                    │      Notificação       │
                    │         :8082          │
                    └───────────┬────────────┘
                                │
                                ▼
                         ┌──────────────┐
                         │     SMTP     │
                         │    E-mail    │
                         └──────────────┘
```

---

## 🛠️ Tecnologias utilizadas

- **Java 17**
- **Spring Boot**
- **Spring Web MVC**
- **Spring Mail**
- **Thymeleaf**
- **Lombok**
- **Gradle**
- **JUnit**
- **Docker**
- **GitHub Actions**
- **API REST**
- **SMTP**

---

## 📧 Funcionamento do envio

O processo de envio da notificação funciona da seguinte maneira:

1. O microsserviço recebe uma requisição HTTP através do endpoint `/email`.
2. Os dados da tarefa são recebidos através do `TarefaDTO`.
3. O `EmailService` cria a mensagem de e-mail.
4. O Thymeleaf processa o template HTML.
5. Os dados da tarefa são inseridos dinamicamente no template.
6. A mensagem é enviada através do servidor SMTP.
7. O usuário recebe a notificação por e-mail.

---

## 📡 Endpoint

### Enviar notificação por e-mail

```text
POST /email
```

Responsável por receber os dados da tarefa e realizar o envio da notificação.

### Exemplo de requisição

```json
{
  "id": 1,
  "nomeTarefa": "Reunião com cliente",
  "descricao": "Reunião para apresentação do projeto.",
  "dataCriacao": "07-09-2026 10:00:00",
  "dataEvento": "08-09-2026 14:00:00",
  "emailUsuario": "usuario@email.com",
  "dataAlteracao": "07-09-2026 10:30:00",
  "statusTarefaEnum": "PENDENTE"
}
```

### Resposta

Em caso de sucesso:

```text
HTTP 200 OK
```

---

## 🎨 Template do e-mail

O projeto utiliza **Thymeleaf** para gerar o conteúdo HTML das notificações.

O template utilizado está localizado em:

```text
src/main/resources/templates/notificacao-tarefa.html
```

O template utiliza informações da tarefa para montar o conteúdo da mensagem, como:

- Nome da tarefa
- Data do evento
- Descrição

---

## 📂 Estrutura do projeto

```text
src
├── main
│   ├── java
│   │   └── com.example.notificacao
│   │       ├── Application.java
│   │       │
│   │       ├── business
│   │       │   ├── EmailService.java
│   │       │   └── dto
│   │       │       └── TarefaDTO.java
│   │       │
│   │       ├── controller
│   │       │   └── EmailController.java
│   │       │
│   │       └── infrastructure
│   │           ├── enums
│   │           │   └── StatusTarefaEnum.java
│   │           └── exceptions
│   │               └── EmailException.java
│   │
│   └── resources
│       ├── application.yaml
│       └── templates
│           └── notificacao-tarefa.html
│
└── test
    └── java
        └── com.example.notificacao
            └── ApplicationTests.java
```

---

## 🧩 Principais componentes

### EmailController

Responsável por disponibilizar o endpoint REST para recebimento das informações da tarefa.

```text
POST /email
```

---

### EmailService

Responsável pela lógica de envio dos e-mails.

Entre suas responsabilidades estão:

- Criar a mensagem
- Configurar remetente e destinatário
- Processar o template Thymeleaf
- Inserir os dados da tarefa
- Enviar o e-mail através do `JavaMailSender`
- Tratar erros relacionados ao envio

---

### TarefaDTO

Objeto utilizado para transportar os dados da tarefa entre os serviços.

Principais informações:

```text
id
nomeTarefa
descricao
dataCriacao
dataEvento
emailUsuario
dataAlteracao
statusTarefaEnum
```

---

### StatusTarefaEnum

Representa os possíveis estados de uma tarefa:

```text
PENDENTE
NOTIFICADO
CANCELADO
```

---

### EmailException

Exceção personalizada utilizada para tratar erros relacionados ao processo de envio dos e-mails.

---

## ⚙️ Configuração

As configurações relacionadas ao servidor SMTP devem ser armazenadas através de **variáveis de ambiente**.

Exemplo:

```text
MAIL_HOST=smtp.gmail.com
MAIL_PORT=587
MAIL_USERNAME=seu_email@gmail.com
MAIL_PASSWORD=sua_senha_de_aplicativo
MAIL_FROM=seu_email@gmail.com
MAIL_SENDER_NAME=VitroX
SERVER_PORT=8082
```

> ⚠️ Nunca publique senhas, tokens ou outras credenciais no GitHub.

Para execução local, configure as variáveis de ambiente na IDE ou no ambiente utilizado para executar a aplicação.

---

## 🐳 Docker

O projeto possui um `Dockerfile` para execução da aplicação utilizando containers.

Primeiro, gere o projeto:

```powershell
.\gradlew build
```

Depois, construa a imagem:

```powershell
docker build -t notificacao .
```

Execute o container:

```powershell
docker run -p 8082:8082 notificacao
```

As variáveis de ambiente necessárias para o envio dos e-mails também devem ser configuradas no container.

---

## 🧪 Testes

O projeto possui testes automatizados utilizando **JUnit** e **Spring Boot Test**.

Para executar os testes:

```powershell
.\gradlew test
```

Para executar o build completo:

```powershell
.\gradlew build
```

---

## 🔄 Integração com o projeto

O microsserviço de Notificação faz parte do projeto:

**Agendador de Tarefas — Arquitetura de Microsserviços**

A arquitetura é dividida em diferentes serviços, cada um responsável por uma parte específica do sistema.

```text
┌─────────────────────┐
│       Cliente       │
└──────────┬──────────┘
           │
           ▼
┌─────────────────────┐
│        BFF          │
│       :8084         │
└──────────┬──────────┘
           │
           ▼
┌─────────────────────┐
│ Agendador de Tarefas│
│       :8081         │
└──────────┬──────────┘
           │
           │ POST /email
           ▼
┌─────────────────────┐
│     Notificação     │
│       :8082         │
└──────────┬──────────┘
           │
           ▼
       SMTP / E-mail
```

---

## 🎯 Objetivos de aprendizado

Este projeto foi desenvolvido com o objetivo de praticar e aplicar conceitos importantes de desenvolvimento backend, incluindo:

- Desenvolvimento de APIs REST
- Arquitetura de microsserviços
- Spring Boot
- Injeção de dependências
- DTOs
- Envio de e-mails
- Integração com SMTP
- Templates HTML com Thymeleaf
- Tratamento de exceções
- Testes automatizados
- Docker
- GitHub Actions
- Configuração através de variáveis de ambiente

---

## 🚧 Status do projeto

🚧 **Em desenvolvimento**

O projeto pode receber novas melhorias e funcionalidades durante a evolução da arquitetura de microsserviços.

---

## 👨‍💻 Autor

**Victor Raupp**

Estudante de Engenharia de Software e desenvolvedor em formação com foco em:

- Java
- Spring Boot
- APIs REST
- Banco de dados
- Microsserviços
- Desenvolvimento Backend
