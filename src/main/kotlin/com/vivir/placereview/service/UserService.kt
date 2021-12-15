package com.vivir.placereview.service

interface UserService {
    fun register(username: String, email: String, password: String): Boolean
}