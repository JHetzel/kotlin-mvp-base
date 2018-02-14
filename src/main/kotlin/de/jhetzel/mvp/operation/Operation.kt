package de.jhetzel.mvp.operation

import de.jhetzel.mvp.Environment

/**
 *
 */
abstract class Operation<in A : OperationAction>(
        protected open val environment: Environment
) {

    private lateinit var action: A
    private var resultCallback: ((OperationResult) -> Unit)? = null

    fun execute(action: A, postResult: ((OperationResult) -> Unit)? = null) {
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

    protected fun postResult(result: OperationResult) {
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