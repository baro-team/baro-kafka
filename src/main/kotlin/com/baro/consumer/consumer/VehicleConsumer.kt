package com.baro.consumer.consumer

import com.baro.consumer.model.VehicleData
import com.baro.consumer.repository.VehicleRepository
import com.fasterxml.jackson.databind.ObjectMapper
import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Service

private val logger = KotlinLogging.logger {}

@Service
@ConditionalOnProperty(name = ["app.consumer.timescaledb.enabled"], havingValue = "true", matchIfMissing = true)
class VehicleConsumer(
    private val repository: VehicleRepository,
    private val objectMapper: ObjectMapper
) {

    @KafkaListener(topics = ["\${app.kafka.topic}"], groupId = "\${spring.kafka.consumer.group-id}")
    fun consume(message: String) {
        try {
            val vehicleData: VehicleData = objectMapper.readValue(message, VehicleData::class.java)
            repository.save(vehicleData)
            logger.debug { "Successfully processed and saved vehicle data: $vehicleData" }
        } catch (e: Exception) {
            logger.error(e) { "Error processing Kafka message: $message" }
        }
    }
}
