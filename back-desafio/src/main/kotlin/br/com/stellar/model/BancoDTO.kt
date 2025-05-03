package br.com.stellar.model

import br.com.stellar.serialization.LocalDateTimeJson
import kotlinx.serialization.Serializable

@Serializable
data class BancoDTO(
    val nome: String,
    val dataFundacao: LocalDateTimeJson
)
