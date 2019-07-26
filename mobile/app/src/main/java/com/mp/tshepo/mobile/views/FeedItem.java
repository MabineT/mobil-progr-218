package com.mp.tshepo.mobile.views;

import com.mp.tshepo.mobile.R;

/**
 * Created by Tshepo on 30/04/2018.
 */

public class FeedItem {
    String title;
    String details;

    String time;


    public FeedItem()
    {
        this.title = "Function";
        this.details = String.valueOf(R.string.calc_details);
        this.time = "4pm";
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}