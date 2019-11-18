package com.diprana.testrule

import com.diprana.testrule.annotation.Repeat
import org.junit.rules.TestRule
import org.junit.runner.Description
import org.junit.runners.model.Statement

class RepeatTestRule : TestRule {

    override fun apply(base: Statement, description: Description): Statement {
        var result = base
        val repeat = description.getAnnotation(Repeat::class.java)
        if (repeat != null) {
            val repeatTimes = repeat.value
            result = RepeatStatement(result, repeatTimes)
        }

        return result
    }

    class RepeatStatement(
        private val statement: Statement,
        private val repeatTimes: Int
    ) : Statement() {

        override fun evaluate() {
            (1..repeatTimes).forEach {
                statement.evaluate()
            }
        }
    }
}
