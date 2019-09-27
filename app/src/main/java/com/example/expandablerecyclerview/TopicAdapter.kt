package com.example.expandablerecyclerview

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ObjectAnimator
import android.util.SparseBooleanArray
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.AsyncDifferConfig
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_topic_level_1.view.*
import java.util.concurrent.Executors

class TopicAdapter : ListAdapter<Topic, RecyclerView.ViewHolder>(AsyncDifferConfig.Builder(object :
    DiffUtil.ItemCallback<Topic>() {
    override fun areContentsTheSame(oldItem: Topic, newItem: Topic): Boolean {
        return oldItem == newItem
    }

    override fun areItemsTheSame(oldItem: Topic, newItem: Topic): Boolean {
        return oldItem.id == newItem.id
    }
}).setBackgroundThreadExecutor(Executors.newSingleThreadExecutor()).build()) {

    companion object {
        const val LEVEL_1_TYPE = 1
        const val LEVEL_2_TYPE = 2
        const val LEVEL_3_TYPE = 3
    }

    private var topics: List<Topic>? = null
    var level1CollapseItems = SparseBooleanArray()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        when (viewType) {
            LEVEL_1_TYPE -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_topic_level_1, parent, false)
                TopicLevel1ViewHolder(view)
            }
            LEVEL_2_TYPE -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_topic_level_2, parent, false)
                TopicLevel2ViewHolder(view)
            }
            else -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_topic_level_3, parent, false)
                TopicLevel3ViewHolder(view)
            }
        }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is TopicLevel1ViewHolder -> holder.bindView(getItem(position))
            is TopicLevel2ViewHolder -> holder.bindView(getItem(position))
            is TopicLevel3ViewHolder -> holder.bindView(getItem(position))
        }
    }

    override fun getItemViewType(position: Int): Int = when (getItem(position).level) {
        1 -> LEVEL_1_TYPE
        2 -> LEVEL_2_TYPE
        else -> LEVEL_3_TYPE
    }

    override fun submitList(list: MutableList<Topic>?) {
        topics = list?.toList()
        super.submitList(list)
    }

    inner class TopicLevel1ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val textTitle: TextView = view.textTitle
        private val btnCollapseExpand: ImageButton = view.btnCollapseExpand;

        fun bindView(topic: Topic) {
            textTitle.text = topic.title
            btnCollapseExpand.setOnClickListener {
//                ObjectAnimator.ofFloat(btnCollapseExpand, "rotation", 0f, 180f).apply {
//                    addListener(object : Animator.AnimatorListener{
//                        override fun onAnimationRepeat(p0: Animator?) {
//                        }
//
//                        override fun onAnimationEnd(p0: Animator?) {
//
//                        }
//
//                        override fun onAnimationCancel(p0: Animator?) {
//                        }
//
//                        override fun onAnimationStart(p0: Animator?) {
//                        }
//                    })
//                }.start()

                btnCollapseExpand.animate().rotationBy(180.0f)
                if (level1CollapseItems.get(topic.id)) {
                    level1CollapseItems.put(topic.id, false)
//                    btnCollapseExpand.setImageResource(R.drawable.ic_keyboard_arrow_down_black_24dp)
                } else {
                    level1CollapseItems.put(topic.id, true)
//                    btnCollapseExpand.setImageResource(R.drawable.ic_keyboard_arrow_up_black_24dp)
                }
//                notifyItemChanged(adapterPosition)
            }
            if (level1CollapseItems.get(topic.id)) {
                btnCollapseExpand.setImageResource(R.drawable.ic_keyboard_arrow_up_black_24dp)
            } else {
                btnCollapseExpand.setImageResource(R.drawable.ic_keyboard_arrow_down_black_24dp)
            }
        }

    }

    class TopicLevel2ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val textTitle: TextView = view.textTitle

        fun bindView(topic: Topic) {
            textTitle.text = topic.title
        }
    }

    class TopicLevel3ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val textTitle: TextView = view.textTitle

        fun bindView(topic: Topic) {
            textTitle.text = topic.title
        }
    }
}