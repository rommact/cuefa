package com.rommac.lint_rules

import com.android.tools.lint.client.api.UElementHandler
import com.android.tools.lint.detector.api.*
import com.android.tools.lint.detector.api.Detector.UastScanner;
import com.intellij.psi.PsiType
import org.jetbrains.uast.UClass
import org.jetbrains.uast.UElement

class MvpViewimplementsDetector: Detector(), UastScanner {

    companion object{

            val ISSUE_MVP_VIEW_IMPL = Issue.create(
                "MvpViewimplements",
                "Имплиментация MvpView интерфейса",
                "Имплиментация MvpView интерфейса в ",
                Category.CORRECTNESS,
                5,
                Severity.ERROR,
                Implementation(
                    MvpViewimplementsDetector::class.java,
                    Scope.JAVA_FILE_SCOPE
                )
            )
    }


    override fun applicableSuperClasses(): List<String>? {
        return listOf("AppCompatActivity", "Fragment")
    }

    override fun getApplicableUastTypes(): List<Class<out UElement?>>? {
        return listOf(UClass::class.java)
    }


    override fun visitClass(context: JavaContext, declaration: UClass) {
        val finded = checkSuperTypes(declaration.superTypes.map { it as PsiType })
        if(finded){
            val className = declaration.name!!
            context.report(ISSUE_MVP_VIEW_IMPL,declaration,context.getNameLocation(declaration),"test")
        }
    }

    private fun checkSuperTypes(psiTypes: List<PsiType>): Boolean{
        return psiTypes.any() {
            //                    if(!it.canonicalText.contains("com.rommac"))
//                        return false
            if(checkSuperType(it))
                return true
            return checkSuperTypes(it.superTypes.map { it as PsiType });
        }
    }

    private fun checkSuperType(psiType: PsiType): Boolean{
        return psiType.presentableText.contains("MvpView")
    }





}