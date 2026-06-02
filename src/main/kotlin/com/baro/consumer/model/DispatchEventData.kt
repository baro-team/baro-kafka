package com.baro.consumer.model

data class DispatchEventData(
    val dispatchId: Long,
    val userId: Long,
    val carId: Long,
    val startLatitude: Double,
    val startLongitude: Double,
    val endLatitude: Double,
    val endLongitude: Double,
    val fare: Int,
    val distanceKm: Double,
    val estimatedTime: Int,
    val status: String,
    val requestedAt: String,
)
