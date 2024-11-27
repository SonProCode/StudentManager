package vn.edu.hust.activityexamples

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class AddStudentActivity : Activity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_add_student)

    val nameEditText: EditText = findViewById(R.id.nameEditText)
    val mssvEditText: EditText = findViewById(R.id.mssvEditText)
    val addButton: Button = findViewById(R.id.addButton)

    addButton.setOnClickListener {
      val name = nameEditText.text.toString()
      val mssv = mssvEditText.text.toString()

      val resultIntent = Intent()
      resultIntent.putExtra("name", name)
      resultIntent.putExtra("mssv", mssv)
      setResult(RESULT_OK, resultIntent)
      finish()
    }
  }
}
