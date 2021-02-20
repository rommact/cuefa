package com.rommac.lint_rules

import com.android.tools.lint.client.api.IssueRegistry
import com.android.tools.lint.detector.api.Issue
import com.rommac.lint_rules.MvpViewimplementsDetector.Companion.ISSUE_MVP_VIEW_IMPL
import com.rommac.lint_rules.XmlViewNamingDetector.Companion.ISSUE_XML_VIEW_NAMING

class MyIssueRegistry : IssueRegistry() {
    override val issues: List<Issue> = listOf(ISSUE_MVP_VIEW_IMPL, ISSUE_XML_VIEW_NAMING)
}