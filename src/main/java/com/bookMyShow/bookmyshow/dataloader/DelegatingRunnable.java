package com.bookMyShow.bookmyshow.dataloader;

import com.bookMyShow.bookmyshow.userfilter.UserContext;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DelegatingRunnable implements Runnable{
    private final Runnable realRunnable;
    private final String userId;

    @Override
    public void run() {
        UserContext.setUserThreadId(userId);
        realRunnable.run();
    }
}
