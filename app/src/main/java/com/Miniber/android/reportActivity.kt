package com.Miniber.android
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.ListView
import android.widget.TextView
import com.Miniber.android.R.id.button19
import com.Miniber.android.R.id.button22
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_item.*
import kotlinx.android.synthetic.main.activity_report.*
import org.jetbrains.anko.*

import java.text.SimpleDateFormat
import java.util.Calendar
class reportActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_report)
        val room = getIntent().getExtras().getString("room")
        var gg = room
        val OStr = getIntent().getExtras().getString("OldString")
        var am = OStr
        val textView: TextView = findViewById(R.id.textView2)
        textView.setText(gg)
        val us = getIntent().getExtras().getString("user")
        var user = us
        val item = getIntent().getExtras().getString("name")
        var name = item
        val num = getIntent().getExtras().getString("amount")
        var amount = num
        var newStr = name + " (" + amount + ")\n" + am
        val editT: EditText = findViewById(R.id.editText)
        editT.setText(newStr)
        var timE :String
        timE = SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime())
        button.setOnClickListener {
            val intent = Intent(this@reportActivity, MainActivity::class.java)
            intent.putExtra("room", gg)
            intent.putExtra("OldString", newStr)
            intent.putExtra("user", user)
            startActivity(intent)
        }
        button22.setOnClickListener {
            newStr = ""
            val editT: EditText = findViewById(R.id.editText)
            editT.setText(newStr)
        }
        button23.setOnClickListener {
            val intent = Intent(this@reportActivity, RoomActivity::class.java)
            startActivity(intent)
        }
        button19.setOnClickListener {
            val str = newStr
            val dataAry = str.split(" (", ")", "\n")
            //println()
            alert() {
                title = "Comfirm to add item"
                positiveButton("Comfirm") {
                    //val ItemCode = emptyArray<String>()
                    //val NumberOfAmount = emptyArray<String>()
                    var ItemCode: MutableList<String> = mutableListOf<String>()
                    var NumberOfAmount: MutableList<String> = mutableListOf<String>()

                    var j = 0
                    var RT = dataAry.size - 1
                    for (items in dataAry) {
                        if (j == RT) break
                        if (items != "+") {
                            if (j % 3 == 0) ItemCode.add(items)
                            else if (j % 3 == 1) NumberOfAmount.add(items)
                            j += 1
                        }
                    }

                    //gg -> room
                    //ItemCode -> Item
                    //NumberOfAmount -> Amount of Item

                    // .... Add item to database
                    var i = 0
                    //var FF = gg
                    val ref = FirebaseDatabase.getInstance().getReference("ReportRoom").child(gg)
                    for (count in ItemCode) {

                        val ItemID = ref.push().key
                        val items = Item(ItemCode[i], NumberOfAmount[i],timE,user)
                        ref.child(ItemID).setValue(items).addOnCompleteListener {
                            // Toast .....
                        }
                        i += 1
                    }
                    toast("Finish")
                    val intent = Intent(this@reportActivity, RoomActivity::class.java)
                    startActivity(intent)
                }
                negativeButton("CANCLE") { }
            }.show()

        }
    }

}
