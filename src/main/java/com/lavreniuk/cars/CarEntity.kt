package com.lavreniuk.cars

import java.util.*
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "cars")
class CarEntity(
    @Id
    val id: String = UUID.randomUUID().toString(),
    val model: String,
    val make: String,
    val year: Int,

    val createdAt: Long
) {
    constructor() : this("", "", "", 0, 0L)

    enum class Property(
        private val fieldName: String
    ) {
        CREATED_AT("createdAt");

        fun fieldName(): String = fieldName
    }
}