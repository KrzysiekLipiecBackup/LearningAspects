package com.example.learningaspects;

public class Czasownik {
    private boolean mStarred;
    private String mText1;
    private String mText2;
    public Czasownik(boolean starred, String text1, String text2) {
        mStarred = starred;
        mText1 = text1;
        mText2 = text2;
    }

    public void changeImage(boolean star) {
        mStarred = star;
    }

    public boolean getImageResource() {
        return mStarred;
    }
    public String getText1() {
        return mText1;
    }
    public String getText2() {
        return mText2;
    }
}