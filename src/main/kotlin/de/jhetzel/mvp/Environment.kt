package de.jhetzel.mvp

import de.jhetzel.eventbus.EventBus
import de.jhetzel.mvp.data.Repository
import java.util.concurrent.Executor
import kotlin.reflect.KClass


interface Environment {

    fun getEventBus(): EventBus

    fun getOperationExecutor(): Executor

    fun <T> getRepository(repositoryClass: KClass<T>): T where T : Repository
}