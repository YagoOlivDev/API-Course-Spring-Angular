package com.yago.exception;

public class NotFoundExeception extends RuntimeException
{
    public NotFoundExeception(Long id)
    {
        super("Registro não encontrado com o id: "+id);
    }
}
