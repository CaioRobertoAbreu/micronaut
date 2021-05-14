package com.zupacademy.autores

import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Delete
import io.micronaut.http.annotation.PathVariable
import javax.inject.Inject
import javax.transaction.Transactional

@Controller("/autores")
class DeletaAutorController(@Inject val autorRepository: AutorRepository) {

    @Delete("/{id}")
    @Transactional
    fun deletar(@PathVariable id: Long): HttpResponse<Any> {

        val autorEncontrado = autorRepository.findById(id)

        if(autorEncontrado.isEmpty){
            return HttpResponse.notFound()
        }

        val autor = autorEncontrado.get()
        autor.also { deleteAutor ->
            autorRepository.delete(deleteAutor)
            return HttpResponse.noContent()
        }
    }
}