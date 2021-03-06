package com.attendance.work_assistant.manager;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.widget.Toast;

import com.attendance.work_assistant.base.MyConstant;
import com.attendance.work_assistant.manager.camer.FileStorage;
import com.attendance.work_assistant.utils.common.BitmapUtils;
import com.attendance.work_assistant.utils.common.LogUtils;
import com.attendance.work_assistant.utils.common.ToastUtils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

/**
 * Created by aiodiy on 2017/3/3.
 */

public class PhotoCamerManager {
    private static final String TAG = PhotoCamerManager.class.getSimpleName();
    private Activity clazz;
    private Context context;
    private int type;  //0.头像上传 1. 相机  2.相册
    private Handler handler;

    private String saveCamerUrl; //拍照路径
    private Uri imageUri;
    public String dataUrl;//图片本地路径
    public byte[] dataBytes; //压缩后的图片字节数组
    Uri uritempFile;

    public PhotoCamerManager(Activity clazz, Context context) {
        this.clazz = clazz;
        this.context = context;
    }

    public void takePhotoCamer(final int type, Handler handler) {
        this.type = type;
        this.handler = handler;
        if (type == 1) {
            saveCamerUrl = MyConstant.IM_IMAGE_PATH + System.currentTimeMillis();
            takeCamear();
        } else {
            takePhoto();
        }
    }

    public void takeCamear() {
//        String status = Environment.getExternalStorageState();
//        if (status.equals(Environment.MEDIA_MOUNTED)) {
//            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//            /*清除相机之前存储的照片*/
//            File file = null;
//            file = new File(Environment.getExternalStorageDirectory(), saveCamerUrl);
//            if (file.exists()) {
//                file.delete();
//            }
//            /*指定照片存储路径*/
//            intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(new File(Environment.getExternalStorageDirectory(), saveCamerUrl)));
//            /*裁剪图片宽高*/
//            intent.putExtra("outputX", 100);
//            intent.putExtra("outputY", 200);
//
////            /*调用系统相机拍照返回Intent 为空的处理 */
////            SimpleDateFormat format = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss");
////            String fileName = format.format(new Date());
////            ContentValues values = new ContentValues();
////            values.put(MediaStore.Images.Media.TITLE,fileName);
////            Uri photoUri = clazz.getContentResolver().insert(
////                    MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
////
////            intent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri);
//            clazz.startActivityForResult(intent, MyConstant.RESULT_CAMER);
//        } else {
//            Toast.makeText(context, "没有可用的存储卡", Toast.LENGTH_SHORT).show();
//        }

        File file = new FileStorage().createIconFile();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            imageUri = FileProvider.getUriForFile(clazz, "com.xahaolan.fileprovider", file);//通过FileProvider创建一个content类型的Uri
        } else {
            imageUri = Uri.fromFile(file);
        }
        Intent intent = new Intent();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION); //添加这一句表示对目标应用临时授权该Uri所代表的文件
        }
        intent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);//设置Action为拍照
        intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);//将拍取的照片保存到指定URI
        clazz.startActivityForResult(intent, MyConstant.RESULT_CAMER);
    }

    public void takePhoto() {
        Intent intent = new Intent(Intent.ACTION_PICK, null);
        /*限制上传到服务器的图片类型*/
        intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/jpeg、image/png");
        clazz.startActivityForResult(intent, MyConstant.RESULT_PHOTO);
    }

    public void activityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            /*相机*/
            case MyConstant.RESULT_CAMER:
//                if (resultCode == 0) {
//                    LogUtils.e(TAG, "没有选择图片");
//                    Toast.makeText(context, "没有选择图片", Toast.LENGTH_SHORT).show();
//                    return;
//                }
//                File addHeadTemp = new File(Environment.getExternalStorageDirectory(), saveCamerUrl);
//                String addHeadPath = addHeadTemp.getPath();

