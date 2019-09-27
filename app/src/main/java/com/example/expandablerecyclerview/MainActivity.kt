package com.example.expandablerecyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val adapter = TopicAdapter()
//        supportActionBar?.hide()

        recycler.apply {
            this.adapter = adapter
//            itemAnimator = null
        }
        adapter.submitList(getTopic().toMutableList())
    }

    private fun getTopic() = listOf(
        Topic(1, 1, "Đại sảnh"),
        Topic(2, 2, "Thông báo"),
        Topic(3, 2, "Thắc mắc - góp ý"),
        Topic(34, 3, "Thread post sai mục"),
        Topic(31, 2, "Tin tức iNet"),
        Topic(32, 2, "Review sản phẩm"),
        Topic(4, 1, "Máy tính để bàn"),
        Topic(5, 2, "Modding"),
        Topic(6, 2, "Đồ họa máy tính"),
//        Topic(61, 2, "Phần mềm"),
//        Topic(611, 3, "Download"),
//        Topic(612, 3, "Phát triển phần mềm"),
//        Topic(62, 2, "Trường đua"),
        Topic(7, 1, "Games"),
        Topic(71, 2, "Thảo luận chung"),
        Topic(72, 2, "Đế chế"),
        Topic(73, 2, "Garena"),
        Topic(8, 1, "Khu vui chơi giải trí"),
        Topic(9, 2, "Chuyện trò linh tinh"),
        Topic(10, 3, "From f17 with love"),
        Topic(101, 3, "Offline"),
        Topic(102, 3, "Thể dục thể thao"),
        Topic(111, 2, "Phim - Nhạc - Sách"),
        Topic(112, 1, "Phim - XXXX - Sách"),
        Topic(113, 2, "Phim - XXXX - Sách"),
        Topic(114, 3, "Phim - XXXX - Sách")
    )
}
