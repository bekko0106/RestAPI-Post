package developer.behzod.retrofit_one.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import developer.behzod.retrofit_one.models.MyToDoGetResponse
import developer.behzod.retrofittwo.databinding.ItemRvBinding

class MyGetTodoAdapter(var list: List<MyToDoGetResponse>) :
    RecyclerView.Adapter<MyGetTodoAdapter.Vh>() {

    lateinit var myToDoGetResponse: MyToDoGetResponse


    inner class Vh(var itemRvBinding: ItemRvBinding) : RecyclerView.ViewHolder(itemRvBinding.root) {
        fun onBind(myToDoGetResponse: MyToDoGetResponse, position: Int) {
            itemRvBinding.tvName.text = myToDoGetResponse.sarlavha
            itemRvBinding.tvText.text = myToDoGetResponse.matn
            itemRvBinding.tvDate.text = myToDoGetResponse.oxirgi_muddat
            itemRvBinding.tvStatus.text = myToDoGetResponse.holat


        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Vh {
        return Vh(
            ItemRvBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    }

    override fun onBindViewHolder(holder: Vh, position: Int) {
        holder.onBind(list[position], position)


    }

    override fun getItemCount(): Int = list.size
}