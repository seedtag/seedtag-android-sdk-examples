package com.seedtag.sampleapp.ui_main

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.seedtag.sampleapp.R
import com.seedtag.sampleapp.ui_samples.glideDisplay.GlideDisplayAdFragment
import com.seedtag.sampleapp.ui_samples.inscreenSample.InscreenSampleFragment
import com.seedtag.sampleapp.ui_samples.volleyDisplay.VolleyDisplayAdFragment
import com.seedtag.sampleapp.ui_samples.picassoDisplay.PicassoDisplayAdFragment
import com.seedtag.sampleapp.ui_samples.staticDisplay.StaticDisplayAdFragment

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        val root = inflater.inflate(R.layout.main_fragment, container, false)

        val btnPicassoDisplayAd : Button = root.findViewById(R.id.btn_picasso_display_ad)
        btnPicassoDisplayAd.setOnClickListener {
            goToFragment(PicassoDisplayAdFragment())
        }

        val btnGlideDisplayAd : Button = root.findViewById(R.id.btn_glide_display_ad)
        btnGlideDisplayAd.setOnClickListener {
            goToFragment(GlideDisplayAdFragment())
        }

        val btnStaticDisplayAd : Button = root.findViewById(R.id.btn_static_display_ad)
        btnStaticDisplayAd.setOnClickListener {
            goToFragment(StaticDisplayAdFragment())
        }

        val btnVolleyDisplayAd : Button = root.findViewById(R.id.btn_volley_display_ad)
        btnVolleyDisplayAd.setOnClickListener {
            goToFragment(VolleyDisplayAdFragment())
        }

        val btnInscreenSample : Button = root.findViewById(R.id.btn_inscreen_sample)
        btnInscreenSample.setOnClickListener {
            goToFragment(InscreenSampleFragment())
        }

        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)

    }

    private fun goToFragment(fragment: Fragment) {
        val transaction = activity?.supportFragmentManager?.beginTransaction()
        transaction?.replace(R.id.container,
            fragment
        )
        transaction?.disallowAddToBackStack()
        transaction?.commit()
    }

}