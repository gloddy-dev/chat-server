import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("org.springframework.boot") version "3.2.3"
	id("io.spring.dependency-management") version "1.1.4"
	kotlin("jvm") version "1.9.22"
	kotlin("plugin.spring") version "1.9.22"
}

allprojects {

	apply(plugin = "org.springframework.boot")
	apply(plugin = "io.spring.dependency-management")
	apply(plugin = "org.jetbrains.kotlin.jvm")
	apply(plugin = "org.jetbrains.kotlin.plugin.spring")

	group = "gloddy"
	version = "0.0.1-SNAPSHOT"
	java {
		sourceCompatibility = JavaVersion.VERSION_17
	}

	repositories {
		mavenCentral()
	}

	dependencies{
		implementation("org.jetbrains.kotlin:kotlin-reflect")
		implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
		implementation("org.slf4j:slf4j-api:2.0.10")
		implementation("ch.qos.logback:logback-core:1.4.14")
		testCompileOnly("org.junit.jupiter:junit-jupiter-params:5.10.1")
		testImplementation("org.junit.jupiter:junit-jupiter-api:5.10.1")
		testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.10.1")
		testImplementation("org.mockito.kotlin:mockito-kotlin:5.2.1")
		testImplementation("org.springframework.boot:spring-boot-starter-test") {
			exclude("junit")
		}
	}

	tasks.withType<KotlinCompile> {
		kotlinOptions {
			freeCompilerArgs += "-Xjsr305=strict"
			jvmTarget = "17"
		}
	}

	tasks.withType<Test> {
		useJUnitPlatform()
	}

	tasks {
		withType<Jar> { enabled = true }
		withType<org.springframework.boot.gradle.tasks.bundling.BootJar> { enabled = false }
	}
}
