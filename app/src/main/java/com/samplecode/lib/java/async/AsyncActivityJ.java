package com.samplecode.lib.java.async;

import androidx.appcompat.app.AppCompatActivity;

import com.samplecode.lib.KotlinJavaExtensionsKt;

import kotlinx.coroutines.Job;

public abstract class AsyncActivityJ extends AppCompatActivity implements AsyncImplJ {

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
    protected void onDestroy() {
        clearJobs();
        super.onDestroy();
    }
}
