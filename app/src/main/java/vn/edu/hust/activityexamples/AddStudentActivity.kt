package vn.edu.hust.activityexamples

import android.app.Activity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class AddStudentActivity : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_add_student)

    val editHoten = findViewById<EditText>(R.id.edit_hoten)
    val editMssv = findViewById<EditText>(R.id.edit_mssv)

    findViewById<Button>(R.id.button_ok).setOnClickListener {
      val hoten = editHoten.text.toString()
      val mssv = editMssv.text.toString()

      intent.putExtra("hoten", hoten)
      intent.putExtra("mssv", mssv)

      setResult(Activity.RESULT_OK, intent)
      finish()
    }

    findViewById<Button>(R.id.button_cancel).setOnClickListener {
      setResult(Activity.RESULT_CANCELED)
      finish()
    }
  }
}