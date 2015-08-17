package im.actor.core.api.rpc;
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
import im.actor.core.api.*;

public class RequestDisableInterests extends Request<ResponseVoid> {

    public static final int HEADER = 0x9e;
    public static RequestDisableInterests fromBytes(byte[] data) throws IOException {
        return Bser.parse(new RequestDisableInterests(), data);
    }

    private List<Integer> interests;

    public RequestDisableInterests(@NotNull List<Integer> interests) {
        this.interests = interests;
    }

    public RequestDisableInterests() {

    }

    @NotNull
    public List<Integer> getInterests() {
        return this.interests;
    }

    @Override
    public void parse(BserValues values) throws IOException {
        this.interests = values.getRepeatedInt(1);
    }

    @Override
    public void serialize(BserWriter writer) throws IOException {
        writer.writeRepeatedInt(1, this.interests);
    }

    @Override
    public String toString() {
        String res = "rpc DisableInterests{";
        res += "interests=" + this.interests;
        res += "}";
        return res;
    }

    @Override
    public int getHeaderKey() {
        return HEADER;
    }
}
