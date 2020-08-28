# user-crud
Aplicação para cadastro de usuários (CRUD). Objetivo deste projeto é um CRUD com leitura de arquivos csv.

# Tecnologias

- Java 8
- Spring Boot 2.0.8.RELEASE
- Banco H2 embedded
- Gradle
- Jackson

# Rotina de inserção csv
A rotina irá rodar ao iniciar a aplicação e a cada 30 segundos irá verificar a pasta de resources.

# Inicialização
Adicione os arquivos .csv em `src/main/resources`. 
Na pasta root do projeto executar: `./init-local.sh` ou rode a aplicação `UserApplication.java`.

# Arquitetura
Arquitetura orientada a domínio:
O domain não enxerga fora do dominio.
O incoming são os canais de conexão com a aplicação.
O outcoming não foi utilizado, mas é o canal de conexão externo.
A infrastructure são as configurações da aplicação.

# Outras informações
Tem um pacote de testes que verifica os pacotes para manter dentro da arquitetura
O banco H2 está em memória e será apagado sempre que desligar a aplicação.
Mensagens de erros estão configuradas no message.properties nas resources.

# TODO
Testes integrados com JUnit4 e MockMvc com container do H2.
