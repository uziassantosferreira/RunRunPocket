package com.uzias.runrunpocket.core.presentation

import io.reactivex.subjects.PublishSubject

object ObservableItemsLoaded {
    val events: PublishSubject<Int> = PublishSubject.create()

    fun publish(size: Int) {
        events.onNext(size)
    }
}