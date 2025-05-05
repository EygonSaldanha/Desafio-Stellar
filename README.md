# Desafio Stellar

Este projeto é composto por três componentes principais:

- **front-desafio**: Aplicação frontend desenvolvida com Angular.
- **back-desafio**: Backend construído com Kotlin e Quarkus.
- **database**: Configuração do banco de dados utilizando Docker Compose.

## Pré-requisitos

- [Docker](https://www.docker.com/)
- [Node.js](https://nodejs.org/) (versão 14 ou superior)
- [Angular CLI](https://angular.io/cli)
- [JDK 17+](https://adoptium.net/)

## Instruções de Execução

### 1. Iniciar o Banco de Dados

Na raiz do projeto, execute:

```bash
docker compose up --build
```

Este comando irá construir e iniciar os containers definidos no arquivo `docker-compose.yml`, incluindo o banco de dados necessário para o backend.

#### Credenciais do Banco de Dados

| Parâmetro        | Valor        |
|------------------|--------------|
| Host             | localhost    |
| Porta            | 5432         |
| Nome do Banco    | master       |
| Usuário          | stellar      |
| Senha            | stellar      |

> 💡 O banco de dados PostgreSQL será iniciado com as credenciais acima. Certifique-se de que a porta 5432 esteja disponível em sua máquina.

### 2. Executar o Backend (Quarkus + Kotlin)

Navegue até a pasta `back-desafio`:

```bash
cd back-desafio
```

Em seguida, execute o backend em modo de desenvolvimento:

```bash
./gradlew quarkusDev
```

> 💡 Se preferir, você pode utilizar o Quarkus CLI ou executar diretamente o JAR gerado:

```bash
./gradlew build
java -jar build/quarkus-app/quarkus-run.jar
```

> 📌 Certifique-se de que o JDK 17 ou superior esteja instalado e configurado corretamente.

### 3. Executar o Frontend (Angular)

Navegue até a pasta `front-desafio`:

```bash
cd front-desafio
```

Instale as dependências do projeto:

```bash
npm install
```

Inicie a aplicação Angular:

```bash
ng serve
```

A aplicação estará disponível em `http://localhost:4200`.

## Estrutura do Projeto

```
Desafio-Stellar/
├── back-desafio/      # Backend com Kotlin e Quarkus
├── front-desafio/     # Frontend com Angular
├── database/          # Configuração do banco de dados
├── docker-compose.yml # Arquivo de configuração do Docker Compose
└── README.md          # Este arquivo
```

## Contato

Para dúvidas ou sugestões, entre em contato com [Eygon Saldanha](https://github.com/EygonSaldanha)
