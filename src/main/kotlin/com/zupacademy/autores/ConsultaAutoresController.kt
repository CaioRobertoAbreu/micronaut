package com.zupacademy.autores

import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.QueryValue
import javax.inject.Inject
import javax.transaction.Transactional

@Controller("/autores")
class ConsultaAutoresController(@Inject val autorRepository: AutorRepository) {

    @Get
    @Transactional
    fun lista(@QueryValue("email") email: String): HttpResponse<Any> {

        if(email.isBlank()){
            val autores = autorRepository.findAll()
            val response = autores.map { autor -> DetalhesAutorResponse(autor) }
            return HttpResponse.ok(response)
        }

        val autorEncontrado = autorRepository.findByEmail(email)

        if(autorEncontrado.isEmpty){
            return HttpResponse.notFound()
        }

        return HttpResponse.ok(DetalhesAutorResponse(autorEncontrado.get()))
    }
}