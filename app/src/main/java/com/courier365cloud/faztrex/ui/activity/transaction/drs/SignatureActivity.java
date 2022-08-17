package com.courier365cloud.faztrex.ui.activity.transaction.drs;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;

import com.courier365cloud.faztrex.R;
import com.courier365cloud.faztrex.baseclass.BaseActivity;
import com.courier365cloud.faztrex.databinding.ActivitySignatureBinding;
import com.courier365cloud.faztrex.helper.PermissionManager;
import com.courier365cloud.faztrex.listener.PermissionGrantedListener;
import com.courier365cloud.faztrex.model.PodModel;
import com.courier365cloud.faztrex.network.model.response.ImageUploadResponse;
import com.courier365cloud.faztrex.network.model.response.drs.Drs;
import com.courier365cloud.faztrex.network.retrofit.ApiService;
import com.courier365cloud.faztrex.network.retrofit.ImageUploadApiClient;
import com.courier365cloud.faztrex.utils.AppConstant;
import com.github.gcacace.signaturepad.views.SignaturePad;
import com.google.gson.Gson;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.Objects;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.courier365cloud.faztrex.utils.AppUtils.castToInteger;
import static com.courier365cloud.faztrex.utils.AppUtils.isEmptyString;

public class SignatureActivity extends BaseActivity implements
        View.OnClickListener,
        PermissionGrantedListener {

    private ActivitySignatureBinding binding;
    private final Context mContext = this;
    private final String TAG = this.getClass().getSimpleName();

    private Integer drsCloseById;
    private String drsCloseName;
    String drsId;
    Drs drsDocket;

    File tmpDir;
    File tmpFile = null;
    Bitmap bitmap;
    String signaturePath;

    private File signatureFile = null;

    @Override
    public Activity setCurrentActivity() {
        return this;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView((Activity) mContext, R.layout.activity_signature);

        drsId = getIntent().getStringExtra(mContext.getResources().getString(R.string.key_drs_id));
        drsDocket = getIntent().getParcelableExtra(mContext.getResources().getString(R.string.key_data_drs_docket));

        // initialize toolbar
        initToolbar(binding.toolbarMain1, "Close Drs");

        if (isEmptyString(drsId) || drsDocket == null) {
            displayLongToast(mContext, "Something went wrong!");
            finish();
        }

        getPreferenceData();

        //   getSpinnerList(AppConstant.SP_DELIVERY_STATUS, null, binding.spinnerDrsClosedBy);

        binding.signaturePad.setOnSignedListener(new SignaturePad.OnSignedListener() {

            @Override
            public void onStartSigning() {
                //Event triggered when the pad is touched
            }

            @Override
            public void onSigned() {
                //Event triggered when the pad is signed

            }

            @Override
            public void onClear() {
                //Event triggered when the pad is cleared
            }
        });


        binding.btnCloseDrs.setOnClickListener(this);
        binding.tvClearDigitalSignature.setOnClickListener(this);

    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_close_drs:
                try {
                    PermissionManager permissionManager = new PermissionManager(mContext);
                    permissionManager.checkMultiplePermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE});
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case R.id.tvClearDigitalSignature:
                binding.signaturePad.clear();
                break;
        }
    }

    @Override
    public void onSinglePermissionGranted(String permissionName) {
    }

    @Override
    public void onMultiplePermissionGranted(String[] permissions) {
        saveFile();
    }

    private void saveFile() {
        try {

            bitmap = Bitmap.createBitmap(binding.signaturePad.getSignatureBitmap());

            signaturePath = setBitmapImageInFile(bitmap);

            uploadSignature();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String setBitmapImageInFile(Bitmap myBitmap) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        myBitmap.compress(Bitmap.CompressFormat.JPEG, 90, bytes);
        File wallpaperDirectory = new File(
                Environment.getExternalStorageDirectory() + "/" + Environment.DIRECTORY_PICTURES);
        // have the object build the directory structure, if needed.
        if (!wallpaperDirectory.exists()) {
            wallpaperDirectory.mkdirs();
            Log.d("hhhhh", wallpaperDirectory.toString());
        }

        try {

            signatureFile = new File(wallpaperDirectory, "sign_" + Calendar.getInstance()
                    .getTimeInMillis() + ".jpg");
            signatureFile.createNewFile();
            FileOutputStream fo = new FileOutputStream(signatureFile);
            fo.write(bytes.toByteArray());
            MediaScannerConnection.scanFile(SignatureActivity.this,
                    new String[]{signatureFile.getPath()},
                    new String[]{"image/jpeg"}, null);
            fo.close();
            Log.d("TAG", "File Saved::--->" + signatureFile.getAbsolutePath());

            return signatureFile.getAbsolutePath();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        return "";
    }

    private void uploadSignature() {

        try {

            // start progress dialog
            startProgressDialog(this, false);

            ApiService apiService = ImageUploadApiClient.createService(ApiService.class, "", "");

            File propertyFile = new File(signaturePath);
            propertyFile = saveBitmapToFile(propertyFile);

            if (propertyFile == null) {
                displayShortToast(mContext, "Make signature again");
                binding.signaturePad.clear();
                return;
            }

            RequestBody fileBody = RequestBody.create(MediaType.parse("text/*, video/*, audio/*, image/*, application/*"), propertyFile);
            MultipartBody.Part selectedFile = MultipartBody.Part.createFormData("file[]", propertyFile.getName(), fileBody);

            PodModel podModel = new PodModel();
            podModel.setDRSId(castToInteger(drsId));
            podModel.setDrsClosedById(prefUserModel.getId());
            //podModel.setUserId(castToInteger(prefUserModel.getId()));

            String requestString = new Gson().toJson(podModel);
            Log.d("TAG", "uploadPod: " + requestString);

            Call<ImageUploadResponse> call = apiService.uploadSignature(selectedFile, requestString);

            call.enqueue(new Callback<ImageUploadResponse>() {

                @Override
                public void onResponse(@NonNull Call<ImageUploadResponse> call, @NonNull Response<ImageUploadResponse> response) {

                    // stop progress dialog
                    stopProgressDialog();

                    // call method to delete files from directory
                    //  deleteRecursive(new File(Environment.getExternalStorageDirectory() + AppConstant.DIR_NAME));

                    if (response.isSuccessful()) {

                        if (signatureFile != null) {

                            String[] projection = {MediaStore.Images.Media._ID};
                            String selection = MediaStore.Images.Media.DATA + " = ?";
                            String[] selectionArgs = new String[]{signatureFile.getAbsolutePath()};

                            Uri queryUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                            ContentResolver contentResolver = getContentResolver();
                            Cursor c = contentResolver.query(queryUri, projection, selection, selectionArgs, null);

                            if (c != null) {
                                if (c.moveToFirst()) {
                                    // We found the ID. Deleting the item via the content provider will also remove the file
                                    long id = c.getLong(c.getColumnIndexOrThrow(MediaStore.Images.Media._ID));
                                    Uri deleteUri = ContentUris.withAppendedId(queryUri, id);
                                    contentResolver.delete(deleteUri, null, null);
                                } else {
                                    displayShortToast(mContext, "File not found.");
                                }
                                c.close();
                            }
                        }

                        printInfoLog(TAG, new Gson().toJson(response.body()));

                        ImageUploadResponse uploadResponse = response.body();

                        if (Objects.requireNonNull(uploadResponse).getStatus().equalsIgnoreCase(AppConstant.STATUS_SUCCESS)) {

                            AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                            builder.setTitle("DRS Closed!");
                            builder.setMessage("DRS Closed successfully");
                            builder.setPositiveButton("OK", (dialog, which) -> {
                                dialog.dismiss();
                                Intent intent = new Intent();
                                setResult(RESULT_OK, intent);
                                finish();
                            });
                            builder.show();
                        }

                    } else {

                        printInfoLog(TAG, new Gson().toJson(response.errorBody()));

                        displayShortToast(mContext, response.message());
                    }
                }

                @Override
                public void onFailure(@NonNull Call<ImageUploadResponse> call, @NonNull Throwable t) {

                    printErrorLog(TAG, t.getLocalizedMessage());

                    // stop progress dialog
                    stopProgressDialog();

                    displayShortToast(mContext, t.getMessage());
                }
            });

            /*} else {

                displayShortToast(mContext, "Status updated successfully");

                Intent mIntent = new Intent();
                setResult(RESULT_OK, mIntent);
                finish();
            }*/

        } catch (Exception e) {
            e.printStackTrace();
            printErrorLog(TAG, e.getLocalizedMessage());
        }
    }

}