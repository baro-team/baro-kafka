package com.baro.consumer.repository

import com.baro.consumer.model.VehicleData
import com.fasterxml.jackson.databind.ObjectMapper
import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Repository

private val logger = KotlinLogging.logger {}

@Repository
class VehicleRepository(
    private val jdbcTemplate: JdbcTemplate,
    private val objectMapper: ObjectMapper
) {

    fun save(data: VehicleData) {
        val sql = """
            INSERT INTO vehicle_data (
                car_id, latitude, longitude, speed, battery, timestamp,
                heading, tire_pressure, engine_oil, brake_oil, washer_fluid, ext_temp
            ) VALUES (?, ?, ?, ?, ?, ?::timestamptz, ?, ?::jsonb, ?, ?, ?, ?)
        """.trimIndent()

        val tirePressureJson = data.tirePressure?.let { objectMapper.writeValueAsString(it) }

        jdbcTemplate.update(
            sql,
            data.carId, data.latitude, data.longitude, data.speed, data.battery, data.timestamp,
            data.heading, tirePressureJson, data.engineOil, data.brakeOil, data.washerFluid, data.extTemp
        )

        logger.debug { "Saved to DB: $data" }
    }
}
