package com.seedtag.sampleapp.ui_samples.inScreenExample

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.seedtag.sampleapp.R
import com.seedtag.sampleapp.ui_main.MainFragment
import com.seedtag.sampleapp.ui_main.MainViewModel
import com.seedtag.seedtagads.api.SeedtagAdsLayout
import com.seedtag.seedtagads.api.SeedtagAdsManager
import com.seedtag.seedtagads.internal.models.SeedtagAdsContext
import com.squareup.picasso.Picasso

class InScreenAdFragment : Fragment() {

    private var inscreenadUnit: SeedtagAdsLayout? = null

    companion object {
        fun newInstance() =
            InScreenAdFragment()
    }

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        val root = inflater.inflate(R.layout.inscreen_example_fragment, container, false)

        val btnBack : Button = root.findViewById(R.id.btn_back)
        btnBack.setOnClickListener {
            val transaction = activity?.supportFragmentManager?.beginTransaction()
            transaction?.replace(
                R.id.container,
                MainFragment()
            )
            transaction?.disallowAddToBackStack()
            transaction?.commit()
        }

        root.findViewById<TextView>(R.id.tv_dynamic_title).text = context?.getText(R.string.picasso_display_sample)

        val url = "https://lucasfh1976.files.wordpress.com/2016/01/mujerquellora.jpg?w=625"
        Picasso.get().load(url).into(root.findViewById(R.id.iv_display_ad) as ImageView)

        inscreenadUnit = root.findViewById(R.id.isl_inscreen)
        inscreenadUnit!!.setInscreenAdunit()

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
            .setSeedtagAdsContext(
                SeedtagAdsContext(
                requireContext(),
                "d2391f22-fc60-4160-9f33-894f0343decf",
                false,
                null,
                "5f8973d1b7f86d0600aa2196"
            )
            )
            .registerReferenceUrl("https://referenceurl.com/article")
            .registerAdunit(adUnit)
            .registerAdunit(inscreenadUnit!!, false)
            .requestAds()

    }

}