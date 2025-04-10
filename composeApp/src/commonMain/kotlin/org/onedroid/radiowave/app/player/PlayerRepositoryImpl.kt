package org.onedroid.radiowave.app.player

class PlayerRepositoryImpl(
    private val playerController: PlayerController
) : PlayerRepository {

    override fun play(audioUrl: String) {
        playerController.play(audioUrl)
    }

    override fun pauseResume() {
        playerController.pauseResume()
    }
}