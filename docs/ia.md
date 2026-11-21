# Documentação do Uso de Inteligência Artificial

## Atividade 1 — Configuração com Singleton

**Disciplina:** Padrões de Projetos Orientados a Objetos  
**Atividade:** Lista Avaliativa II — Questão 1  
**Linguagem utilizada:** Java  
**Padrão de projeto:** Singleton  
**Ferramenta de IA:** ChatGPT (OpenAI)

---

## 1. Objetivo da atividade

Desenvolver uma aplicação Java que utilize o padrão de projeto Singleton para carregar as configurações de um jogo a partir de um arquivo `.properties`.

A aplicação deve garantir que o arquivo seja lido somente durante a criação da instância de configuração, armazenando os dados em memória para consultas posteriores.

Também é necessário disponibilizar métodos de acesso individual às propriedades e demonstrar o funcionamento do padrão.

A IA foi utilizada para orientar a implementação passo a passo, explicar conceitos de orientação a objetos, analisar o código desenvolvido e auxiliar na identificação de erros.

---

## 2. Primeira interação — Planejamento da solução

### Prompt utilizado

> Analise o documento, veja as três primeiras atividades e explique como fazer cada uma.
>
> Lembrando que é para explicar como fazer passo a passo e o pq de cada coisa, para que cada parte possa ser enviada por commit.

### Orientação fornecida pela IA

A IA analisou as atividades e propôs dividir a primeira questão em etapas menores, permitindo desenvolver e registrar cada funcionalidade separadamente.

As etapas sugeridas foram:

1. Criar a estrutura do projeto.
2. Adicionar o arquivo de configuração.
3. Implementar a estrutura Singleton.
4. Desenvolver a leitura do arquivo.
5. Mapear as propriedades para atributos.
6. Criar os métodos de acesso e impressão.
7. Testar e documentar o funcionamento.

### Decisão de implementação

Optou-se por utilizar Java e a classe `Properties`, disponível na biblioteca padrão da linguagem.

A classe `Properties` permite interpretar pares de chave e valor, como `UserName=elamigos`, evitando a necessidade de desenvolver manualmente um mecanismo de leitura e separação de cada linha.

Também foi adotada uma organização que separa o código-fonte, as configurações e a documentação.

### Justificativa

A divisão em etapas facilita a identificação de erros e permite que cada commit represente uma evolução específica do projeto.

Além disso, a utilização de bibliotecas nativas do Java mantém a implementação simples, sem dependências externas.

**Commit correspondente:** [Preencher, caso essa etapa tenha sido registrada separadamente.]

---

## 3. Segunda interação — Implementação inicial da classe Singleton

### Prompt utilizado

Foi enviado à IA o código inicial da classe `ConfiguracaoJogo.java`, acompanhado da pergunta:

> está correto??

A implementação enviada continha a declaração da instância estática, os 15 atributos de configuração, o construtor privado e o método `getInstancia()`.

### Implementação apresentada

A estrutura principal do Singleton era:

```java
private static ConfiguracaoJogo instancia;

private ConfiguracaoJogo() throws IOException {
    // Inicialização das configurações
}

public static synchronized ConfiguracaoJogo getInstancia()
        throws IOException {

    if (instancia == null) {
        instancia = new ConfiguracaoJogo();
    }

    return instancia;
}
```

### Orientação fornecida pela IA

A IA explicou a responsabilidade dos principais elementos:

- `private static`: mantém a referência da instância associada à classe.
- Construtor `private`: impede que outras classes criem diretamente novas instâncias.
- `getInstancia()`: disponibiliza um ponto único de acesso.
- `instancia == null`: permite criar o objeto somente quando ainda não existe.
- `synchronized`: impede que duas threads executem simultaneamente a criação da instância.

### Problemas identificados

**Problema 1 — Utilização incorreta do tipo String**

Alguns atributos foram declarados utilizando `string`, com letra minúscula.

Exemplo:

```java
private string DLCName;
```

A IA explicou que Java diferencia letras maiúsculas de minúsculas e que o tipo correto é `String`.

Correção:

```java
private String dlcName;
```

Além da correção do tipo, foi sugerida a padronização dos nomes utilizando camelCase.

**Problema 2 — Variável propriedades não declarada**

O construtor utilizava:

```java
propriedades = new Properties();
```

Entretanto, não havia uma declaração correspondente entre os atributos da classe.

A IA orientou adicionar:

```java
private final Properties propriedades;
```

O modificador `final` impede que a referência seja substituída após sua inicialização.

### Justificativa das correções

As alterações foram necessárias para resolver erros de compilação e melhorar a organização dos atributos.

A padronização dos nomes também facilita a leitura do código e segue as convenções utilizadas em Java.

**Commit correspondente:** [Inserir hash do commit de correção.]

---

