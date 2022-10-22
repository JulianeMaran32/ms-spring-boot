
<h2>Spring Security</h2>

<h3>Dependência</h3>  

Adicionar no [pom.xml](pom.xml) a seguinte dependência:  

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-security</artifactId>
</dependency>
```

<br>

<h3>Autenticação x Autorização</h3>
 

- Autenticação: "quem é você?"   

- Autorização ou Controle de Acesso: "o que você tem permissão para fazer?"  


<br>

<h4>Autenticação</h4>

Para autenticação do usuário, o Spring disponbiliza uma interface `AuthenticationManager`, que possui um único método, no qual possui 3 funções:  


**Sucesso**: Retorna um `Authentication` se possui uma entrada válida.    
**Erro**: lança uma exceção `AuthenticationException`, quando for uma entrada inválida.  

E pode retornar `null`, quando não souber identificar a informação de entrada.   

`AuthenticationException`: trata-se de um exceção do tempo de execução, que poderá retornar um status code 401.  




<br>

<h4>Autorização ou Controle de Acesso</h4>


<br>

<h3>Links</h3>

[Spring Security Architecture](https://spring.io/guides/topicals/spring-security-architecture/)   
[Spring Security and Angular js](https://spring.io/guides/tutorials/spring-security-and-angular-js/)   
[Spring Security OAuth2 Authorization Server](https://docs.spring.io/spring-authorization-server/docs/current/reference/html/getting-started.html)
[Uploading files](https://spring.io/guides/gs/uploading-files/)

