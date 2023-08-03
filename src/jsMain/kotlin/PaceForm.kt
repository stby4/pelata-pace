package net.pelata.frontend

import kotlinx.browser.window
import net.pelata.units.Distance
import org.w3c.dom.Element
import org.w3c.dom.HTMLFormElement
import org.w3c.dom.HTMLInputElement
import org.w3c.dom.HTMLSelectElement
import org.w3c.dom.NodeList
import org.w3c.dom.events.KeyboardEvent
import kotlin.math.floor

const val MIN_INPUT_LENGTH = 0
const val MAX_INPUT_LENGTH = 2

fun formSubmit(form: Element?) {
    form?.addEventListener(
        "submit",
        {
            it.preventDefault()

            val target = it.target as HTMLFormElement

            val action = target.action

            val queryParams =
                buildMap<String, Double?> {
                    val inputs = listOf("distance", "hours", "minutes", "seconds")
                    for (input in inputs) {
                        val element = form.querySelector("#$input") as HTMLInputElement
                        val value = element.value.toDoubleOrNull()
                        // can't use map.getOrDefault for some reasons, so make sure the key
                        // is in map and accept null values
                        put(input, value)
                    }
                }

            val unitField = form.querySelector("#unit") as HTMLSelectElement
            val unit =
                Distance.values().firstOrNull { it.name == unitField.value }
                    ?: Distance.KILOMETERS
            val distance = queryParams["distance"] ?: 0.0
            val hours = queryParams["hours"] ?: 0.0
            val seconds = queryParams["seconds"] ?: 0.0
            val minutes = queryParams["minutes"] ?: 0.0
            var time = 60 * hours + minutes
            if (0 < seconds) {
                time += seconds / 60
            }

            if (0 < time) {
                time = floor(time * 1000) / 1000
                window.location.assign("$action?distance=$distance&unit=$unit&time=$time")
            }
        }
    )
}

/*
 * Adds event listener to the time input field (hh:mm:ss).
 * Any number of inputs is supported, as long as each field only needs two numbers, e.g. hh:mm, ss, all would work.
 */
fun timeInput(timeInputFields: NodeList) {
    for (i in 0..timeInputFields.length - 1) {
        val field = timeInputFields.item(i)
        field?.addEventListener(
            "input",
            {
                val target = it.target as HTMLInputElement

                val previousSibling = target.previousElementSibling as? HTMLInputElement
                val nextSibling = target.nextElementSibling as? HTMLInputElement

                when {
                    MIN_INPUT_LENGTH == target.value.length -> previousSibling?.focus()
                    MAX_INPUT_LENGTH == target.value.length -> nextSibling?.focus()
                    MAX_INPUT_LENGTH < target.value.length ->
                        target.value = target.value.substring(0..1)
                    else -> {}
                }
            },
            false
        )

        field?.addEventListener(
            "keydown",
            {
                val event = it as KeyboardEvent
                val target = it.target as HTMLInputElement

                val previousSibling = target.previousElementSibling as? HTMLInputElement
                val nextSibling = target.nextElementSibling as? HTMLInputElement
                val cursorPosStart = target.selectionStart
                val cursorPosEnd = target.selectionEnd
                val length = target.value.length

                if (cursorPosStart == cursorPosEnd) {
                    when (event.key) {
                        "ArrowRight" ->
                            if (cursorPosStart == length) {
                                event.preventDefault()
                                nextSibling?.focus()
                            }
                        "ArrowLeft", "Backspace" ->
                            if (cursorPosStart == MIN_INPUT_LENGTH) {
                                event.preventDefault()
                                previousSibling?.focus()
                            }
                        else -> {}
                    }
                }
            },
            false
        )
    }
}
