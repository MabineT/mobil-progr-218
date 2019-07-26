package com.mp.tshepo.mobile.views;

import com.mp.tshepo.mobile.R;

/**
 * Created by Tshepo on 30/04/2018.
 */

public class RelatedItem {
    String title;
    String details;

    public RelatedItem() {
        this.title = "Title";
        this.details = String.valueOf(R.string.calc_details);
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


}