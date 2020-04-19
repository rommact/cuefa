package com.rommac.core_api.mediator

interface MediatorsProvider {
    fun provideGameMediator(): GameMediator
}