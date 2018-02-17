package de.jhetzel.mvp.eventbus

import kotlin.reflect.KClass


interface EventBus {

    fun pushEvent(event: Event)

    fun subscribe(consume: (Notification<Event>) -> Unit): Subscription<Event>

    fun <T : Event> subscribe(eventClass: KClass<T>, consume: (Notification<T>) -> Unit): Subscription<T>

    companion object {
        fun create(): EventBus = EventBusImpl()
    }

}