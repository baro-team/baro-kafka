package com.baro.consumer.model

// kafka 수신 데이터 포맷

data class VehicleData(
    val carId: Long,
    val latitude: Float,
    val longitude: Float,
    val speed: Int, // (m/s)
    val battery: Int, // 배터리 잔량
    val timestamp: String, // 데이터 전송 시간
    val heading: Float? = null, // 진행방향 방위각
    val tirePressure: TirePressure? = null, // JSONB
    val engineOil: Float? = null, // 엔진오일 잔량
    val brakeOil: Float? = null, // 브레이크 오일 잔량
    val washerFluid: Float? = null, // 워셔액 잔량
    val extTemp: Float? = null // 외부 온도
)

data class TirePressure(
    val frontLeft: Int,
    val frontRight: Int,
    val rearLeft: Int,
    val rearRight: Int
)
