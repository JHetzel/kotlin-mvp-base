package de.jhetzel.mvp.ui


abstract class Presenter<V : View>(
        protected val view: V
)