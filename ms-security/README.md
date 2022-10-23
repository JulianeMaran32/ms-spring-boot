
<h2>Spring Security</h2>


Uma mesma REST API pode ser acessada por diversos tipos de clientes, como aplicativos, web sites, mobile etc.  

1. Autenticação   
2. Autorização   

### HTTP Basic Authentication  

Sempre que um cliente for fazer uma requisição para um endpoint da API, ele deve passar um _header_ chamado _Authorization_ o seu valor vem com a palavra "Basic " + codificação de acesso.   

Exemplo:  

```
GET /produtos/1  
Authorization: Basic am9hbzoxMjM=   
```

base64("joao:123") = "am9hbzoxMjM="     
base64("usuario:senha")   
base64 é uma codificação e não uma criptografia.  


### OAuth2   

O cliente obtém um token de acesso com o servidor de autorização.  

O cliente solicita autorização informando o usuário e senha e o servidor de autorização retorna o _access token_   

Cliente <-> Servidor de Autorização    

Após obter a autorização o cliente envia a requisição para a API.   
O token possui tempo limitado para acesso.  

Cliente  -> API   


```
GET /produtos/1  
Authorization: Bearer a726d6413e6618f3   
```

Exemplo de cURL:  

```
curl --location --request GET 'localhost:8081/user' \
--header 'Authorization: Basic c2VjdXJpdHlkYjoxMjM0NTY='   
```

Exemplo de Response:  

```json
[
    {
        "id": 1,
        "username": "Juliane",
        "password": "123456"
    },
    {
        "id": 2,
        "username": "Rian",
        "password": "159632"
    },
    {
        "id": 3,
        "username": "Suzi",
        "password": "478521"
    }
]
```


<h3>


<h3>Dependência</h3>  

Adicionar no [pom.xml](pom.xml) a seguinte dependência:  

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-security</artifactId>
</dependency>

<dependency>
    <groupId>org.springframework.security</groupId>
    <artifactId>spring-security-web</artifactId>
    <version>5.7.4</version>
</dependency>

<dependency>
    <groupId>org.springframework.security</groupId>
    <artifactId>spring-security-config</artifactId>
    <version>5.7.4</version>
</dependency>

<dependency>
    <groupId>io.jsonwebtoken</groupId>
    <artifactId>jjwt-api</artifactId>
    <version>0.11.5</version>
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

<h2>Spring Security nova versão</h2>

<hr>  

<br>

<h3>1 - Configurando o HttpSecurity</h3>

```java
@Configuration
public class SecurityConfiguration {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests((authz) -> authz
                .anyRequest().authenticated()
            )
            .httpBasic(withDefaults());
        return http.build();
    }

}
```

<br>

<h3>2 - Configurando o WebSecurity</h3>

```java
@Configuration
public class SecurityConfiguration {

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        
		return (web) -> web.ignoring().antMatchers("/ignore1", "/ignore2");
		
		// obs: Se você estiver configurando WebSecuritypara ignorar solicitações, considere usar   
		// permitAllvia HttpSecurity#authorizeHttpRequests . 
		
    }

}
```

<br>

<h3>3 - Autenticação LDAP</h3>   

<p>Utilizado para criar um servidor LDAP incorporado e um AuthenticationManager que executa a autenticação LDAP</p>

```java
@Configuration
public class SecurityConfiguration {

    @Bean
    public EmbeddedLdapServerContextSourceFactoryBean contextSourceFactoryBean() {
		
        var contextSourceFactoryBean = EmbeddedLdapServerContextSourceFactoryBean.fromEmbeddedLdapServer();
        
		contextSourceFactoryBean.setPort(0);
        
		return contextSourceFactoryBean;
		
    }

    @Bean
    AuthenticationManager ldapAuthenticationManager(BaseLdapPathContextSource contextSource) {
        
		var factory = new LdapBindAuthenticationManagerFactory(contextSource);
        
		factory.setUserDnPatterns("uid={0},ou=people");
        factory.setUserDetailsContextMapper(new PersonContextMapper());
        
		return factory.createAuthenticationManager();
		
    }

}
``` 

<br>

<h3>4 - Autenticação JDBC</h3>

<p>Incorpora o DataSource que é inicializad com o esquema padrão e possui um único usuário. <br>
User.withDefaultPasswordEncoder() -> serve para facilitar a leitura. <br> 
Não se destina à produção e, em vez disso, recomendamos o hash de suas senhas externramente. <br>
Uma maneira de fazer isso é usar a CLI do Spring Boot (ver link do doc Spring Security: Authentication - Password Storage) 
</p>

