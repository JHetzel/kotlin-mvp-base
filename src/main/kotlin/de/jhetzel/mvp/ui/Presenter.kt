package de.jhetzel.mvp.ui


abstract class Presenter<V : ViewComponent>(
        protected val view: V
)