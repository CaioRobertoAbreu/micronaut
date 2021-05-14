package com.zupacademy.validacaocustomizada

import io.micronaut.core.annotation.AnnotationValue
import io.micronaut.validation.validator.constraints.ConstraintValidator
import io.micronaut.validation.validator.constraints.ConstraintValidatorContext
import javax.inject.Singleton
import javax.validation.Constraint
import kotlin.annotation.AnnotationRetention.RUNTIME
import kotlin.annotation.AnnotationTarget.CONSTRUCTOR
import kotlin.annotation.AnnotationTarget.FIELD

@MustBeDocumented
@Retention(RUNTIME)
@Target(FIELD, CONSTRUCTOR)
@Constraint(validatedBy = [PlacaValidator::class])
annotation class Placa(
    val message: String = "Placa inválida"
) {

}

@Singleton
class PlacaValidator : ConstraintValidator<Placa, String> {

    override fun isValid(
        value: String?,
        annotationMetadata: AnnotationValue<Placa>,
        context: ConstraintValidatorContext

    ): Boolean {

        //[A-Z]{3}[0-9][0-9A-Z][0-9]{2} - incluindo Mercosul - fonte: https://ricardo.coelho.eti.br/regex-mercosul/

        val valido = value?.matches(regex = "[A-Z]{3}[0-9][0-9A-Z][0-9]{2}".toRegex())

        if (valido == false || valido == null) {
            return false
        }

        return true
    }

}
