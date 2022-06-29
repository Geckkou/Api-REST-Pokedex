package com.projeto.Pokedex.exceptions;

import com.projeto.Pokedex.util.MessageUtils;

public class NotFoundException extends RuntimeException{

    public NotFoundException() {
        super(MessageUtils.POKEMON_NOT_EXIST);
    }
}
