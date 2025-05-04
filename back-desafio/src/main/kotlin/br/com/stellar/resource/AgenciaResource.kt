package br.com.stellar.resource

import br.com.stellar.model.AgenciaRequestDTO
import br.com.stellar.model.AgenciaResponseDTO
import br.com.stellar.service.AgenciaService
import jakarta.annotation.security.RolesAllowed
import jakarta.inject.Inject
import jakarta.ws.rs.*
import jakarta.ws.rs.core.MediaType
import jakarta.ws.rs.core.Response

@Path("/agencia")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
class AgenciaResource @Inject constructor(
    private val service: AgenciaService
) {

    @POST
    @RolesAllowed("ADMIN")
    fun cadastrar(request: AgenciaRequestDTO): Response {
        val novaAgencia = service.cadastrar(request)
        return Response.status(Response.Status.CREATED).entity(novaAgencia).build()
    }

    @GET
    @RolesAllowed("ADMIN")
    fun listar(): List<AgenciaResponseDTO> = service.listarTodas()

    @PUT
    @Path("/{id}/desativar")
    @RolesAllowed("ADMIN")
    fun desativar(@PathParam("id") id: Long): Response {
        service.desativar(id)
        return Response.ok(mapOf("mensagem" to "Agência desativada com sucesso")).build()
    }
}
