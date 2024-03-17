import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar

plugins {
    kotlin("jvm") version "1.9.22"
    kotlin("plugin.serialization") version "1.9.0"
    id("com.github.johnrengelman.shadow") version "8.1.1"
    `maven-publish`
}

group = "world.avionik"
version = "1.0.1"

repositories {
    mavenCentral()

    maven("https://repo.thesimplecloud.eu/artifactory/list/gradle-release-local/")
}

dependencies {
    compileOnly(kotlin("stdlib"))
    runtimeOnly(kotlin("stdlib"))

    api("com.charleskorn.kaml:kaml:0.56.0") {
        exclude("org.jetbrains.kotlin")
        exclude("org.jetbrains.kotlinx", "kotlinx-coroutines-core")
        exclude("org.jetbrains.kotlinx", "kotlinx-coroutines-core-common")
        exclude("org.jetbrains.kotlinx", "kotlinx-coroutines-core-native")
    }

    api("com.google.code.gson:gson:2.10.1")
    compileOnly("eu.thesimplecloud.jsonlib:json-lib:1.0.10")
}

tasks.named("shadowJar", ShadowJar::class) {
    mergeServiceFiles()
}

publishing {
    repositories {
        maven {
            name = "GitHubPackages"
            url = uri("https://maven.pkg.github.com/avionik-world/config-kit")
            credentials {
                username = project.findProperty("gpr.user") as String? ?: System.getenv("USERNAME")
                password = project.findProperty("gpr.key") as String? ?: System.getenv("TOKEN")
            }
        }
    }
    publications {
        register<MavenPublication>("gpr") {
            from(components["java"])
        }
    }
}