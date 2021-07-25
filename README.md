# Desafio JAVA

## Detalhes Técnicos

Estamos esperando uma aplicação executável com uma estrutura mínima. Você deverá modelar essa estrutura de objetos e utilizar Design Patterns sempre que achar necessário, práticas baseadas em Clean Code também sempre são bem vindas.

- A aplicação deverá ser feita com Spring boot versão 2.1.17.RELEASE e maven.
- A versão do Java deverá ser 8 ou superior.
- Testes unitários serão considerados na avaliação.
- A aplicação pode ser inicializada pelo simples comando Maven: mvn springboot:run.
- Você pode definir outra maneira de inicializar sua aplicação conforme se sentir mais confortável, porém sua aplicação deve funcionar perfeitamente utilizando o comando de inicialização do Spring Boot.
- A aplicação deve conter a API stateless conforme especificação e usar um banco de dados h2, quando necessário, você deve garantir que sua aplicação funcione independente do ambiente externo.
- A criação do banco de dados, assim como sua estrutura de tabelas, deverá acontecer de forma automática em tempo de execução.
- Não se esqueça de documentar qualquer informação adicional que você acreditar que seja importante para avaliação do seu projeto.

## Critério de aceitação

A entrada será dada como um grafo direcionado onde um nó representa um bairro e uma aresta representa uma rota entre dois bairros. O peso da aresta representa então, a distância dessa rota. Uma dada rota jamais aparecerá mais de uma vez; e para uma dada rota, os bairros de origem e destino sempre serão diferentes.

Uma rota direcionada será dada como um objeto JSON, onde os bairros serão nomeados usando letras do alfabeto [A-Z]. Exemplo: uma rota de A para B com distância 5 é representada como:

```json
{ 
 "source": "A",
 "target": "B",
 "distance":5
}
```

<p align="center">
 <img src="https://ibb.co/VTsRmTc" width="400">
</p>

## Funcionalidades Esperadas (Especificação Funcional)

### Salvar Grafo

Esse endpoint deverá receber as arestas de um grafo e salvá-las em um banco de dados para consultas posteriores.

- Endpoint: /graph
- HTTP Method: POST
- HTTP Success Response Code: CREATED (201)
- Contract:
  - Request payload

  ```json
  {
   "data":[
   { 
   "source": "A", "target": "B", "distance":6
   },
   { 
   "source": "A", "target": "E", "distance":4
   },
   { 
   "source": "B", "target": "A", "distance":6
   },
   { 
   "source": "B", "target": "C", "distance":2
   },
   { 
   "source": "B", "target": "D", "distance":4
   },
   { 
   "source": "C", "target": "B", "distance":3
   },
   { 
   "source": "C", "target": "D", "distance":1
   },
   { 
   "source": "C", "target": "E", "distance":7
   },
   { 
   "source": "E", "target": "B", "distance":5
   },
   { 
   "source": "E", "target": "D", "distance":7
   }
   ]
  }
  ```

- Response payload

  ```json
  {
   "id" : 1,
   "data":[
   { 
   "source": "A", "target": "B", "distance":6
   },
   { 
   "source": "A", "target": "E", "distance":4
   },
   { 
   "source": "B", "target": "A", "distance":6
   },
   { 
   "source": "B", "target": "C", "distance":2
   },
   { 
   "source": "B", "target": "D", "distance":4
   },
   { 
   "source": "C", "target": "B", "distance":3
   },
   { 
   "source": "C", "target": "D", "distance":1
   },
   { 
   "source": "C", "target": "E", "distance":7
   },
   {
   "source": "E", "target": "B", "distance":5
   },
   { 
   "source": "E", "target": "D", "distance":7
   }
   ]
  }
  ```
### Recuperar Grafo


