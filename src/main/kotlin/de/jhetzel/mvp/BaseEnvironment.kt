package de.jhetzel.mvp

import de.jhetzel.eventbus.EventBus
import de.jhetzel.mvp.concurrency.BaseExecutor
import de.jhetzel.mvp.concurrency.Executor


abstract class BaseEnvironment : Environment {

    private val eventBus: EventBus = EventBus.create()

    private val executor: Executor = object : BaseExecutor() {
        override fun executeOnMainThread(operation: () -> Unit) {
            this@BaseEnvironment.executeOnMainThread(operation)
        }
    }

    protected abstract fun executeOnMainThread(operation: () -> Unit)

    override fun getEventBus(): EventBus = eventBus

    override fun getOperationExecutor(): Executor = executor
}