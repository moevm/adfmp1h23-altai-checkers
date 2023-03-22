package com.example.altai_checkers.data

import io.realm.RealmObject

open class GameSettings(
    var player1: String = "",
    var player2: String = "",
    var time: Int = 0,
    var addition: Int = 0
) : RealmObject()