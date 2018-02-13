package de.jhetzel.eventbus

import kotlin.reflect.KClass


class Subscription<T> internal constructor(
        internal val eventClass: KClass<T>,
        internal val consume: (Notification<T>) -> Unit,
        internal val unsubscribe: (Subscription<T>) -> Unit
) where T : Event {

    fun unsubscribe() = unsubscribe.invoke(this)
}