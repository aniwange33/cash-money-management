package com.aniwange.cashmoneymanagement.usecases.impl

import com.aniwange.cashmoneymanagement.usecases.UploadAndDeleteImage
import com.cloudinary.Cloudinary
import com.cloudinary.utils.ObjectUtils
import javassist.NotFoundException
import org.springframework.stereotype.Service

@Service
class UploadAndDeleteImageImpl: UploadAndDeleteImage {
    private  val apiKey = "627735892586734"
    private  val cloudName = "vela-business-solutions"
    private  val apiSecret = "c8U-EXOtADXd1aNbPX2mkOGCKyE"
    override fun uploadImage(image: ByteArray): String {

/*
{
 public_id: 'sample',
 version: '1312461204',
 width: 864,
 height: 564,
 format: 'jpg',
 created_at: '2017-08-10T09:55:32Z',
 resource_type: 'image',
 tags: [],
 bytes: 9597,
 type: 'upload',
 etag: 'd1ac0ee70a9a36b14887aca7f7211737',
 url: 'http://res.cloudinary.com/demo/image/upload/v1312461204/sample.jpg',
 secure_url: 'https://res.cloudinary.com/demo/image/upload/v1312461204/sample.jpg',
 signature: 'abcdefgc024acceb1c1baa8dca46717137fa5ae0c3',
 original_filename: 'sample'
}
 */
        val cloudinary = cloudinaryConfig()
        val response = cloudinary.uploader().upload(image, ObjectUtils.emptyMap() )
        val imageUrl = response["url"].toString()
        val publicId = response["public_id"].toString()
        val  signature = response["signature"].toString()
        val  resourceType = response["resource_type"].toString()
        val size = (image.size/1024).toLong()
        println(" id: $publicId sizeKilobyte: $size  resourceType: $resourceType")
        println(response.toString())
        return  imageUrl

    }

    override fun deleteImage(imageId: String) {
         val cloudinary = cloudinaryConfig()
         val res = cloudinary.uploader().destroy(imageId, ObjectUtils.emptyMap())
         if(res["result"] != "ok") throw (NotFoundException("No image found"))
    }

    private fun cloudinaryConfig(): Cloudinary {
        val cloudinary = Cloudinary()
        cloudinary.config.apiKey = apiKey
        cloudinary.config.cloudName = cloudName
        cloudinary.config.apiSecret = apiSecret
        return cloudinary
    }
}