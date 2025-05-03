package br.com.stellar.resource

import br.com.stellar.form.BancoForm
import br.com.stellar.service.BancoService
import jakarta.annotation.security.RolesAllowed
import jakarta.inject.Inject
import jakarta.ws.rs.*
import jakarta.ws.rs.core.MediaType

@Path("/banco")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
class BancoResource(@Inject private val bancoService: BancoService) {

    @POST
    @Path("/novo")
    @RolesAllowed("ADMIN")
    fun createBanco(form: BancoForm) = bancoService.criarBanco(form)

    @GET
    @Path("/all")
    @RolesAllowed("ADMIN")
    fun listBancos() = bancoService.listBancos()

    @PUT
    @Path("/{id}/desativar")
    @RolesAllowed("ADMIN")
    fun desativarBanco(@PathParam("id") id: Long) = bancoService.desativarBanco(id)

}