## 4. Terceira interação — Leitura e mapeamento das propriedades

### Prompt utilizado

Foi enviada uma nova versão da classe `ConfiguracaoJogo.java` para análise, já contendo os atributos com tipos corrigidos, chamadas a `getProperty()` e métodos getters.

Entre as instruções adicionadas estavam:

```java
appId = propriedades.getProperty("AppId");
userName = propriedades.getProperty("UserName");
language = propriedades.getProperty("Language");
```

### Orientação fornecida pela IA

A IA explicou que:

```java
propriedades.load(leitor);
```

carrega as propriedades do arquivo para o objeto `Properties`.

Porém, para disponibilizar as informações pelos atributos da classe, é necessário atribuir os valores individualmente utilizando `getProperty()`.

Também foi esclarecido que o método retorna `null` quando a chave solicitada não é encontrada.

### Problemas identificados

**Problema 1 — Nome incorreto da variável updateDB**

Na versão enviada havia a instrução:

```java
pdateDB = propriedades.getProperty("PdateB");
```

Entretanto, o atributo declarado era `updateDB`, e a chave correta no arquivo era `UpdateDB`.

A IA sugeriu:

```java
updateDB = propriedades.getProperty("UpdateDB");
```

O getter correspondente também precisava utilizar o nome correto do atributo.

**Problema 2 — Chave DLCName escrita incorretamente**

O código utilizava:

```java
dlcName = propriedades.getProperty("DlcName");
```

Mas o arquivo contém:

```properties
DLCName=Returnal Pre-Purchase Entitlement
```

A correção sugerida foi:

```java
dlcName = propriedades.getProperty("DLCName");
```

### Justificativa das correções

Os erros ocorreram por inconsistências entre os nomes dos atributos e as chaves do arquivo.

O método `getProperty()` diferencia letras maiúsculas e minúsculas. Portanto, utilizar uma chave diferente da existente no arquivo impede a recuperação correta do valor.

Essas correções são importantes para garantir que os 15 atributos recebam as informações esperadas.

### Métodos de acesso

Também foram implementados getters para consultar as propriedades individualmente.

Exemplo:

```java
public String getUserName() {
    return userName;
}

public String getLanguage() {
    return language;
}

public String getAppId() {
    return appId;
}
```

A utilização de getters preserva o encapsulamento dos atributos, permitindo consultas sem disponibilizar acesso direto para alterações externas.

**Commit correspondente:** [Inserir hash do commit de mapeamento.]

---

## 5. Quarta interação — Compilação e execução

### Prompt utilizado

Foi enviado à IA o resultado da execução dos comandos:

```powershell
javac -encoding UTF-8 -d out src/*.java
java -cp out Main
```

O terminal apresentou:

```text
Mesma instancia? true
Idioma: brazilian
AppId=1649240
UserName=elamigos
Language=brazilian
Offline=0
AutoDLC=0
BuildId=10547147
DLCName=Returnal Pre-Purchase Entitlement
UpdateB=null
Signature=FitGirl
WindowInfo=LAAAAAAAAAAIP50AAACIAAAATQMAAJACAAA=
LVWindowInfo=AwAAADEBAAByAAAApQAAAA==
ApplicationPath=../../../Returnal.exe
WorkingDirectory=
WaitForExit=1
NoOperation=0
```

### Análise do resultado

A compilação foi concluída sem mensagens de erro, e o programa executou.

O resultado:

```text
Mesma instancia? true
```

demonstrou que duas referências obtidas por `getInstancia()` apontavam para o mesmo objeto.

Isso confirma o comportamento esperado do Singleton nesse teste.

O resultado:

```text
Idioma: brazilian
```

também demonstrou que o programa conseguiu recuperar uma configuração através de um getter.

A impressão das propriedades confirmou que a maior parte dos dados do arquivo estava sendo carregada corretamente.

### Problema identificado durante a execução

A propriedade `UpdateDB` apresentou um valor incorreto:

```text
UpdateB=null
```

Entretanto, o arquivo original contém:

```properties
UpdateDB=1
```

A IA explicou que o valor `null` indica que a propriedade pode não ter sido encontrada, sendo necessário verificar a correspondência exata entre a chave consultada e o arquivo.

Também foi observado que o rótulo utilizado na impressão estava escrito como `UpdateB`, em vez de `UpdateDB`.

### Correção orientada

Verificar a leitura:

```java
updateDB = propriedades.getProperty("UpdateDB");
```

E a impressão:

```java
System.out.println("UpdateDB=" + updateDB);
```

### Resultado esperado após a correção

```text
UpdateDB=1
```

**Situação:** correção orientada pela IA; a nova execução ainda precisa ser registrada nesta documentação para comprovar o resultado.

**Commit correspondente:** [Inserir após realizar e registrar a correção.]

---

## 6. Testes e validações

### Teste 1 — Compilação

