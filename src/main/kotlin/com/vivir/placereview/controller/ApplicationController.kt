package com.vivir.placereview.controller

import com.vivir.placereview.service.ReviewService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import java.security.Principal

@Controller
class ApplicationController(val reviewService: ReviewService) {

    @GetMapping("/register")
    fun register(): String {
        return "register"
    }

    @GetMapping("/home")
    fun home(model: Model, principal: Principal): String {
        val reviews = reviewService.listReviews()

        model.addAttribute("reviews", reviews)
        model.addAttribute("principal", principal)

        return "home"
    }

    @GetMapping("/create-review")
    fun createReview(model: Model, principal: Principal): String {
        model.addAttribute("principal", principal)
        return "create-review"
    }
}