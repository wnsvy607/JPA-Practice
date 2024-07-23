package com.practice.jpa.entity

import jakarta.persistence.*
import java.io.Serializable

@Entity
@Table(name = "users")
class User(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    var name: String? = null,

    var age: Int? = null,
) : Serializable {


}