**Comando executado:**

```powershell
javac -encoding UTF-8 -d out src/*.java
```

**Resultado observado:** compilação concluída sem erros.

### Teste 2 — Execução

**Comando executado:**

```powershell
java -cp out Main
```

**Resultado observado:** aplicação executada e propriedades exibidas no terminal.

### Teste 3 — Verificação da instância

**Resultado:**

```text
Mesma instancia? true
```

**Interpretação:** as duas referências apontam para a mesma instância de `ConfiguracaoJogo`.

### Teste 4 — Consulta individual

**Resultado:**

```text
Idioma: brazilian
```

**Interpretação:** o getter `getLanguage()` retornou corretamente o valor armazenado.

### Teste 5 — Verificação das propriedades

**Resultado:** foram exibidos os 15 campos esperados, mas o campo correspondente a `UpdateDB` apresentou `null`.

**Situação:** teste parcialmente satisfatório, com correção pendente de confirmação.

---

## 7. Decisões técnicas e justificativas

| Decisão | Justificativa |
|---|---|
| Utilizar Singleton | Garantir um único objeto responsável pelas configurações |
| Tornar o construtor privado | Impedir a criação direta de instâncias por outras classes |
| Utilizar synchronized | Proteger a criação da instância em chamadas concorrentes |
| Utilizar Properties | Facilitar a leitura de arquivos com pares de chave e valor |
| Utilizar Files.newBufferedReader | Realizar a leitura do arquivo com codificação definida |
| Utilizar try-with-resources | Garantir o fechamento automático do leitor |
| Armazenar valores em atributos | Representar individualmente as configurações do jogo |
| Utilizar getters | Permitir consultas preservando o encapsulamento |
| Testar com duas referências | Demonstrar a reutilização da instância |
| Separar alterações em commits | Documentar a evolução da implementação |

---

## 8. Ajustes realizados durante o desenvolvimento

A implementação passou por revisões orientadas pela IA.

Entre os ajustes identificados nas versões apresentadas estão:

1. Correção do tipo `string` para `String`.
2. Padronização dos nomes dos atributos.
3. Necessidade de declarar o objeto `Properties`.
4. Inclusão das chamadas a `getProperty()` para preencher os atributos.
5. Implementação de getters para acesso individual às configurações.
6. Identificação e correção orientada de inconsistências nas chaves `DLCName` e `UpdateDB`.
7. Verificação do comportamento Singleton por meio da comparação de referências.

Os ajustes relacionados à sintaxe e aos nomes dos atributos contribuíram para a compilação da aplicação.

A separação entre leitura, armazenamento e acesso aos dados contribuiu para uma implementação organizada e compatível com o objetivo do exercício.

---

## 9. Situação atual e próximas verificações

Na última execução apresentada, a aplicação compilou sem erros, reutilizou a mesma instância e exibiu as informações do arquivo.

Entretanto, ainda é necessário:

- Confirmar a correção da propriedade `UpdateDB`.
- Realizar uma nova execução após essa correção.
- Verificar a leitura única do arquivo durante a vida da instância.
- Conferir o histórico de commits e associá-los às etapas documentadas.
- Atualizar o README com os resultados finais.

---

## 10. Considerações sobre o uso da IA

A inteligência artificial foi utilizada como ferramenta de apoio à aprendizagem e à revisão do código.

As orientações foram organizadas em etapas, permitindo compreender a finalidade dos atributos, métodos, construtores e recursos utilizados na implementação.

O desenvolvimento também permitiu identificar problemas de sintaxe, diferenças entre nomes de variáveis e inconsistências na leitura de propriedades.

A análise das respostas da IA ajudou a compreender não apenas as correções necessárias, mas também os motivos pelos quais determinados erros ocorrem em Java.

O histórico de implementação e seus respectivos commits devem permitir acompanhar as alterações realizadas desde a estrutura inicial até a versão funcional da aplicação.

---

## 11. Histórico de commits

Preencher com os commits efetivamente realizados no repositório, mantendo a ordem cronológica real.

| Etapa | Descrição | Hash do commit |
|---|---|---|
| 1 | Estrutura inicial do projeto | Pendente de registro |
| 2 | Arquivo de configuração | Pendente de registro |
| 3 | Estrutura Singleton | Pendente de registro |
| 4 | Leitura do arquivo de configuração | Pendente de registro |
| 5 | Mapeamento de atributos e getters | Pendente de registro |
| 6 | Teste de execução e impressão | Pendente de registro |
| 7 | Correção da propriedade UpdateDB | Pendente de confirmação |
| 8 | Documentação e testes finais | Pendente |

**Observação:** esta tabela deve refletir o histórico verdadeiro do Git. Etapas realizadas em conjunto não devem ser apresentadas como commits separados caso não tenham sido registradas dessa forma.
