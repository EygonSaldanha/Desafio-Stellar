package br.com.stellar.entities

import io.quarkus.hibernate.orm.panache.kotlin.PanacheCompanion
import io.quarkus.hibernate.orm.panache.kotlin.PanacheEntityBase
import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "historico_sistema")
class HistoricoSistema(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    var id: Long,

    @Column(nullable = false)
    var acao: String,

    @Column(nullable = false)
    var entidadeAfetada: String,

    @Column(nullable = false)
    var entidadeId: Long,

    @Column(nullable = false)
    var usuarioResponsavel: String,

    @Column(nullable = false)
    var dataHora: LocalDateTime

) : PanacheEntityBase {

    constructor() : this(
        id = 0,
        acao = "",
        entidadeAfetada = "",
        entidadeId = 0L,
        usuarioResponsavel = "",
        dataHora = LocalDateTime.now()
    )

    companion object : PanacheCompanion<HistoricoSistema>
}
