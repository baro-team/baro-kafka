package com.baro.consumer.repository

import com.baro.consumer.model.VehicleData
import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Repository

private val logger = KotlinLogging.logger {}

@Repository
class VehicleRepository(
    private val jdbcTemplate: JdbcTemplate
) {

    /**
     * 데이터 TimescaleDB에 저장
     */
    fun save(data: VehicleData) {

        logger.debug { "Saved to DB: $data" }
    }
}
