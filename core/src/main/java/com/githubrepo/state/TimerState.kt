package com.githubrepo.state

data class TimerState(
    val secondsRemaining: Int? = null,
    val totalSeconds: Int = 30,
    val textWhenStopped: String = "-"
) {
    val displaySeconds: String = (secondsRemaining ?: textWhenStopped).toString()

    // Show 100% if seconds remaining is null
    val progressPercentage: Float = (secondsRemaining ?: totalSeconds) / totalSeconds.toFloat()

    override fun toString(): String = "Seconds Remaining $secondsRemaining, totalSeconds: $totalSeconds, progress: $progressPercentage"

}
