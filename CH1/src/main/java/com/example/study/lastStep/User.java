package com.example.study.lastStep;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class User {

    private int illegalBehaviorCnt;
    private boolean isIllegalBannedUser;
    private double gold;

    public void increaseIllegalBehaviorCnt() {
        illegalBehaviorCnt++;
        if (illegalBehaviorCnt == 3) isIllegalBannedUser = true;
    }

    public void clearIllegalBehaviorCnt() {
        illegalBehaviorCnt = 0;
    }

    public void increaseMoney(double earnGold) {
        gold += earnGold;
    }

    public void decreaseMoney(double loseGold) {
        gold -= loseGold;
    }
}
