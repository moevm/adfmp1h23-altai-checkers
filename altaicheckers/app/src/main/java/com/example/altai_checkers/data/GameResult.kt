package com.example.altai_checkers.data

import io.realm.RealmObject

open class GameResult(
    var player1: String = "",
    var player2: String = "",
    var result: String = ""
) : RealmObject()