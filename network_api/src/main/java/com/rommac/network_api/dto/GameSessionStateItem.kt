package com.rommac.network_api.dto

import com.rommac.core_api.dto.Action

data class GameSessionStateItem (
    val actions: Array<Action>, val winnerUid: String, val selectedActionUsers: Array<String>
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as GameSessionStateItem

        if (!actions.contentEquals(other.actions)) return false
        if (winnerUid != other.winnerUid) return false

        return true
    }

    override fun hashCode(): Int {
        var result = actions.contentHashCode()
        result = 31 * result + winnerUid.hashCode()
        return result
    }
}