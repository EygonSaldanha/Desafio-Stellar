package br.com.stellar.form

import br.com.stellar.entities.TipoConta
import java.math.BigDecimal

data class ContaForm(
    val tipo: TipoConta,
    val usuarioId: Long,
    val agenciaId: Long,
)
