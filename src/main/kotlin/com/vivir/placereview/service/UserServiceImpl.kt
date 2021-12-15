package com.vivir.placereview.service

import com.vivir.placereview.data.models.User
import com.vivir.placereview.data.repository.UserRepository
import org.springframework.stereotype.Service

@Service
class UserServiceImpl(val userRepository: UserRepository) : UserService {
    override fun register(username: String, email: String, password: String): Boolean {
        val user = User(email, username, password)
        userRepository.save(user)

        return true
    }
}