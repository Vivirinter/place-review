package com.vivir.placereview.data.models

import com.vivir.placereview.listener.UserListener
import org.springframework.format.annotation.DateTimeFormat
import java.time.Instant
import java.util.*
import javax.persistence.*
import javax.validation.constraints.Pattern
import javax.validation.constraints.Size

@Entity
@Table(name = "`user`")
@EntityListeners(UserListener::class)
data class User(
    @Column(unique = true)
    @Size(min = 2)
    @Pattern(regexp = "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\\\.[A-Z] {2,6}\$")
    var email: String = "",
    @Column(unique = true)
    var username: String = "",
    @Size(min = 60, max = 60)
    var password: String = "",
    @Column(name = "account_status")
    @Pattern(regexp = "\\A(activated|deactivated)\\z")
    var accountStatus: String = "activated",
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long = 0,
    @DateTimeFormat
    @Column(name = "created_at")
    var createdAt: Date = Date.from(Instant.now())
) {
    @OneToMany(mappedBy = "reviewer", targetEntity = Review::class)
    private var reviews: Collection<Review>? = null
}
