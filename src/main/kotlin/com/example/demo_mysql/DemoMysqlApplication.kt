package com.example.demo_mysql

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.boot.runApplication
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters
import javax.persistence.Entity

@SpringBootApplication
@EntityScan(basePackageClasses = [DemoMysqlApplication::class,Jsr310JpaConverters::class])
class DemoMysqlApplication

fun main(args: Array<String>) {
    runApplication<DemoMysqlApplication>(*args)
}
