package com.vivir.placereview.controller

import com.vivir.placereview.data.models.User
import com.vivir.placereview.service.UserService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import com.vivir.placereview.component.UserValidator
import com.vivir.placereview.service.SecurityService
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/users")
class UserController(val userValidator: UserValidator, val userService: UserService,
                     val securityService: SecurityService) {

    @PostMapping("/registrations")
    fun create(@ModelAttribute form: User, bindingResult: BindingResult, model: Model): String {
        userValidator.validate(form, bindingResult)

        if (bindingResult.hasErrors()) {
            with (model) {
                addAttribute("error", bindingResult.allErrors.first().defaultMessage)
                addAttribute("username", form.username)
                addAttribute("email", form.email)
                addAttribute("password", form.password)
            }

            return "register"
        }

        userService.register(form.username, form.email, form.password)
        securityService.autoLogin(form.username, form.password)

        return "redirect:/home"
    }
}