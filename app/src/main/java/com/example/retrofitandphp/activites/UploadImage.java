package com.example.retrofitandphp.activites;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.retrofitandphp.R;
import com.example.retrofitandphp.api.RetrofitClient;
import com.example.retrofitandphp.models.DefaultResponse;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UploadImage extends AppCompatActivity {

    private int ch;
    private Bitmap bitmap1;
    private Bitmap bitmap2;
    private TextView tvImageOne,tvImageTow;
    private String KEY_IMAGE = "image";
    private String KEY_IMAGE_Tow = "image2";
    private String KEY_NAME = "hosam77";
    private int PICK_IMAGE_REQUEST = 1;
    private ImageView imageOne,imageTow;
    private final String string="Preview  Photo";
    private String imgString,imgStringTow;
    private Button btnUpload;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_image);
        imageOne=findViewById(R.id.img_one);
        imageTow=findViewById(R.id.img_tow);
        tvImageOne=findViewById(R.id.tv_img);
        tvImageTow=findViewById(R.id.tv_img2);
        btnUpload=findViewById(R.id.btn_uplad);
        tvImageOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ch=1;
                showFileChooser();



            }
        });
        tvImageTow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ch=2;
                showFileChooser();
            }
        });
        btnUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploadImage();

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == this.RESULT_OK && data != null && data.getData() != null) {
            Uri filePath = data.getData();
            //Getting the Bitmap from Gallery
//                tvImageOne.setText(string);
            if (ch==1){
                bitmap1=getBitmap(filePath, imageOne,tvImageOne);
            }else if(ch==2){
                bitmap2=getBitmap(filePath, imageTow,tvImageTow);
            }
            //bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), filePath);

            //Setting the Bitmap to ImageView
//                imageOne.setImageBitmap(bitmap);
        }
    }


    private void showFileChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
    }
    public String getStringImage(Bitmap bmp){
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] imageBytes = baos.toByteArray();
        String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
        return encodedImage;
    }
    private Bitmap getBitmap(Uri filePath ,ImageView imageView,TextView textView){
        Bitmap bitmap=null;
        try {
             bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), filePath);
            //Setting the Bitmap to ImageView
            imageView.setImageBitmap(bitmap);
            textView.setText(string);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return bitmap;
    }
    private void uploadImage(){
        if (bitmap1==null){
            Toast.makeText(getApplicationContext(),"bitmap1 null",Toast.LENGTH_LONG).show();
            return;
        }else if (bitmap2==null){
            Toast.makeText(getApplicationContext(),"bitmap2 null",Toast.LENGTH_LONG).show();
            return;
        }else {
            String imgOne=getStringImage(bitmap1);
            String imgTow=getStringImage(bitmap2);
            Call<DefaultResponse> call= RetrofitClient
                    .getInstance()
                    .getApi()
                    .addImage(imgOne,imgTow,"4");
            call.enqueue(new Callback<DefaultResponse>() {
                @Override
                public void onResponse(Call<DefaultResponse> call, Response<DefaultResponse> response) {
                    DefaultResponse responseData=response.body();
                    if (response.code()==200){
                        Toast.makeText(getApplicationContext(),response.body().getMsg(),Toast.LENGTH_LONG).show();
                    }else {
                        Toast.makeText(getApplicationContext(),response.code(),Toast.LENGTH_LONG).show();
                    }
                }

                @Override
                public void onFailure(Call<DefaultResponse> call, Throwable t) {

                }
            });
        }
    }
}
