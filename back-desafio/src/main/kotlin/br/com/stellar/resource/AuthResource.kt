package br.com.stellar.resource

import br.com.stellar.service.AuthService
import jakarta.inject.Inject
import jakarta.ws.rs.*
import jakarta.ws.rs.core.MediaType

@Path("/auth")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
class AuthResource {

    @Inject
    lateinit var authService: AuthService

    data class RegisterRequest(
        val email: String,
        val senha: String,
        val nome: String,
        val endereco: String
    )

    data class LoginRequest(
        val email: String,
        val senha: String
    )

    data class AuthResponse(val token: String)

    @POST
    @Path("/register")
    fun register(request: RegisterRequest): AuthResponse {
        val token = authService.register(request.email, request.senha, request.nome, request.endereco)
        return AuthResponse(token)
    }

    @POST
    @Path("/login")
    fun login(request: LoginRequest): AuthResponse {
        val token = authService.login(request.email, request.senha)
        return AuthResponse(token)
    }
}
