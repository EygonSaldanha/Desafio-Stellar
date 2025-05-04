package br.com.stellar.service

import br.com.stellar.entities.Agencia
import br.com.stellar.entities.Banco
import br.com.stellar.model.AgenciaRequestDTO
import br.com.stellar.model.AgenciaResponseDTO
import jakarta.enterprise.context.ApplicationScoped
import jakarta.transaction.Transactional
import jakarta.ws.rs.WebApplicationException

@ApplicationScoped
class AgenciaService {

    @Transactional
    fun cadastrar(request: AgenciaRequestDTO): AgenciaResponseDTO {
        val banco = Banco.findById(request.bancoId)
            ?: throw WebApplicationException("Banco não encontrado", 404)

        val agencia = Agencia().apply {
            nome = request.nome
            ativo = true
            this.banco = banco
        }
        agencia.persist()
        return agencia.toResponse()
    }

    fun listarTodas(): List<AgenciaResponseDTO> {
        return Agencia.find("ativo", true).list().map { (it as Agencia).toResponse() }
    }

    @Transactional
    fun desativar(id: Long) {
        val agencia = Agencia.findById(id)
            ?: throw WebApplicationException("Agência não encontrada", 404)
        agencia.ativo = false
    }

    private fun Agencia.toResponse() = AgenciaResponseDTO(
        id = this.id,
        nome = this.nome,
        bancoNome = this.banco.nome
    )
}
