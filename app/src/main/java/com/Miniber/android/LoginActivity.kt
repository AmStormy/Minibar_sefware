package com.Miniber.android
import android.content.Intent
import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_login.*
class LoginActivity : AppCompatActivity() {
    var pin_list: MutableList<String> = mutableListOf<String>()
    lateinit var databaseRef: DatabaseReference
    lateinit var pin: String
    var error:Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        pin = ""

        Make_pin_color()
        databaseRef = FirebaseDatabase.getInstance().getReference()
        databaseRef.child("users").addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                for (postSnapshot in snapshot.getChildren()) {
                    val pin = postSnapshot.getValue(User::class.java)
                    pin_list.add(pin!!.pin)
                }
            }
            override fun onCancelled(p0: DatabaseError) {}
        })
        button24.setOnClickListener{
            pin = pin+"1"
            Make_pin_color()
            if(pin.length == 6){
                check_pin()
            }
        }//number 1
        button25.setOnClickListener{
            pin = pin+"2"
            Make_pin_color()
            if(pin.length == 6){
                check_pin()
            }
        }//number 2
        button26.setOnClickListener{
            pin = pin+"3"
            Make_pin_color()
            if(pin.length == 6){
                check_pin()
            }
        }//number 3
        button27.setOnClickListener{
            pin = pin+"4"
            Make_pin_color()
            if(pin.length == 6){
                check_pin()
            }
        }//number 4
        button28.setOnClickListener{
            pin = pin+"5"
            Make_pin_color()
            if(pin.length == 6){
                check_pin()
            }
        }//number 5
        button29.setOnClickListener{
            pin = pin+"6"
            Make_pin_color()
            if(pin.length == 6){
                check_pin()
            }
        }//number 6
        button30.setOnClickListener{
            pin = pin+"7"
            Make_pin_color()
            if(pin.length == 6){
                check_pin()
            }
        }//number 7
        button31.setOnClickListener{
            pin = pin+"8"
            Make_pin_color()
            if(pin.length == 6){
                check_pin()
            }
        }//number 8
        button32.setOnClickListener{
            pin = pin+"9"
            Make_pin_color()
            if(pin.length == 6){
                check_pin()
            }
        }//number 9
        button33.setOnClickListener{
            pin = pin+"0"
            Make_pin_color()
            if(pin.length == 6){
                check_pin()
            }
        }//number 0
        button34.setOnClickListener{
            Del_pin()
            Make_pin_color()
        }//Del
    }
    fun Make_pin_color(){
        if(pin.length == 1){
            textView13.setTextColor(Color.parseColor("#cc0000"))
            textView14.setTextColor(Color.parseColor("#f6dccc"))
            textView11.setTextColor(Color.parseColor("#f6dccc"))
            textView12.setTextColor(Color.parseColor("#f6dccc"))
            textView10.setTextColor(Color.parseColor("#f6dccc"))
            textView8.setTextColor(Color.parseColor("#f6dccc"))
        }
        else if(pin.length == 2){
            textView13.setTextColor(Color.parseColor("#cc0000"))
            textView14.setTextColor(Color.parseColor("#cc0000"))
            textView11.setTextColor(Color.parseColor("#f6dccc"))
            textView12.setTextColor(Color.parseColor("#f6dccc"))
            textView10.setTextColor(Color.parseColor("#f6dccc"))
            textView8.setTextColor(Color.parseColor("#f6dccc"))
        }
        else if(pin.length == 3){
            textView13.setTextColor(Color.parseColor("#cc0000"))
            textView14.setTextColor(Color.parseColor("#cc0000"))
            textView11.setTextColor(Color.parseColor("#cc0000"))
            textView12.setTextColor(Color.parseColor("#f6dccc"))
            textView10.setTextColor(Color.parseColor("#f6dccc"))
            textView8.setTextColor(Color.parseColor("#f6dccc"))
        }
        else if(pin.length == 4){
            textView13.setTextColor(Color.parseColor("#cc0000"))
            textView14.setTextColor(Color.parseColor("#cc0000"))
            textView11.setTextColor(Color.parseColor("#cc0000"))
            textView12.setTextColor(Color.parseColor("#cc0000"))
            textView10.setTextColor(Color.parseColor("#f6dccc"))
            textView8.setTextColor(Color.parseColor("#f6dccc"))
        }
        else if(pin.length == 5){
            textView13.setTextColor(Color.parseColor("#cc0000"))
            textView14.setTextColor(Color.parseColor("#cc0000"))
            textView11.setTextColor(Color.parseColor("#cc0000"))
            textView12.setTextColor(Color.parseColor("#cc0000"))
            textView10.setTextColor(Color.parseColor("#cc0000"))
            textView8.setTextColor(Color.parseColor("#f6dccc"))
        }
        else if(pin.length == 6){
            textView13.setTextColor(Color.parseColor("#cc0000"))
            textView14.setTextColor(Color.parseColor("#cc0000"))
            textView11.setTextColor(Color.parseColor("#cc0000"))
            textView12.setTextColor(Color.parseColor("#cc0000"))
            textView10.setTextColor(Color.parseColor("#cc0000"))
            textView8.setTextColor(Color.parseColor("#cc0000"))
        }
        else{
            textView13.setTextColor(Color.parseColor("#f6dccc"))
            textView14.setTextColor(Color.parseColor("#f6dccc"))
            textView11.setTextColor(Color.parseColor("#f6dccc"))
            textView12.setTextColor(Color.parseColor("#f6dccc"))
            textView10.setTextColor(Color.parseColor("#f6dccc"))
            textView8.setTextColor(Color.parseColor("#f6dccc"))
        }
    }
    fun Del_pin(){
        var text: String
        if(pin.length == 1){
            pin = ""
        }
        else if(pin.length == 2){
            text = pin
            pin = text.substring(0,1)
        }
        else if(pin.length == 3){
            text = pin
            pin = text.substring(0,2)
        }
        else if(pin.length == 4){
            text = pin
            pin = text.substring(0,3)
        }
        else if(pin.length == 5){
            text = pin
            pin = text.substring(0,4)
        }
        else if(pin.length == 6){
            text = pin
            pin = text.substring(0,5)
        }
    }
    fun check_pin(){
        Make_pin_color()
        error = 0
        for(i in pin_list.indices){
            if (pin_list[i] == pin){
                error = 1
                startActivity(Intent(this@LoginActivity, RoomActivity::class.java))
                pin = ""
                //Make_pin_color()
            }
        }
        if(error == 0) {
            Toast.makeText(this, "PIN is wrong", Toast.LENGTH_SHORT).show()
            pin = ""
            //Make_pin_color()
        }
    }
}
