import org.jetbrains.kotlin.gradle.targets.js.webpack.KotlinWebpack

val ktor_version: String by project
val kotlin_version: String by project
val logback_version: String by project
val konform_version: String by project

plugins {
    kotlin("multiplatform") version "1.8.22"
    application
    id("io.ktor.plugin") version "2.3.0"
    id("io.gitlab.arturbosch.detekt") version "1.23.0"
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
    jvmToolchain(17)
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
                implementation("io.ktor:ktor-server-core-jvm:$ktor_version")
                implementation("io.ktor:ktor-server-freemarker:$ktor_version")
                implementation("io.ktor:ktor-server-content-negotiation-jvm:$ktor_version")
                implementation("io.ktor:ktor-server-call-logging-jvm:$ktor_version")
                implementation("io.ktor:ktor-server-default-headers-jvm:$ktor_version")
                implementation("io.ktor:ktor-server-conditional-headers-jvm:$ktor_version")
                implementation("io.ktor:ktor-server-compression-jvm:$ktor_version")
                implementation("io.ktor:ktor-server-host-common-jvm:$ktor_version")
                implementation("io.ktor:ktor-server-status-pages-jvm:$ktor_version")
                implementation("io.ktor:ktor-server-netty-jvm:$ktor_version")
                implementation("io.ktor:ktor-server-request-validation-jvm:$ktor_version")
                implementation("ch.qos.logback:logback-classic:$logback_version")
                implementation("io.konform:konform-jvm:$konform_version")
            }
        }
        val jvmTest by getting {
            dependencies {
                implementation("io.ktor:ktor-server-tests-jvm:$ktor_version")
                implementation("org.jetbrains.kotlin:kotlin-test-junit:$kotlin_version")
            }
        }
        val jsMain by getting
    }
}

// include JS artifacts in production fat jar
tasks.getByName<Jar>("shadowJar") {
    val isDevelopment: Boolean = project.ext.has("development")
    val taskName = if (isDevelopment) {
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
    val taskName = if (isDevelopment) {
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
