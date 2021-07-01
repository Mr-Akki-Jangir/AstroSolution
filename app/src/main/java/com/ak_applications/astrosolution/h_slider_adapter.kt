package com.ak_applications.astrosolution

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.makeramen.roundedimageview.RoundedImageView

class h_slider_adapter internal constructor
    (
       sliderh_slider_item: MutableList<h_slider_item>,
        viewpager: ViewPager2
    ): RecyclerView.Adapter<h_slider_adapter.hSliderViewHold>()


{
    private val sliderh_slider_item: List<h_slider_item>
    private val viewpager2: ViewPager2

    init {
        this.sliderh_slider_item = sliderh_slider_item
        this.viewpager2 = viewpager
    }

    class hSliderViewHold(itemview: View) : RecyclerView.ViewHolder(itemview)
    {
        private val imageView: RoundedImageView = itemView.findViewById(R.id.hImageSlide)

        fun image(sliderh_slider_item: h_slider_item)
        {
            imageView.setImageResource(sliderh_slider_item.Image)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): hSliderViewHold {
        return hSliderViewHold(
            LayoutInflater.from(parent.context).inflate(R.layout.h_slide_item_container, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return sliderh_slider_item.size
    }

    override fun onBindViewHolder(holder: hSliderViewHold, position: Int) {
        holder.image(sliderh_slider_item[position])
        if (position == sliderh_slider_item.size - 2)
        {
            viewpager2.post(runnable)
        }
    }

    private  val runnable = Runnable {
        sliderh_slider_item.addAll(sliderh_slider_item)
        notifyDataSetChanged()
    }
}