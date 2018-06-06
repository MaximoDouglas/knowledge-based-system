# Skynet
Repositório para controle de versão dos projetos de I.A. 1 do período de 2017.2.

Todos os passos de configuração e execução do projeto estão demonstrados no vídeo em: https://www.useloom.com/share/dd467f37665d4283b5e1f0240451aaee

## 1. Configurações
### 1.1. Configuração do arquivo persistence.xlm.
Este arquivo fica em \inteligenciaartificial\code\skynet\src\main\resources\META-INF. Nele, devem ser adicionadas as informações referentes ao banco de dados local de quem está executando. No mysql local, deve haver um banco criado com o nome "skynet". A senha e usuário devem ser configurados por quem está executando o projeto.

### 1.2. Após configurado o banco de dados, deve-se configurar a máquina virtual java local, pois o jogo dos 8 exige um tamanho de pilha muito grande.
Para configurar a máquina virtual java no eclipse, deve-se ir em Run -> Run Configurations -> Arguments. No campo para VM Arguments, deve-se preencher com: ```-Xss515m```. Isso fará com que o tamanho de pilha de execução inicial do Java seja grande o suficiente.

### 1.3. Agora basta executar a classe App, que fica no pacote br.ufal.ic.ia.skynet.view.
Para executrar, aperte com o botão direito -> Run as -> Java Application.

## 2. Execução
### 2.1. Jogo dos 8.
Para executar o jogo dos 8, na tela inicial do aplicativo selecione a opção "Jogo dos 8" e clique em executar. Na tela seguinte, deve-se digitar no campo existente uma sequência inicial, considerando que: é uma matriz 3x3, os números devem ser preenchidos de 0 a 8, não devem haver elementos repetidos e o número de dígitos deve ser sempre igual a 9. Para todos esses casos, caso o usuário digite uma configuração invália ou uma sequência insolúvel, o sistema retorna uma mensagem de erro, pedindo para que o usuário digite uma nova sequência. Uma configuração exemplo seria: 123456708.

### 2.2. Motor de inferência - Exemplos
Este módulo é importante para ver o funcionamento dos três algoritmos implementados: Encadeamento para frente, encadeamento para frente com função de explicação e encadeamento para trás. Além disto, é possível realizar uma consulta das bases de fatos e regras. Lembrando que o módulo de exemplos foi desenvolvido com o intuito de mostrar que os algoritmos foram implementados e mostrar o funcionamento de cada um. Por isso, as bases de dados e de fatos são pequenas.
Para executar o módulo de Exemplos, na tela inicial do aplicativo selecione a opção "Motor de inferência". Na tela seguinte, devem aparecer duas opções: Exemplos e Recomendação. Deve-se selecionar "Exemplos" e clicar em executar. A tela seguinte deve mostrar os algoritmos disponíveis para o usuário executar, além de uma consulta da base de fatos/regras.

### 2.2. Motor de inferência - Recomendação
Este módulo utiliza principalmente do Encadeamento para frente.
Para executar o módulo de Recomendação, na tela inicial do aplicativo selecione a opção "Motor de inferência" e, na tela seguinte, devem aparecer duas opções: Exemplos e Recomendação. Deve-se selecionar "Recomendação" e clicar em executar. A tela seguinte deve mostrar funcionalidades de celulares disponíveis para o usuário selecionar as que ele procura em um celular, além disso, é disponibilizada uma consulta da base de fatos/regras do módulo de Recomendação. 

#### 2.2.1. Para consultar a base de regras:
Deve-se clicar em Consultar Regras. O resultado será mostrado no terminal.

#### 2.2.2. Para ver o resultado dos celulares recomendados: 
Basta selecionar o que o usuário procura em um smartphone e clicar em executar. O resultado da execução será mostrado no terminal. 
