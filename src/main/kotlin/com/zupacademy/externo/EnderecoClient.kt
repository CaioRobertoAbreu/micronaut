package com.zupacademy.externo

import io.micronaut.http.HttpResponse
import io.micronaut.http.MediaType
import io.micronaut.http.annotation.Consumes
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.QueryValue
import io.micronaut.http.client.annotation.Client

@Client("https://viacep.com.br/ws/")
interface EnderecoClient {

    @Get("/{cep}/json", consumes = [MediaType.APPLICATION_JSON] )
//    @Get("/{cep}/xml/")
//    @Consumes(MediaType.APPLICATION_XML)
    fun consultar(@QueryValue cep: String): HttpResponse<EnderecoResponseClient>
}