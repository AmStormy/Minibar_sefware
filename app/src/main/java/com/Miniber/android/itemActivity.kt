package com.Miniber.android

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_item.*
import kotlinx.android.synthetic.main.activity_report.*

class itemActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item)
        val item = getIntent().getExtras().getString("id")
        var I = item
        val OStr = getIntent().getExtras().getString("OldString")
        var am = OStr
        val textView: TextView = findViewById(R.id.textView6)
        textView.setText(I)

        val room = getIntent().getExtras().getString("room")
        var gg = room



        button20.setOnClickListener {
            val intent = Intent(this@itemActivity,MainActivity::class.java)
            intent.putExtra("room", gg)
            intent.putExtra("OldString", am)
            startActivity(intent)
         }
        button21.setOnClickListener {
            var num = findViewById<EditText>(R.id.editText2)
            var amount =  num.getText().toString()
            //Toast.makeText(this, room.text, Toast.LENGTH_SHORT).show()
            if(amount.length >= 1){
                val intent = Intent(this@itemActivity,reportActivity::class.java)
                intent.putExtra("name", I)
                intent.putExtra("amount", amount)
                intent.putExtra("room", gg)
                intent.putExtra("OldString", am)
                startActivity(intent)
            }
            else Toast.makeText(this, "Please input data", Toast.LENGTH_LONG).show()
        }
    }
}
