package com.notesdea.startsoundandlrc;

import android.media.MediaPlayer;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    //显示当前播放的歌词
    private TextView tvLyric;
    private MediaPlayer mMediaPlayer = null;
    //存储歌词内容和时间点
    private ArrayList<Lyric> mLyrics = new ArrayList<>();
    private Handler mHandler;
    //当前播放的Lyrics的Index
    private int mIndexOfLyrics = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvLyric = (TextView) findViewById(R.id.tv_lyric);

        readLyricFromRaw(R.raw.counting_stars_lrc);

        //显示当前播放的歌词
        mHandler = new Handler() {
            @Override
            public void handleMessage(final Message msg) {
                if (msg.what == 0x1233) {
                    tvLyric.setText(mLyrics.get(msg.arg1).getContent());
                }
            }
        };

        play();

    }

    @Override
    protected void onResume() {
        if (!mMediaPlayer.isPlaying()) {
            mMediaPlayer.start();
        }
        //开始同步歌词
        new LrcSyncThread().start();
        super.onResume();
    }


    //播放音乐
    private void play() {
        if (mMediaPlayer == null) {
            mMediaPlayer = MediaPlayer.create(this, R.raw.counting_stars);
            mMediaPlayer.setLooping(true);
            mMediaPlayer.start();
        }
    }

    //读取歌词
    private void readLyricFromRaw(int resid) {
        InputStream input = null;
        InputStreamReader inputReader = null;
        BufferedReader reader = null;
        try {
            input =  getResources().openRawResource(resid);
            inputReader = new InputStreamReader(input, "utf-8");
            reader = new BufferedReader(inputReader);
            String line = "";
            while ((line = reader.readLine()) != null) {
                Lyric lyric = getLyric(line);
                if (lyric != null) {
                    mLyrics.add(lyric);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
                if (inputReader != null) {
                    inputReader.close();
                }
                if (input != null) {
                    input.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    //获取从 line 中获取已解析的 Lyric 对象
    private Lyric getLyric(String line) {
        long time = parseTime(line);
        String lyric = parseLrcContent(line);
        if (time != -1 && lyric != null) {
            return new Lyric(time, lyric);
        }
        return null;
    }

    //解析出歌词内容部分
    private String parseLrcContent(String line) {
        if (!line.matches("\\[[\\d].*")) {
            return null;
        }

        String blankLine = " ";
        String lyric;
        int startIndex = line.indexOf("]") + 1;
        if (line.endsWith("]")) {
            lyric = blankLine;
        } else {
            lyric = line.substring(startIndex);
        }
        return lyric;
    }

    //解析出时间部分，返回以毫秒为单位的时间
    private long parseTime(String line) {
        if (!line.matches("\\[[\\d].*")) {
            return -1;
        }

        String[] arrTime = null;
        int startIndex = line.indexOf("[");
        int endIndex = line.indexOf("]");
        if (startIndex == 0 && endIndex != -1) {
            String strTime = line.substring(startIndex + 1, endIndex);
            arrTime = strTime.split(":");
        }
        if (arrTime != null) {
            return transToMills(arrTime[0], arrTime[1]);
        }
        return -1;
    }

    //把分、秒转换成毫秒
    private long transToMills(String min, String second) {
        int m = Integer.valueOf(min);
        float s = Float.valueOf(second);
        return (long) (m * 60 * 1000 + s * 1000);
    }

    //校正歌词时间
    private void correctTime() {
        int currentPosition = -1;
        if (mMediaPlayer.isPlaying()) {
            currentPosition = mMediaPlayer.getCurrentPosition();
        }

        for (int i = 0; i < mLyrics.size(); i++) {
            long currentTimePoint = mLyrics.get(i).getTime();
            long nextTimePoint = mLyrics.get(i + 1).getTime();
            if (i < mLyrics.size() - 1 && currentPosition >= currentTimePoint && currentPosition <= nextTimePoint) {
                mIndexOfLyrics = i;
            }
            if (i == mLyrics.size() - 1 && currentPosition >= currentTimePoint) {
                mIndexOfLyrics = i;
            }
        }
    }

    @Override
    protected void onPause() {
        mMediaPlayer.pause();
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        mMediaPlayer.stop();
        mMediaPlayer.release();
        mMediaPlayer = null;
        super.onDestroy();
    }

    class LrcSyncThread extends Thread {
        @Override
        public void run() {
            while (mMediaPlayer.isPlaying()) {
                int currentPosition = mMediaPlayer.getCurrentPosition();
                long currentTimePoint = mLyrics.get(mIndexOfLyrics).getTime();
                Log.d("Time", currentPosition + "");
                //校正时间
                if (!(currentPosition >= currentTimePoint &&
                        currentPosition <= mLyrics.get(mIndexOfLyrics + 1).getTime())) {
                    correctTime();
                }
                if ((Math.abs(currentPosition - currentTimePoint)) < 50) {
                    Message msg = new Message();
                    msg.what = 0x1233;
                    msg.arg1 = mIndexOfLyrics;
                    mHandler.sendMessage(msg);
                    mIndexOfLyrics++;
                }

                if (mIndexOfLyrics == mLyrics.size()) {
                    mIndexOfLyrics = 0;
                }
            }
        }
    }
}
