package de.jhetzel.mvp.operation

import java.util.concurrent.*
import java.util.concurrent.Executor


class ExecutorImpl : Executor {

    private val threadPoolExecutor: ThreadPoolExecutor = ThreadPoolExecutor(3, 10, 50L, TimeUnit.SECONDS,  LinkedBlockingDeque<Runnable>(), ThreadFactoryImpl())

    override fun execute(command: Runnable?) {
        threadPoolExecutor.execute(command)
    }

    private class ThreadFactoryImpl : ThreadFactory {

        var count: Int = 0

        override fun newThread(runnable: Runnable?): Thread {
            return Thread(runnable, "Operation_Thread_${count++}")
        }

    }
}