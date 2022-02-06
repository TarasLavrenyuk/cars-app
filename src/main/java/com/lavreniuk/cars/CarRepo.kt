package com.lavreniuk.cars

import org.springframework.data.jpa.repository.JpaRepository

interface CarRepo : JpaRepository<CarEntity, String> {

    override fun deleteAll()

}