package de.jhetzel.mvp

import de.jhetzel.mvp.data.Repository


inline fun <reified T> Environment.getRepository(): T where T : Repository = getRepository(T::class)