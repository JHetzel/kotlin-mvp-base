package de.jhetzel.mvp

import de.jhetzel.mvp.data.Service


inline fun <reified T> Environment.getService(): T where T : Service = getService(T::class)