//                Message camerMsg = new Message();
//                camerMsg.what = MyConstant.HANDLER_SUCCESS;
//                camerMsg.obj = imageUri;
//                handler.sendMessage(camerMsg);
//                startPhotoZoom(Uri.fromFile(addHeadTemp));// 裁剪
                imageUri = startPhotoZoom(MyConstant.RESULT_TAILER);// 裁剪
                break;
            /*相册*/
            case MyConstant.RESULT_PHOTO:
                if (data == null) {
                    Toast.makeText(context, "没有选择图片", Toast.LENGTH_SHORT).show();
                } else {
                    Uri uri = data.getData();
                    LogUtils.e(TAG, "从相册得到的uri : " + uri);
                    String photoAlbumPath = getAbsolutePath(uri);//从相册得到的uri转换为图片路径
                    if (photoAlbumPath == null || photoAlbumPath.isEmpty()) {
                        Toast.makeText(context, "未在存储中找到该图片", Toast.LENGTH_SHORT).show();
                    } else {
                        if (type == 0) {
                            startPhotoZoom(uri);//裁剪
                        } else {
                            Message photoMsg = new Message();
                            photoMsg.what = MyConstant.HANDLER_SUCCESS;
                            photoMsg.obj = getAbsolutePath(uri);
                            handler.sendMessage(photoMsg);
                        }
                    }
                }

                break;
            /*裁剪*/
            case MyConstant.RESULT_TAILER:
