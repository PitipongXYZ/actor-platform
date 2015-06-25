package im.actor.model.api.rpc;
/*
 *  Generated by the Actor API Scheme generator.  DO NOT EDIT!
 */

import im.actor.model.droidkit.bser.Bser;
import im.actor.model.droidkit.bser.BserParser;
import im.actor.model.droidkit.bser.BserObject;
import im.actor.model.droidkit.bser.BserValues;
import im.actor.model.droidkit.bser.BserWriter;
import im.actor.model.droidkit.bser.DataInput;
import im.actor.model.droidkit.bser.DataOutput;
import im.actor.model.droidkit.bser.util.SparseArray;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.NotNull;
import com.google.j2objc.annotations.ObjectiveCName;
import static im.actor.model.droidkit.bser.Utils.*;
import java.io.IOException;
import im.actor.model.network.parser.*;
import java.util.List;
import java.util.ArrayList;
import im.actor.model.api.*;

public class RequestLoadDialogs extends Request<ResponseLoadDialogs> {

    public static final int HEADER = 0x68;
    public static RequestLoadDialogs fromBytes(byte[] data) throws IOException {
        return Bser.parse(new RequestLoadDialogs(), data);
    }

    private long minDate;
    private int limit;

    public RequestLoadDialogs(long minDate, int limit) {
        this.minDate = minDate;
        this.limit = limit;
    }

    public RequestLoadDialogs() {

    }

    public long getMinDate() {
        return this.minDate;
    }

    public int getLimit() {
        return this.limit;
    }

    @Override
    public void parse(BserValues values) throws IOException {
        this.minDate = values.getLong(1);
        this.limit = values.getInt(2);
    }

    @Override
    public void serialize(BserWriter writer) throws IOException {
        writer.writeLong(1, this.minDate);
        writer.writeInt(2, this.limit);
    }

    @Override
    public String toString() {
        String res = "rpc LoadDialogs{";
        res += "minDate=" + this.minDate;
        res += ", limit=" + this.limit;
        res += "}";
        return res;
    }

    @Override
    public int getHeaderKey() {
        return HEADER;
    }
}
