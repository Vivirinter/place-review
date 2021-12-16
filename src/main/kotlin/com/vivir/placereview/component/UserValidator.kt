package com.vivir.placereview.component

import com.vivir.placereview.data.models.User
import com.vivir.placereview.data.repository.UserRepository
import org.springframework.stereotype.Component
import org.springframework.validation.Errors
import org.springframework.validation.ValidationUtils
import org.springframework.validation.Validator

@Component
class UserValidator(private val userRepository: UserRepository) : Validator {

    override fun supports(clazz: Class<*>): Boolean {
        return User::class == clazz
    }

    override fun validate(target: Any, errors: Errors) {
        val user: User = target as User

        /*
         * Проверка того, что отправленные параметры пользователя не пусты
         * Пустой параметр отклоняется с кодом ошибки и сообщением об ошибке
         */
        ValidationUtils.rejectIfEmptyOrWhitespace(
            errors, "username", "Empty.userForm.username",
            "Username cannot be empty"
        )
        ValidationUtils.rejectIfEmptyOrWhitespace(
            errors, "password", "Empty.userForm.password",
            "Password cannot be empty"
        )
        ValidationUtils.rejectIfEmptyOrWhitespace(
            errors, "email", "Empty.userForm.email",
            "Email cannot be empty"
        )

        /*
         * Проверка длины отправленного имени пользователя
         * Имя пользователя, длина которого меньше 6, отклоняется
         */
        if (user.username.length < 6) {
            errors.rejectValue(
                "username", "Length.userForm.username",
                "Username must be at least 6 characters in length"
            )
        }

        /*
         * Проверка отправленного имени пользователя еще не существует
         * Имя пользователя, уже занятое пользователем, отклоняется
         */
        if (userRepository.findByUsername(user.username) != null) {
            errors.rejectValue("username", "Duplicate.userForm.username", "Username unavailable")
        }

        /*
         * Проверка длины отправленного пароля
         * Пароли длиной менее 8 символов отклоняются
         */
        if (user.password.length < 8) {
            errors.rejectValue(
                "password", "Length.userForm.password",
                "Password must be at least 8 characters in length"
            )
        }
    }

}