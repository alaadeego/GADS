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
import androidx.lifecycle.lifecycleScope
import com.alaaalrayes.gads2020leaderboard.R
import kotlinx.android.synthetic.main.activity_submission.*
import kotlinx.android.synthetic.main.confirm_dialog.view.*
import kotlinx.android.synthetic.main.status_dialog.view.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback


class SubmissionActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_submission)
        customeToolbar()
        submit_btn.setOnClickListener() {
            showMessageBox()

        }

    }

    fun submit(
        firstName_str: String,
        lasttName_str: String,
        email_str: String,
        project_str: String
    ) {
        val apiInterface: ApiInterface = ApiClient().createGoogleDoc()

        lifecycleScope.launch(Dispatchers.IO) {
            try {
                apiInterface.Submission(firstName_str, lasttName_str, email_str, project_str)
                //this line will run when everything is successful
                withContext(Dispatchers.Main) {
                    showStatusBox("Submission Successful", R.drawable.right)
                }
            } catch (exception: Throwable) {
                withContext(Dispatchers.Main) {
                    showStatusBox("Submission not Successful", R.drawable.issue)
                }
            }

        }
//        val call = apiInterface.Submission(firstName_str, lasttName_str, email_str, project_str)
//        call.enqueue(object : Callback<Void> {
//            override fun onResponse(
//                call: Call<Void>,
//                response: retrofit2.Response<Void>?
//            ) {
//
//                if (response != null) {
//                    if (response.isSuccessful)
//                        showStatusBox("Submission Successful" , R.drawable.right)
//
//                    else
//                        showStatusBox("Submission not Successful" , R.drawable.issue)
//
//
//                }
//
//
//            }
//
//            override fun onFailure(call: Call<Void>, t: Throwable) {
//                Log.wtf("onFailure", t.message)
//                Toast.makeText(this@SubmissionActivity, "there is issue", Toast.LENGTH_SHORT).show()
//                showStatusBox("Submission not Successful" , R.drawable.issue)
//
//            }
//        })
    }

    fun checkData() {
        var firstName_str = firstName_txt.text.toString().trim()
        var lasttName_str = lastName_txt.text.toString().trim()
        var email_str = email_txt.text.toString().trim()
        var project_str = project_txt.text.toString().trim()

        if (firstName_str.isEmpty() || lasttName_str.isEmpty() || email_str.isEmpty() || project_str.isEmpty()) {
            Toast.makeText(this, "please fill all field", Toast.LENGTH_SHORT).show()
        } else {
            submit(firstName_str, lasttName_str, email_str, project_str)
        }


    }

    fun showMessageBox() {

        //Inflate the dialog as custom view
        //val messageBoxView = LayoutInflater.from(this).inflate(R.layout.confirm_dialog, null)
        val viewGroup = findViewById<ViewGroup>(android.R.id.content)
        val messageBoxView =
            LayoutInflater.from(this).inflate(R.layout.confirm_dialog, viewGroup, false)

        //AlertDialogBuilder
        val messageBoxBuilder = AlertDialog.Builder(this).setView(messageBoxView)
        //show dialog
        val messageBoxInstance = messageBoxBuilder.show()
        //set Listener
        messageBoxView.confirm_btn.setOnClickListener() {
            checkData()
            messageBoxInstance.dismiss()

        }
        messageBoxView.close_btn.setOnClickListener() {
            //close dialog
            messageBoxInstance.dismiss()
        }
    }

    fun showStatusBox(msg: String, img: Int) {
        //MaterialAlertDialogBuilder

        val viewGroup = findViewById<ViewGroup>(android.R.id.content)
        val StatusBoxView =
            LayoutInflater.from(this).inflate(R.layout.status_dialog, viewGroup, false)
        //AlertDialogBuilder
        val StatusBoxBuilder = AlertDialog.Builder(this).setView(StatusBoxView)
        //setting text values
        StatusBoxView.msg_txt.text = msg
        StatusBoxView.img.setImageResource(img)
        //show dialog
        val messageBoxInstance = StatusBoxBuilder.show()
    }

    fun customeToolbar() {
        //display img in toolbar
        getSupportActionBar()?.setLogo(R.drawable.logo);
        getSupportActionBar()?.setDisplayUseLogoEnabled(true);

        //remove title
        getSupportActionBar()?.setDisplayShowTitleEnabled(false);

        //display back btn
        getSupportActionBar()?.setDisplayHomeAsUpEnabled(true);
        getSupportActionBar()?.setDisplayShowHomeEnabled(true);
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

}