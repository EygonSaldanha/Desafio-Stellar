package br.com.stellar.service

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
        return BancoDTO(banco.nome, banco.dataFundacao)
    }

    fun listBancos(): List<BancoDTO> {
        return Banco.find("ativo", true).list().map { BancoDTO(it.nome, it.dataFundacao) }
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
        return true
    }

}