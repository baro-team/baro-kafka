package com.baro.consumer.consumer

import com.baro.consumer.model.VehicleData
import com.fasterxml.jackson.databind.ObjectMapper
import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.data.geo.Point
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Service

private val logger = KotlinLogging.logger {}

private const val VEHICLE_GEO_KEY = "vehicles:geo"

@Service
@ConditionalOnProperty(name = ["app.consumer.geo.enabled"], havingValue = "true")
class VehicleGeoConsumer(
    private val redisTemplate: RedisTemplate<String, String>,
    private val objectMapper: ObjectMapper,
) {
    @KafkaListener(topics = ["\${app.kafka.topic}"], groupId = "\${spring.kafka.consumer.group-id}")
    fun consume(message: String) {
        try {
            val data: VehicleData = objectMapper.readValue(message, VehicleData::class.java)
            redisTemplate.opsForGeo()
                .add(VEHICLE_GEO_KEY, Point(data.longitude.toDouble(), data.latitude.toDouble()), data.carId.toString())
            logger.debug { "Updated GEO for carId=${data.carId}" }
        } catch (e: Exception) {
            logger.error(e) { "Error updating vehicle GEO: $message" }
            throw e
        }
    }
}
