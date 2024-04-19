package com.example.study.step3;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class User {

    private int illegalBehaviorCnt;
    private boolean isIllegalBannedUser;

    public void increaseIllegalBehaviorCnt() {
        illegalBehaviorCnt++;
        if (illegalBehaviorCnt == 3) isIllegalBannedUser = true;
    }

    public void clearIllegalBehaviorCnt() {
        illegalBehaviorCnt = 0;
    }
}
