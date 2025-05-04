package br.com.stellar.form

import java.math.BigDecimal

data class TransacaoForm(
    val contaOrigemId: Long,
    val contaDestinoId: Long,
    val valor: BigDecimal
)
