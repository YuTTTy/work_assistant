package com.attendance.work_assistant.manager;

import android.content.Context;
import android.media.AudioFormat;
import android.media.AudioRecord;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.net.Uri;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.attendance.work_assistant.base.MyConstant;
import com.attendance.work_assistant.utils.common.LogUtils;

import java.io.File;
import java.io.IOException;

/**
 * Created by aiodiy on 2017/2/28.
 */

public class VoiceManager {
    private final String TAG = VoiceManager.class.getSimpleName();
    static final int SAMPLE_RATE_IN_HZ = 8000;
    static final int BUFFER_SIZE = AudioRecord.getMinBufferSize(SAMPLE_RATE_IN_HZ,
            AudioFormat.CHANNEL_IN_DEFAULT, AudioFormat.ENCODING_PCM_16BIT);

    private MediaRecorder mMediaRecorder;
    private MediaPlayer mPlayer;
    public static final int MAX_LENGTH = 1000 * 60 * 10;// 最大录音时长1000*60*10;
    private String filePath;

    //    private long startTime;
//    private long endTime;
    public VoiceManager() {
//        this.filePath = "/dev/null";
    }

    public VoiceManager(String fileStr) {
        this.filePath = fileStr;
    }

    /**
     * 录音文件
     *
     */
    public void startRecord() {
        /* ①Initial：实例化MediaRecorder对象 */
        if (mMediaRecorder == null) {
            mMediaRecorder = new MediaRecorder();
        }
        try {
            /* ②setAudioSource/setVedioSource */
            mMediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);// 设置麦克风
            /* ②设置音频文件的编码：AAC/AMR_NB/AMR_MB/Default 声音的（波形）的采样 */
            mMediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.AMR_NB);
            /*
             * ②设置输出文件的格式：THREE_GPP/MPEG-4/RAW_AMR/Default THREE_GPP(3gp格式，H263视频/ARM音频编码)、MPEG-4、RAW_AMR(只支持音频且音频编码要求为AMR_NB)
             */
            mMediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);

            /* ③准备 */
            File newFile = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + MyConstant.IM_VOICE_PATH);
            if (!newFile.exists()) {
                newFile.mkdirs();
            }
            filePath = newFile + "/" + System.currentTimeMillis() + ".amr";
//        filePath = newFile + "/" + "im_voice.amr";
            mMediaRecorder.setOutputFile(filePath);
            mMediaRecorder.setMaxDuration(MAX_LENGTH);
            mMediaRecorder.prepare();
            mMediaRecorder.start();
            LogUtils.e(TAG," start voice ");
        } catch (IOException e) {
            e.printStackTrace();
            Log.e(TAG, "录音 failed ,IOException : " + e.getMessage());
        } catch (IllegalStateException e) {
            e.printStackTrace();
            Log.e(TAG, "录音 failed, IllegalStateException : " + e.getMessage());
        }
    }

    /**
     * 更新话筒状态（获取分贝值）
     */
    private int BASE = 1;
    private int SPACE = 1000;// 间隔取样时间

    public double updateMicStatus() {
        if (mMediaRecorder != null) {
            double ratio = (double) mMediaRecorder.getMaxAmplitude() / BASE;
            Log.e(TAG, "mMediaRecorder.getMaxAmplitude()：" + mMediaRecorder.getMaxAmplitude());
            Log.e(TAG, "ratio ：" + ratio);
            double db = 0;// 分贝
            if (ratio > 1)
                db = 20 * Math.log10(ratio);
            Log.e(TAG, "分贝值：" + db);
            return db;
        }
        return 0;
    }

    /**
     * 停止录音
     */
    public void stopRecord(Handler handler) {
        if (mMediaRecorder != null) {
            //added by ouyang start
            try {
                //下面三个参数必须加，不加的话会奔溃，在mMediaRecorder.stop();
                //报错为：RuntimeException:stop failed
                mMediaRecorder.setOnErrorListener(null);
                mMediaRecorder.setOnInfoListener(null);
                mMediaRecorder.setPreviewDisplay(null);
                mMediaRecorder.stop();
                LogUtils.e(TAG, "录音结束");

                Message message = new Message();
                message.what = 123;
                message.obj = filePath;
                handler.sendMessage(message);
            } catch (IllegalStateException e) {
                // TODO: handle exception
                LogUtils.e("Exception", Log.getStackTraceString(e));
            } catch (RuntimeException e) {
                // TODO: handle exception
                LogUtils.e("Exception", Log.getStackTraceString(e));
            } catch (Exception e) {
                // TODO: handle exception
                LogUtils.e("Exception", Log.getStackTraceString(e));
            }
            //added by ouyang end

//            mMediaRecorder.reset();
            mMediaRecorder.release();
            mMediaRecorder = null;
        } else {
            LogUtils.e(TAG, "停止录音 mMediaRecorder为null");
        }
    }

    /**
     * 播放录音
     *
     * @return
     */
    public void playAudio(Context context) {
        if (filePath == null || filePath.equals("")) {
            LogUtils.e(TAG, "语音网络路径url为空");
            return;
        }
        Uri uri = Uri.parse(filePath);
        mPlayer = MediaPlayer.create(context, uri);
//        try {
//            mPlayer.prepare();
//        } catch (IOException e) {
//            LogUtils.e(TAG,e.getMessage());
//            e.printStackTrace();
//        }
        mPlayer.start();
        Log.e(TAG, "播放开始");
    }

    /**
     * 停止播放录音
     */
    public void stopAudio() {
        if (mPlayer != null) {
            mPlayer.release();
            mPlayer = null;
            Log.e(TAG, "播放结束");
        } else {
            Log.e(TAG, "mPlayer 为空");
        }
    }
}
