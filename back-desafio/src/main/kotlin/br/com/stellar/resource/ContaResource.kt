package br.com.stellar.resource

import br.com.stellar.entities.Conta
import br.com.stellar.form.ContaForm
import br.com.stellar.model.ContaDTO
import br.com.stellar.service.ContaService
import jakarta.annotation.security.RolesAllowed
import jakarta.inject.Inject
import jakarta.transaction.Transactional
import jakarta.validation.Valid
import jakarta.ws.rs.*
import jakarta.ws.rs.core.MediaType
import jakarta.ws.rs.core.Response

@Path("/conta")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
class ContaResource @Inject constructor(
    private val contaService: ContaService
) {

    @POST
    @RolesAllowed("ADMIN")
    fun criar(form: ContaForm): ContaDTO = contaService.criarConta(form)

    @GET
    fun listar(): List<ContaDTO> = contaService.listarContas()

    @DELETE
    @Path("/{id}")
    fun desativar(@PathParam("id") id: Long) = contaService.desativarConta(id)
}
