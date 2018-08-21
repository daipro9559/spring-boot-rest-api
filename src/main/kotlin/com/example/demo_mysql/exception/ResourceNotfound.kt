package com.example.demo_mysql.exception

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus
import java.lang.RuntimeException
@ResponseStatus(HttpStatus.NOT_FOUND)
class ResourceNotfound( var resourceName: String,
                        var fieldName: String,
                        var fieldValue: Any) : RuntimeException(){
}