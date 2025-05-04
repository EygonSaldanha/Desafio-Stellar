package br.com.stellar.entities

import io.quarkus.hibernate.orm.panache.kotlin.PanacheCompanion
import io.quarkus.hibernate.orm.panache.kotlin.PanacheEntityBase
import jakarta.persistence.*
import java.math.BigDecimal
import java.time.OffsetDateTime

@Entity
@Table(
    name = "account",
    uniqueConstraints = [UniqueConstraint(columnNames = ["agency_id", "user_id"])]
)
class Conta(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    var id: Long,

    @Enumerated(EnumType.STRING)
    @Column(name = "account_type", nullable = false)
    var tipo: TipoConta,

    @Column(name = "account_number", nullable = false, unique = true)
    var numeroConta: String,

    @Column(name = "balance", nullable = false)
    var saldo: BigDecimal,

    @Column(name = "credit_limit")
    var limiteCredito: BigDecimal?,

    @Column(name = "overdraft_limit")
    var limiteChequeEspecial: BigDecimal?,

    @Column(name = "is_active", nullable = false)
    var ativo: Boolean,

    @Column(name = "created_at", nullable = false)
    var criadoEm: OffsetDateTime,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    var usuario: Usuario,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "agency_id", nullable = false)
    var agencia: Agencia

) : PanacheEntityBase {

    constructor() : this(
        id = 0,
        tipo = TipoConta.PADRAO,
        saldo = BigDecimal.ZERO,
        limiteCredito = null,
        numeroConta = "",
        limiteChequeEspecial = null,
        ativo = true,
        criadoEm = OffsetDateTime.now(),
        usuario = Usuario(),
        agencia = Agencia()
    )

    companion object : PanacheCompanion<Conta>
}
