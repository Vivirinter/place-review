package com.vivir.placereview.data.repository

import com.vivir.placereview.data.models.Review
import org.springframework.data.repository.CrudRepository

interface ReviewRepository : CrudRepository<Review, Long> {
    fun findByPlaceId(placeId: String)
}