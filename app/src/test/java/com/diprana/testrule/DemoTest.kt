package com.diprana.testrule

import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.Description
import org.junit.runners.model.Statement

class DemoTest {

    @get:Rule
    val rule = LogTimingRule()

    @Test
    fun first_test(){
        Thread.sleep(1000)
    }

    @Test
    @LogTiming
    fun second_test(){
        Thread.sleep(500)
    }
}


class LogTimingRule: TestRule {
    override fun apply(base: Statement, description: Description): Statement {
        return object : Statement(){
            override fun evaluate() {

                val enabled = description
                    .annotations
                    .filterIsInstance<LogTiming>()
                    .isNotEmpty()

                val startTime = System.currentTimeMillis()
                try {
                    base.evaluate()
                } finally {

                    if(enabled){
                        val endTime = System.currentTimeMillis()
                        println("${description.methodName} took (${endTime - startTime} ms)")
                    }
                }
            }
        }
    }

}


annotation class LogTiming




/*
what is happening is that by default the
@Rule annotation is applied to the property target,
that is ignored by the JUnit runner. Kotlin allows you to specify the target
of your annotations so in this case we need to specify the target to be the getter.
 */


