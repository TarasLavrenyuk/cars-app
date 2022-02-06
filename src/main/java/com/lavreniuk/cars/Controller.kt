package com.lavreniuk.cars

import org.springframework.data.domain.Sort
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class Controller(
    private val carRepo: CarRepo
) {

    @GetMapping(
        value = ["/cars"],
        produces = [MediaType.APPLICATION_JSON_VALUE]
    )
    fun getAllCars(): ResponseEntity<List<CarEntity>> {
        val cars: List<CarEntity> =
            carRepo.findAll(Sort.by(Sort.Direction.ASC, CarEntity.Property.CREATED_AT.fieldName()))
        return ResponseEntity.ok(cars)
    }

    @PostMapping(
        value = ["/cars"],
        produces = [MediaType.APPLICATION_JSON_VALUE],
        consumes = [MediaType.APPLICATION_JSON_VALUE]
    )
    fun createCar(
        @RequestBody payload: CreateCarPayload
    ): ResponseEntity<CarEntity> {
        val entity = carRepo.save(
            CarEntity(
                make = payload.make,
                model = payload.model,
                year = payload.year,
                createdAt = System.currentTimeMillis()
            )
        )
        return ResponseEntity.status(HttpStatus.CREATED).body(entity)
    }
}