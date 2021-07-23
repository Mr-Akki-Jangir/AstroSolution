package com.ak_applications.astrosolution


import android.content.res.Configuration
import android.os.Bundle
import android.os.Handler
import android.os.Looper
//import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.card.MaterialCardView
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.navigation.NavigationView
import com.google.android.material.textfield.TextInputEditText
import java.util.*
import javax.mail.*
import javax.mail.internet.InternetAddress
import javax.mail.internet.MimeMessage
import kotlin.collections.ArrayList
import kotlin.math.abs


class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var viewPager2: ViewPager2
    private val sliderHandler = Handler(Looper.getMainLooper())
    private lateinit var card1: MaterialCardView
    lateinit var appBarNavIcon: ActionBarDrawerToggle
    private lateinit var homeDLyt: DrawerLayout

    private lateinit var customFormAlertDialogLayout: View
    private lateinit var customFormDialog: MaterialAlertDialogBuilder



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        when (resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK) {
            Configuration.UI_MODE_NIGHT_YES -> {
                Toast.makeText(this, "Night Mode Enabled",Toast.LENGTH_LONG).show()
            }
            Configuration.UI_MODE_NIGHT_NO -> {
                Toast.makeText(this, "No Night Mode",Toast.LENGTH_LONG).show()
            }
        }

        viewPager2 = findViewById(R.id.h_slider)
        val hNav: NavigationView = findViewById(R.id.hNavView)
        homeDLyt = findViewById(R.id.hDrawerLayout)
        card1 = findViewById(R.id.card1)
        val topAppBar: MaterialToolbar = findViewById(R.id.h_src_topAb)
        val card2: LinearLayout = findViewById(R.id.card2)
        val card3: LinearLayout = findViewById(R.id.card3)
        val card4: LinearLayout = findViewById(R.id.card4)
        val card5: LinearLayout = findViewById(R.id.card5)
        val card6: LinearLayout = findViewById(R.id.card6)
        val card7: LinearLayout = findViewById(R.id.card7)
        val card8: LinearLayout = findViewById(R.id.card8)
        val card9: LinearLayout = findViewById(R.id.card9)



        setSupportActionBar(topAppBar)
        appBarNavIcon = ActionBarDrawerToggle(this, homeDLyt, R.string.NOpen, R.string.NClose)
        appBarNavIcon.syncState()


         card1.setOnClickListener(this)
         card2.setOnClickListener(this)
         card3.setOnClickListener(this)
         card4.setOnClickListener(this)
         card5.setOnClickListener(this)
         card6.setOnClickListener(this)
         card7.setOnClickListener(this)
         card8.setOnClickListener(this)
         card9.setOnClickListener(this)
        customFormDialog = MaterialAlertDialogBuilder(this)

        hNav.setNavigationItemSelectedListener {
            it.isChecked = true
            homeDLyt.closeDrawer(GravityCompat.START)
            true

        }

        val sliderHSliderItem: MutableList<h_slider_item> = ArrayList()
        sliderHSliderItem.add(h_slider_item(R.drawable.a1))
        sliderHSliderItem.add(h_slider_item(R.drawable.a2))
        sliderHSliderItem.add(h_slider_item(R.drawable.slide2))
        sliderHSliderItem.add(h_slider_item(R.drawable.slide3))

        viewPager2.adapter = h_slider_adapter(sliderHSliderItem, viewPager2)

        viewPager2.clipToPadding = false
        viewPager2.clipChildren = false
        viewPager2.offscreenPageLimit = 3
        viewPager2.getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_NEVER

        val compositePageTransformer = CompositePageTransformer()
        compositePageTransformer.addTransformer(MarginPageTransformer(30))
        compositePageTransformer.addTransformer { page, position ->
            val r = 1 - abs(position)
            page.scaleY = 0.85f + r * 0.25f

        }
        viewPager2.setPageTransformer(compositePageTransformer)

        viewPager2.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                sliderHandler.removeCallbacks(sliderRunnable)
                sliderHandler.postDelayed(sliderRunnable, 2000)
            }
        })


    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (appBarNavIcon.onOptionsItemSelected(item))
            return true
        return super.onOptionsItemSelected(item)
    }

    private val sliderRunnable = Runnable {
        viewPager2.currentItem = viewPager2.currentItem + 1

    }

    override fun onPause() {
        super.onPause()
        sliderHandler.postDelayed(sliderRunnable, 2000)
    }

    override fun onResume() {
        super.onResume()
        sliderHandler.postDelayed(sliderRunnable, 2000)
    }

    override fun onClick(v: View?) {
        when (v?.id) {

            R.id.card1 -> {
                customFormAlertDialogLayout =
                    View.inflate(this, R.layout.custom_dialog_form_lyt, null)
//                    LayoutInflater.from(this).inflate(R.layout.custom_dialog_form_lyt, null, false)
                launchCustomFromMaterialDialog()
//                val policy: StrictMode.ThreadPolicy =
//                    StrictMode.ThreadPolicy.Builder().permitAll().build()
//                StrictMode.setThreadPolicy(policy)
            }

            R.id.card2 -> {
                customFormAlertDialogLayout =
                    View.inflate(this, R.layout.custom_dialog_form_lyt, null)
//                    LayoutInflater.from(this).inflate(R.layout.custom_dialog_form_lyt, null, false)
                launchCustomFromMaterialDialog()
//                val policy: StrictMode.ThreadPolicy =
//                    StrictMode.ThreadPolicy.Builder().permitAll().build()
//                StrictMode.setThreadPolicy(policy)
            }

            R.id.card3 -> {
                customFormAlertDialogLayout =
                    View.inflate(this, R.layout.custom_dialog_form_lyt, null)
//                    LayoutInflater.from(this).inflate(R.layout.custom_dialog_form_lyt, null, false)
                launchCustomFromMaterialDialog()
//                val policy: StrictMode.ThreadPolicy =
//                    StrictMode.ThreadPolicy.Builder().permitAll().build()
//                StrictMode.setThreadPolicy(policy)
            }

            R.id.card4 -> {
                customFormAlertDialogLayout =
                    View.inflate(this, R.layout.custom_dialog_form_lyt, null)
//                    LayoutInflater.from(this).inflate(R.layout.custom_dialog_form_lyt, null, false)
                launchCustomFromMaterialDialog()
//                val policy: StrictMode.ThreadPolicy =
//                    StrictMode.ThreadPolicy.Builder().permitAll().build()
//                StrictMode.setThreadPolicy(policy)
            }

            R.id.card5 -> {
                customFormAlertDialogLayout =
                    View.inflate(this, R.layout.custom_dialog_form_lyt, null)
//                    LayoutInflater.from(this).inflate(R.layout.custom_dialog_form_lyt, null, false)
                launchCustomFromMaterialDialog()
//                val policy: StrictMode.ThreadPolicy =
//                    StrictMode.ThreadPolicy.Builder().permitAll().build()
//                StrictMode.setThreadPolicy(policy)
            }

            R.id.card6 -> {
                customFormAlertDialogLayout =
                    View.inflate(this, R.layout.custom_dialog_form_lyt, null)
//                    LayoutInflater.from(this).inflate(R.layout.custom_dialog_form_lyt, null, false)
                launchCustomFromMaterialDialog()
//                val policy: StrictMode.ThreadPolicy =
//                    StrictMode.ThreadPolicy.Builder().permitAll().build()
//                StrictMode.setThreadPolicy(policy)
            }

            R.id.card7 -> {
                customFormAlertDialogLayout =
                    View.inflate(this, R.layout.custom_dialog_form_lyt, null)
//                    LayoutInflater.from(this).inflate(R.layout.custom_dialog_form_lyt, null, false)
                launchCustomFromMaterialDialog()
//                val policy: StrictMode.ThreadPolicy =
//                    StrictMode.ThreadPolicy.Builder().permitAll().build()
//                StrictMode.setThreadPolicy(policy)
            }

            R.id.card8 -> {
                customFormAlertDialogLayout =
                    View.inflate(this, R.layout.custom_dialog_form_lyt, null)
//                    LayoutInflater.from(this).inflate(R.layout.custom_dialog_form_lyt, null, false)
                launchCustomFromMaterialDialog()
//                val policy: StrictMode.ThreadPolicy =
//                    StrictMode.ThreadPolicy.Builder().permitAll().build()
//                StrictMode.setThreadPolicy(policy)
            }

            R.id.card9 -> {
                customFormAlertDialogLayout =
                    View.inflate(this, R.layout.custom_dialog_form_lyt, null)
//                    LayoutInflater.from(this).inflate(R.layout.custom_dialog_form_lyt, null, false)
                launchCustomFromMaterialDialog()
//                val policy: StrictMode.ThreadPolicy =
//                    StrictMode.ThreadPolicy.Builder().permitAll().build()
//                StrictMode.setThreadPolicy(policy)
            }
        }
    }

    private fun launchCustomFromMaterialDialog() {
        val cDFName = customFormAlertDialogLayout.findViewById<TextInputEditText>(R.id.customFormDialogFullName)
        val cDFPhoneNo = customFormAlertDialogLayout.findViewById<TextInputEditText>(R.id.customFormDialogPhoneNo)
        val cDFProblem =
            customFormAlertDialogLayout.findViewById<TextInputEditText>(R.id.customFormDialogProblem)

        customFormDialog.setView(customFormAlertDialogLayout)
            .setPositiveButton("Send") { _, _ ->
                val myMail: String = getString(R.string.FromEmail)
                val myPas: String = getString(R.string.FromEmailPass)
                val uEmail: String =
                    cDFName.text.toString() + cDFPhoneNo.text.toString() + cDFProblem.text.toString()
                val prop = Properties()
                prop["mail.smtp.auth"] = "true"
                prop["mail.smtp.starttls.enable"] = "true"
                prop["mail.smtp.host"] = "smtp.gmail.com"
                prop["mail.smtp.port"] = "587"
                val session =
                    Session.getInstance(prop, object : Authenticator() {
                        override fun getPasswordAuthentication(): PasswordAuthentication {
                            return PasswordAuthentication(myMail, myPas)
                        }
                    })
                try {
                    val message: Message = MimeMessage(session)
                    message.setFrom(InternetAddress(myMail))
                    message.setRecipients(
                        Message.RecipientType.TO,
                        InternetAddress.parse(getString(R.string.ToMail))
                    )
                    message.subject = "Coming From App"
                    message.setText(uEmail)
                    Transport.send(message)
                    Toast.makeText(applicationContext, "Send Success", Toast.LENGTH_LONG)
                        .show()
                } catch (e: MessagingException) {
                    throw RuntimeException(e)
                }
            }


            .setNegativeButton("Cancel") { dialog, _ ->
                dialog.dismiss()
            }.show()
    }

}
