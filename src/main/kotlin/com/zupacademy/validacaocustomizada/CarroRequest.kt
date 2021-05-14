package com.zupacademy.validacaocustomizada

import io.micronaut.core.annotation.Introspected
import javax.persistence.Entity
import javax.validation.constraints.NotBlank

@Introspected
data class CarroRequest(
    @field:NotBlank
    val modelo: String,
    @field:NotBlank
    @field:Placa
    val placa: String
) {

}
