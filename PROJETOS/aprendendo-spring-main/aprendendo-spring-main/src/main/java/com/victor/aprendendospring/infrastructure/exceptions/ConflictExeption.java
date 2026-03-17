package com.victor.aprendendospring.infrastructure.exceptions;

public class ConflictExeption extends RuntimeException{

    public ConflictExeption(String mensagem) {
        super(mensagem);
    }
    public ConflictExeption(String mensagem, Throwable throwable){
        super(mensagem);
    }
}
