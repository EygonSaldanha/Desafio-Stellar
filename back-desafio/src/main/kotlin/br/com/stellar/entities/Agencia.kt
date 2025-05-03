package br.com.stellar.entities

import io.quarkus.hibernate.orm.panache.kotlin.PanacheCompanion
import io.quarkus.hibernate.orm.panache.kotlin.PanacheEntityBase
import jakarta.persistence.*

@Entity
@Table(name = "agencia")
class Agencia(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    var id: Long,

    @Column(nullable = false)
    var nome: String,

    @Column(nullable = false)
    var ativo: Boolean,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "banco_id", nullable = false)
    var banco: Banco
) : PanacheEntityBase {

    constructor() : this(
        id = 0,
        nome = "",
        ativo = true,
        banco = Banco()
    )

    companion object : PanacheCompanion<Agencia>
}
