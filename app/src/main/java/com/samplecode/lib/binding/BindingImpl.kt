package com.samplecode.lib.binding

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding

interface FragmentBindingImpl<VB : ViewBinding> {

    fun getViewBinding(inflater: LayoutInflater, container: ViewGroup?): VB
}

interface ActivityBindingImpl<VB : ViewBinding> {

    fun getViewBinding(): VB
}