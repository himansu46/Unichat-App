package com.example.unichat

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.google.firebase.auth.FirebaseAuth

class Login : AppCompatActivity() {
    private lateinit var mAuth: FirebaseAuth
    private lateinit var edtEmail: EditText
    private lateinit var edtPassword: EditText
    private lateinit var btnLogin: Button
    private lateinit var btnSignUp: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        edtEmail=findViewById(R.id.edt_email)
        edtPassword=findViewById(R.id.edt_password)
        btnLogin=findViewById(R.id.btnLogin)
        btnSignUp=findViewById(R.id.btnSignUp)
        mAuth=FirebaseAuth.getInstance()

        btnSignUp.setOnClickListener{
            val intent= Intent(this,SignUp::class.java)
            startActivity(intent)
        }
        btnLogin.setOnClickListener{

            val email=edtEmail.text.toString()
            val password=edtPassword.text.toString()
            if(email=="" || password==""){
                Toast.makeText(this,"Enter All Details!", Toast.LENGTH_SHORT).show()
            }
            else
            login(email,password);

        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                "default",
                "Channel name",
                NotificationManager.IMPORTANCE_HIGH
            )
            val notificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }
    private fun login(email:String,password:String){
        mAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {


                    val intent= Intent(this@Login,MainActivity::class.java)
                    finish()
                    startActivity(intent)

                    val notificationBuilder = NotificationCompat.Builder(this, "default")
                        .setSmallIcon(R.drawable.notification_icon)
                        .setContentTitle("Login Successful")
                        .setContentText("You have successfully logged in!")
                        .setPriority(NotificationCompat.PRIORITY_HIGH)

                    val notificationManager = NotificationManagerCompat.from(this)
                    notificationManager.notify(1, notificationBuilder.build())



                } else {
                    Toast.makeText(this@Login,"User does not exist", Toast.LENGTH_SHORT).show()

                }
            }
    }
}