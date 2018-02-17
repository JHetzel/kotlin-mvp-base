package de.jhetzel.mvp

import de.jhetzel.mvp.eventbus.EventBus
import de.jhetzel.mvp.concurrency.Executor


interface Environment {

    fun getEventBus(): EventBus

    fun getOperationExecutor(): Executor
}