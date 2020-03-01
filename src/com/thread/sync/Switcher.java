package com.thread.sync;

public class Switcher {
    private volatile boolean on;

    public boolean isOn() {
        return on;
    }

    public void setOn(boolean on) {
        this.on = on;
    }
}

/*
加入volatile后，Java在操作对应变量时插入特殊的指令，保证读写到内存的最新值，而非缓存的值
 */