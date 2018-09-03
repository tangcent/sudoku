package com.itangcent

import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import java.util.concurrent.atomic.AtomicInteger
import java.util.concurrent.locks.ReentrantLock
import kotlin.concurrent.thread

/**
 * Created by TangMing on 2017/4/8.
 */
class SudokuExecutor(private val sudokuData: SudokuData, private val sudokuRules: List<SudokuRule>) {

    @Volatile
    private var flag = 0

    @Volatile
    private var failed = 0

    private val lock = ReentrantLock()

    private val completedCondition = lock.newCondition()

    private val changeCondition = lock.newCondition()

    @Volatile
    private var lasted = -1

    @Volatile
    private var completed = false

    private val counter = AtomicInteger(0)

    private var executorService: ExecutorService? = null

    fun start() {
        failed = (1 shl sudokuRules.size) - 1
        executorService = Executors.newFixedThreadPool(sudokuRules.size)
        for (i in sudokuRules.indices) {
            executorService!!.submit(SudokuRuleTask(sudokuRules[i], i))
        }
        Thread {
            while (true) {
                if (flag == failed || sudokuData.completed()) {
                    lock.lock()
                    try {
                        //DCL
                        completed = true
                        completedCondition.signalAll()
                        executorService!!.shutdownNow()
                        return@Thread
                    } catch (ignored:IllegalThreadStateException){
                        return@Thread
                    }finally {
                        lock.unlock()
                    }
                }
            }
        }.start()
    }


    fun waitCompleted() {
        lock.lock()
        try {
            if (!completed) {
                completedCondition.await()
            }
        } catch (ignored: InterruptedException) {
        } finally {
            lock.unlock()
        }
    }

    fun result(): Array<IntArray> {
        waitCompleted()
        return sudokuData.result()
    }

    private inner class SudokuRuleTask(private val sudokuRule: SudokuRule, index: Int) : Runnable {
        private val index: Int

        private var before: Int = 0

        init {
            this.index = 1 shl index
        }

        override fun run() {
            while (true) {
                try {
                    before = counter.getAndIncrement()
                    if (sudokuRule.check(sudokuData)) {
                        lock.lock()
                        try {
                            lasted = counter.getAndIncrement()
                            flag = 0
                            changeCondition.signalAll()
                        } finally {
                            lock.unlock()
                        }
                    } else {
                        if (before > lasted) {
                            lock.lock()
                            try {
                                //DCL
                                if (before > lasted) {
                                    flag = flag or index
                                    changeCondition.await()
                                }
                            } catch (e: InterruptedException) {
                                return
                            } finally {
                                lock.unlock()
                            }
                        }
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                }

            }
        }
    }

}
