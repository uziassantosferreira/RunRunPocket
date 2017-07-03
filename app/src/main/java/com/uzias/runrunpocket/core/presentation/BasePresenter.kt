package com.uzias.runrunpocket.core.presentation

interface BasePresenter {

    fun attachTo(view: BaseView)

    fun detachFrom(view: BaseView)

}
