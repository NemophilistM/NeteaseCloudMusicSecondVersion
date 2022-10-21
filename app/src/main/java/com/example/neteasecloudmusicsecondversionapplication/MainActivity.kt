package com.example.neteasecloudmusicsecondversionapplication

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.example.neteasecloudmusicsecondversionapplication.databinding.ActivityMainBinding
import com.example.neteasecloudmusicsecondversionapplication.view.SearchActivity

class MainActivity : AppCompatActivity() {

    private lateinit var drawerLayout: DrawerLayout
    private lateinit var activityMainBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)
        //修改状态栏
        val window = this.window
        window.statusBarColor = Color.GRAY

        //标题栏初始化
        initToolBar()


        //侧边栏初始化
        initSidebar()

    }

    private fun initSidebar() {


    }

    private fun initToolBar() {
        val toolbar = activityMainBinding.tbSearch
        drawerLayout = activityMainBinding.drawerLayoutBaseActivity
        setSupportActionBar(toolbar)
        val actionBar = supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)
        actionBar?.setHomeAsUpIndicator(R.drawable.ic_hamburger_button)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_base_activity_toolbar, menu)
        return true
    }
    @SuppressLint("NonConstantResourceId")
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.search_toolbar -> {
                val intent = Intent(this, SearchActivity::class.java)
                startActivity(intent)
            }
            android.R.id.home -> drawerLayout.openDrawer(GravityCompat.START)
        }
        return true
    }

}