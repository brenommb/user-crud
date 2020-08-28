# user-crud
Aplicação para cadastro de usuários (CRUD). Objetivo deste projeto é um CRUD com leitura de arquivos csv.

# Tecnologias

- Java 8
- Spring Boot 2.0.8.RELEASE
- Banco H2 embedded
- Gradle

# Rotina de inserção csv
A rotina irá rodar ao iniciar a aplicação e a cada 30 segundos irá verificar a pasta de resources.

# Inicialização
Adicione os arquivos .csv em `src/main/resources`. 
Na pasta root do projeto executar: `./init-local.sh` ou rode a aplicação `UserApplication.java`.

# Outras informações
O banco H2 está em memória e será apagado sempre que desligar a aplicação.
