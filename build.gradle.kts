import org.gradle.api.tasks.bundling.Jar
import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.targets.js.webpack.KotlinWebpack

val ktorVersion: String by project
val kotlinVersion: String by project
val logbackVersion: String by project
val konformVersion: String by project

plugins {
    kotlin("multiplatform") version "2.2.0"
    id("io.ktor.plugin") version "3.2.1" apply false
    id("io.gitlab.arturbosch.detekt") version "1.23.8"
    id("org.jlleitschuh.gradle.ktlint") version "13.1.0"
}

group = "net.pelata"
version = "0.0.1"

repositories {
    mavenCentral()
    maven { url = uri("https://maven.pkg.jetbrains.space/public/p/ktor/eap") }
    maven { url = uri("https://plugins.gradle.org/m2/") }
}

detekt {
    toolVersion = "1.22.0"
    config.from(files("config/detekt/detekt.yml"))
    buildUponDefaultConfig = true
}

kotlin {
    jvmToolchain(21)

    jvm {
        compilations.all {
            val opts = (this as org.jetbrains.kotlin.gradle.plugin.mpp.KotlinJvmCompilation).compilerOptions
            opts.configure {
                jvmTarget.set(org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_21)
            }
        }
        @OptIn(ExperimentalKotlinGradlePluginApi::class)
        binaries {
            executable {
                mainClass.set("net.pelata.ApplicationKt")
            }
        }
    }

    js {
        browser {
            commonWebpackConfig {
                outputFileName = "main.js"
                outputPath = File(layout.buildDirectory.get().asFile, "processedResources/jvm/main/static")
            }
            binaries.executable()
        }
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation("io.ktor:ktor-client-core:$ktorVersion")
            }
        }

        val jvmMain by getting {
            dependencies {
                implementation("io.ktor:ktor-server-core:$ktorVersion")
                implementation("io.ktor:ktor-server-freemarker:$ktorVersion")
                implementation("io.ktor:ktor-server-content-negotiation:$ktorVersion")
                implementation("io.ktor:ktor-server-call-logging:$ktorVersion")
                implementation("io.ktor:ktor-server-default-headers:$ktorVersion")
                implementation("io.ktor:ktor-server-conditional-headers:$ktorVersion")
                implementation("io.ktor:ktor-server-compression:$ktorVersion")
                implementation("io.ktor:ktor-server-host-common:$ktorVersion")
                implementation("io.ktor:ktor-server-status-pages:$ktorVersion")
                implementation("io.ktor:ktor-server-netty:$ktorVersion")
                implementation("io.ktor:ktor-server-request-validation:$ktorVersion")
                implementation("ch.qos.logback:logback-classic:$logbackVersion")
                implementation("io.konform:konform:$konformVersion")
            }
        }

        val jvmTest by getting {
            dependencies {
                implementation("io.ktor:ktor-server-test-host:$ktorVersion")
                implementation("org.jetbrains.kotlin:kotlin-test:$kotlinVersion")
                implementation("org.jetbrains.kotlin:kotlin-test-junit:$kotlinVersion")
            }
        }

        val jsMain by getting {
            dependencies {
                implementation("io.ktor:ktor-client-js:$ktorVersion")
            }
        }
    }
}

// include JS artifacts in dev jar
tasks.getByName<Jar>("jvmJar") {
    val isDevelopment: Boolean = project.ext.has("development")
    val taskName =
        if (isDevelopment) {
            "jsBrowserDevelopmentWebpack"
        } else {
            "jsBrowserProductionWebpack"
        }
    val webpackTask = tasks.getByName<KotlinWebpack>(taskName)
    dependsOn(webpackTask)
    manifest {
        attributes["Main-Class"] = "net.pelata.ApplicationKt"
    }
}

// Create run task
tasks.register<JavaExec>("run") {
    dependsOn("jvmJar")
    classpath = sourceSets["jvmMain"].runtimeClasspath
    mainClass.set("net.pelata.ApplicationKt")

    val isDevelopment: Boolean = project.ext.has("development")
    jvmArgs = listOf("-Dio.ktor.development=$isDevelopment")
}
