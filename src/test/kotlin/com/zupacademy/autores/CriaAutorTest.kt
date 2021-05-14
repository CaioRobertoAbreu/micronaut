package com.zupacademy.autores

import com.zupacademy.externo.EnderecoClient
import com.zupacademy.externo.EnderecoResponseClient
import io.micronaut.http.HttpRequest
import io.micronaut.http.HttpResponse
import io.micronaut.http.HttpStatus
import io.micronaut.http.client.HttpClient
import io.micronaut.http.client.annotation.Client
import io.micronaut.test.extensions.junit5.annotation.MicronautTest
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import javax.inject.Inject

@MicronautTest
internal class CriaAutorTest {

    @field:Inject
    lateinit var enderecoClient: EnderecoClient

    lateinit var enderecoResponseClient: EnderecoResponseClient

    @field:Inject
    @field:Client("/")
    lateinit var client: HttpClient

    lateinit var novoAutor: NovoAutorRequest

    @BeforeEach
    fun setUp() {
        novoAutor = NovoAutorRequest("Jose de Alencar", "josealencar@email.com", "romancista brasileiro",
        "11707160", "860")

        enderecoClient = Mockito.mock(EnderecoClient::class.java)

        enderecoResponseClient = EnderecoResponseClient(novoAutor.cep, "Rua das Laranjeiras", "",
        "Boqueirao", "Praia Grande", "SP")
    }

    @AfterEach
    fun tearDown() {
    }

    @Test
    internal fun `deve salvar um autor`() {
        //Cenario
        Mockito.`when`(enderecoClient.consultar(novoAutor.cep)).thenReturn(HttpResponse.ok(enderecoResponseClient))


        //Acao
        val response = client.toBlocking().exchange(HttpRequest.POST("/autores", novoAutor), HttpResponse::class.java)

        //Verificacao
        assertEquals(HttpStatus.CREATED, response.status)
        assertTrue(response.headers.contains("Location"))
        assertTrue(response.header("Location")!!.matches("/autores/\\d".toRegex()))


    }
}