package net.pelata.frontend

import kotlinx.browser.document

fun main() {
    document.addEventListener(
            "DOMContentLoaded",
            {
                // Register time input
                val timeInputFields =
                        document.querySelectorAll(".input-group.time input[inputmode=\"numeric\"]")
                timeInput(timeInputFields)
            }
    )
}
