package com.zupacademy.endereco

import com.zupacademy.externo.EnderecoResponseClient
import javax.persistence.*

@Embeddable
class Endereco(
    enderecoClient: EnderecoResponseClient,
    val numero: String
) {
    val cep: String = enderecoClient.cep
    val logradouro = enderecoClient.logradouro
    val complemento = enderecoClient.complemento
    val bairro = enderecoClient.bairro
    val localidade = enderecoClient.localidade
    val uf = enderecoClient.uf
}
