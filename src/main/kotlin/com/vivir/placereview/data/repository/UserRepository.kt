package com.vivir.placereview.data.repository

import com.vivir.placereview.data.models.User
import org.springframework.data.repository.CrudRepository

interface UserRepository : CrudRepository<User, Long> {
    fun findByUsername(username: String): User?
}