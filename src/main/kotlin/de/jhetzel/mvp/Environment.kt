package de.jhetzel.mvp

import de.jhetzel.mvp.eventbus.EventBus
import de.jhetzel.mvp.concurrency.Executor
import kotlin.reflect.KClass


interface Environment {

    fun getEventBus(): EventBus

    fun getOperationExecutor(): Executor

    fun <T : Any> getConfig(configClass: KClass<T>)
}

inline fun <reified T : Any> Environment.getConfig() =  getConfig(T::class)