<h1 align="center">Microservice with Springboot</h1>  

<p>
Os projetos desenvolvidos tanto nesse como em outros repositórios, tenho utilizado como base algumas dependências e plugins. <br>
Além das suas anotações fornecidas que servem para deixar o código mais enxuto e compreensivo. <br>
A baixo explico um pouco sobre cada um deles e ao final possui os links e referências. 
</p>

<hr>

<h2>1. Dependências</h2>

<p> Para que serve cada uma das dependências utilizadas. </p>

<h3>1.1 Lombok</h3>

```xml
    <dependency>
        <groupId>org.projectlombok</groupId>
        <artifactId>lombok</artifactId>
        <optional>true</optional>
    </dependency>

    <build>
		<plugins>
			<plugin>
				<groupId>org.springframework.boot</groupId>
				<artifactId>spring-boot-maven-plugin</artifactId>
				<configuration>
					<excludes>
						<exclude>
							<groupId>org.projectlombok</groupId>
							<artifactId>lombok</artifactId>
						</exclude>
					</excludes>
				</configuration>
			</plugin>
		</plugins>
	</build>
```

<h3>1.2 DevTools</h3>

```xml
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-devtools</artifactId>
        <scope>runtime</scope>
        <optional>true</optional>
    </dependency>
```

<hr>

<h2>2. Anotações</h2>

<p> Para que serve cada uma das anotações utilizadas. </p>

<hr> 

<h3>2.1 Anotações Spring</h3>  

- Repositório ou Repository ou DAO (_Data Access Object_): fornece os métodos para as operações CRUD.   
- Controller é responsável por expor cada URI que estará disponível na API   

`@RestController`: contém as anotações `@Controller` e `@ResponseBody`   
`@Controller`: representa uma classe com endpoints, ou seja, as URIs que serão expostas pela API   
`@ResponseBody`: indica que o valor retornado pelos métodos devem ser vinculados ao corpo da resposta  
`@RequestMapping`: indica que a URL da API desse controller começa com "/", o que significa que a URL para para acessar será: "http://{host}:{port}/" 

Service: contém a lógica de negócio  
A boa prática é acessar o service e não o repositório diretamente   

Banco de Dados SQL: `findAll` é o mesmo que `SELECT * FROM nome_tabela;`    

`@ResponseEntity`: Serve para situações em que precisamos ter mais controle sobre a resposta HTTP em um endpoint, ou seja, serve para manipular os dados HTTP da resposta   

<hr> 

<h3>2.2 Anotações Lombok</h3>

- `@AllArgsConstructor`: cria automaticamente um construtor com todas os atributos da classe como argumento.     
- `@NoArgsConstructor`: cria automaticamente um construtor vazio (sem argumentos).       
- `@Data`: cria automaticamente os métodos toString, equals, hashCode, getters e setters.   

<hr> 

<h3>2.3 Anotações JPA</h3>

- `@Entity`: a classe será automaticamente mapeada à tabela com o mesmo nome (classe `Contact` e tabela `Contact`, por exemplo)      
- `@Table`: serve para definir o nome da tabela       
- `@Column`: serve para definir o nome da coluna    
- `@Id`: serve para identificar a chave primária     
- `@GeneratedValue`: identifica como a coluna id será gerada      

<hr> 

<h3>2.4 Exemplos</h3>

```java
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Contact {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="contact_name")
    private String name;
    private String email;
    private String phone;

}
```

<hr> 

<h3>2.5 Injeção de Dependência</h3>

`@Autowired`   

- Segue duas opções de como realizar a injeção de dependência, sendo a 2ª a mais adequada e recomendada para a boa prática     
- Obs.: a baixo dei o exemplo de uma classe controller, porém podemos aplicar em outras classes que utilizam injeção de dependência, como o service.  

- 1ª OPÇÃO: quando tiver apenas 1 repository podemos fazer da seguinte forma    

```java

// package e imports omitidos

class NoteController {

    @Autowired  // injeção de dependência
    private NoteRepository noteRepository;

    // método omitidos

}
```

- 2ª OPÇÃO: havendo mais de um repository na mesma classe, o ideal é receber as dependências via construtor    

```java

// package e imports omitidos

class NoteController {

    private NoteRepository noteRepository;
    private UserRepository userRepository;

    // aplicar método construtor
    @Autowired
    public  NoteController(NoteRepository noteRepository, UserRepository userRepository){
        this.noteRepository = noteRepository;
        this.userRepository = userRepository;
    }

    // método omitidos

}
```

<hr> 

<h2>3. Maven</h2>

<p> Trata-se de uma ferramenta de código aberto, mantida pela Apache, que automatiza os processos de obtenção de dependências e de compilação de projetos Java. Ao criarmos um projeto, utilizando o Maven, é necessário preencher algumas informações, tais como: groupId (nome da organização, que normalmente segue um padrão, sendo ele o site do projeto de trás para frente), artictId (nome do projeto), o version (versão do projeto que iremos utilizar) e o package (nome do pacote principal do projeto). </p>   

<br>

- Comandos maven para utilizar na liha de comando:   

`mvn --version`: verificar a versão do maven  
`mvn clean`: exclui as pastas do /target     
`mvn package`: conver o código fonte .java em um arquivo .jar ou .war e insere na pasta /target  
`mvn install`: gera o pacote e envia o arquivo .jar ou .war para o repositório Maven local, que fica em ../.m2/repository    
`mvn clean install`: build do projeto   
`mvn spring-boot:run`: iniciar o spring boot    
`mvn verify`: executa todos os testes de integração encontrados no projeto       
`mvn deploy`: serve para copiar o pacote final para o repositório remoto para compartilhamento com outros desenvolvedores e projetos.          

<br>

- Exemplo de estrutura dos pacotes

/src   
  - /main     
    - /java -> todos os arquivos java  
    - /resources -> todos os arquivos de configuração (ex: xml, properties, yaml etc.)   
    - /webapp -> arquivos de um projeto .war   
  - /test  
    - /java   
    - / resources  

/target -> usado pelo Maven para compilar e gerar os pacotes   
      



<hr> 

<h2>4. Links</h2>

[Devmedia: Introdução ao Maven](https://www.devmedia.com.br/introducao-ao-maven/25128)    
[Treinaweb: Introdução ao Maven aprenda como criar e gerenciar projetos java](https://www.treinaweb.com.br/blog/introducao-ao-maven-aprenda-como-criar-e-gerenciar-projetos-java)   
[MVN Repository](https://mvnrepository.com/)   

[Formatar Data](https://docs.oracle.com/javase/7/docs/api/java/text/SimpleDateFormat.html)   


<hr> 