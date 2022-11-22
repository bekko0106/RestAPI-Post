package developer.behzod.retrofit_one

import android.app.AlertDialog
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import developer.behzod.retrofit_one.adapters.MyGetTodoAdapter
import developer.behzod.retrofit_one.models.MyToDoGetResponse
import developer.behzod.retrofit_one.models.MyToDoPostRequest
import developer.behzod.retrofit_one.retrofit.APIClient
import developer.behzod.retrofittwo.R
import developer.behzod.retrofittwo.databinding.ActivityMainBinding
import developer.behzod.retrofittwo.databinding.ItemDialogBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    lateinit var myGetTodoAdapter: MyGetTodoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        getData()


    }

    private fun getData() {
        APIClient.getRetrofitService().getAllTodo()
            .enqueue(object : Callback<List<MyToDoGetResponse>> {
                override fun onResponse(
                    call: Call<List<MyToDoGetResponse>>,
                    response: Response<List<MyToDoGetResponse>>
                ) {
                    if (response.isSuccessful) {

                        myGetTodoAdapter = MyGetTodoAdapter(response.body()!!)
                        binding.myRv.adapter = myGetTodoAdapter
                    }

                }

                override fun onFailure(call: Call<List<MyToDoGetResponse>>, t: Throwable) {
                    Toast.makeText(this@MainActivity, "Internet bilan muammo", Toast.LENGTH_SHORT)
                        .show()
                }

            })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.my_menu, menu)

        return super.onCreateOptionsMenu(menu)
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        postData()
        return super.onOptionsItemSelected(item)
    }

    private fun postData() {
        val dialog = AlertDialog.Builder(this).create()
        val itemDialogBinding = ItemDialogBinding.inflate(layoutInflater)
        dialog.setView(itemDialogBinding.root)

        itemDialogBinding.apply {
            btnSave.setOnClickListener {
                val myToDoPostRequest = MyToDoPostRequest(
                    spinnerHolat.selectedItem.toString(),
                    edtMatn.text.toString(),
                    edtMuddat.text.toString(),
                    edtTitle.text.toString()
                )

                APIClient.getRetrofitService().addToDo(myToDoPostRequest)
                    .enqueue(object : Callback<MyToDoGetResponse> {
                        override fun onResponse(
                            call: Call<MyToDoGetResponse>,
                            response: Response<MyToDoGetResponse>
                        ) {
                            if (response.isSuccessful) {
                                Toast.makeText(
                                    this@MainActivity,
                                    "${response.body()?.sarlavha}  Saqlandi",
                                    Toast.LENGTH_SHORT
                                ).show()
                                dialog.cancel()
                                getData()
                            }

                        }

                        override fun onFailure(call: Call<MyToDoGetResponse>, t: Throwable) {
                            Toast.makeText(this@MainActivity, "Saqlanmadi ", Toast.LENGTH_SHORT)
                                .show()
                        }

                    })

            }

        }

        dialog.show()
    }


}