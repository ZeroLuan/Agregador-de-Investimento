# Agregador de Investimentos

## Descrição

Este projeto é uma API REST desenvolvida por mim em Java utilizando o framework Spring Boot, que permite gerenciar e agregar informações de investimentos de forma eficiente e escalável. Com uma arquitetura baseada em MVC, este aplicativo oferece uma interface simples e intuitiva para interação.

## Tecnologias Utilizadas

- **Java 21**: Linguagem de programação moderna e robusta.
- **Spring Boot 3.4.4**: Facilita a criação de aplicações Java com configuração mínima.
- **Spring Data JPA**: Para acesso e manipulação de dados no banco de dados.
- **MySQL**: Banco de dados relacional utilizado para armazenar as informações.
- **Docker**: Para contêinerização do banco de dados, garantindo portabilidade e consistência.
- **Postman**: Usado para testar as APIs de forma prática e rápida.
- **JUnit e Mockito**: Para execução de testes unitários, garantindo a qualidade do código.

## Como Rodar o Projeto

### Pré-requisitos

- **Java JDK 21**: Certifique-se de que o JDK está instalado em sua máquina.
- **Docker**: Para rodar o banco de dados MySQL em um contêiner.

### Passos

1. **Clone o repositório:**

    ```bash
   git clone https://github.com/seuusuario/agregadorinvestimentos.git
   cd agregadorinvestimentos

2. **Suba o banco de dados MySQL com Docker:**

Utilize o docker-compose para iniciar o contêiner do MySQL:

    docker-compose up -d

3. **Execute a aplicação:**

Utilize o Maven para compilar e executar o projeto:

    ./mvnw spring-boot:run

Teste a API:

4. **Abra o Postman e faça requisições para a API, utilizando as rotas definidas na documentação.**

**Contribuições**

Sinta-se à vontade para contribuir com melhorias ou correções! Basta abrir uma issue ou um pull request.

Licença
Este projeto está licenciado sob a [Licença Apache 2.0].

Contato
Para mais informações ou dúvidas, entre em contato pelo e-mail: 20232ireads0017@ifba.edu.br
