package br.com.stellar.resource

import br.com.stellar.form.TransacaoForm
import br.com.stellar.model.TransacaoDTO
import br.com.stellar.service.TransacaoService
import jakarta.inject.Inject
import jakarta.ws.rs.*
import jakarta.ws.rs.core.MediaType

@Path("/transacao")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
class TransacaoResource {

    @Inject
    lateinit var transacaoService: TransacaoService

    @POST
    fun transferir(form: TransacaoForm): TransacaoDTO =
        transacaoService.realizarTransferencia(form)

    @GET
    @Path("/conta/{id}")
    fun listarPorConta(@PathParam("id") contaId: Long): List<TransacaoDTO> =
        transacaoService.listarTransacoesPorConta(contaId)
}
