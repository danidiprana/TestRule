package com.diprana.testrule

import com.diprana.testrule.annotation.Repeat
import org.junit.Rule
import org.junit.Test
import org.junit.rules.RuleChain

class RuleChainExampleTest {

    @get:Rule
    val rule = RuleChain.outerRule(RepeatTestRule()).around(PrintRule("Test"))

    @Test
    @Repeat(3)
    fun first_test(){

    }
}