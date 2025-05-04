package br.com.stellar.model

import kotlinx.serialization.Serializable

@Serializable
data class AgenciaResponseDTO(
    val id: Long,
    val nome: String,
    val bancoNome: String
)