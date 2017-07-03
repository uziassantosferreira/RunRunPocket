package com.uzias.runrunpocket.tasks.presentation.presenter

import com.uzias.runrunpocket.core.presentation.BaseView
import com.uzias.runrunpocket.tasks.domain.usecase.ChangeStatusWorkingTask
import com.uzias.runrunpocket.tasks.domain.usecase.DoneTask
import com.uzias.runrunpocket.tasks.domain.usecase.GetTasks
import com.uzias.runrunpocket.tasks.domain.usecase.RefreshTasks
import com.uzias.runrunpocket.tasks.presentation.mapper.PresentationTaskMapper
import com.uzias.runrunpocket.tasks.presentation.model.PresentationTask
import com.uzias.runrunpocket.tasks.presentation.view.TasksView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class TasksPresenterImpl(val getTasks: GetTasks,
                         val changeStatusWorkingTask: ChangeStatusWorkingTask,
                         val doneTask: DoneTask,
                         val refreshTasks: RefreshTasks)
    : TasksPresenter {

    private lateinit var view: TasksView
    private lateinit var presentationTasks: MutableList<PresentationTask>

    private val compositeDispose: CompositeDisposable = CompositeDisposable()

    override fun attachTo(view: BaseView) {
        this.view = view as TasksView
        onGetTasks()
    }

    override fun detachFrom(view: BaseView) {
    }

    private fun onGetTasks(){
        compositeDispose.add(getTasks.run()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map { PresentationTaskMapper.transformFromMutableList(it.toMutableList(), true) }
                .subscribe({
                    presentationTasks = it
                    view.showList(presentationTasks)
                },{
                    view.showToastError()
                })
        )
    }

    override fun clickedButtonPlayOrPause(presentationTask: PresentationTask) {
        view.showLoading()
        onChangeStatusWorkingTask(presentationTask)
    }

    private fun onChangeStatusWorkingTask(presentationTask: PresentationTask) {
        compositeDispose.add(changeStatusWorkingTask.set(PresentationTaskMapper.transformFrom(presentationTask))
                .run()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    updateTask(presentationTask)
                    view.notifyTasksChanged()
                    view.dismissLoading()
                }, {
                    view.dismissLoading()
                    view.showToastError()
                })
        )
    }

    private fun updateTask(presentationTask: PresentationTask) {
        presentationTask.working = presentationTask.working.not()
        if (presentationTask.working){
            presentationTasks.forEach {
                if (it != presentationTask){
                    it.working = false
                }
            }
        }
    }

    override fun clickedButtonDone(presentationTask: PresentationTask) {
        view.showLoading()
        onDoneTask(presentationTask)
    }

    private fun onDoneTask(presentationTask: PresentationTask) {
        compositeDispose.add(doneTask.set(PresentationTaskMapper.transformFrom(presentationTask))
                .run()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    view.dismissLoading()
                    view.removeTask(presentationTask)
                }, {
                    view.dismissLoading()
                    view.showToastError()
                })
        )
    }

    override fun onRefresh() {
        view.cleanList()
        view.dismissRefresh()
        onCleanList()
    }

    private fun onCleanList() {
        compositeDispose.add(refreshTasks.run()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map { PresentationTaskMapper.transformFromMutableList(it.toMutableList(), true) }
                .subscribe({
                    presentationTasks = it
                    view.showList(presentationTasks)
                },{
                    view.showToastError()
                })
        )
    }

}