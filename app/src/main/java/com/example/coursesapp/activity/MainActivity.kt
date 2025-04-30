package com.example.coursesapp.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.core.ui.theme.CoursesAppTheme
import com.example.coursesapp.navigation.CoursesAppNavGraph
import org.koin.android.ext.android.inject

class MainActivity : ComponentActivity() {

    private val presenter: MainPresenter by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CoursesAppTheme {
                CoursesAppNavGraph(
                    startScreen = presenter.getStartScreen()
                )
            }
        }
    }
}