plugins {
	java
	id("org.springframework.boot") version "4.0.2"
	id("io.spring.dependency-management") version "1.1.7"
}

group = "com.tpm.fileserver"
version = "0.0.1-SNAPSHOT"
description = "File service for home usage"

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(21)
	}
}

configurations {
	compileOnly {
		extendsFrom(configurations.annotationProcessor.get())
	}
}

repositories {
	mavenCentral()
}

dependencies {
	// Spring
//	implementation("org.springframework.boot:spring-boot-starter-jooq")
//	implementation("org.springframework.boot:spring-boot-starter-liquibase")
	implementation("org.springframework.boot:spring-boot-starter-restclient")
	implementation("org.springframework.boot:spring-boot-starter-webmvc")

	// Ftp server
	implementation("org.apache.ftpserver:ftpserver-core:1.2.1")
	implementation("org.apache.mina:mina-core:2.2.5")
	implementation("org.apache.ftpserver:ftplet-api:1.2.1")

	//logging
	implementation("org.slf4j:slf4j-api:2.0.17")
	testImplementation("org.slf4j:slf4j-simple:2.0.17")

	// lombok
	compileOnly("org.projectlombok:lombok")
	annotationProcessor("org.projectlombok:lombok")

	//Spring Test
	testImplementation("org.springframework.boot:spring-boot-starter-jooq-test")
	testImplementation("org.springframework.boot:spring-boot-starter-liquibase-test")
	testImplementation("org.springframework.boot:spring-boot-starter-restclient-test")
	testImplementation("org.springframework.boot:spring-boot-starter-webmvc-test")
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

tasks.withType<Test> {
	useJUnitPlatform()
}
