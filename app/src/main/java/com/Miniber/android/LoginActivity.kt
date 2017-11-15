package com.Miniber.android

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {
    var pin_list: MutableList<String> = mutableListOf<String>()
    lateinit var databaseRef: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        databaseRef = FirebaseDatabase.getInstance().getReference()

        databaseRef.child("users").addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                for (postSnapshot in snapshot.getChildren()) {
                    val pin = postSnapshot.getValue(User::class.java)

                    pin_list.add(pin!!.pin)
                    // TODO: handle the post
                }
                //println(pin_list)



            }
            override fun onCancelled(p0: DatabaseError) {}
        })
        button_login.setOnClickListener {
            var code = findViewById<EditText>(R.id.pin)
            var number =  code.getText().toString()
            var error:Int = 0
            for(i in pin_list.indices){
                if (pin_list[i] == number){
                    error = 1
                }
            }
            if(error == 1)startActivity(Intent(this@LoginActivity, RoomActivity::class.java))
            else Toast.makeText(this, "PIN wrong", Toast.LENGTH_SHORT).show()
        }

    }
}
