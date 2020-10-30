package com.seedtag.sampleapp.ui_samples.inscreenSample

import android.graphics.Bitmap
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.ImageRequest
import com.android.volley.toolbox.Volley
import com.seedtag.sampleapp.R
import com.seedtag.sampleapp.ui_main.MainFragment
import com.seedtag.sampleapp.ui_main.MainViewModel
import com.seedtag.seedtagads.api.SeedtagAdsLayout
import com.seedtag.seedtagads.api.SeedtagAdsManager


class InscreenSampleFragment : Fragment() {

    companion object {
        fun newInstance() =
            InscreenSampleFragment()
    }

    private lateinit var viewModel: InscreenSampleViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        val root = inflater.inflate(R.layout.inscreen_sample_article_fragment, container, false)

        val btnBack : Button = root.findViewById(R.id.btn_back)
        btnBack.setOnClickListener {
            val transaction = activity?.supportFragmentManager?.beginTransaction()
            transaction?.replace(R.id.container,
                MainFragment()
            )
            transaction?.disallowAddToBackStack()
            transaction?.commit()
        }

        root.findViewById<TextView>(R.id.tv_dynamic_title).text = context?.getText(R.string.inscreen_sample)

        val url = "https://upload.wikimedia.org/wikipedia/commons/thumb/2/2d/PikiWiki_Israel_3963_Gan-Shmuel_sg5-_28.jpg/220px-PikiWiki_Israel_3963_Gan-Shmuel_sg5-_28.jpg"
        val im : ImageView = root.findViewById(R.id.iv_display_ad)

        val imageRequest = ImageRequest(url,
            { response -> im.setImageBitmap(
                response
            )
            }, 0, 0, ImageView.ScaleType.CENTER_INSIDE, null,
            { error -> Toast.makeText(
                context,
                error.toString(),
                Toast.LENGTH_LONG
            ).show()
            })

        val requestQueue: RequestQueue = Volley.newRequestQueue(context)
        requestQueue.add(imageRequest)

        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(InscreenSampleViewModel::class.java)
    }

    override fun onResume() {
        super.onResume()
        initAd()
    }

    private fun initAd(){

        val adUnit: SeedtagAdsLayout = view?.findViewById(R.id.isl_display_ad)!!

        val inscreenAdunit: SeedtagAdsLayout = view?.findViewById(R.id.isl_inscreen)!!

        inscreenAdunit.setInscreenAdunit()

        SeedtagAdsManager.createNewPageView()
            .registerReferenceUrl("https://referenceurl.com/article")
            .registerAdunit(adUnit)
            .registerAdunit(inscreenAdunit)
            .requestAds()

    }

}