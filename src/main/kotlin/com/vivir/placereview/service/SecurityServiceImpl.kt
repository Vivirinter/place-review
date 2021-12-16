package com.vivir.placereview.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Service

@Service
class SecurityServiceImpl(private val userDetailsService: AppUserDetailsService) : SecurityService {

    @Autowired
    lateinit var authManager: AuthenticationManager

    override fun findLoggerInUser(): String? {
        val userDetails = SecurityContextHolder.getContext().authentication.details

        if (userDetails is UserDetails) {
            return userDetails.username
        }

        return null
    }

    override fun autoLogin(username: String, password: String) {
        val userDetails: UserDetails = userDetailsService.loadUserByUsername(username)

        val usernamePasswordAuthenticationToken = UsernamePasswordAuthenticationToken(
            userDetails, password, userDetails.authorities
        )

        authManager.authenticate(usernamePasswordAuthenticationToken)

        if (usernamePasswordAuthenticationToken.isAuthenticated) {
            SecurityContextHolder.getContext().authentication = usernamePasswordAuthenticationToken
        }
    }
}
