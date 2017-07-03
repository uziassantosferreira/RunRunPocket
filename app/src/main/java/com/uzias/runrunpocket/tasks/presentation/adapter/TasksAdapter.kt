package com.uzias.runrunpocket.tasks.presentation.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import com.uzias.runrunpocket.R
import com.uzias.runrunpocket.core.presentation.BaseAdapter
import com.uzias.runrunpocket.tasks.presentation.listener.TaskListener
import com.uzias.runrunpocket.tasks.presentation.model.PresentationTask
import com.uzias.runrunpocket.tasks.presentation.view.TaskViewHolder

class TasksAdapter(
        context: Context,
        data: MutableList<PresentationTask>,
        layout: Int,
        val taskListener: TaskListener
) : BaseAdapter<PresentationTask>(context, data, layout) {

    override fun getViewHolder(view: View) = TaskViewHolder(view)

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is TaskViewHolder) {
            val item = getItem(position)
            item?.let {
                with(holder){
                    titleView.text = item.title
                    idView.text = item.id.toString()
                    playOrPauseView.setImageResource(if (item.working) R.drawable.ic_play
                    else R.drawable.ic_pause)

                    playOrPauseView.setOnClickListener{ taskListener.clickedButtonPlayOrPause(item) }
                    doneView.setOnClickListener{ taskListener.clickedButtonDone(item) }
                }
            }
        }
    }
}