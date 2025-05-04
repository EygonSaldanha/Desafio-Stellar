package br.com.stellar.model

import br.com.stellar.entities.TipoConta
import java.math.BigDecimal

data class ContaDTO(
    val id: Long,
    val tipo: TipoConta,
    val saldo: BigDecimal,
    val usuarioNome: String,
    val agenciaNome: String
)
