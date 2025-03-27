# Meetime - Teste Técnico

## Tecnologias Utilizadas e Justificativa

### 📌 **Spring Boot**
- **spring-boot-starter-web**: Facilita a criação de aplicações RESTful, fornecendo suporte a servidores web embutidos.
- **spring-boot-starter-validation**: Fornece suporte à validação de dados via anotações como `@NotNull`, `@Size`, etc.
- **spring-boot-starter-test**: Biblioteca essencial para realizar testes unitários e de integração com JUnit e Mockito.
- **spring-boot-starter-aop**: Permite a implementação de Aspect-Oriented Programming (AOP) para manipulação avançada de lógica transacional, logging, etc.

### 🔐 **Spring Security**
- **spring-security-test**: Fornece ferramentas para testes de segurança em endpoints protegidos.

### 🔗 **Spring Cloud OpenFeign**
- **spring-cloud-starter-openfeign**: Simplifica a comunicação com APIs externas, permitindo a criação de clients declarativos baseados em interfaces.

### ⚡ **Resilience4j**
- **resilience4j-spring-boot3**: Implementa padrões de resiliência como o Rate Limiting.

### 🏗 **Lombok**
- **lombok**: Reduz a verbosidade do código ao eliminar a necessidade de escrever getters, setters e construtores manualmente.

### 📜 **Log4j**
- **log4j-api**: Biblioteca robusta para logging, proporcionando alto desempenho e flexibilidade na configuração dos logs.

### 🔄 **Gerenciamento de Dependências**
- **spring-cloud-dependencies**: Centraliza a versão das dependências do Spring Cloud, garantindo compatibilidade entre os módulos.

## Como Executar
1. Certifique-se de ter o **Java 17** instalado.
2. Clone este repositório.
3. Garanta que seu JAVA_HOME esteja com um sdk do java 17.
4. Execute o comando:   
```sh
   mvn spring-boot:run
   ```
5.  A aplicação estará disponível em `http://localhost:8080`.
