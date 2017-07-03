package com.uzias.runrunpocket.tasks.presentation.view

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageButton
import android.widget.TextView
import kotlinx.android.synthetic.main.list_item_task.view.*

class TaskViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val titleView: TextView = view.textview_title
    val idView: TextView = view.textview_id
    val playOrPauseView: ImageButton = view.imagebutton_play_or_pause
    val doneView: ImageButton = view.imagebutton_done
}