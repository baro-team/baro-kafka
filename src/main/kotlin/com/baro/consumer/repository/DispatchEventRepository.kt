package com.baro.consumer.repository

import com.baro.consumer.model.DispatchEventData
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Repository

@Repository
class DispatchEventRepository(private val jdbcTemplate: JdbcTemplate) {

    fun save(data: DispatchEventData) {
        val sql = """
            INSERT INTO dispatch_events (
                dispatch_id, user_id, car_id,
                start_latitude, start_longitude,
                end_latitude, end_longitude,
                fare, distance_km, estimated_time,
                status, requested_at
            ) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?::timestamptz)
            ON CONFLICT (dispatch_id) DO NOTHING
        """.trimIndent()

        jdbcTemplate.update(
            sql,
            data.dispatchId, data.userId, data.carId,
            data.startLatitude, data.startLongitude,
            data.endLatitude, data.endLongitude,
            data.fare, data.distanceKm, data.estimatedTime,
            data.status, data.requestedAt.toString(),
        )
    }
}
