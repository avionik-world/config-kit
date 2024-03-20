import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar

plugins {
    kotlin("jvm") version "1.9.22"
    kotlin("plugin.serialization") version "1.9.0"
    id("com.github.johnrengelman.shadow") version "8.1.1"
    alias(libs.plugins.sonatypeCentralPortalPublisher)
}

group = "world.avionik"
version = "1.0.2"

repositories {
    mavenCentral()

    maven("https://repo.thesimplecloud.eu/artifactory/list/gradle-release-local/")
}

dependencies {
    compileOnly(kotlin("stdlib"))

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

signing {
    useGpgCmd()
    sign(configurations.archives.get())
}

centralPortal {
    username = project.findProperty("sonatypeUsername") as String
    password = project.findProperty("sonatypePassword") as String

    pom {
        name.set("Config Kit")
        description.set("Create easy Config Files with json or yaml")
        url.set("https://github.com/avionik-world/config-kit")

        developers {
            developer {
                id.set("niklasnieberler")
                email.set("admin@avionik.world")
            }
        }
        licenses {
            license {
                name.set("Apache-2.0")
                url.set("https://www.apache.org/licenses/LICENSE-2.0.txt")
            }
        }
        scm {
            url.set("https://github.com/avionik-world/config-kit.git")
            connection.set("git:git@github.com:avionik-world/config-kit.git")
        }
    }
}