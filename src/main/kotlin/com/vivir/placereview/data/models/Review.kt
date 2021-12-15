package com.vivir.placereview.data.models

import org.springframework.format.annotation.DateTimeFormat
import java.time.Instant
import java.util.*
import javax.persistence.*
import javax.validation.constraints.Size

@Entity
@Table(name = "`review`")
data class Review(
    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    var reviewer: User? = null,
    @Size(min = 5)
    var title: String = "",
    @Size(min = 10)
    var body: String = "",
    @Column(name = "place_address")
    @Size(min = 2)
    var placeAddress: String = "",
    @Column(name = "place_address")
    var placeName: String = "",
    @Column(name = "place_id")
    var placeId: String = "",
    var latitude: Double = 0.0,
    var longitude: Double = 0.0,
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long = 0,
    @DateTimeFormat
    @Column(name = "created_at")
    var createdAt: Date = Date.from(Instant.now())

)