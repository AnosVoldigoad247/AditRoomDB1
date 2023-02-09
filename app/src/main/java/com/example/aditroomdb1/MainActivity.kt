package com.example.aditroomdb1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.aditroomdb1.Room.Constant
import com.example.challengeroom1adhityamz.Room.DbSmksa
import com.example.challengeroom1adhityamz.Room.TbSiswa
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {

    private val db by lazy { DbSmksa(this) }
    lateinit var siswaAdapter: SiswaAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupListener()
        setupRecyclerView()
    }

    override fun onStart() {
        super.onStart()
        loadNote()
    }
    fun setupListener(){
        button_create.setOnClickListener {
            intentEdit(0, Constant.TYPE_CREATE)
        }
    }

    fun loadNote(){
        CoroutineScope(Dispatchers.IO).launch {
            val siswas = db.siswa().getDatas()
            Log.d("MainActivity","dbResponse: $siswas")
            withContext(Dispatchers.Main) {
                siswaAdapter.setData(siswas)
            }
        }
    }

    fun intentEdit(noteId: Int,intentType:Int){
        startActivity(
            Intent(applicationContext,EditActivity::class.java)
                .putExtra("intent_id", noteId)
                .putExtra("intent_type", intentType)
        )

    }

    private fun setupRecyclerView(){
        siswaAdapter = SiswaAdapter(arrayListOf(), object :SiswaAdapter.onAdapterListener{
            override fun onClick(siswa: TbSiswa) {
                intentEdit(siswa.nis,Constant.TYPE_READ)
            }

            override fun onUpdate(siswa: TbSiswa) {
                intentEdit(siswa.nis,Constant.TYPE_UPDATE)
            }

            override fun onDelete(siswa: TbSiswa) {
                deleteDialog(siswa)
            }

        })
        list_siswa.apply {
            layoutManager = LinearLayoutManager(applicationContext)
            adapter = siswaAdapter
        }
    }

    private fun deleteDialog(siswa: TbSiswa){
        val alertDialog = AlertDialog.Builder(this)
        alertDialog.apply {
            setTitle("KONFIRMASI")
            setMessage("Yakin Hapus ${siswa.nama}?")
            setNegativeButton("Batal") { dialogInterface, i ->
                dialogInterface.dismiss()
            }
            setPositiveButton("Hapus") { dialogInterface, i ->
                dialogInterface.dismiss()
                CoroutineScope(Dispatchers.IO).launch {
                    db.siswa().DeleteData(siswa)
                    loadNote()
                }
            }
        }
        alertDialog.show()
    }

}