package br.com.stellar.entities

import io.quarkus.hibernate.orm.panache.kotlin.PanacheCompanion
import io.quarkus.hibernate.orm.panache.kotlin.PanacheEntityBase
import jakarta.persistence.*
import java.math.BigDecimal

@Entity
@Table(
    name = "conta",
    uniqueConstraints = [UniqueConstraint(columnNames = ["usuario_id", "agencia_id"])]
)
class Conta(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    var id: Long,

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo", nullable = false)
    var tipo: TipoConta,

    @Column(nullable = false)
    var saldo: BigDecimal,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id", nullable = false)
    var usuario: Usuario,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "agencia_id", nullable = false)
    var agencia: Agencia

) : PanacheEntityBase {

    constructor() : this(
        id = 0,
        tipo = TipoConta.CORRENTE, // Substitua por o valor padrão que preferir
        saldo = BigDecimal.ZERO,
        usuario = Usuario(),
        agencia = Agencia()
    )

    companion object : PanacheCompanion<Conta>
}
