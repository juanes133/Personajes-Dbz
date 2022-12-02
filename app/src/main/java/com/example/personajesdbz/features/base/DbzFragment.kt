package com.example.personajesdbz.features.base

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.personajesdbz.DbzActivity

open class DbzFragment: Fragment(){
    lateinit var dbzActivity: DbzActivity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dbzActivity = (activity as DbzActivity)
    }
}
