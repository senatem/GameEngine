package com.fulmirex.game.engine

import kotlinx.coroutines.*
import java.awt.Canvas
import java.awt.Dimension
import java.awt.Graphics2D

class Game(val screenSize: Dimension) : Canvas() {
    var scene: Scene? = null

    val input = Input()

    private var gameLoop: Job? = null

    init {
        size = screenSize
        addKeyListener(input)
    }

    fun play() {
        if (gameLoop != null) return

        gameLoop = GlobalScope.launch {
            var previousIterationTime = System.nanoTime()

            while (isActive) {
                val timePassed = System.nanoTime() - previousIterationTime
                previousIterationTime = System.nanoTime()
                scene?.updateAndDraw(timePassed, bufferStrategy.drawGraphics as Graphics2D)
                bufferStrategy.show()
            }
        }
    }

    fun pause() = runBlocking {
        gameLoop?.cancel()
        gameLoop?.join()
        gameLoop = null
    }
}