package net.pelata.frontend

import kotlinx.browser.document

fun helloWorld(name: String) {
    console.log("Hello ${name}")
}


fun main() {
    document.addEventListener("DOMContentLoaded", {
        helloWorld("kotlin.js")
    })
}