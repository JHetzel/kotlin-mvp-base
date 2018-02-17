package de.jhetzel.mvp.eventbus

import kotlin.reflect.KClass


internal class EventBusImpl : EventBus {

    private val subscriptions: MutableList<Subscription<Event>> = mutableListOf()

    override fun pushEvent(event: Event) {

        val notification: Notification<*> = Notification.of(event)

        synchronized(this) {
            if(subscriptions.isEmpty()) {
                return
            }
        }
        subscriptions.forEach { notifySubscription(it, notification)}
    }

    @Synchronized private fun notifySubscription(subscription: Subscription<Event>, notification: Notification<*>) {
        if(subscription.eventClass == Event::class) {
            subscription.consume.invoke(notification)
        } else if(subscription.eventClass == notification.event::class) {
            subscription.consume.invoke(notification)
        }
    }

    override fun subscribe(consume: (Notification<Event>) -> Unit): Subscription<Event> {
        return subscribe(Event::class, consume)
    }

    @Synchronized override fun <T : Event> subscribe(eventClass: KClass<T>, consume: (Notification<T>) -> Unit): Subscription<T> {
        val subscription: Subscription<T> = Subscription(eventClass, consume) {
            synchronized(this) {
                subscriptions.remove(it as Subscription<Event>)
            }
        }
        subscriptions.add(subscription as Subscription<Event>)
        return subscription
    }
}