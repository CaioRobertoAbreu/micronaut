package com.zupacademy.autores

class DetalhesAutorResponse(
    var nome: String = "",
    var email: String = "",
    var descricao: String = "") {

    constructor (autor: Autor) : this() {
        nome = autor.nome
        email = autor.email
        descricao = autor.descricao
    }

}
