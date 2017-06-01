package hu.bme.aut.android.kotifydemo.di

import javax.inject.Qualifier
import kotlin.annotation.AnnotationRetention.RUNTIME


@Target(AnnotationTarget.FIELD, AnnotationTarget.FUNCTION)
@Qualifier
@Retention(RUNTIME)
annotation class Network
