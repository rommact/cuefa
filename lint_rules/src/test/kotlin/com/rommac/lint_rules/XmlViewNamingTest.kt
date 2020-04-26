package com.rommac.lint_rules

import com.android.tools.lint.checks.infrastructure.LintDetectorTest
import com.android.tools.lint.detector.api.Detector
import com.android.tools.lint.detector.api.Issue
import com.rommac.lint_rules.MvpViewimplementsDetector.Companion.ISSUE_MVP_VIEW_IMPL
import com.rommac.lint_rules.XmlViewNamingDetector.Companion.ISSUE_XML_VIEW_NAMING
import org.junit.Test

class XmlViewNamingTest : LintDetectorTest() {


    @Test
    fun testFoundOne(){
        lint()
            .allowMissingSdk()
            .files(
                xml("res/layout/test.xml","<androidx.constraintlayout.widget.ConstraintLayout xmlns:android=\"http://schemas.android.com/apk/res/android\"\n" +
                        "                                                   xmlns:app=\"http://schemas.android.com/apk/res-auto\"\n" +
                        "                                                   android:orientation=\"vertical\"\n" +
                        "                                                   android:layout_width=\"match_parent\"\n" +
                        "                                                   android:layout_height=\"wrap_content\">\n" +
                        "\n" +
                        "    <TextView\n" +
                        "        android:id=\"@+id/txtName\"\n" +
                        "        android:layout_width=\"match_parent\"\n" +
                        "        android:layout_height=\"wrap_content\"\n" +
                        "        android:layout_marginStart=\"16dp\"\n" +
                        "        android:layout_marginTop=\"16dp\"\n" +
                        "        android:layout_marginEnd=\"16dp\"\n" +
                        "        android:layout_marginBottom=\"16dp\"\n" +
                        "        android:text=\"TextView\"\n" +
                        "        android:textSize=\"18sp\"\n" +
                        "        app:layout_constraintBottom_toBottomOf=\"parent\"\n" +
                        "        app:layout_constraintEnd_toEndOf=\"parent\"\n" +
                        "        app:layout_constraintStart_toStartOf=\"parent\"\n" +
                        "        app:layout_constraintTop_toTopOf=\"parent\" />\n" +
                        "</androidx.constraintlayout.widget.ConstraintLayout>")
            )
            .issues(ISSUE_XML_VIEW_NAMING)
            .run()
            .expectErrorCount(1)
    }
    @Test
    fun testFoundNothing(){
        lint()
            .allowMissingSdk()
            .files(
                xml("res/layout/test.xml","<androidx.constraintlayout.widget.ConstraintLayout xmlns:android=\"http://schemas.android.com/apk/res/android\"\n" +
                        "                                                   xmlns:app=\"http://schemas.android.com/apk/res-auto\"\n" +
                        "                                                   android:orientation=\"vertical\"\n" +
                        "                                                   android:layout_width=\"match_parent\"\n" +
                        "                                                   android:layout_height=\"wrap_content\">\n" +
                        "\n" +
                        "    <TextView\n" +
                        "        android:id=\"@+id/txt_name\"\n" +
                        "        android:layout_width=\"match_parent\"\n" +
                        "        android:layout_height=\"wrap_content\"\n" +
                        "        android:layout_marginStart=\"16dp\"\n" +
                        "        android:layout_marginTop=\"16dp\"\n" +
                        "        android:layout_marginEnd=\"16dp\"\n" +
                        "        android:layout_marginBottom=\"16dp\"\n" +
                        "        android:text=\"TextView\"\n" +
                        "        android:textSize=\"18sp\"\n" +
                        "        app:layout_constraintBottom_toBottomOf=\"parent\"\n" +
                        "        app:layout_constraintEnd_toEndOf=\"parent\"\n" +
                        "        app:layout_constraintStart_toStartOf=\"parent\"\n" +
                        "        app:layout_constraintTop_toTopOf=\"parent\" />\n" +
                        "</androidx.constraintlayout.widget.ConstraintLayout>")
            )
            .issues(ISSUE_XML_VIEW_NAMING)
            .run()
            .expectErrorCount(0)
    }

    override fun getDetector(): Detector {
        return XmlViewNamingDetector()
    }

    override fun getIssues(): MutableList<Issue> {
        return mutableListOf(ISSUE_XML_VIEW_NAMING)
    }


}