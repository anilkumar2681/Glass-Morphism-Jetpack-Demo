package com.team42.glassviewdemo.utils

import android.content.Context
import android.graphics.Bitmap
import android.renderscript.Allocation
import android.renderscript.Element
import android.renderscript.RenderScript
import android.renderscript.ScriptIntrinsicBlur
import androidx.core.graphics.createBitmap

/**
 * Project: GlassViewDemo
 * File: blurBitmap.kt
 * Created By: ANIL KUMAR on 9/25/2025
 * Copyright © 2025 Team42. All rights reserved.
 **/
@Suppress("DEPRECATION")
fun blurBitmap(context: Context, bitmap: Bitmap, radius: Float): Bitmap {
    val output = createBitmap(bitmap.width, bitmap.height)
    val rs = RenderScript.create(context)
    val inputAlloc = Allocation.createFromBitmap(rs, bitmap)
    val outputAlloc = Allocation.createFromBitmap(rs, output)
    val script = ScriptIntrinsicBlur.create(rs, Element.U8_4(rs))
    script.setRadius(radius.coerceIn(1f, 25f))
    script.setInput(inputAlloc)
    script.forEach(outputAlloc)
    outputAlloc.copyTo(output)
    rs.destroy()
    return output
}