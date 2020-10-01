package com.seedtag.sampleapp.ui_samples.picassoDisplay

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.seedtag.sampleapp.R
import com.seedtag.sampleapp.ui_main.MainFragment
import com.seedtag.sampleapp.ui_main.MainViewModel
import com.seedtag.seedtagads.api.SeedtagAdsLayout
import com.seedtag.seedtagads.api.SeedtagAdsManager
import com.squareup.picasso.Picasso

class PicassoDisplayAdFragment : Fragment() {

    companion object {
        fun newInstance() =
            PicassoDisplayAdFragment()
    }

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        val root = inflater.inflate(R.layout.dynamic_display_ad_article_fragment, container, false)

        val btnBack : Button = root.findViewById(R.id.btn_back)
        btnBack.setOnClickListener {
            val transaction = activity?.supportFragmentManager?.beginTransaction()
            transaction?.replace(R.id.container,
                MainFragment()
            )
            transaction?.disallowAddToBackStack()
            transaction?.commit()
        }

        root.findViewById<TextView>(R.id.tv_dynamic_title).text = context?.getText(R.string.picasso_display_sample)

        val url = "https://lucasfh1976.files.wordpress.com/2016/01/mujerquellora.jpg?w=625"
        Picasso.get().load(url).into(root.findViewById(R.id.iv_display_ad) as ImageView)

        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
    }

    override fun onResume() {
        super.onResume()
        initAd()
    }

    private fun initAd(){

        val adUnit: SeedtagAdsLayout = view?.findViewById(R.id.isl_display_ad)!!
        SeedtagAdsManager.createNewPageView()
            .registerReferenceUrl("https://referenceurl.com/article")
            .registerAdunit(adUnit)
            .requestAds()

    }

}