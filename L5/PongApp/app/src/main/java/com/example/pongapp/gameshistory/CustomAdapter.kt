package com.example.pongapp.gameshistory

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.pongapp.R
import com.example.pongapp.pong.GameRecord
import java.text.SimpleDateFormat

class CustomAdapter(var dataSet: ArrayList<GameRecord>) :
    RecyclerView.Adapter<CustomAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val gameDateTextView: TextView = view.findViewById(R.id.gameDateTextView)
        val gameScoreTextView: TextView = view.findViewById(R.id.gameScoreTextView)
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.game_history_row, viewGroup, false)
        return ViewHolder(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.gameDateTextView.text = SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(dataSet[position].time)
        viewHolder.gameScoreTextView.text = "${dataSet[position].scorePlayerOne} - ${dataSet[position].scorePlayerTwo}"
    }

    override fun getItemCount() = dataSet.size

}