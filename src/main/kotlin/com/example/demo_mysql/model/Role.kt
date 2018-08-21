package com.example.demo_mysql.model

import javax.persistence.*

@Entity
@Table(name = "roles")
class Role(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Long,
        @Enumerated(EnumType.STRING)
        var name: RoleName
) {
    constructor() : this(0, RoleName.ROLE_USER)
}