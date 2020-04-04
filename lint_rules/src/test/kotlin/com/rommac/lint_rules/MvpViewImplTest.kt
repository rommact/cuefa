package com.rommac.lint_rules

import com.android.tools.lint.checks.infrastructure.LintDetectorTest
import com.android.tools.lint.detector.api.Detector
import com.android.tools.lint.detector.api.Issue
import com.rommac.lint_rules.MvpViewimplementsDetector.Companion.ISSUE_MVP_VIEW_IMPL
import org.junit.Assert
import org.junit.Test

class MvpViewImplTest : LintDetectorTest() {

    @Test
    fun test(){
//        lint()
//            .allowMissingSdk()
//            .files(kotlin("interface MvpView{}\n" +
//                    "open class AppCompatActivity(){}\n" +
//                    "class GameActivity : AppCompatActivity(), MvpView {}"))
//            .issues(ISSUE_MVP_VIEW_IMPL)
//            .run()
//            .expectErrorCount(1)
        lint()
            .allowMissingSdk()
            .files(kotlin("interface MvpView{}\n" +
                    "interface View: MvpView{}\n" +
                    "open class AppCompatActivity(){}\n" +
                    "class GameActivity : AppCompatActivity(), View {}"))
            .issues(ISSUE_MVP_VIEW_IMPL)
            .run()
            .expectErrorCount(1)
    }

    @Test
    fun test2(){
        lint()
            .allowMissingSdk()
            .files(kotlin("interface MvpView{}\n" +
                    "interface View: MvpView{}\n" +
                    "open class AppCompatActivity(){}\n" +
                    "class GameActivity : AppCompatActivity(), View {}"))
            .issues(ISSUE_MVP_VIEW_IMPL)
            .run()
            .expectErrorCount(1)
    }

    override fun getDetector(): Detector {
        return MvpViewimplementsDetector()
    }

    override fun getIssues(): MutableList<Issue> {
        return mutableListOf(ISSUE_MVP_VIEW_IMPL)
    }


}