package com.ak_applications.astrosolution


import android.os.Bundle
import android.os.Handler
import android.os.StrictMode
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
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


public class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var viewPager2: ViewPager2
    private val sliderHandler = Handler()
    lateinit var card1: MaterialCardView
    lateinit var AppBarNavIcon: ActionBarDrawerToggle
    lateinit var HomeDLyt: DrawerLayout

    private lateinit var CustomFormMaterialDialogLayout: View
    private lateinit var CustomFormMaterialDialog: MaterialAlertDialogBuilder


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        viewPager2 = findViewById(R.id.h_slider)
        val HNav: NavigationView = findViewById(R.id.hNavView)
        HomeDLyt = findViewById(R.id.h_drawerlyt)
        card1 = findViewById(R.id.card1)
        val TopAppBar: MaterialToolbar = findViewById(R.id.h_src_topAb)

        setSupportActionBar(TopAppBar)
        AppBarNavIcon = ActionBarDrawerToggle(this, HomeDLyt, R.string.NOpen, R.string.NClose)
        AppBarNavIcon.syncState()


         card1.setOnClickListener(this)
        CustomFormMaterialDialog = MaterialAlertDialogBuilder(this)

        HNav.setNavigationItemSelectedListener {
            it.isChecked = true
            HomeDLyt.closeDrawer(GravityCompat.START)
            true

        }

        val sliderh_slider_item: MutableList<h_slider_item> = ArrayList()
        sliderh_slider_item.add(h_slider_item(R.drawable.a1))
        sliderh_slider_item.add(h_slider_item(R.drawable.a2))
        sliderh_slider_item.add(h_slider_item(R.drawable.slide2))
        sliderh_slider_item.add(h_slider_item(R.drawable.slide3))

        viewPager2.adapter = h_slider_adapter(sliderh_slider_item, viewPager2)

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
        if (AppBarNavIcon.onOptionsItemSelected(item))
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
                CustomFormMaterialDialogLayout =
                    LayoutInflater.from(this).inflate(R.layout.custom_dialog_form_lyt, null, false)
                LaunchCustomFromMaterialDialog()
//                val policy: StrictMode.ThreadPolicy =
//                    StrictMode.ThreadPolicy.Builder().permitAll().build()
//                StrictMode.setThreadPolicy(policy)
            }
        }
    }

    private fun LaunchCustomFromMaterialDialog() {
        val CDFName = CustomFormMaterialDialogLayout.findViewById<TextInputEditText>(R.id.cfdfn)
        val CDFPhoneNo = CustomFormMaterialDialogLayout.findViewById<TextInputEditText>(R.id.cfdpn)
        val CDFProblem =
            CustomFormMaterialDialogLayout.findViewById<TextInputEditText>(R.id.cfdpblm)

        CustomFormMaterialDialog.setView(CustomFormMaterialDialogLayout)
            .setPositiveButton("Send") { dialog, which ->
                val MyMail: String = getString(R.string.FromEmail)
                val MyPas: String = getString(R.string.FromEmailPass)
                val UEmail: String =
                    CDFName.text.toString() + CDFPhoneNo.text.toString() + CDFProblem.text.toString()
                val prop: Properties = Properties()
                prop.put("mail.smtp.auth", "true")
                prop.put("mail.smtp.starttls.enable", "true")
                prop.put("mail.smtp.host", "smtp.gmail.com")
                prop.put("mail.smtp.port", "587")
                val session =
                    Session.getInstance(prop, object : Authenticator() {
                        override fun getPasswordAuthentication(): PasswordAuthentication {
                            return PasswordAuthentication(MyMail, MyPas)
                        }
                    })
                try {
                    val message: Message = MimeMessage(session)
                    message.setFrom(InternetAddress(MyMail))
                    message.setRecipients(
                        Message.RecipientType.TO,
                        InternetAddress.parse(getString(R.string.ToMail))
                    )
                    message.setSubject("Coming From App")
                    message.setText(UEmail)
                    Transport.send(message)
                    Toast.makeText(applicationContext, "Send Success", Toast.LENGTH_LONG)
                        .show()
                } catch (e: MessagingException) {
                    throw RuntimeException(e)
                }
            }


            .setNegativeButton("Cancel") { dialog, which ->
                dialog.dismiss()
            }.show()
    }

}
