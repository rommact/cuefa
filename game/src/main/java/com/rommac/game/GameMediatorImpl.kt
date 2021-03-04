package com.rommac.game

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.navigation.NavController
import com.rommac.core_api.dto.GameSession
import com.rommac.core_api.dto.GameSessionState
import com.rommac.core_api.mediator.GameMediator
import javax.inject.Inject

public class GameMediatorImpl
@Inject constructor(val navController: NavController) : GameMediator {
     override fun toGame(gameSession: GameSession, state: GameSessionState) {
         var bundle = Bundle()
        GameActivity.putParams(bundle, gameSession,state)
         navController.navigate(R.id.action_sessionFragment_to_gameActivity,bundle)
    }
}