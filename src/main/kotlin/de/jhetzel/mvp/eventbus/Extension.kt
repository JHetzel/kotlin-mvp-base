package de.jhetzel.eventbus

inline fun <reified T> EventBus.subscribe(noinline consume: (Notification<T>) -> Unit): Subscription<T> where T : Event {
    return subscribe(T::class, { consume.invoke(it) })
}