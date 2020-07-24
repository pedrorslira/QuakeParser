# QuakeParser

Um parser para o `games.log` do jogo Quake 3 Arena, onde foram obtidas informações como o número de kills de cada jogador por partida.

## Ferramentas utilizadas:

- Spring Framework (Java) (https://spring.io/)

- Postman  (https://www.postman.com/)

- Ruby  (https://www.ruby-lang.org/pt/)

## Instalação:

Para rodar o código, basta abrir a pasta `parser` como projeto em uma IDE do Spring.
No meu caso eu utilizei o Spring Tools Suite 4. Link: (https://spring.io/tools)

Feito isso, rode o main `QuakeParserApplication.java` como "Spring Boot App". Com isso feito, a porta 8080 vai ficar reservada para o Spring e é dela que faremos os requests dos endpoints (que estão localizados no `GameController.java`).

Para fazer os requests, eu usei o Postman. O arquivo `response.json` que está na pasta do projeto é o resultado do endpoint da Task 1 que pode ser obtido através de um Get Method `localhost:8080/game/parser` no Postman. 

O resultado da task 3 é obtido através de um Get Method `localhost:8080/game/id/{gameId}` onde no {gameId}, o usuário informa o número do id a ser buscado. 
Ex: `localhost:8080/game/id/5`

O script `report.rb` na pasta do projeto se trata da Task 2. Basta rodá-lo para a hash e o relatório serem exibidos.
