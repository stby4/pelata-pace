package net.pelata.frontend

import org.w3c.dom.HTMLInputElement
import org.w3c.dom.NodeList
import org.w3c.dom.events.KeyboardEvent

const val MIN_INPUT_LENGTH = 0
const val MAX_INPUT_LENGTH = 2

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
                    val cursorPos = target.selectionStart
                    val length = target.value.length

                    when (event.key) {
                        "ArrowRight" ->
                                if (cursorPos == length) {
                                    event.preventDefault()
                                    nextSibling?.focus()
                                }
                        "ArrowLeft", "Backspace" ->
                                if (cursorPos == MIN_INPUT_LENGTH) {
                                    event.preventDefault()
                                    previousSibling?.focus()
                                }
                        else -> {}
                    }
                },
                false
        )
    }
}
