package de.jhetzel.mvp

import de.jhetzel.eventbus.EventBus
import de.jhetzel.mvp.operation.ExecutorImpl
import java.util.concurrent.Executor


abstract class BaseEnvironment : Environment {

    private val eventBus: EventBus = EventBus.create()
    private val executor: Executor = ExecutorImpl()

    override fun getEventBus(): EventBus = eventBus

    override fun getOperationExecutor(): Executor = executor

}