# OdontoSys - Sistema de Gerenciamento Odontológico

## Descrição

OdontoSys é uma aplicação desenvolvida para gerenciar dentistas, pacientes, consultas e usuários em uma clínica odontológica. O sistema inclui funcionalidades como cadastro de dentistas e pacientes, agendamento de consultas, gestão de pagamentos e controle de usuários (administradores, recepcionistas, dentistas).

## Funcionalidades

- **Gerenciamento de Dentistas**:

  - Cadastro, atualização e exclusão de dentistas.
  - Dados como nome, CRO, especialidade e endereço.

- **Gestão de Consultas**:

  - Agendamento, reagendamento e cancelamento de consultas.
  - Consultas associadas a pacientes e dentistas.

- **Controle de Usuários**:

  - Cadastro, atualização e exclusão de usuários.
  - Perfis de acesso: administrador, recepcionista e dentista.

- **Gestão de Pagamentos**:
  - Registro de pagamentos realizados.
  - Controle de pendências e quitação.

## Tecnologias Utilizadas

- **Java 17**
- **Spring Boot**: Framework principal da aplicação.
- **Spring Data JPA**: Para interação com o banco de dados.
- **Hibernate**: ORM para persistência de dados.
- **Lombok**: Para redução de boilerplate no código.
- **Jakarta Persistence API**: Gerenciamento das entidades.
- **H2 Database**: Banco de dados em memória (para desenvolvimento).
- **Postman**: Para testar endpoints da API.

## Endpoints Principais

### Usuários

- **POST `/usuarios`**: Cadastrar um novo usuário.

  ```json
  {
    "nome": "João Silva",
    "email": "joao@email.com",
    "senha": "12345678",
    "perfil": "administrador"
  }
  ```

  GET /usuarios: Listar todos os usuários.
  GET /usuarios/{id}: Obter usuário por ID.
  Dentistas
  POST /dentistas: Cadastrar um novo dentista.

  ```json
  {
  "nome": "Dra. Ana Costa",
  "cro": "567890-SP",
  "especialidade": "ORTODONTIA",
  "endereco": {
    "logradouro": "Rua das Palmeiras",
    "bairro": "Centro",
    "cep": "12345-678",
    "cidade": "Rio de Janeiro",
    "uf": "RJ",
    "complemento": "Apto 202",
    "numero": "100"
  }
  ```

  }
  GET /dentistas: Listar todos os dentistas.
  GET /dentistas/{id}: Obter dentista por ID.
  Consultas
  POST /consultas: Agendar uma nova consulta.
  PUT /consultas/{id}: Reagendar consulta.
  DELETE /consultas/{id}: Cancelar consulta.
  Pagamentos
  POST /pagamentos: Registrar pagamento.
  GET /pagamentos/{id}: Obter detalhes de um pagamento.
  Configuração do Ambiente
  Certifique-se de ter o Java 17 instalado.
  Clone o repositório:
  bash
  git clone https://github.com/seu-usuario/odonto-app.git
  Navegue até a pasta do projeto:
  bash
  cd odonto-app
  Execute a aplicação:
  bash
  ./mvnw spring-boot:run
  Acesse a aplicação no navegador:
  http://localhost:8080
  Testando a API
  Use o Postman ou outra ferramenta para enviar requisições HTTP para os endpoints listados.

  ```

  ```
