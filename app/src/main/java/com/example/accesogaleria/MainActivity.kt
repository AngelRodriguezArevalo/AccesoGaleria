package com.example.accesogaleria

import android.content.Intent
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.ImageButton
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import com.example.accesogaleria.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var imagen: ImageButton
    lateinit var binding: ActivityMainBinding

    val pickFoto = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
        val image = it.data?.extras?.get("data") as Bitmap

        binding.imageView.setImageBitmap(image)
    }

    val pickMedia = registerForActivityResult(ActivityResultContracts.PickVisualMedia()){

        uri ->
        if(uri!=null){
            imagen.setImageURI(uri)
        }else{
            //no se ha seleccionado ninguna imagen
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.imageView.setOnClickListener{
            pickMedia.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
        }

        binding.buttonAccederCamara.setOnClickListener {
            pickFoto.launch(Intent(MediaStore.ACTION_IMAGE_CAPTURE))
        }
    }
}