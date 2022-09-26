package net.pelata.frontend

import kotlinx.browser.document

fun main() {
    document.addEventListener(
            "DOMContentLoaded",
            {
                // Register handler when form is submitted
                val paceform = document.querySelector("#paceform")
                formSubmit(paceform)

                // Register time input
                val timeInputFields =
                        document.querySelectorAll(
                                "#paceform .input-group.time input[inputmode=\"numeric\"]"
                        )
                timeInput(timeInputFields)
            }
    )
}
