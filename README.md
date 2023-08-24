# Microsserviço de Administração do Catálogo de Vídeos - Parte IV

O microsserviço de Administração do Catálogo de Vídeos é a aplicação _backend_ responsável por gerenciar os vídeos, incluindo as categorias, os gêneros e os membros do elenco.

Dentro da dinâmica do sistema:

1. A aplicação _Backend Admin_ do Catálogo de Vídeos vai falar com o banco de dados, salvar os dados dos vídeos, dos gêneros, das categorias e membros do elenco;
2. A aplicação _Frontend Admin_ do Catálogo de Vídeos vai falar com a _API_ do _backend_ para realizar as ações de cadastro;
3. A aplicação _Encoder_ de Vídeos vai acessar os vídeos que forem enviados via _Backend_ de Administração de Vídeos, fazer o _encoding_ e salvar os dados em um _bucket_ no _Google Cloud Storage_. Na seqüência, uma notificação é enviada via _RabbitMQ_ para a aplicação Admin do Catálogo de Vídeos atualizar o _status_ de processamento dos vídeos em sua base de dados.

Esta terceira parte contempla o desenvolvimento para o Agregado (segundo _Domain-Driven Design_ (_DDD_)) de Vídeo.

Com relação ao _software design_, a aplicação segue uma arquitetura _middle-out_, baseada nos modelos de _Clean Architecture_ e _DDD_. O agregado de Vídeo inclui o modelo de _Event-Driven Architecture_ (_EDA_) à aplicação ao implementar _domain events_ e comunicar-se de forma assíncrona com a aplicação _Encoder_.

Estão envolvidas, nesta aplicação, tecnologias de:

- Backend

  - Java (JDK 17)
  - Spring Boot 3
  - Gradle (gerenciador de dependências)
  - Spring Data & JPA
  - MySQL
  - Flyway (gerenciamento do banco de dados)
  - RabbitMQ (sistema de mensageria)
  - H2 (testes integrados de persistência)
  - JUnit Jupiter (testes unitários)
  - Mockito JUnit Jupiter (testes integrados)
  - Testcontainers MySQL (testes end-to-end)
  - Springdoc-openapi (documentação da API)

A partir de um _request_ via _Postman_ para a criação de um novo vídeo, a dinâmica da aplicação consiste em:

1. Persistir os dados do vídeo na base de dados;
2. Fazer o _upload_ do arquivo de vídeo para o _Google Cloud Storage_;
3. Enviar uma notificação via _RabbitMQ_ para a aplicação _Encoder_ iniciar o processo de _encoding_ do arquivo _mp4_. Nos _logs_ dessa aplicação, é possível acompanhar as mudanças de _status_ no processamento: _DOWNLOADING, FRAGMENTING, ENCODING, UPLOADING, FINISHING, COMPLETED_. Por fim, a aplicação _Encoder_ notifica via _RabbitMQ_ a aplicação de Administração do Catálogo de Vídeos com os dados do processamento;
4. A aplicação Admin do Catálogo de Vídeos escuta uma fila que recebe os dados de resultado do processo de _encoding_ da aplicação _Encoder_ e atualiza a sua base de dados com o _status_ do processamento.

A própria aplicação cria de maneira automática a infraestrutura de filas, _exchange_, _routing keys_ e _bindings_ do _RabbitMQ_ a partir do _RabbitMQ Admin_ que o _Spring AMQP_ provê. No entanto, é necessário criar manualmente a _exchange dlx_, do tipo _fanout_, como uma _dead-letter exchange_ e associar uma fila a essa _exchange_ (por exemplo, _videos.failed_).
