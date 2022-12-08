# Pokedex

## Como Rodar a aplicação

### Requisitos 
- Docker

### Comandos
Com o Docker instalado rode os seguintes comandos

```docker
sudo docker pull alexandreasr/pokedex:1.0
docker run --name pokedex -d -p 8080:8080 alexandreasr/pokedex:1.0
```
Com o container rodando acesse a URL: [Aplicação Pokédex.](http://localhost:8080/swagger-ui.html#/)