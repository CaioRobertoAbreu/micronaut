package com.zupacademy.autores

import com.zupacademy.endereco.Endereco
import com.zupacademy.externo.EnderecoClient
import io.micronaut.http.HttpRequest
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Post
import io.micronaut.http.uri.UriBuilder
import io.micronaut.validation.Validated
import javax.inject.Inject
import javax.transaction.Transactional
import javax.validation.Valid

@Validated
@Controller("/autores")
class CadastraAutorController(
    @Inject val autorRepository: AutorRepository,
    @Inject val enderecoClient: EnderecoClient,
) {

    @Post
    @Transactional
    fun cadastra(@Body @Valid request: NovoAutorRequest, httpRequest: HttpRequest<Any>): HttpResponse<*> {

        val consultar = enderecoClient.consultar(request.cep)
        if (consultar.body() == null) {
            return HttpResponse.badRequest<Any>()
        }

        val autor = request.toAutor(Endereco(consultar.body(), request.numero))
        autorRepository.save(autor)

        val uri = UriBuilder.of(httpRequest.path+"/{id}")
            .expand(mutableMapOf("id" to autor.id))

        return HttpResponse.created<Autor>(uri)
    }
}