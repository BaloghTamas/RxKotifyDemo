package hu.bme.aut.android.kotifydemo.utils

import android.support.test.espresso.Espresso
import android.support.test.espresso.IdlingResource

class ElapsedTimeIdlingResource(private val waitingTime: Long) : IdlingResource {
    private val startTime: Long
    private var resourceCallback: IdlingResource.ResourceCallback? = null

    init {
        this.startTime = System.currentTimeMillis()
    }

    override fun getName(): String {
        return ElapsedTimeIdlingResource::class.java.name + ":" + waitingTime
    }

    override fun isIdleNow(): Boolean {
        val elapsed = System.currentTimeMillis() - startTime
        val idle = elapsed >= waitingTime
        if (idle) {
            resourceCallback!!.onTransitionToIdle()
        }
        return idle
    }

    override fun registerIdleTransitionCallback(resourceCallback: IdlingResource.ResourceCallback) {
        this.resourceCallback = resourceCallback
    }


    interface Listener {
        fun inIdle()
    }

    companion object {
        val WAITING_TIME: Long = 500


        fun waitFor(time: Long, body: () -> Unit) {
            val idlingResource = ElapsedTimeIdlingResource(WAITING_TIME)
            Espresso.registerIdlingResources(idlingResource)
            body.invoke();
            Espresso.unregisterIdlingResources(idlingResource)
        }


        fun waitFor(body: () -> Unit) {
            waitFor(WAITING_TIME, body)
        }
    }
}