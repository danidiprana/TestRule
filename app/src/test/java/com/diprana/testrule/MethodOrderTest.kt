package com.diprana.testrule

import org.junit.*
import org.junit.rules.TestRule
import org.junit.runner.Description
import org.junit.runners.model.Statement

class MethodOrderTest {

    @get:Rule
    val rule = PrintRule("  @Rule")

    @Before
    fun before() = println("   @Before")

    @After
    fun after() = println("   @After")

    @Test
    fun test() = println("    @Test testSomething")

    @Test
    fun test2() = println("    @Test testSomethingElse")


    companion object {
        @get:ClassRule @JvmStatic val printRule = PrintRule("@ClassRule")
        @BeforeClass @JvmStatic fun beforeClass() = println(" @BeforeClass")
        @AfterClass @JvmStatic fun afterClass() = println(" @AfterClass")
    }
}

class PrintRule(val name: String) : TestRule {
    override fun apply(base: Statement, description: Description): Statement {
        return object: Statement(){
            override fun evaluate() {

                println("$name before statement")

                try {
                    base.evaluate()
                }finally {
                    println("$name after statement")
                }
            }
        }
    }
}
