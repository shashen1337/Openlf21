/*
 * Decompiled with CFR 0.152.
 */
package lf21.exception;

import java.io.PrintStream;
import java.io.PrintWriter;

public class a
extends RuntimeException {
    public a() {
        this.setStackTrace(new StackTraceElement[0]);
    }

    @Override
    public void printStackTrace(PrintStream printStream) {
    }

    @Override
    public void printStackTrace(PrintWriter printWriter) {
    }

    @Override
    public void printStackTrace() {
    }
}

