package com.zupacademy.autores

import com.zupacademy.endereco.Endereco
import com.zupacademy.externo.EnderecoResponseClient
import io.micronaut.http.HttpStatus
import io.micronaut.http.client.HttpClient
import io.micronaut.http.client.annotation.Client
import io.micronaut.test.extensions.junit5.annotation.MicronautTest
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import javax.inject.Inject

@MicronautTest
internal class BuscaAutorTest {

    @field:Inject
    @field:Client("/")
    lateinit var httpClient: HttpClient

    @field:Inject
    lateinit var autorRepository: AutorRepository

    lateinit var autor: Autor

    @BeforeEach
    fun setup() {
        val responseClient = EnderecoResponseClient("11708160", "rua sei la", "", "caicara", "pg", "sp")
        autor = Autor(
            "Lima Barreto", "limabarreto@email.com", "um grande autor",
            Endereco(responseClient, "813")
        )

        autorRepository.save(autor)
    }

    @AfterEach
    internal fun cleanBD() {

        autorRepository.deleteAll()
    }


    @Test
    internal fun `deve retornar autor com base no email`() {

        val respose =
            httpClient.toBlocking().exchange("/autores?email=${autor.email}", DetalhesAutorResponse::class.java)

        assertNotNull(respose.body())
        assertEquals(HttpStatus.OK, respose.status)
        assertEquals("Lima Barreto", respose.body()!!.nome)
        assertEquals("limabarreto@email.com", respose.body()!!.email)
        assertEquals("um grande autor", respose.body()!!.descricao)

    }
}