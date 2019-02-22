package org.fonuhuolian.xpictureutils;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v4.content.FileProvider;

import java.io.File;

import static android.app.Activity.RESULT_OK;
import static org.fonuhuolian.xpictureutils.XPicture.REQUEST_CLIP;

public class XPictureClipUtils {


    /**
     * 图片裁剪的方法
     *
     * @param fragment
     * @param file        要裁剪的图片
     * @param aspectX     裁剪框的 X 方向的比例
     * @param aspectY     裁剪框的 Y 方向的比例
     * @param outputX     返回数据的时候的X像素大小
     * @param outputY     返回数据的时候的Y像素大小
     * @param packageName 包名
     */
    public static void startPhotoZoom(Fragment fragment, File file, int aspectX, int aspectY, int outputX, int outputY, String packageName) {

        //com.android.camera.action.CROP，这个action是调用系统自带的图片裁切功能
        Intent intent = new Intent("com.android.camera.action.CROP");

        // 获取文件的uri
        Uri uri;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            uri = FileProvider.getUriForFile(fragment.getContext(), packageName + ".fileprovider", file);
            //开启临时访问的读和写权限
            intent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION | Intent.FLAG_GRANT_READ_URI_PERMISSION);
        } else {
            uri = Uri.fromFile(file);
        }

        /**
         * 华为手机特殊处理  如果 aspectX = aspectY
         * 裁剪框 是圆形
         */
        if (Build.MANUFACTURER.equals("HUAWEI") && aspectX == aspectY) {
            aspectX = 9998;
            aspectY = 9999;
        }

        intent.setDataAndType(uri, "image/*");//裁剪的图片uri和图片类型
        intent.putExtra("crop", "true");//设置允许裁剪，如果不设置，就会跳过裁剪的过程，还可以设置putExtra("crop", "circle")
        intent.putExtra("aspectX", aspectX);//裁剪框的 X 方向的比例,需要为整数
        intent.putExtra("aspectY", aspectY);//裁剪框的 Y 方向的比例,需要为整数
        intent.putExtra("outputX", outputX);//返回数据的时候的X像素大小。
        intent.putExtra("outputY", outputY);//返回数据的时候的Y像素大小。


        File dir = new File(Environment.getExternalStorageDirectory().getAbsolutePath()
                + File.separator + "temp");

        if (!dir.exists()) {
            dir.mkdirs();
        }

        File tempFile = new File(dir, System.currentTimeMillis() + ".jpg");

        // 裁剪后的图片Uri路径
        Uri clipUri = Uri.fromFile(tempFile);

        //Android 对Intent中所包含数据的大小是有限制的，一般不能超过 1M，否则会使用缩略图 ,所以我们要指定输出裁剪的图片路径
        intent.putExtra(MediaStore.EXTRA_OUTPUT, clipUri);
        intent.putExtra("return-data", false);//是否将数据保留在Bitmap中返回
        intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());//输出格式，一般设为Bitmap格式及图片类型
        intent.putExtra("noFaceDetection", true);//人脸识别功能
        fragment.startActivityForResult(intent, REQUEST_CLIP);//裁剪完成的标识
    }

    public static void startPhotoZoom(Fragment fragment, String fileName, int aspectX, int aspectY, int outputX, int outputY, String packageName) {
        startPhotoZoom(fragment, new File(fileName), aspectX, aspectY, outputX, outputY, packageName);
    }


    /**
     * 图片裁剪的方法
     *
     * @param activity
     * @param file        要裁剪的图片
     * @param aspectX     裁剪框的 X 方向的比例
     * @param aspectY     裁剪框的 Y 方向的比例
     * @param outputX     返回数据的时候的X像素大小
     * @param outputY     返回数据的时候的Y像素大小
     * @param packageName 包名
     */
    public static void startPhotoZoom(Activity activity, File file, int aspectX, int aspectY, int outputX, int outputY, String packageName) {

        //com.android.camera.action.CROP，这个action是调用系统自带的图片裁切功能
        Intent intent = new Intent("com.android.camera.action.CROP");

        // 获取文件的uri
        Uri uri;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            uri = FileProvider.getUriForFile(activity, packageName + ".fileprovider", file);
            //开启临时访问的读和写权限
            intent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION | Intent.FLAG_GRANT_READ_URI_PERMISSION);
        } else {
            uri = Uri.fromFile(file);
        }

        /**
         * 华为手机特殊处理  如果 aspectX = aspectY
         * 裁剪框 是圆形
         */
        if (Build.MANUFACTURER.equals("HUAWEI") && aspectX == aspectY) {
            aspectX = 9998;
            aspectY = 9999;
        }

        intent.setDataAndType(uri, "image/*");//裁剪的图片uri和图片类型
        intent.putExtra("crop", "true");//设置允许裁剪，如果不设置，就会跳过裁剪的过程，还可以设置putExtra("crop", "circle")
        intent.putExtra("aspectX", aspectX);//裁剪框的 X 方向的比例,需要为整数
        intent.putExtra("aspectY", aspectY);//裁剪框的 Y 方向的比例,需要为整数
        intent.putExtra("outputX", outputX);//返回数据的时候的X像素大小。
        intent.putExtra("outputY", outputY);//返回数据的时候的Y像素大小。


        File dir = new File(Environment.getExternalStorageDirectory().getAbsolutePath()
                + File.separator + "temp");

        if (!dir.exists()) {
            dir.mkdirs();
        }

        File tempFile = new File(dir, System.currentTimeMillis() + ".jpg");

        // 裁剪后的图片Uri路径
        Uri clipUri = Uri.fromFile(tempFile);

        //Android 对Intent中所包含数据的大小是有限制的，一般不能超过 1M，否则会使用缩略图 ,所以我们要指定输出裁剪的图片路径
        intent.putExtra(MediaStore.EXTRA_OUTPUT, clipUri);
        intent.putExtra("return-data", false);//是否将数据保留在Bitmap中返回
        intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());//输出格式，一般设为Bitmap格式及图片类型
        intent.putExtra("noFaceDetection", true);//人脸识别功能
        activity.startActivityForResult(intent, REQUEST_CLIP);//裁剪完成的标识
    }

    public static void startPhotoZoom(Activity activity, String fileName, int aspectX, int aspectY, int outputX, int outputY, String packageName) {
        startPhotoZoom(activity, new File(fileName), aspectX, aspectY, outputX, outputY, packageName);
    }

    public static String onActivityResultGetPath(int requestCode, int resultCode, Intent data) {

        if (resultCode == RESULT_OK && requestCode == REQUEST_CLIP) {
            String action = data.getAction();
            Uri parse = Uri.parse(action);
            return parse.getPath();
        }

        return "";
    }
}
