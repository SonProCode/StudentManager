package vn.edu.hust.activityexamples

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class EditStudentActivity : AppCompatActivity() {

    private lateinit var nameEditText: EditText
    private lateinit var mssvEditText: EditText
    private var position: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_student)

        val name = intent.getStringExtra("name") ?: ""
        val mssv = intent.getStringExtra("mssv") ?: ""
        position = intent.getIntExtra("position", -1)

        nameEditText = findViewById(R.id.nameEditText)
        mssvEditText = findViewById(R.id.mssvEditText)

        nameEditText.setText(name)
        mssvEditText.setText(mssv)

        findViewById<Button>(R.id.updateButton).setOnClickListener {
            val updatedName = nameEditText.text.toString()
            val updatedMssv = mssvEditText.text.toString()

            val resultIntent = Intent()
            resultIntent.putExtra("name", updatedName)
            resultIntent.putExtra("mssv", updatedMssv)
            resultIntent.putExtra("position", position)
            setResult(RESULT_OK, resultIntent)

            finish()
        }
    }
}
