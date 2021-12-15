package com.vivir.placereview.service

import com.vivir.placereview.data.models.Review
import com.vivir.placereview.data.models.User
import com.vivir.placereview.data.repository.ReviewRepository
import com.vivir.placereview.data.repository.UserRepository
import org.springframework.stereotype.Service

@Service
class ReviewServiceImpl(val reviewRepository: ReviewRepository, val userRepository: UserRepository) : ReviewService {
    override fun listReviews(): Iterable<Review> {
        return reviewRepository.findAll()
    }

    override fun createReview(reviewUsername: String, reviewData: Review): Boolean {
        val reviewer: User? = userRepository.findByUsername(reviewUsername)

        if (reviewer != null) {
            reviewData.reviewer = reviewer
            reviewRepository.save(reviewData)
            return true
        }
        return false

    }
}