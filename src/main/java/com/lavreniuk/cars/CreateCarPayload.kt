package com.lavreniuk.cars

data class CreateCarPayload(
    val model: String,
    val make: String,
    val year: Int
)