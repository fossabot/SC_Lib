package com.samplecode.lib.async.java;

import android.os.Build;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.samplecode.lib.KotlinJavaExtensionsKt;

import org.jetbrains.annotations.NotNull;

import java.io.Serializable;

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

    @SuppressWarnings({"unchecked", "deprecation"})
    @Nullable
    public <T extends Serializable> T getSerializeExtraOrNull(@NotNull String name, @NotNull Class<T> data) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            return this.getIntent().getSerializableExtra(name, data);
        }
        return (T) this.getIntent().getSerializableExtra(name);
    }

    @NotNull
    public <T extends Serializable> T getSerializeExtra(@NotNull String name, @NotNull Class<T> data, @NotNull T defaultValue) {
        T res = getSerializeExtraOrNull(name, data);
        if (res != null) {
            return res;
        }
        return defaultValue;
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