//                if (resultCode == 0) {
//                    LogUtils.e(TAG, "没有选择图片");
//                    Toast.makeText(context, "没有选择图片", Toast.LENGTH_SHORT).show();
//                    return;
//                }
//                if (uritempFile != null) {
//                    setPicToView(uritempFile);
//                }

                Message camerMsg = new Message();
                camerMsg.what = MyConstant.HANDLER_SUCCESS;
                camerMsg.obj = getAbsolutePath(imageUri);
                handler.sendMessage(camerMsg);
                break;
        }

    }
    /**
     * 裁剪图片方法实现
     */
    public Uri startPhotoZoom(int requestCode) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        Uri outPutUri = Uri.fromFile(new FileStorage().createCropFile());
        //sdk>=24
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            intent.setDataAndType(imageUri, "image/*");
            intent.putExtra(MediaStore.EXTRA_OUTPUT, outPutUri);
            intent.putExtra("noFaceDetection", false);//去除默认的人脸识别，否则和剪裁匡重叠
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            intent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
        }else {
            intent.setDataAndType(imageUri, "image/*");
            intent.putExtra(MediaStore.EXTRA_OUTPUT, outPutUri);
        }

        // 设置裁剪
        intent.putExtra("crop", "true");
        // aspectX aspectY 是宽高的比例
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        // outputX outputY 是裁剪图片宽高
        intent.putExtra("outputX", 500);
        intent.putExtra("outputY", 500);
        intent.putExtra("return-data", false);
        intent.putExtra("circleCrop", false);
        intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());// 图片格式
        clazz.startActivityForResult(intent, requestCode);//这里就将裁剪后的图片的Uri返回了
        return outPutUri;
    }
    /**
     * 调用系统裁剪功能
     *
     * @param uri
     */
    public void startPhotoZoom(Uri uri) {
//        LogUtils.e(TAG,"调用系统裁剪功能传入的uri ：" + uri);
//        uritempFile = uri;
        /*调用手机自带裁剪*/
        Intent intent = new Intent("com.android.camera.action.CROP");
//        intent.setDataAndType(uri, "image/jpeg、image/png");
        intent.setDataAndType(uri, "image/*");
        // crop为true是设置在开启的intent中设置显示的view可以剪裁
        intent.putExtra("crop", true);
        /*宽高比列*/
        if (Build.MODEL.contains("HUAWEI")) {
            //华为特殊处理 不然会显示圆
            intent.putExtra("aspectX", 9998);
            intent.putExtra("aspectY", 9999);
        } else {
            intent.putExtra("aspectX", 13);
            intent.putExtra("aspectY", 21);
        }
        /*裁剪图片宽高*/
        intent.putExtra("outputX", 270);
        intent.putExtra("outputY", 480);

        /*
         * 此方法返回的图片只能是小图片（sumsang测试为高宽160px的图片）
         * 故将图片保存在Uri中，调用时将Uri转换为Bitmap，此方法还可解决miui系统不能return data的问题
         */
//        intent.putExtra("return-data", true);

        /*裁剪后，将裁剪的图片保存在Uri中，在onActivityResult()方法中，再提取对应的Uri图片转换为Bitmap使用。
          其实大家直观也能感觉出来，Intent主要用于不同Activity之间通信，是一种动态的小巧的资源占用，类似于Http请求中的GET，
          并不适用于传递图片之类的大数据。于是当A生成一个大数据要传递给B，往往不是通过Intent直接传递，而是在A生成数据的时候
          将数据保存到C，B再去调用C，C相当于一个转换的中间件。*/
        //uritempFile为Uri类变量，实例化uritempFile
        uritempFile = Uri.parse("file://" + "/" + Environment.getExternalStorageDirectory().getPath() + "/"+ System.currentTimeMillis()+".jpg");
        LogUtils.e(TAG, "裁剪之后存储的uri ：" + uritempFile);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, uritempFile);
        intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());

        clazz.startActivityForResult(intent, MyConstant.RESULT_TAILER);
    }

    /**
     * 将uri转换为存储中的路径
     *
     * @param uri
     * @return
     */
    public String getAbsolutePath(final Uri uri) {
        if (null == uri) return null;
        final String scheme = uri.getScheme();
        String data = null;
        if (scheme == null)
            data = uri.getPath();
        else if (ContentResolver.SCHEME_FILE.equals(scheme)) {
            data = uri.getPath();
        } else if (ContentResolver.SCHEME_CONTENT.equals(scheme)) {
            Cursor cursor = context.getContentResolver().query(uri,
                    new String[]{MediaStore.Images.ImageColumns.DATA}, null, null, null);
            if (null != cursor) {
                if (cursor.moveToFirst()) {
                    int index = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
                    if (index > -1) {
                        data = cursor.getString(index);
                    }
                }
                cursor.close();
            }
        }
        return data;
    }

    /**
     * 读取裁剪之后的图片文件字节数组，并转换为Base64string
     */
    public void setPicToView(Uri uriFile) {
        //将Uri图片转换为Bitmap
        Bitmap photo = null;
        photo = BitmapUtils.decodeStreamToBitmap(context, 0, "", "", uriFile, clazz, 1); //压缩图片（比列）
        ByteArrayOutputStream baos = null;
        try {
            baos = new ByteArrayOutputStream();
            if (photo == null) {
                Toast.makeText(context, "没有选择图片", Toast.LENGTH_SHORT).show();
                return;
            }
            photo.compress(Bitmap.CompressFormat.JPEG, 10, baos); //压缩图片（分辨率） 100为不压缩
            baos.flush();
        } catch (IOException e) {
            LogUtils.e(TAG, "图片压缩异常");
            e.printStackTrace();
        }
        dataBytes = baos.toByteArray(); //压缩后的字节数组
        dataUrl = getAbsolutePath(uriFile);//图片路径
        LogUtils.e(TAG, "图片压缩后的图片路径：" + dataUrl);

        if (dataUrl == null || dataUrl.equals("")) {
            ToastUtils.showShort(context, "裁剪后的图片路径为空");
            LogUtils.e(TAG, "裁剪后的图片路径为空");
            return;
        }

//            /*读取文件字节数组，并使用Base64编码*/
//            String dataStr = new String(Base64.encode(dataBytes, 0));
//            dataStr = "data:image/jpeg;base64," + dataStr;
//            LogUtils.e(TAG, "Base64编码后的字节 ：" + dataStr);
        Message photoMsg = new Message();
        photoMsg.what = MyConstant.HANDLER_SUCCESS;
        photoMsg.obj = dataUrl;
        handler.sendMessage(photoMsg);

        /*上传图片后处理bigmap，防止内存溢出*/
        if (photo != null && !photo.isRecycled()) {
            LogUtils.e(TAG, "bitmap回收处理");
            photo.isRecycled(); //回收图片所占的内存
            photo = null;
            System.gc(); //提醒系统及时回收
        }
    }
}
