package com.baro.consumer.model

// kafka 수신 데이터 포맷

data class VehicleData(
    val carId: Long,
    val latitude: Float,
    val longitude: Float,
    // (m/s)
    val speed: Int,
    // 배터리 잔량
    val battery: Int,
    // 데이터 전송 시간
    val timestamp: String,
    // 배차 가능 여부 (idle / dispatched / on_trip 등)
    val status: String? = null,
    // 진행방향 방위각
    val heading: Float? = null,
    // JSONB
    val tirePressure: TirePressure? = null,
    // 엔진오일 잔량
    val engineOil: Float? = null,
    // 브레이크 오일 잔량
    val brakeOil: Float? = null,
    // 워셔액 잔량
    val washerFluid: Float? = null,
    // 외부 온도
    val extTemp: Float? = null,
)

data class TirePressure(
    val frontLeft: Int,
    val frontRight: Int,
    val rearLeft: Int,
    val rearRight: Int,
)
