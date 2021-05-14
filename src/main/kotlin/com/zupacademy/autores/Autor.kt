package com.zupacademy.autores

import com.zupacademy.endereco.Endereco
import org.hibernate.annotations.CreationTimestamp
import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "autores")
class Autor(
    @field:Column(nullable = false)
    val nome: String,
    @field:Column(nullable = false)
    val email: String,
    @field:Column(nullable = false)
    var descricao: String,
    @Embedded
    var endereco: Endereco
){

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null
    @CreationTimestamp
    val criadoEm: LocalDateTime = LocalDateTime.now()

}
