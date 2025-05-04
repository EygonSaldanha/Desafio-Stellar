package br.com.stellar.model

import kotlinx.serialization.Serializable

@Serializable
data class AgenciaRequestDTO(
    val nome: String,
    val bancoId: Long
)