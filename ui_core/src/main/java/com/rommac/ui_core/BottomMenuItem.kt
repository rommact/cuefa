package com.rommac.ui_core
data class BottomMenuItem(
        val resId: Int,
        val name: Int,
        val action: () -> Unit
)