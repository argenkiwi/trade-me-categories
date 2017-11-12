package com.trademe.leandro.trademecategories

import android.arch.lifecycle.ViewModel
import com.trademe.leandro.trademecategories.data.Category
import io.reactivex.subjects.BehaviorSubject
import io.reactivex.subjects.Subject

/**
 * Created by Leandro on 13/11/2017.
 */
class MainViewModel : ViewModel() {
    val category: Subject<Category> = BehaviorSubject.create()
}