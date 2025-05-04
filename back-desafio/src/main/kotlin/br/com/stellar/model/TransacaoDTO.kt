package br.com.stellar.model

import java.math.BigDecimal

data class TransacaoDTO(
    val id: Long,
    val valor: BigDecimal,
    val contaOrigemId: Long,
    val contaOrigemNumero: String,
    val contaOrigemNomeUsuario: String,
    val contaDestinoId: Long,
    val contaDestinoNumero: String,
    val contaDestinoNomeUsuario: String
)

