## Aplicando a autenticação com JWT e Spring Security

Para entendimento do JWT e tambem do Spring security, podemos dar uma olhada nas documentações oficiais de cada ferramenta.

- JWT: https://jwt.io/introduction
- Spring Security: https://spring.io/projects/spring-security

Como o proejto é dado com Migrations, primeiro foi implementado as migrations contendo as tabelas de `users`, `user_permissions` e `permissions`. Senda estas geradas automaticamente pelo Spring.

Adicionamos as dependencias a serem utilizadas, que são:
```
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-security</artifactId>
</dependency>
```
```		
<dependency>
    <groupId>com.auth0</groupId>
    <artifactId>java-jwt</artifactId>
    <version>${java-jwt.version}</version>
</dependency>
```

E criamos as classes `Permission.java` e `User.java`, sendo que a primeira é somente os tipos de permissões, não sendo necessário a implementação de um controller para a mesma.

Em seguida criamos os serviços e repositorios de `User`, e dentro desta há dois links interessantes a serem analizados que falam um pouco mais sobre os tipos de injeção de dependência.
(https://reflectoring.io/constructor-injection/  e  https://spring.io/blog/2007/07/11/setter-injection-versus-constructor-injection-and-the-use-of-required).

