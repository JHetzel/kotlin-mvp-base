package de.jhetzel.mvp.concurrency

import java.util.concurrent.*


abstract class BaseExecutor : Executor {

    private val threadPoolExecutor: ThreadPoolExecutor = ThreadPoolExecutor(3, 10, 50L, TimeUnit.SECONDS,  LinkedBlockingDeque<Runnable>(), ThreadFactoryImpl())

    override fun executeOnBackgroundThread(operation: () -> Unit) {
        threadPoolExecutor.execute { operation.invoke() }
    }

    private class ThreadFactoryImpl : ThreadFactory {

        var count: Int = 0

        override fun newThread(runnable: Runnable?): Thread {
            return Thread(runnable, "Operation_Thread_${count++}")
        }

    }
}