package com.notesdea.startsoundandlrc;

/**
 * Created by notes on 2016/10/8.
 */

public class Lyric {
    private long time;
    private String content;

    public Lyric() {

    }

    public Lyric(long time, String content) {
        this.time = time;
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }
}
