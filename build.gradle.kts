import org.jetbrains.kotlin.gradle.targets.js.webpack.KotlinWebpack

val ktor_version: String by project
val kotlin_version: String by project
val logback_version: String by project
val konform_version: String by project

plugins {
    application
    kotlin("multiplatform") version "1.7.20-RC"
    id("io.ktor.plugin") version "2.1.1"
    id("io.gitlab.arturbosch.detekt") version "1.21.0"
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
    toolVersion = "1.21.0"
    config = files("config/detekt/detekt.yml")
    buildUponDefaultConfig = true
}

kotlin {
    targets {
        jvm {
            withJava()
        }
        js {
            browser {
                binaries.executable()
            }
        }
    }
    sourceSets {
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

tasks {
    named("frontendBrowserProductionWebpack") {
        outputFileName("pelata.js")
    }
    named("backendJar") {
        dependsOn("frontendBrowserProductionWebpack")
        val frontendBrowserProductionWebpack = project.tasks.getByName("frontendBrowserProductionWebpack", KotlinWebpack::class)
        from(frontendBrowserProductionWebpack.destinationDirectory)
        into(frontendBrowserProductionWebpack.outputFileName)
    }
    run<JavaExec> {
        main("net.pelata.ApplicationKt")
        classpath(configurations.backendRuntimeClasspath, backendJar)
        args = []
    }
}


// task run(type: JavaExec, dependsOn: [backendJar]) {
//     main = "net.pelata.ApplicationKt"
//     classpath(configurations.backendRuntimeClasspath, backendJar)
//     args = []
// }
