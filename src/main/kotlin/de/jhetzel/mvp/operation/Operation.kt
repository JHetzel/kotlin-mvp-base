package de.jhetzel.mvp.operation

import de.jhetzel.mvp.Environment
import javafx.application.Platform.runLater

/**
 *
 */
abstract class Operation<in A : Action>(
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
                .executeOnBackgroundThread {
                    onExecute(action)
                }
    }

    protected abstract fun onExecute(action: A)

    protected fun postResult(result: Result) {
        resultCallback?.let { callback ->
            environment.getOperationExecutor()
                    .executeOnMainThread {
                        callback.invoke(result)
                    }
        }

        environment.getEventBus()
                .pushEvent(result)
    }

}