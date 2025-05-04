package br.com.stellar.entities

import io.quarkus.hibernate.orm.panache.kotlin.PanacheCompanion
import io.quarkus.hibernate.orm.panache.kotlin.PanacheEntityBase
import jakarta.persistence.*
import java.math.BigDecimal
import java.time.LocalDateTime

@Entity
@Table(name = "transaction")
class Transacao(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    var id: Long,

    @Column(name = "amount",nullable = false)
    var valor: BigDecimal,

    @Column(name = "created_at",nullable = false)
    var dataHora: LocalDateTime,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "from_account_id", nullable = false)
    var contaOrigem: Conta,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "to_account_id")
    var contaDestino: Conta

) : PanacheEntityBase {

    constructor() : this(
        id = 0,
        valor = BigDecimal.ZERO,
        dataHora = LocalDateTime.now(),
        contaOrigem = Conta(),
        contaDestino = Conta()
    )

    companion object : PanacheCompanion<Transacao>
}
