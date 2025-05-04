package br.com.stellar.service

import br.com.stellar.entities.Agencia
import br.com.stellar.entities.Conta
import br.com.stellar.entities.Usuario
import br.com.stellar.form.ContaForm
import jakarta.enterprise.context.ApplicationScoped
import jakarta.transaction.Transactional
import jakarta.ws.rs.NotFoundException
import jakarta.ws.rs.WebApplicationException
import br.com.stellar.model.ContaDTO
import jakarta.ws.rs.core.Response
import java.math.BigDecimal

@ApplicationScoped
class ContaService {

    @Transactional
    fun criarConta(form: ContaForm): ContaDTO {
        val usuario = Usuario.findById(form.usuarioId) ?: throw WebApplicationException(
            Response.status(Response.Status.NOT_FOUND)
                .entity(mapOf("mensagem" to "Usuário não encontrado"))
                .build()
        )
        if (!usuario.ativo) throw WebApplicationException(
            Response.status(Response.Status.BAD_REQUEST)
                .entity(mapOf("mensagem" to "Usuário está desativado"))
                .build()
        )

        val agencia = Agencia.findById(form.agenciaId) ?: throw WebApplicationException(
            Response.status(Response.Status.NOT_FOUND)
                .entity(mapOf("mensagem" to "Agência não encontrada"))
                .build()
        )
        if (!agencia.ativo) throw WebApplicationException(
            Response.status(Response.Status.BAD_REQUEST)
                .entity(mapOf("mensagem" to "Agência está desativada"))
                .build()
        )

        val contaExistente = Conta.find("usuario.id = ?1 and agencia.id = ?2", form.usuarioId, form.agenciaId)
            .firstResult()
        if (contaExistente != null) throw WebApplicationException(
            Response.status(Response.Status.BAD_REQUEST)
                .entity(mapOf("mensagem" to "Usuário já possui conta nesta agência"))
                .build()
        )

        val conta = Conta().apply {
            tipo = form.tipo
            saldo = BigDecimal(10)
            ativo = true
            numeroConta = gerarNumeroConta()
            this.usuario = usuario
            this.agencia = agencia
            limiteCredito = BigDecimal(1000)
            limiteChequeEspecial = BigDecimal(100)
        }

        conta.persist()

        return conta.toResponse()
    }

    fun listarContas(): List<ContaDTO> {
        return Conta.find("ativo", true).list().map { it.toResponse() }
    }

    @Transactional
    fun desativarConta(id: Long) {
        val conta = Conta.find("id = ?1 and ativo = true", id).firstResult() ?: throw WebApplicationException(
            Response.status(Response.Status.NOT_FOUND)
                .entity(mapOf("mensagem" to "Conta não encontrada"))
                .build()
        )
        conta.ativo = false
    }

    fun Conta.toResponse() = ContaDTO(
        id = this.id,
        tipo = this.tipo,
        saldo = this.saldo,
        usuarioNome = this.usuario.nome,
        agenciaNome = this.agencia.nome
    )
    fun gerarNumeroConta(): String {
        return (100000..999999).random().toString()
    }
}
