package com.Miniber.android

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    lateinit var databaseRef: DatabaseReference
    var List_Item: MutableList<String> = mutableListOf<String>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        //val menu: MutableList<Item> = mutableListOf()

        databaseRef = FirebaseDatabase.getInstance().getReference()

        databaseRef.child("Product").addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                for (postSnapshot in snapshot.getChildren()) {
                    val product = postSnapshot.getValue(Product::class.java)
                    //println("##########################" + product!!.product)
                    List_Item.add(product!!.product)
                    // TODO: handle the post
                }
                setText()

            }

            override fun onCancelled(p0: DatabaseError) {}
        })


    }
        //var List_Item = arrayOf("Cashew Nut","Coca Cola Zero","Coca Cola","Dried Jack Fruit","Heineken","Ichitan Lemon","Ichitan Original","M & M","Milk","Perrier","Potato Chips","Singha","Soda Schweooes","Sprite","Tong Garden","Tonic Schweppes","Water","Yen Yen")
        fun setText(){
            var RT = List_Item.size
            //println("##########################"+List_Item)
            //println("##########################"+RT)
            val room = getIntent().getExtras().getString("room")
            var gg = room
            val OStr = getIntent().getExtras().getString("OldString")
            var am = OStr
            val textView: TextView = findViewById(R.id.textView4)
            textView.setText(gg)

            val buttonText1: Button = findViewById(R.id.button1)
            val buttonText2: Button = findViewById(R.id.button2)
            val buttonText3: Button = findViewById(R.id.button3)
            val buttonText4: Button = findViewById(R.id.button4)
            val buttonText5: Button = findViewById(R.id.button5)
            val buttonText6: Button = findViewById(R.id.button6)
            val buttonText7: Button = findViewById(R.id.button7)
            val buttonText8: Button = findViewById(R.id.button8)
            val buttonText9: Button = findViewById(R.id.button9)
            val buttonText10: Button = findViewById(R.id.button10)
            val buttonText11: Button = findViewById(R.id.button11)
            val buttonText12: Button = findViewById(R.id.button12)
            val buttonText13: Button = findViewById(R.id.button13)
            val buttonText14: Button = findViewById(R.id.button14)
            val buttonText15: Button = findViewById(R.id.button15)
            val buttonText16: Button = findViewById(R.id.button16)
            val buttonText17: Button = findViewById(R.id.button17)
            val buttonText18: Button = findViewById(R.id.button18)

            if (RT>0)   buttonText1.setText(List_Item[0])
            if (RT>1)   buttonText2.setText(List_Item[1])
            if (RT>2)   buttonText3.setText(List_Item[2])
            if (RT>3)   buttonText4.setText(List_Item[3])
            if (RT>4)   buttonText5.setText(List_Item[4])
            if (RT>5)   buttonText6.setText(List_Item[5])
            if (RT>6)   buttonText7.setText(List_Item[6])
            if (RT>7)   buttonText8.setText(List_Item[7])
            if (RT>8)   buttonText9.setText(List_Item[8])
            if (RT>9)   buttonText10.setText(List_Item[9])
            if (RT>10)  buttonText11.setText(List_Item[10])
            if (RT>11)  buttonText12.setText(List_Item[11])
            if (RT>12)  buttonText13.setText(List_Item[12])
            if (RT>13)  buttonText14.setText(List_Item[13])
            if (RT>14)  buttonText15.setText(List_Item[14])
            if (RT>15)  buttonText16.setText(List_Item[15])
            if (RT>16)  buttonText17.setText(List_Item[16])
            if (RT>17)  buttonText18.setText(List_Item[17])

            if (RT>0)
                button1.setOnClickListener {
                    val intent = Intent(this@MainActivity,itemActivity::class.java)
                    var te = List_Item[0]
                    intent.putExtra("id", te)
                    intent.putExtra("room", gg)
                    intent.putExtra("OldString", am)
                    startActivity(intent)
                }
            if (RT>1)
                button2.setOnClickListener {
                    val intent = Intent(this@MainActivity,itemActivity::class.java)
                    var te = List_Item[1]
                    intent.putExtra("id", te)
                    intent.putExtra("room", gg)
                    intent.putExtra("OldString", am)
                    startActivity(intent)
                }
            if (RT>2)
                button3.setOnClickListener {
                    val intent = Intent(this@MainActivity,itemActivity::class.java)
                    var te = List_Item[2]
                    intent.putExtra("id", te)
                    intent.putExtra("room", gg)
                    intent.putExtra("OldString", am)
                    startActivity(intent)
                }
            if (RT>3)
                button4.setOnClickListener {
                    val intent = Intent(this@MainActivity,itemActivity::class.java)
                    var te = List_Item[3]
                    intent.putExtra("id", te)
                    intent.putExtra("room", gg)
                    intent.putExtra("OldString", am)
                    startActivity(intent)
                }
            if (RT>4)
                button5.setOnClickListener {
                    val intent = Intent(this@MainActivity,itemActivity::class.java)
                    var te = List_Item[4]
                    intent.putExtra("id", te)
                    intent.putExtra("room", gg)
                    intent.putExtra("OldString", am)
                    startActivity(intent)
                }
            if (RT>5)
                button6.setOnClickListener {
                    val intent = Intent(this@MainActivity,itemActivity::class.java)
                    var te = List_Item[5]
                    intent.putExtra("id", te)
                    intent.putExtra("room", gg)
                    intent.putExtra("OldString", am)
                    startActivity(intent)
                }
            if (RT>6)
                button7.setOnClickListener {
                    val intent = Intent(this@MainActivity,itemActivity::class.java)
                    var te = List_Item[6]
                    intent.putExtra("id", te)
                    intent.putExtra("room", gg)
                    intent.putExtra("OldString", am)
                    startActivity(intent)
                }
            if (RT>7)
                button8.setOnClickListener {
                    val intent = Intent(this@MainActivity,itemActivity::class.java)
                    var te = List_Item[7]
                    intent.putExtra("id", te)
                    intent.putExtra("room", gg)
                    intent.putExtra("OldString", am)
                    startActivity(intent)
                }
            if (RT>8)
                button9.setOnClickListener {
                    val intent = Intent(this@MainActivity,itemActivity::class.java)
                    var te = List_Item[8]
                    intent.putExtra("id", te)
                    intent.putExtra("room", gg)
                    intent.putExtra("OldString", am)
                    startActivity(intent)
                }
            if (RT>9)
                button10.setOnClickListener {
                    val intent = Intent(this@MainActivity,itemActivity::class.java)
                    var te = List_Item[9]
                    intent.putExtra("id", te)
                    intent.putExtra("room", gg)
                    intent.putExtra("OldString", am)
                    startActivity(intent)
                }
            if (RT>10)
                button11.setOnClickListener {
                    val intent = Intent(this@MainActivity,itemActivity::class.java)
                    var te = List_Item[10]
                    intent.putExtra("id", te)
                    intent.putExtra("room", gg)
                    intent.putExtra("OldString", am)
                    startActivity(intent)
                }
            if (RT>11)
                button12.setOnClickListener {
                    val intent = Intent(this@MainActivity,itemActivity::class.java)
                    var te = List_Item[11]
                    intent.putExtra("id", te)
                    intent.putExtra("room", gg)
                    intent.putExtra("OldString", am)
                    startActivity(intent)
                }
            if (RT>12)
                button13.setOnClickListener {
                    val intent = Intent(this@MainActivity,itemActivity::class.java)
                    var te = List_Item[12]
                    intent.putExtra("id", te)
                    intent.putExtra("room", gg)
                    intent.putExtra("OldString", am)
                    startActivity(intent)
                }
            if (RT>13)
                button14.setOnClickListener {
                    val intent = Intent(this@MainActivity,itemActivity::class.java)
                    var te = List_Item[13]
                    intent.putExtra("id", te)
                    intent.putExtra("room", gg)
                    intent.putExtra("OldString", am)
                    startActivity(intent)
                }
            if (RT>14)
                button15.setOnClickListener {
                    val intent = Intent(this@MainActivity,itemActivity::class.java)
                    var te = List_Item[14]
                    intent.putExtra("id", te)
                    intent.putExtra("room", gg)
                    intent.putExtra("OldString", am)
                    startActivity(intent)
                }
            if (RT>15)
                button16.setOnClickListener {
                    val intent = Intent(this@MainActivity,itemActivity::class.java)
                    var te = List_Item[15]
                    intent.putExtra("id", te)
                    intent.putExtra("room", gg)
                    intent.putExtra("OldString", am)
                    startActivity(intent)
                }
            if (RT>16)
                button17.setOnClickListener {
                    val intent = Intent(this@MainActivity,itemActivity::class.java)
                    var te = List_Item[16]
                    intent.putExtra("id", te)
                    intent.putExtra("room", gg)
                    intent.putExtra("OldString", am)
                    startActivity(intent)
                }
            if (RT>17)
                button18.setOnClickListener {
                    val intent = Intent(this@MainActivity,itemActivity::class.java)
                    var te = List_Item[17]
                    intent.putExtra("id", te)
                    intent.putExtra("room", gg)
                    intent.putExtra("OldString", am)
                    startActivity(intent)
                }
        }


}
