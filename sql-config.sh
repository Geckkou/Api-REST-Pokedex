#!/bin/bash
docker cp /home/vertigo-tecmobile/Programação/intellij/workspace/Pokedex/src/main/java/com/projeto/Pokedex/config/mysqld.cnf mysql:/etc/mysql/mysql.conf.d/mysqld.cnf
docker exec -it mysql bash