package br.com.stellar.service

import br.com.stellar.entities.Conta
import br.com.stellar.entities.Transacao
import br.com.stellar.form.TransacaoForm
import br.com.stellar.model.TransacaoDTO
import jakarta.enterprise.context.ApplicationScoped
import jakarta.transaction.Transactional
import jakarta.ws.rs.WebApplicationException
import java.math.BigDecimal

@ApplicationScoped
class TransacaoService {

    @Transactional
    fun realizarTransferencia(form: TransacaoForm): TransacaoDTO {
        if (form.valor <= BigDecimal.ZERO) {
            throw WebApplicationException("Valor da transação deve ser positivo", 400)
        }

        val origem = Conta.findById(form.contaOrigemId)
            ?: throw WebApplicationException("Conta de origem não encontrada", 400)
        val destino = Conta.findById(form.contaDestinoId)
            ?: throw WebApplicationException("Conta de destino não encontrada", 400)

        if (!origem.ativo || !destino.ativo) {
            throw WebApplicationException("Uma das contas está desativada", 400)
        }

        if (origem.saldo < form.valor) {
            throw WebApplicationException("Saldo insuficiente", 400)
        }

        origem.saldo = origem.saldo.minus(form.valor)
        destino.saldo = destino.saldo.plus(form.valor)

        val transacao = Transacao().apply {
            contaOrigem = origem
            contaDestino = destino
            valor = form.valor

        }

        transacao.persist()
        return transacao.toResponse()
    }

    fun listarTransacoesPorConta(contaId: Long): List<TransacaoDTO> {
        return Transacao.find(
            "contaOrigem.id = ?1 OR contaDestino.id = ?1",
            contaId
        ).list().map { it.toResponse() }
    }

    fun Transacao.toResponse() = TransacaoDTO(
        id = this.id,
        valor = this.valor,
        contaOrigemId = this.contaOrigem.id,
        contaDestinoId = this.contaDestino.id
    )
}
