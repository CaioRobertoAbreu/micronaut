package com.zupacademy.externo

import io.micronaut.core.annotation.Introspected

@Introspected
data class EnderecoResponseClient(
    val cep: String,
    val logradouro: String,
    val complemento: String,
    val bairro: String,
    val localidade: String,
    val uf: String
){

}

