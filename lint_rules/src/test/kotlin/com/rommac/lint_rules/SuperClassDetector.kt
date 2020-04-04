package com.rommac.lint_rules

import com.android.tools.lint.detector.api.Detector
import com.android.tools.lint.detector.api.JavaContext
import com.intellij.psi.PsiType
import org.jetbrains.uast.UClass
import org.jetbrains.uast.UElement

abstract class SuperClassDetector: Detector(), Detector.UastScanner {

    override fun getApplicableUastTypes(): List<Class<out UElement?>>? {
        return listOf(UClass::class.java)
    }


    override fun visitClass(context: JavaContext, declaration: UClass) {
        val finded = checkSuperTypes(declaration.superTypes.map { it as PsiType })
        if(finded){
            val className = declaration.name!!
            context.report(MvpViewimplementsDetector.ISSUE_MVP_VIEW_IMPL,declaration,context.getNameLocation(declaration),"test")
        }
    }

    fun checkSuperTypes(psiTypes: List<PsiType>): Boolean{
        return psiTypes.any() {
            //                    if(!it.canonicalText.contains("com.rommac"))
//                        return false
            if(checkSuperType(it))
                return true
            return checkSuperTypes(it.superTypes.map { it as PsiType });
        }
    }

    fun checkSuperType(psiType: PsiType): Boolean{
        return psiType.presentableText.contains("MvpView")
    }
}