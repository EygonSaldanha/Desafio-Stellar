package br.com.stellar.service

import br.com.stellar.entities.Usuario
import io.smallrye.jwt.build.Jwt
import jakarta.enterprise.context.ApplicationScoped
import jakarta.transaction.Transactional
import jakarta.ws.rs.WebApplicationException
import jakarta.ws.rs.core.Response
import java.time.Duration
import java.util.*

@ApplicationScoped
class AuthService {

    @Transactional
    fun register(email: String, senha: String, nome: String, endereco: String): String {
        val existingUser = Usuario.find("email", email).firstResult()

        if (existingUser != null) {
            throw WebApplicationException(
                Response.status(Response.Status.CONFLICT)
                    .entity(mapOf("mensagem" to "E-mail já está em uso."))
                    .build()
            )
        }

        val user = Usuario().apply {
            this.email = email
            this.senha = senha
            this.nome = nome
            this.endereco = endereco
            this.ativo = true
        }
        user.persist()

        return generateToken(user)
    }

    fun login(email: String, senha: String): String {
        val existingUser = Usuario.find("email", email).firstResult()
            ?: throw WebApplicationException(
                Response.status(Response.Status.CONFLICT)
                    .entity(mapOf("mensagem" to "Usuário não encontrado."))
                    .build()
            )

        if (existingUser.senha != senha) {
            throw WebApplicationException(
                Response.status(Response.Status.CONFLICT)
                    .entity(mapOf("mensagem" to "Senha incorreta."))
                    .build()
            )
        }

        return generateToken(existingUser)
    }

    private fun generateToken(user: Usuario): String {
        return Jwt.issuer("http://localhost:8089")
            .upn(user.email)
            .groups(setOf(user.role.name))
            .expiresIn(Duration.ofHours(2))
            .sign()
    }
}
