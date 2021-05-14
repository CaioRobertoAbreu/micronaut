package com.zupacademy.autores

import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.PathVariable
import io.micronaut.http.annotation.Put
import io.micronaut.validation.Validated
import javax.inject.Inject
import javax.transaction.Transactional
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size

@Validated
@Controller("/autores")
class AtualizaAutorController(@Inject val autorRepository: AutorRepository) {

    @Put("/{id}")
    @Transactional
    fun atualiza(@PathVariable id: Long, @NotBlank @Size(max = 400) descricao: String): HttpResponse<Any> {
        val autorEncontado = autorRepository.findById(id)

        if(autorEncontado.isEmpty){
            return HttpResponse.notFound()
        }

        val autor = autorEncontado.get()
        autor.apply {
            /**
             * No final da linha abaixo o hibernate fará a atualização automaticamente.
             * Mas poderíamos implementar o seguinte código que teríamos o mesmo resultado
             * (isso acontece devido ao @Transactional):
             *
             * autorRepository.update(autor)
             */
            this.descricao = descricao
            return HttpResponse.noContent()
        }

    }
}