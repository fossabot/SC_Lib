package com.samplecode.lib.async.java;

import java.util.ArrayList;

import kotlinx.coroutines.Job;

public interface AsyncImplJ {

    ArrayList<Job> jobs = new ArrayList<>();

    void newThread(Runnable runnable);

    void newSafeThread(Runnable runnable);

    void clearJobs();
}
