package com.rommac.lint_rules

import com.android.tools.lint.detector.api.*
import org.w3c.dom.Attr

class XmlViewNamingDetector: LayoutDetector() {
    companion object{

        val ISSUE_XML_VIEW_NAMING = Issue.create(
            "XmlViewNaming",
            "Не правильное именнование view",
            "Не правильное именнование view",
            Category.CORRECTNESS,
            5,
            Severity.ERROR,
            Implementation(
                XmlViewNamingDetector::class.java,
                Scope.RESOURCE_FILE_SCOPE
            )
        )
    }

    private val regex = Regex("[A-Z]");
    override fun getApplicableAttributes(): Collection<String>? {
        return listOf("id")
    }

    override fun visitAttribute(context: XmlContext, attribute: Attr) {
        if(regex.containsMatchIn(attribute.name)){
            context.report(ISSUE_XML_VIEW_NAMING,attribute,context.getNameLocation(attribute),"test")
        }
    }
}