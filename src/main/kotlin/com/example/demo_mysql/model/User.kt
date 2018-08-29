package com.example.demo_mysql.model

import javax.persistence.*
import javax.validation.constraints.Email
import javax.validation.constraints.Size

@Entity
@Table(name = "users", uniqueConstraints = [UniqueConstraint(columnNames = arrayOf("username")), UniqueConstraint(columnNames = arrayOf("email"))])
data class User(
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        var id:Long,
        var name : String,
        var username : String,
        @Email
        var email :String,
        @Size(max = 100)
        var password:String
){
    @ManyToMany(cascade = [CascadeType.ALL])
    @JoinTable(name = "user_role",
            joinColumns = [JoinColumn(name = "user_id",referencedColumnName = "id")],
            inverseJoinColumns = [JoinColumn(name = "role_id",referencedColumnName = "id")])
    var role :Set<Role> = HashSet()
    constructor(): this(0,"","","","")
    constructor( name : String,
                 username : String,
                @Email
                email :String,
                @Size(max = 100)
               password:String): this(0,name,username,email,password)
}