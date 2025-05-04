package br.com.stellar.model

import java.math.BigDecimal

data class TransacaoDTO(
    val id: Long,
    val valor: BigDecimal,
    val contaOrigemId: Long,
    val contaDestinoId: Long,
)
