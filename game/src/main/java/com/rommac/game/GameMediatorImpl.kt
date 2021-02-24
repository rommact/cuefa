package com.rommac.game

import android.content.Context
import android.content.Intent
import com.rommac.core_api.dto.GameSession
import com.rommac.core_api.dto.GameSessionState
import com.rommac.core_api.mediator.GameMediator
import javax.inject.Inject

public class GameMediatorImpl
@Inject constructor() : GameMediator {
    override fun toGame(context: Context, gameSession: GameSession,state: GameSessionState) {
        val intent = Intent(context,GameActivity::class.java)
        GameActivity.putParams(intent, gameSession,state)
        context.startActivity(intent)
    }
}