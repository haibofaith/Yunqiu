package com.kball.function.Match.bean;

import java.io.Serializable;

/**
 * Created by user on 2017/3/25.
 * 点球数量
 */

public class PenaltyNumBean implements Serializable {
    private int num;
    private boolean isSelect;

    public PenaltyNumBean(int num, boolean isSelect) {
        this.num = num;
        this.isSelect = isSelect;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public boolean isSelect() {
        return isSelect;
    }

    public void setSelect(boolean select) {
        isSelect = select;
    }
}
