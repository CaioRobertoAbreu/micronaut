package com.zupacademy.validacaocustomizada

import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Post
import io.micronaut.validation.Validated
import javax.validation.Valid

@Controller("/carros")
@Validated
class Controller {

    @Post
    fun cadastrar(@Valid @Body carroRequest: CarroRequest) : HttpResponse<Any>{


        return HttpResponse.ok()
    }
}