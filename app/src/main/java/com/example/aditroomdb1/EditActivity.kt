package com.example.aditroomdb1

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.aditroomdb1.Room.Constant
import com.example.aditroomdb1.Room.DbSmksa
import com.example.aditroomdb1.Room.TbSiswa
import kotlinx.android.synthetic.main.activity_edit.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class EditActivity : AppCompatActivity() {

    private val db by lazy { DbSmksa(this) }
    private var siswaId: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit)
        setupView()
        setupListener()

        siswaId = intent.getIntExtra("intent_id",0)
        Toast.makeText(this, siswaId.toString(), Toast.LENGTH_SHORT).show()
    }

    private fun setupView(){
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        val intentType = intent.getIntExtra("intent_type",0)
        when (intentType){
            Constant.TYPE_CREATE -> {
                button_update.visibility = View.GONE
            }
            Constant.TYPE_READ -> {
                button_update.visibility = View.GONE
                getNote()
                button_save.visibility = View.GONE

            }
            Constant.TYPE_UPDATE -> {
                button_save.visibility = View.GONE
                getNote()
            }
        }
    }

    private fun setupListener(){
        button_save.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                db.siswa().addData(
                    TbSiswa(0,edit_nama.text.toString(),edit_Kelas.text.toString(),edit_alamat.text.toString())
                )
                finish()
            }
        }
        button_update.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                db.siswa().UpdateData(
                    TbSiswa(siswaId,edit_nama.text.toString(),edit_Kelas.text.toString(),edit_alamat.text.toString())
                )
                finish()
            }
        }
    }

    private fun getNote(){
        siswaId = intent.getIntExtra("intent_id",0)
        CoroutineScope(Dispatchers.IO).launch {
            var siswas = db.siswa().getData(siswaId)[0]
            edit_nama.setText(siswas.nama)
            edit_Kelas.setText(siswas.kelas)
            edit_alamat.setText(siswas.alamat)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }
}