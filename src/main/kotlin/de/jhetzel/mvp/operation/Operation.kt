package de.jhetzel.mvp.operation

import de.jhetzel.mvp.Environment
import javafx.application.Platform.runLater

/**
 *
 */
abstract class Operation<A : Action>(
        protected val environment: Environment
) {

    private lateinit var action: A
    private var resultCallback: ((Result) -> Unit)? = null

    fun execute(action: A, postResult: ((Result) -> Unit)? = null) {
        this.action = action
        resultCallback = postResult

        environment.getEventBus()
                .pushEvent(action)

        environment.getOperationExecutor()
                .execute(RunnableImpl(this))
    }

    protected abstract fun onExecute(action: A)

    protected fun postResult(result: Result) {
        resultCallback?.let { callback ->
            runLater {
                callback.invoke(result)
            }
        }

        environment.getEventBus()
                .pushEvent(result)
    }

    private class RunnableImpl<T : Action>(
            private val operation: Operation<T>
    ) : Runnable {
        override fun run() {
            operation.onExecute(operation.action)
        }
    }

}