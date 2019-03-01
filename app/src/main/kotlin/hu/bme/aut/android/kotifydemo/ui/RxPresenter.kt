package hu.bme.aut.android.kotifydemo.ui

import io.reactivex.Flowable
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

open class RxPresenter<S : Screen> : Presenter<S>() {
    private var compositeSubscription: CompositeDisposable? = null

    override fun attachScreen(screen: S) {
        super.attachScreen(screen)
        if (compositeSubscription != null && !compositeSubscription!!.isDisposed) {
            compositeSubscription!!.dispose()
        }
        compositeSubscription = CompositeDisposable()
    }

    override fun detachScreen() {
        if (compositeSubscription != null && !compositeSubscription!!.isDisposed) {
            compositeSubscription!!.dispose()
        }
        super.detachScreen()
    }

    protected fun subscribe(disposable: Disposable) {
        compositeSubscription!!.add(disposable)
    }

    protected fun <T> observable(lambda: () -> T): Flowable<T> =
            Flowable.defer { Flowable.just(lambda()) }

    protected fun <T> single(lambda: () -> T): Single<T> =
            Single.defer { Single.just(lambda()) }
}
