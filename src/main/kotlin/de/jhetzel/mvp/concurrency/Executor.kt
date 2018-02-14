package de.jhetzel.mvp.concurrency


interface Executor {

    fun executeOnBackgroundThread(operation: () -> Unit)

    fun executeOnMainThread(operation: () -> Unit)
}