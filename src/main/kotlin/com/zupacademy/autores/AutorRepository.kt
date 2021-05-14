package com.zupacademy.autores

import io.micronaut.data.annotation.Query
import io.micronaut.data.annotation.Repository
import io.micronaut.data.jpa.repository.JpaRepository
import io.micronaut.http.annotation.QueryValue
import java.util.*

@Repository
interface AutorRepository : JpaRepository<Autor, Long> {

    fun findByEmail(email: String): Optional<Autor>

    /**
     * Podemos usar JPQL para realizar consultas explícitas
     * no banco de dados. Veja abaixo:
     */
    @Query("SELECT a FROM Autor a WHERE a.email = :email")
    fun buscarEmail(@QueryValue("email") email: String): Optional<Autor>
}