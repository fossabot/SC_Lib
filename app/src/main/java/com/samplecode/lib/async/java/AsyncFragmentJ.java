package com.samplecode.lib.async.java;

import androidx.fragment.app.Fragment;

import com.samplecode.lib.KotlinJavaExtensionsKt;

import kotlinx.coroutines.Job;

public abstract class AsyncFragmentJ extends Fragment implements AsyncImplJ {

    @Override
    public void newThread(Runnable runnable) {
        Job job = KotlinJavaExtensionsKt.newThread(runnable);
        KotlinJavaExtensionsKt.onEnd(job, () -> {
            try {
                jobs.remove(job);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        jobs.add(job);
    }

    @Override
    public void newSafeThread(Runnable runnable) {
        Job job = KotlinJavaExtensionsKt.newSafeThread(runnable);
        KotlinJavaExtensionsKt.onEnd(job, () -> {
            try {
                jobs.remove(job);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        jobs.add(job);
    }

    @Override
    public void clearJobs() {
        if (jobs.isEmpty()) {
            return;
        }
        for (Job job : jobs) {
            try {
                job.cancel(null);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        jobs.clear();
    }

    @Override
    public void onDestroy() {
        clearJobs();
        super.onDestroy();
    }
}
