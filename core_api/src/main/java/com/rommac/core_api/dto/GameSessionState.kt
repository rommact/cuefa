package com.rommac.core_api.dto

import java.io.Serializable

data class GameSessionState (val actions: Array<Action>, val winnerUid: String?): Serializable {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as GameSessionState

        if (!actions.contentEquals(other.actions)) return false
        if (winnerUid != other.winnerUid) return false

        return true
    }

    override fun hashCode(): Int {
        var result = actions.contentHashCode()
        result = 31 * result + (winnerUid?.hashCode() ?: 0)
        return result
    }
}