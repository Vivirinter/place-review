package com.vivir.placereview.controller

import com.vivir.placereview.component.ReviewValidator
import com.vivir.placereview.data.models.Review
import com.vivir.placereview.service.ReviewService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import javax.servlet.http.HttpServletRequest

@Controller
@RequestMapping("/reviews")
class ReviewController(val reviewValidator: ReviewValidator, val reviewService: ReviewService) {

    @PostMapping
    fun create(
        @ModelAttribute reviewForm: Review, bindingResult: BindingResult, model: Model,
        request: HttpServletRequest
    ): String {
        reviewValidator.validate(reviewForm, bindingResult)

        if (!bindingResult.hasErrors()) {
            val res = reviewService.createReview(request.userPrincipal.name, reviewForm)

            if (res) {
                return "redirect:/home"
            }
        }

        with(model) {
            addAttribute("error", bindingResult.allErrors.first().defaultMessage)
            addAttribute("title", reviewForm.title)
            addAttribute("body", reviewForm.body)
            addAttribute("placeName", reviewForm.placeName)
            addAttribute("placeAddress", reviewForm.placeAddress)
            addAttribute("placeId", reviewForm.placeId)
            addAttribute("longitude", reviewForm.longitude)
            addAttribute("latitude", reviewForm.latitude)
        }

        return "create-review"
    }
}