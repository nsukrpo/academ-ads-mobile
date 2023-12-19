package nsu.krpo.academads.ui.create_ad.photos_rv

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import nsu.krpo.academads.databinding.ItemPhotoBinding

class PhotoAdapter(
    private val onButtonClicked: (Int) -> Unit
) : Adapter<PhotoViewHolder>() {

    var items: List<ItemPhotoWrapper> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder {
        return PhotoViewHolder(
            ItemPhotoBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {
        holder.bind(items[position], position, onButtonClicked)
    }

    override fun getItemCount(): Int = items.size
}