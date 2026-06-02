package com.baro.consumer.consumer

import com.baro.consumer.model.DispatchEventData
import com.baro.consumer.repository.DispatchEventRepository
import com.fasterxml.jackson.databind.ObjectMapper
import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Service

private val logger = KotlinLogging.logger {}

@Service
class DispatchEventConsumer(
    private val repository: DispatchEventRepository,
    private val objectMapper: ObjectMapper,
) {

    @KafkaListener(topics = ["\${app.kafka.dispatch-events-topic}"], groupId = "\${spring.kafka.consumer.group-id}")
    fun consume(message: String) {
        try {
            val data: DispatchEventData = objectMapper.readValue(message, DispatchEventData::class.java)
            repository.save(data)
            logger.debug { "Saved dispatch event: dispatchId=${data.dispatchId}" }
        } catch (e: Exception) {
            logger.error(e) { "Error processing dispatch event message: $message" }
        }
    }
}
