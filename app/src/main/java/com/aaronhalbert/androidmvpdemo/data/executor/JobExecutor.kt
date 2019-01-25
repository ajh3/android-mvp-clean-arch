package com.aaronhalbert.androidmvpdemo.data.executor

import com.aaronhalbert.androidmvpdemo.domain.executor.ThreadExecutor
import java.util.concurrent.LinkedBlockingQueue
import java.util.concurrent.ThreadFactory
import java.util.concurrent.ThreadPoolExecutor
import java.util.concurrent.TimeUnit
import javax.inject.Inject

/**
 * Decorated [ThreadPoolExecutor]
 */

private const val RUNNABLE_TO_EXECUTE_CANNOT_BE_NULL = "Runnable to execute cannot be null"
private const val THREAD_NAME = "android_"
private const val INITIAL_POOL_SIZE = 3
private const val MAX_POOL_SIZE = 5
private const val KEEP_ALIVE_TIME = 10
private val KEEP_ALIVE_TIME_UNIT = TimeUnit.SECONDS

open class JobExecutor @Inject constructor() : ThreadExecutor {
    private val workQueue: LinkedBlockingQueue<Runnable> = LinkedBlockingQueue()
    private val threadFactory: ThreadFactory = JobThreadFactory()
    private val threadPoolExecutor: ThreadPoolExecutor = ThreadPoolExecutor(
        INITIAL_POOL_SIZE, MAX_POOL_SIZE,
        KEEP_ALIVE_TIME.toLong(), KEEP_ALIVE_TIME_UNIT, this.workQueue, this.threadFactory
    )

    override fun execute(runnable: Runnable?) {
        if (runnable == null) {
            throw IllegalArgumentException(RUNNABLE_TO_EXECUTE_CANNOT_BE_NULL)
        }
        this.threadPoolExecutor.execute(runnable)
    }

    private class JobThreadFactory : ThreadFactory {
        private var counter = 0

        override fun newThread(runnable: Runnable): Thread {
            return Thread(runnable, THREAD_NAME + counter++)
        }
    }
}
