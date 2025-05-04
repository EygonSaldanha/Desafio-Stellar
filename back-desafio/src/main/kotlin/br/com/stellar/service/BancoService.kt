package br.com.stellar.service

import br.com.stellar.entities.Agencia
import br.com.stellar.entities.Banco
import br.com.stellar.form.BancoForm
import br.com.stellar.model.BancoDTO
import jakarta.enterprise.context.ApplicationScoped
import jakarta.transaction.Transactional
import jakarta.ws.rs.WebApplicationException
import jakarta.ws.rs.core.Response

@ApplicationScoped
class BancoService {

    @Transactional
    fun criarBanco(form: BancoForm): BancoDTO {
        val banco = Banco.create(form)
        banco.persist()
        return banco.toResponse()
    }

    fun listBancos(): List<BancoDTO> {
        return Banco.find("ativo", true).list().map { (it as Banco).toResponse() }
    }

    @Transactional
    fun desativarBanco(id: Long): Boolean {
        val banco = Banco.findById(id) ?: throw WebApplicationException(
            Response.status(Response.Status.NOT_FOUND)
                .entity(mapOf("mensagem" to "Banco não encontrado."))
                .build()
        )

        banco.ativo = false
        banco.persist()
        val agencias = Agencia.find("banco.id = ?1", id).list()
        agencias.forEach { it.ativo = false }
        return true
    }

    private fun Banco.toResponse() = BancoDTO(
        id = this.id,
        nome = this.nome,
        dataFundacao = this.dataFundacao
    )

}