package com.fulmirex.game.engine

import com.fulmirex.game.engine.GameFactory
import com.fulmirex.game.scene.MainMenuScene
import java.awt.Dimension

fun main(args: Array<String>) {
    val screenSize = Dimension(660,660)
    val game = GameFactory.create(screenSize)
    game.scene = MainMenuScene(game)
    game.play()
}