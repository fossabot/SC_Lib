package com.samplecode.lib

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import java.io.File
import java.io.FileOutputStream

/**
 * ##Получает [Bitmap] из файла
 *
 * ###Пример Kotlin:
 * ```
 * val userFile: File = File(context.cacheDir, "user_image.jpg")
 * val bitmap: Bitmap? = userFile.getBitmap()
 * if (bitmap != null) {
 *      findViewById<ImageView>(R.id.userImage).setImageBitmap(bitmap)
 * }
 * ```
 *
 * ###Пример Java:
 * ```
 * File userFile = File(context.cacheDir, "user_image.jpg");
 * Bitmap bitmap = ExtensionsKt.getBitmap(userFile);
 * if (bitmap != null) {
 *      ((ImageView)view.findViewById(R.id.userImage)).setImageBitmap(bitmap);
 * }
 * ```
 */
fun File.getBitmap(): Bitmap? {
    return try {
        BitmapFactory.decodeFile(this.absolutePath)
    } catch (e: Exception) {
        e.printStackTrace()
        null
    }
}

/**
 * ##Записывает [Bitmap] в файл
 *
 * ###Примеры Kotlin:
 * Без использования параметров:
 * ```
 * val userImage: Bitmap = findViewById<ImageView>(R.id.userImage).drawable.toBitmap()
 * val userImageFile: File = File(context.cacheDir, "user_image.jpg")
 * userImageFile.putBitmap(userImage)
 * ```
 *
 * С использованием параметров:
 * ```
 * val userImage: Bitmap = findViewById<ImageView>(R.id.userImage).drawable.toBitmap()
 * val userImageFile: File = File(context.cacheDir, "user_image.jpg")
 * userImageFile.putBitmap(
 *      bitmap = userImage,
 *      compressType = Bitmap.CompressFormat.PNG,
 *      quality = 95
 * )
 * ```
 *
 * ###Пример Java:
 * ```
 * ImageView userImageView = findViewById(R.id.userImage);
 * BitmapDrawable bitmapDrawable = (BitmapDrawable) userImageView.getDrawable();
 * Bitmap userImage = bitmapDrawable.getBitmap();
 * File userImageFile = File(context.cacheDir, "user_image.jpg");
 * ExtensionsKt.putBitmap(userImageFile, userImage, Bitmap.CompressFormat.JPEG, 100);
 * ```
 *
 * @param bitmap Изображение которое нужно сохранить
 * @param compressType Тип сжатия (PNG, JPEG) см. [Bitmap.CompressFormat]
 * @param quality Качество изображения (100 - исходное качество)
 */
fun File.putBitmap(
    bitmap: Bitmap,
    compressType: Bitmap.CompressFormat = Bitmap.CompressFormat.JPEG,
    quality: Int = 100
): Boolean {
    try {
        val out = FileOutputStream(this)
        bitmap.compress(compressType, quality, out)
        out.close()
    } catch (e: Exception) {
        e.printStackTrace()
        return false
    }
    return true
}