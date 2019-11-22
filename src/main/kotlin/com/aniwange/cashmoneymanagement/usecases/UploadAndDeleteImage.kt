package com.aniwange.cashmoneymanagement.usecases

interface UploadAndDeleteImage {

    fun uploadImage(image:ByteArray): String

    fun deleteImage(imageId:String)
}