package de.jhetzel.mvp

import de.jhetzel.eventbus.EventBus
import de.jhetzel.mvp.data.Service
import de.jhetzel.mvp.concurrency.Executor
import kotlin.reflect.KClass


interface Environment {

    fun getEventBus(): EventBus

    fun getOperationExecutor(): Executor

    fun <T> getService(serviceClass: KClass<T>): T where T : Service
}