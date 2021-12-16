package com.vivir.placereview.service

interface SecurityService {
    fun findLoggerInUser(): String?
    fun autoLogin(username: String, password: String)
}