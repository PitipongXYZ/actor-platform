/*
 * Copyright (C) 2015 Actor LLC. <https://actor.im>
 */

package im.actor.model.entity;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;

import im.actor.model.droidkit.bser.BserValues;
import im.actor.model.droidkit.bser.BserWriter;

public class Avatar extends WrapperEntity<im.actor.model.api.Avatar> {

    private static final int RECORD_ID = 10;

    @Nullable
    private AvatarImage smallImage;
    @Nullable
    private AvatarImage largeImage;
    @Nullable
    private AvatarImage fullImage;

    public Avatar(@NotNull im.actor.model.api.Avatar wrapped) {
        super(RECORD_ID, wrapped);
    }

    public Avatar(@NotNull byte[] data) throws IOException {
        super(RECORD_ID, data);
    }

    public Avatar() {
        super(RECORD_ID, new im.actor.model.api.Avatar());
    }

    @Nullable
    public AvatarImage getSmallImage() {
        return smallImage;
    }

    @Nullable
    public AvatarImage getLargeImage() {
        return largeImage;
    }

    @Nullable
    public AvatarImage getFullImage() {
        return fullImage;
    }

    @Override
    public void parse(BserValues values) throws IOException {
        // Is New Layout
        if (!values.getBool(5, false)) {
            im.actor.model.api.AvatarImage smallImage = null;
            im.actor.model.api.AvatarImage largeImage = null;
            im.actor.model.api.AvatarImage fullImage = null;

            byte[] small = values.optBytes(1);
            if (small != null) {
                AvatarImage oldSmallImage = new AvatarImage(small);
                smallImage = new im.actor.model.api.AvatarImage(
                        oldSmallImage.getFileReference().getFileLocation(),
                        oldSmallImage.getWidth(),
                        oldSmallImage.getHeight(),
                        oldSmallImage.getFileReference().getFileSize());
            }

            byte[] large = values.optBytes(2);
            if (large != null) {
                AvatarImage oldLargeImage = new AvatarImage(large);
                largeImage = new im.actor.model.api.AvatarImage(
                        oldLargeImage.getFileReference().getFileLocation(),
                        oldLargeImage.getWidth(),
                        oldLargeImage.getHeight(),
                        oldLargeImage.getFileReference().getFileSize());
            }

            byte[] full = values.optBytes(3);
            if (full != null) {
                AvatarImage oldFullImage = new AvatarImage(full);
                fullImage = new im.actor.model.api.AvatarImage(
                        oldFullImage.getFileReference().getFileLocation(),
                        oldFullImage.getWidth(),
                        oldFullImage.getHeight(),
                        oldFullImage.getFileReference().getFileSize());
            }

            setWrapped(new im.actor.model.api.Avatar(smallImage, largeImage, fullImage));
        }

        // Deserialize wrapper
        super.parse(values);
    }

    @Override
    public void serialize(BserWriter writer) throws IOException {
        // Mark as new layout
        writer.writeBool(5, true);
        // Write wrapped object
        super.serialize(writer);
    }

    @Override
    protected void applyWrapped(@NotNull im.actor.model.api.Avatar wrapped) {
        if (wrapped.getSmallImage() != null) {
            smallImage = new AvatarImage(wrapped.getSmallImage());
        } else {
            smallImage = null;
        }
        if (wrapped.getLargeImage() != null) {
            largeImage = new AvatarImage(wrapped.getLargeImage());
        } else {
            largeImage = null;
        }
        if (wrapped.getFullImage() != null) {
            fullImage = new AvatarImage(wrapped.getFullImage());
        } else {
            fullImage = null;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Avatar avatar = (Avatar) o;

        if (fullImage != null ? !fullImage.equals(avatar.fullImage) : avatar.fullImage != null)
            return false;
        if (largeImage != null ? !largeImage.equals(avatar.largeImage) : avatar.largeImage != null)
            return false;
        if (smallImage != null ? !smallImage.equals(avatar.smallImage) : avatar.smallImage != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = smallImage != null ? smallImage.hashCode() : 0;
        result = 31 * result + (largeImage != null ? largeImage.hashCode() : 0);
        result = 31 * result + (fullImage != null ? fullImage.hashCode() : 0);
        return result;
    }

    @Override
    @NotNull
    protected im.actor.model.api.Avatar createInstance() {
        return new im.actor.model.api.Avatar();
    }
}