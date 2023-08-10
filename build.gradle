plugins {
    id("java")
    id("com.katalon.gradle-plugin") version("0.1.1")
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testImplementation("com.codeborne:selenide:6.9.0")
    implementation("io.rest-assured:rest-assured:3.2.0")
    implementation("io.rest-assured:json-path:3.2.0")
    implementation("io.rest-assured:xml-path:3.2.0")
    implementation("io.rest-assured:json-schema-validator:3.2.0")
}

tasks.test {
    useJUnitPlatform()
    System.getProperty("selenide.headless")
}