<h2>Ambiente de Desenvolvimento</h2>

<h2>JDK</h2>

<h3>Versão Java 11 ou 17 </h3>

<p>
Atualmente as versões do Java são lançadas a cada 6 meses. <br> Isso é bem interessante por diversos motivos, como nos trazer novidades com mais frequência, por exemplo. <br> Dentre estas versões que são lançadas de 6 em 6 meses, também temos as versões de suporte de longo prazo, as chamadas versões LTS, que são lançadas de 3 em 3 anos. <br> As versões LTS do Java nos proporcionam mais estabilidade e um suporte por mais tempo. Por isso, neste curso nós recomendamos que você use Java 11 ou Java 17, versões LTS.
</p>

<h4>Download e Intalação do OpenJDK</h4>

<p>
Para desenvolver aplicações servidoras com Java, precisamos do kit de desenvolvimento Java instalado (JDK).  <br> Eu recomendo que você instale o OpenJDK, que é uma implementação da plataforma Java open source e gratuita, pronta para ser usada em produção. <br> Vamos usar o OpenJDK da Adoptium, que fornece uma implementação do OpenJDK totalmente de graça e com suporte de longo prazo garantido. <br> Para começar, <a href="https://adoptium.net/">faça download</a> do arquivo de instalação. Escolha a versão adequada para seu sistema operacional e deixe marcadas uma das opções Temurin 11 (LTS) ou Temurin 17 (LTS).
</p>

<h4>Windows</h4>

<p>
Extraia o arquivo ZIP baixado em uma pasta qualquer. Essa extração criará a pasta "jdk-11.x.x". <br>
Acesse o painel de controle e clique em "Sistema" e depois "Configurações avançadas do sistema". <br> Acesse a aba "Avançado" e clique no botão "Variáveis de ambiente".
</p>


![](../img/sistemas-01.png)

<p> Clique no botão "Novo", digite o nome da variável JAVA_HOME e no valor, informe o caminho completo da instalação do JDK e clique no botão "OK". </p>

![](../img/sistemas-02.png)

<p> Encontre e selecione a variável "Path" e clique no botão "Editar". Clique no botão "Novo", adicione a entrada %JAVA_HOME%\bin e clique no botão "OK". </p>

![](../img/sistemas-03.png)

<p> Em seguida, clique em "OK" novamente.

Abra o prompt de comando e verifique se a instalação foi feita com sucesso, executando os comandos baixo: </p>

```bash
$ java -version
$ javac -version
```

<h4>macOS</h4>

<p> Abra o terminal e extraia o arquivo baixado. </p>

```bash
$ cd ~/Downloads
$ tar xf OpenJDK11U-jdk_x64_mac_hotspot_11.0.6_10.tar.gz
```

<p> Mova a pasta extraída para o local onde o seu sistema operacional procura pelo JDK instalado. </p>

```bash
$ sudo mv jdk-11.0.6+10 /Library/Java/JavaVirtualMachines/
```

<p> Confira se o JDK foi instalado corretamente, executando os seguintes comandos: </p>

```bash
$ java -version
$ javac -version
```

<h4>Ubuntu (Linux)</h4>

<p> Para instalação no Ubuntu, não precisamos baixar o JDK diretamente do site. <br> Abra o terminal e execute o comando abaixo: </p>

```bash
$ wget -qO - https://adoptopenjdk.jfrog.io/adoptopenjdk/api/gpg/key/public | sudo apt-key add -
```

<p> Agora adicione o repositório do AdoptOpenJDK com o comando: </p>

```bash
$ sudo add-apt-repository --yes https://adoptopenjdk.jfrog.io/adoptopenjdk/deb/
```

<p> Depois, atualize os repositórios: </p>

```bash
$ sudo apt update
```

<p> Agora execute a instalação do OpenJDK: </p>

```bash
$ sudo apt install adoptopenjdk-11-hotspot
```

<p> Aguarde a instalação ser concluída. <br> Talvez essa versão instalada não seja definida como a padrão do seu sistema operacional. Para ter certeza que vamos usar a versão correta, execute o comando abaixo: </p>

```bash
$ sudo update-alternatives --config java
```

<p> Você verá uma lista dos JDKs instalados na sua máquina, mais ou menos assim: </p>

```bash
Existem 3 escolhas para a alternativa java (disponibiliza /usr/bin/java).

  Selecção   Caminho                                              Prioridade Estado
------------------------------------------------------------
  0            /usr/lib/jvm/adoptopenjdk-11-hotspot-amd64/bin/java   1111      modo automático
  1            /usr/lib/jvm/adoptopenjdk-11-hotspot-amd64/bin/java   1111      modo manual
  2            /usr/lib/jvm/java-11-openjdk-amd64/bin/java           1111      modo manual
* 3            /usr/lib/jvm/java-8-openjdk-amd64/jre/bin/java        1081      modo manual
```

<p> Depois você deve digitar o número correspondente à versão que deseja. No nosso caso é a opção de número 1: </p>

```bash
 1            /usr/lib/jvm/adoptopenjdk-11-hotspot-amd64/bin/java   1111      modo manual
```

<p> Em seguida, confira se o JDK foi instalado com sucesso: </p>

```bash
$ java -version
$ javac -version
```

<p> Deverá aparecer algo como: </p>

```bash
openjdk version "11.0.6" 2020-01-14
OpenJDK Runtime Environment AdoptOpenJDK (build 11.0.6+10)
OpenJDK 64-Bit Server VM AdoptOpenJDK (build 11.0.6+10, mixed mode)
```
