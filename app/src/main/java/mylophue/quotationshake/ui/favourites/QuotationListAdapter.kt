package mylophue.quotationshake.ui.favourites

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import mylophue.quotationshake.databinding.QuotationItemBinding
import mylophue.quotationshake.ui.model.Quotation

class QuotationListAdapter(private val itemClicked: ItemClicked): ListAdapter<Quotation, QuotationListAdapter.ViewHolder>(QuotationDiff) {
    class ViewHolder(private val binding: QuotationItemBinding, itemClicked: ItemClicked): RecyclerView.ViewHolder(binding.root) {
        init {
            binding.root.setOnClickListener {
                itemClicked.onClick(binding.tvFavAuthor.text.toString())
            }
        }
        fun bind(quotation: Quotation) {
            binding.tvFavQuotation.text = quotation.quotation;
            binding.tvFavAuthor.text = quotation.author;
        }
    }

    interface ItemClicked { fun onClick(author: String) }

    object
    QuotationDiff : DiffUtil.ItemCallback<Quotation>() {
        override fun areItemsTheSame(oldItem: Quotation, newItem: Quotation): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Quotation, newItem: Quotation): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
       return ViewHolder(QuotationItemBinding.inflate(LayoutInflater.from(parent.context), parent, false), itemClicked)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position)) //dibuja cada vista
    }
}