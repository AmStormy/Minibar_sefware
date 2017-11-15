package com.Miniber.android

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_room.*

class RoomActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_room)
        button_room.setOnClickListener {
            var room = findViewById<EditText>(R.id.room_number)
            var number =  room.getText().toString()
            if(number.length >= 3){
                var OldString = ""
                val intent = Intent(this@RoomActivity,MainActivity::class.java)
                intent.putExtra("room", number)
                intent.putExtra("OldString", OldString)
                startActivity(intent)
            }
            else Toast.makeText(this, "Please input room number", Toast.LENGTH_LONG).show()
        }
    }
}
