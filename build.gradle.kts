plugins {
    kotlin("jvm") version "1.9.22"
    id("org.jlleitschuh.gradle.ktlint") version "12.1.0"
    application
}

group = "com.baro"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    // Kafka Client
    implementation("org.apache.kafka:kafka-clients:3.7.0")
    
    // JSON Serialization
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.16.1")

    // JDBC for TimescaleDB (PostgreSQL 기반)
    implementation("org.postgresql:postgresql:42.7.1")

    // Dotenv (환경변수 관리)
    implementation("io.github.cdimascio:dotenv-kotlin:3.0.0")

    // Logging (Logback + Kotlin-logging)
    implementation("io.github.oshai:kotlin-logging-jvm:6.0.3")
    implementation("ch.qos.logback:logback-classic:1.5.0")

    // Testing
    testImplementation(kotlin("test"))
    testImplementation("org.junit.jupiter:junit-jupiter:5.10.2")
    testImplementation("io.mockk:mockk:1.13.10")
}

application {
    mainClass.set("com.baro.kafka.ConsumerKt")
}

tasks.test {
    useJUnitPlatform()
}
