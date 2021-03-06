package im.actor.core.api;
/*
 *  Generated by the Actor API Scheme generator.  DO NOT EDIT!
 */

import im.actor.runtime.bser.*;
import im.actor.runtime.collections.*;
import static im.actor.runtime.bser.Utils.*;
import im.actor.core.network.parser.*;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.NotNull;
import com.google.j2objc.annotations.ObjectiveCName;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

public class ApiFastThumb extends BserObject {

    private int w;
    private int h;
    private byte[] thumb;

    public ApiFastThumb(int w, int h, @NotNull byte[] thumb) {
        this.w = w;
        this.h = h;
        this.thumb = thumb;
    }

    public ApiFastThumb() {

    }

    public int getW() {
        return this.w;
    }

    public int getH() {
        return this.h;
    }

    @NotNull
    public byte[] getThumb() {
        return this.thumb;
    }

    @Override
    public void parse(BserValues values) throws IOException {
        this.w = values.getInt(1);
        this.h = values.getInt(2);
        this.thumb = values.getBytes(3);
        if (values.hasRemaining()) {
            setUnmappedObjects(values.buildRemaining());
        }
    }

    @Override
    public void serialize(BserWriter writer) throws IOException {
        writer.writeInt(1, this.w);
        writer.writeInt(2, this.h);
        if (this.thumb == null) {
            throw new IOException();
        }
        writer.writeBytes(3, this.thumb);
        if (this.getUnmappedObjects() != null) {
            SparseArray<Object> unmapped = this.getUnmappedObjects();
            for (int i = 0; i < unmapped.size(); i++) {
                int key = unmapped.keyAt(i);
                writer.writeUnmapped(key, unmapped.get(key));
            }
        }
    }

    @Override
    public String toString() {
        String res = "struct FastThumb{";
        res += "w=" + this.w;
        res += ", h=" + this.h;
        res += ", thumb=" + byteArrayToStringCompact(this.thumb);
        res += "}";
        return res;
    }

}
