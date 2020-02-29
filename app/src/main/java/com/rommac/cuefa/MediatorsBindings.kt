package com.rommac.cuefa

import com.rommac.main.AuthMediatorImpl

interface MediatorsBindings {
    fun bindAuthMediator(authMediator: AuthMediatorImpl)
}