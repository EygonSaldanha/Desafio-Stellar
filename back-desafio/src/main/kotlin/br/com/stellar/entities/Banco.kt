package br.com.stellar.entities

import br.com.stellar.form.BancoForm
import io.quarkus.hibernate.orm.panache.kotlin.PanacheEntityBase
import io.quarkus.hibernate.orm.panache.kotlin.PanacheCompanion
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.time.LocalDateTime

@Entity
@Table(name = "bank")
class Banco(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    var id: Long,

    @Column(name = "name",nullable = false)
    var nome: String,

    @Column(name = "is_active",nullable = false)
    var ativo: Boolean,

    @Column(name = "created_at")
    var dataFundacao: LocalDateTime
) : PanacheEntityBase {

    constructor() : this(
        id = 0,
        nome = "",
        ativo = true,
        dataFundacao = LocalDateTime.now()
    )

    companion object : PanacheCompanion<Banco> {
        fun create(form: BancoForm): Banco {
            return Banco().apply {
                this.nome = form.nome
                this.dataFundacao = form.dataFundacao
            }
        }
    }
}



