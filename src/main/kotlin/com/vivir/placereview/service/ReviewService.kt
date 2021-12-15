package com.vivir.placereview.service

import com.vivir.placereview.data.models.Review

interface ReviewService {
    fun createReview(reviewUsername: String, reviewData: Review): Boolean
    fun listReviews(): Iterable<Review>
}