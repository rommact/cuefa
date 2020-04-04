package com.rommac.lint_rules

interface MvpView{}
interface View: MvpView{}
open class AppCompatActivity(){}
class GameActivity : AppCompatActivity(), View {}