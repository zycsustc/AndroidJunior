package com.example.myapplication

import android.content.Intent
import android.database.Cursor
import android.os.Bundle
import android.provider.ContactsContract
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

open class MainActivity : AppCompatActivity() {
    private val ADD_CONTACT_REQUEST = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.frame_layout)
    }

    fun onLoginClick(view: View) {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
    }

    fun onAddContactClick(view: View) {
        val intent = Intent(ContactsContract.Intents.Insert.ACTION).apply {
            // Sets the MIME type to match the Contacts Provider
            type = ContactsContract.RawContacts.CONTENT_TYPE
        }

        intent.apply {
            putExtra(ContactsContract.Intents.Insert.NAME, "Tim Allen")

            putExtra(ContactsContract.Intents.Insert.EMAIL, "test@test.com")
            putExtra(
                ContactsContract.Intents.Insert.EMAIL_TYPE,
                ContactsContract.CommonDataKinds.Email.TYPE_WORK
            )
            putExtra(ContactsContract.Intents.Insert.PHONE, "12345678")
            putExtra(
                ContactsContract.Intents.Insert.PHONE_TYPE,
                ContactsContract.CommonDataKinds.Phone.TYPE_WORK
            )
        }

        startActivityForResult(intent, ADD_CONTACT_REQUEST)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        print("get result")

        if (requestCode == ADD_CONTACT_REQUEST && resultCode == RESULT_OK && data != null) {

            print("get result data")

            val cursor: Cursor = contentResolver.query(data.data!!,
                arrayOf("display_name", "data1"), null, null, null)!!

            cursor.moveToNext()

            val contactName: String =
                cursor.getString(cursor.getColumnIndexOrThrow(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME))

            val phoneNum: String =
                cursor.getString(cursor.getColumnIndexOrThrow(ContactsContract.CommonDataKinds.Phone.NUMBER))

            cursor.close()

            Toast.makeText(this, "$contactName $phoneNum", Toast.LENGTH_SHORT).show()
        }

    }
}