package vn.edu.hust.activityexamples

import android.content.Intent
import android.os.Bundle
import android.view.ContextMenu
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

  private val students = mutableListOf<Student>()
  private lateinit var adapter: ArrayAdapter<Student>

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    val listView: ListView = findViewById(R.id.listView)


    adapter = object : ArrayAdapter<Student>(this, R.layout.student_item, students) {
      override fun getView(position: Int, convertView: View?, parent: android.view.ViewGroup): View {
        val view = convertView ?: layoutInflater.inflate(R.layout.student_item, parent, false)

        val student = students[position]

        val studentNameView: TextView = view.findViewById(R.id.student_full_name)
        val studentMSSVView: TextView = view.findViewById(R.id.student_mssv)

        studentNameView.text = "Sinh viên: ${student.name}"
        studentMSSVView.text = "MSSV: ${student.mssv}"

        return view
      }
    }

    listView.adapter = adapter

    registerForContextMenu(listView)

    students.add(Student("Trần Hoàng Sơn", "20210744"))
    students.add(Student("Nguyễn Minh Tú", "20210745"))
    adapter.notifyDataSetChanged()
  }

  override fun onCreateContextMenu(menu: ContextMenu?, v: View?, menuInfo: ContextMenu.ContextMenuInfo?) {
    super.onCreateContextMenu(menu, v, menuInfo)
    menuInflater.inflate(R.menu.context_menu, menu)
  }

  override fun onContextItemSelected(item: MenuItem): Boolean {
    val info = item.menuInfo as AdapterView.AdapterContextMenuInfo
    val selectedStudent = students[info.position]

    when (item.itemId) {
      R.id.edit -> {

        val intent = Intent(this, EditStudentActivity::class.java)
        intent.putExtra("name", selectedStudent.name)
        intent.putExtra("mssv", selectedStudent.mssv)
        intent.putExtra("position", info.position)
        startActivityForResult(intent, 1)
      }
      R.id.remove -> {
        students.removeAt(info.position)
        adapter.notifyDataSetChanged()
        Toast.makeText(this, "Sinh viên đã bị xóa", Toast.LENGTH_SHORT).show()
      }
    }
    return super.onContextItemSelected(item)
  }

  override fun onCreateOptionsMenu(menu: Menu?): Boolean {
    menuInflater.inflate(R.menu.menu_main, menu)
    return super.onCreateOptionsMenu(menu)
  }

  override fun onOptionsItemSelected(item: MenuItem): Boolean {
    when (item.itemId) {
      R.id.menu_add_new -> {
        val intent = Intent(this, AddStudentActivity::class.java)
        startActivityForResult(intent, 2)
      }
    }
    return super.onOptionsItemSelected(item)
  }

  override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
    super.onActivityResult(requestCode, resultCode, data)
    if (resultCode == RESULT_OK) {
      val name = data?.getStringExtra("name") ?: ""
      val mssv = data?.getStringExtra("mssv") ?: ""

      if (requestCode == 1) {
        val position = data?.getIntExtra("position", -1) ?: -1
        if (position != -1) {
          students[position] = Student(name, mssv)
          adapter.notifyDataSetChanged()
        }
      } else if (requestCode == 2) {
        students.add(Student(name, mssv))
        adapter.notifyDataSetChanged()
      }
    }
  }
}
