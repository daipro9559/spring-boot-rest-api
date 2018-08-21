package com.example.demo_mysql.model.audit

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.Instant
import javax.persistence.Column
import javax.persistence.EntityListeners
import javax.persistence.MappedSuperclass

@MappedSuperclass
@EntityListeners(AuditingEntityListener::class)
@JsonIgnoreProperties(
        value = ["createAt", "updateAt"],
        allowGetters = true
)
abstract class DateAudit(@CreatedDate
                         @Column(nullable = false, updatable = false)
                         var createAt: Instant,
                         @LastModifiedDate
                         @Column(nullable = false)
                         var updateAt: Instant)