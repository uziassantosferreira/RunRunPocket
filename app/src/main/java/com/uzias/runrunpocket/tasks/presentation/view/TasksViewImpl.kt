package com.uzias.runrunpocket.tasks.presentation.view

import android.app.ProgressDialog
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.widget.Toast
import com.uzias.runrunpocket.R
import com.uzias.runrunpocket.core.presentation.BaseActivity
import com.uzias.runrunpocket.core.presentation.BasePresenter
import com.uzias.runrunpocket.core.presentation.RecyclerViewWithFeedback
import com.uzias.runrunpocket.tasks.di.DaggerTasksComponent
import com.uzias.runrunpocket.tasks.presentation.adapter.TasksAdapter
import com.uzias.runrunpocket.tasks.presentation.listener.TaskListener
import com.uzias.runrunpocket.tasks.presentation.model.PresentationTask
import com.uzias.runrunpocket.tasks.presentation.presenter.TasksPresenter
import kotlinx.android.synthetic.main.taks_view.*
import javax.inject.Inject

class TasksViewImpl: BaseActivity(), TasksView, TaskListener {

    @Inject
    lateinit var taskPresenter: TasksPresenter

    private lateinit var progressDialog: ProgressDialog

    private val tasksAdapter: TasksAdapter by lazy {
        TasksAdapter(this, mutableListOf(), R.layout.list_item_task, this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.taks_view)

        recyclerview_tasks.getRecyclerView().adapter = tasksAdapter
        recyclerview_tasks.getRecyclerView().layoutManager = LinearLayoutManager(this)

        swiperefreshlayout.setOnRefreshListener { taskPresenter.onRefresh() }

    }
    override fun getPresenter(): BasePresenter = taskPresenter

    override fun injectDependencies() {
        DaggerTasksComponent.builder()
                .appComponent(getAppComponent())
                .build()
                .inject(this)
    }

    override fun showList(it: MutableList<PresentationTask>) {
        tasksAdapter.updateDataSet(it)
    }

    override fun showLoading() {
        progressDialog = ProgressDialog.show(this, getString(R.string.app_name),
                getString(R.string.commons_loading), true)
    }

    override fun dismissLoading() {
        progressDialog.dismiss()
    }

    override fun clickedButtonDone(presentationTask: PresentationTask) {
        taskPresenter.clickedButtonDone(presentationTask)
    }

    override fun clickedButtonPlayOrPause(presentationTask: PresentationTask) {
        taskPresenter.clickedButtonPlayOrPause(presentationTask)
    }

    override fun showToastError() {
        Toast.makeText(this, R.string.error_defualt, Toast.LENGTH_LONG).show()
    }

    override fun notifyTasksChanged() {
        tasksAdapter.notifyDataSetChanged()
    }

    override fun removeTask(presentationTask: PresentationTask) {
        tasksAdapter.removeItem(item = presentationTask)
    }

    override fun cleanList() {
        tasksAdapter.removeAll()
        recyclerview_tasks.setState(RecyclerViewWithFeedback.State.LOADING)
    }

    override fun dismissRefresh() {
        swiperefreshlayout.isRefreshing = false
    }

}