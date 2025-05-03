package br.com.stellar.service

import br.com.stellar.entities.Usuario
import br.com.stellar.form.UsuarioForm
import io.quarkus.security.ForbiddenException
import jakarta.enterprise.context.ApplicationScoped
import jakarta.transaction.Transactional

@ApplicationScoped
class UsuarioService(
    private val jwtService: JwtService
) {
    @Transactional
    fun register(form: UsuarioForm): String {
        val existingUser = Usuario.find("email", form.email).firstResult()
        if (existingUser != null) {
            throw ForbiddenException("Email já registrado.")
        }

        val usuario = Usuario().apply {
            email = form.email
            senha = form.senha
        }
        usuario.persist()

        return jwtService.generateToken(form.email)
    }

    fun login(form: UsuarioForm): String {
        val usuario = Usuario.find("email", form.email).firstResult() as? Usuario
            ?: throw ForbiddenException("Email ou senha inválidos.")

        if (usuario.senha != form.senha) {
            throw ForbiddenException("Email ou senha inválidos.")
        }

        return jwtService.generateToken(usuario.email)
    }
}
