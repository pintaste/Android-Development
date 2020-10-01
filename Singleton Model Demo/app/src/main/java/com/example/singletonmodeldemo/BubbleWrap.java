package com.example.singletonmodeldemo;

public class BubbleWrap {
    private static final int ADD_MORE_BUBBLES = 10;
    private int mBubbles;

    /*
        Singleton Support
     */
    private static BubbleWrap instance;
    public static BubbleWrap getInstance() {
        // Lazy initialization
        if(instance == null) {
            instance = new BubbleWrap();
        }
        return instance;
    }

    public int getBubbles() {
        return mBubbles;
    }

    public void addMoreBubbles() {
        mBubbles = mBubbles + ADD_MORE_BUBBLES;
    }

    public void popBubble() {
        mBubbles--;
    }
}
