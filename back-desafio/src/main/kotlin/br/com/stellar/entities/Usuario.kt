package br.com.stellar.entities

import io.quarkus.hibernate.orm.panache.kotlin.PanacheCompanion
import io.quarkus.hibernate.orm.panache.kotlin.PanacheEntityBase
import jakarta.persistence.*

@Entity
@Table(name = "user_account")
class Usuario(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    var id: Long?,

    @Column(nullable = false, unique = true)
    var email: String,

    @Column(name = "name", nullable = false)
    var nome: String,

    @Column(name = "password",nullable = false)
    var senha: String,

    @Column(name = "is_active",nullable = false)
    var ativo: Boolean,

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    var role: Role,

    @Column(name = "address")
    var endereco: String,

) : PanacheEntityBase {

    constructor() : this(
        id = 0,
        email = "",
        senha = "",
        nome = "",
        role = Role.USER,
        ativo = true,
        endereco = "",
    )

    companion object : PanacheCompanion<Usuario>
}