```java
@Configuration
public class SecurityConfiguration {

	@Bean
    public DataSource dataSource() {
		
        return new EmbeddedDatabaseBuilder()
            .setType(EmbeddedDatabaseType.H2)
            .addScript(JdbcDaoImpl.DEFAULT_USER_SCHEMA_DDL_LOCATION)
            .build();
			
    }

    @Bean
    public UserDetailsManager users(DataSource dataSource) {
        
		UserDetails user = User.withDefaultPasswordEncoder()
            .username("user")
            .password("password")
            .roles("USER")
            .build();
        
		var users = new JdbcUserDetailsManager(dataSource);
        users.createUser(user);
		
        return users;
		
    }

}
```

<br>

<h3>5 - Autenticação em Memória</h3> 

<p>Configura um repositório de usuários na memória com um único usuário. <br>
User.withDefaultPasswordEncoder()para facilitar a leitura. <br> 
Não se destina à produção e, em vez disso, recomendamos o hash de suas senhas externamente. <br>  
Uma maneira de fazer isso é usar a CLI do Spring Boot (ver link do doc Spring Security: Authentication - Password Storage) </p>

```java
@Configuration
public class SecurityConfiguration {

    @Bean
    public InMemoryUserDetailsManager userDetailsService() {
        UserDetails user = User.withDefaultPasswordEncoder()
            .username("user")
            .password("password")
            .roles("USER")
            .build();
        return new InMemoryUserDetailsManager(user);
    }

}
```

<br>

<h3>6 - Gerente de Autenticação Global</h3>

<p> Para criar um AuthenticationManagerque esteja disponível para todo o aplicativo, basta registrá-lo AuthenticationManagercomo um @Bean. Veja o tópico 3 - Autenticação LDAP </p>

<br>

<h3>7 - Gerente de Autenticação Local</h3>

```java
@Configuration
public class SecurityConfiguration {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests((authz) -> authz
                .anyRequest().authenticated()
            )
            .httpBasic(withDefaults())
            .authenticationManager(new CustomAuthenticationManager());
        return http.build();
    }

}
``` 

<br>

<h3>8 - Acessando o  AuthenticationManager Local</h3>  

<p>
O local AuthenticationManagerpode ser acessado em um DSL personalizado. <br> 
Na verdade, é assim que o Spring Security implementa internamente métodos como HttpSecurity.authorizeRequests(). <br>  
O DSL personalizado pode ser aplicado no construtor do SecurityFilterChain
</p>

```java
@Configuration
public class SecurityConfiguration {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        // ...
        http.apply(customDsl());
        return http.build();
    }

}
``` 

<hr>

<h3>Teste via Postman</h3>  

Method: GET  
URL: localhost:8081/ms-security/user 

Method: POST  
URL: localhost:8081/ms-security/user/form 

Corpo da requisição:

```json
{   
    "username": "Juliane",
    "password": "123456789",
    "roles": ["ADMIN"]
}
```


<hr>

<h3>Links</h3>

<br>

[Spring Security Architecture](https://spring.io/guides/topicals/spring-security-architecture/)   
[Spring Security and Angular js](https://spring.io/guides/tutorials/spring-security-and-angular-js/)   
[Spring Security OAuth2 Authorization Server](https://docs.spring.io/spring-authorization-server/docs/current/reference/html/getting-started.html)
[Uploading files](https://spring.io/guides/gs/uploading-files/)

<br>

[Spring Security without the WebSecurityConfigurerAdapter](https://spring.io/blog/2022/02/21/spring-security-without-the-websecurityconfigureradapter)   
[Class HttpSecurity](https://docs.spring.io/spring-security/site/docs/current/api/org/springframework/security/config/annotation/web/builders/HttpSecurity.html#authorizeRequests%25%29)   
[Deprecated: WebSecurityConfiguerAdapter](https://docs.spring.io/spring-security/site/docs/5.7.0-M2/api/org/springframework/security/config/annotation/web/configuration/WebSecurityConfigurerAdapter.html#configure%25org.springframework.security.config.annotation.web.builders.WebSecurity%29)   
[Spring Security: Authentication - Password Storage](https://docs.spring.io/spring-security/reference/features/authentication/password-storage.html#authentication-password-storage-boot-cli)   

[Application properties](https://docs.spring.io/spring-boot/docs/current/reference/html/application-properties.html)   


[Base 64: Decode e Encode](https://www.base64decode.org/)   
