package com.example.demo_mysql.payload

/**
 * Created by rajeevkumarsingh on 19/08/17.
 */
class JwtAuthenticationResponse(var accessToken: String?) {
    val tokenType = "Bearer"
}
