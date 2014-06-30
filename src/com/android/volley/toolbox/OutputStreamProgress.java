package com.android.volley.toolbox;

import com.android.volley.Response.ProgressListener;

import java.io.IOException;
import java.io.OutputStream;

public class OutputStreamProgress extends OutputStream {

    // <editor-fold desc="VARIABLES">

    private OutputStream mOutputStream;
    private ProgressListener mProgressListener;
    private long mTotal;
    private long mCurrent;

    // </editor-fold>

    // <editor-fold desc="CONSTRUCTORS">

    public OutputStreamProgress(OutputStream outputStream, ProgressListener progressListener, long total) {
        mProgressListener = progressListener;
        mOutputStream = outputStream;
        mTotal = total;
    }

    // </editor-fold>

    // <editor-fold desc="GETTERS">

    public OutputStream getOutputStream() {
        return mOutputStream;
    }

    // </editor-fold>

    // <editor-fold desc="OUTPUT STREAM IMPLEMENTATION METHODS">

    @Override
    public void write(int oneByte) throws IOException {
        mOutputStream.write(oneByte);
        if (mProgressListener != null) {
            mCurrent += oneByte;
            mProgressListener.onUploadProgress(mCurrent, mTotal);
        }
    }

    @Override
    public void write(byte[] buffer) throws IOException {
        mOutputStream.write(buffer);
        if (mProgressListener != null) {
            mCurrent += buffer.length;
            mProgressListener.onUploadProgress(mCurrent, mTotal);
        }
    }

    @Override
    public void write(byte[] buffer, int offset, int count) throws IOException {
        mOutputStream.write(buffer, offset, count);
        if (mProgressListener != null) {
            mCurrent += count;
            mProgressListener.onUploadProgress(mCurrent, mTotal);
        }
    }

    @Override
    public void close() throws IOException {
        mOutputStream.close();
    }

    @Override
    public void flush() throws IOException {
        mOutputStream.flush();
    }

    // </editor-fold>

}
