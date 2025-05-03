package br.com.stellar.service

import io.smallrye.jwt.build.Jwt
import jakarta.enterprise.context.ApplicationScoped
import java.time.Duration
// TODO: Verificar necessidade
@ApplicationScoped
class JwtService {
    fun generateToken(email: String): String {
        return Jwt.issuer("stellar-app")
            .upn(email)
            .expiresIn(Duration.ofHours(2))
            .sign()
    }
}
