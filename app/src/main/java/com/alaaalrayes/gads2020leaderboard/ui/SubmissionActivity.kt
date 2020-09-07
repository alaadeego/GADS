package com.alaaalrayes.gads2020leaderboard.ui

import ApiClient
import ApiInterface
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.alaaalrayes.gads2020leaderboard.R
import kotlinx.android.synthetic.main.activity_submission.*
import kotlinx.android.synthetic.main.confirm_dialog.view.*
import retrofit2.Call
import retrofit2.Callback


class SubmissionActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_submission)

        submit_btn.setOnClickListener()
        {

            showMessageBox()
            /*
            var firstName_str = firstName_txt.text.toString().trim()
            var lasttName_str = lastName_txt.text.toString().trim()
            var email_str = email_txt.text.toString().trim()
            var project_str = project_txt.text.toString().trim()

            if(firstName_str.isEmpty() || lasttName_str.isEmpty() || email_str.isEmpty() || project_str.isEmpty())
            {
                Toast.makeText(this,  "please fill all field" , Toast.LENGTH_SHORT).show()

            }
            else {
                submit(firstName_str, lasttName_str, email_str, project_str)
            }

             */
        }

    }

    fun submit(
        firstName_str: String,
        lasttName_str: String,
        email_str: String,
        project_str: String
    ) {
        val apiInterface: ApiInterface = ApiClient().createGoogleDoc()
        val call = apiInterface.Submission(firstName_str, lasttName_str, email_str, project_str)
        call.enqueue(object : Callback<Void> {
            override fun onResponse(
                call: Call<Void>,
                response: retrofit2.Response<Void>?
            ) {

                if (response != null) {
                    if (response.isSuccessful)
                        Toast.makeText(this@SubmissionActivity, "isSuccessful", Toast.LENGTH_SHORT)
                            .show()
                    else
                        Toast.makeText(
                            this@SubmissionActivity,
                            "there is issue1",
                            Toast.LENGTH_SHORT
                        ).show()

                }


            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                Log.wtf("onFailure", t.message)
                Toast.makeText(this@SubmissionActivity, "there is issue", Toast.LENGTH_SHORT).show()

            }
        })
    }

    fun showMessageBox() {

        //Inflate the dialog as custom view
      //  val messageBoxView = LayoutInflater.from(this).inflate(R.layout.confirm_dialog, null)
        val viewGroup = findViewById<ViewGroup>(android.R.id.content)

        val messageBoxView = LayoutInflater.from(this).inflate(R.layout.confirm_dialog ,viewGroup , false)


        //AlertDialogBuilder
        val messageBoxBuilder = AlertDialog.Builder(this).setView(messageBoxView)

        //setting text values
        // messageBoxView.message_box_header.text = "This is message header"
        //messageBoxView.message_box_content.text = "This is message content"

        //show dialog
        val messageBoxInstance = messageBoxBuilder.show()

        //set Listener
        messageBoxView.confirm_btn.setOnClickListener() {
            //close dialog
            messageBoxInstance.dismiss()
        }
    }
}