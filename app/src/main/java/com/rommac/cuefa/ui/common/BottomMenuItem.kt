package com.rommac.cuefa.ui.common
data class BottomMenuItem(
        val resId: Int,
        val name: Int,
        val action: () -> Unit
)