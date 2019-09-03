package carlos.nicolau.galves.login_mvpc.mvp.provider

import io.reactivex.Scheduler

interface SchedulerProvider {
    fun io(): Scheduler
    fun ui(): Scheduler
}