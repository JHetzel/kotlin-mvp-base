package de.jhetzel.mvp.eventbus

import java.util.*

/**
 * Wrapper class for the Events pushed on
 * to the eventbus
 */
class Notification<out T> private constructor(
        val event: T
) where T : Event {

    val creationDate: Date = Date()

    companion object {
        fun <T> of(event: T): Notification<T> where T : Event =  Notification(event)
    }
}