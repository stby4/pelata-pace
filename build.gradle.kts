import org.jetbrains.kotlin.gradle.targets.js.webpack.KotlinWebpack

val ktorVersion: String by project
val kotlinVersion: String by project
val logbackVersion: String by project
val konformVersion: String by project

plugins {
    kotlin("multiplatform") version "2.1.10"
    application
    id("io.ktor.plugin") version "3.1.1"
    id("io.gitlab.arturbosch.detekt") version "1.23.8"
    id("org.jlleitschuh.gradle.ktlint") version "12.2.0"
}

group = "net.pelata"
version = "0.0.1"
application {
    mainClass.set("net.pelata.ApplicationKt")

    val isDevelopment: Boolean = project.ext.has("development")
    applicationDefaultJvmArgs = listOf("-Dio.ktor.development=$isDevelopment")
}

repositories {
    mavenCentral()
    maven { url = uri("https://maven.pkg.jetbrains.space/public/p/ktor/eap") }
}

detekt {
    toolVersion = "1.22.0"
    config = files("config/detekt/detekt.yml")
    buildUponDefaultConfig = true
}

kotlin {
    jvmToolchain(21)
    targets {
        jvm {
            withJava()
        }
        js {
            browser {
                commonWebpackConfig {
                    outputFileName = "main.js"
                    outputPath = File(buildDir, "processedResources/jvm/main/static")
                }
            }
            binaries.executable()
        }
    }
    sourceSets {
        val commonMain by getting
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
        val jsMain by getting
    }
}

// include JS artifacts in production fat jar
tasks.getByName<Jar>("shadowJar") {
    val isDevelopment: Boolean = project.ext.has("development")
    val taskName =
        if (isDevelopment) {
            "jsBrowserDevelopmentWebpack"
        } else {
            "jsBrowserProductionWebpack"
        }
    val webpackTask = tasks.getByName<KotlinWebpack>(taskName)
    dependsOn(webpackTask) // make sure JS gets compiled first
    from(File(webpackTask.destinationDirectory, webpackTask.outputFileName)) // bring output file along into the JAR
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
    dependsOn(webpackTask) // make sure JS gets compiled first
    // from(File(webpackTask.destinationDirectory, webpackTask.outputFileName)) // bring output file along into the JAR
}

tasks.getByName<JavaExec>("run") {
    classpath(tasks.getByName<Jar>("jvmJar")) // so that the JS artifacts generated by `jvmJar` can be found and served
}
