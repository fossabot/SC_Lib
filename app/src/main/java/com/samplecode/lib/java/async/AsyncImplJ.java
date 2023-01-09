package com.samplecode.lib.java.async;

import java.util.ArrayList;

import kotlinx.coroutines.Job;

public interface AsyncImplJ {

    ArrayList<Job> jobs = new ArrayList<>();

    void newThread(Runnable runnable);

    void newSafeThread(Runnable runnable);

    void clearJobs();
}
