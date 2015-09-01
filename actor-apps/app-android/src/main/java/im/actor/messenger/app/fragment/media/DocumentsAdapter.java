package im.actor.messenger.app.fragment.media;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import im.actor.core.entity.Message;
import im.actor.messenger.R;

import im.actor.messenger.app.fragment.chat.messages.DocHolder;
import im.actor.messenger.app.fragment.chat.messages.MessageHolder;
import im.actor.messenger.app.fragment.chat.messages.MessagesAdapter;
import im.actor.messenger.app.fragment.chat.messages.MessagesFragment;
import im.actor.messenger.app.fragment.chat.messages.PreprocessedData;
import im.actor.runtime.generic.mvvm.BindedDisplayList;


/**
 * Created by ex3ndr on 26.02.15.
 */
public class DocumentsAdapter extends MessagesAdapter {


    public DocumentsAdapter(BindedDisplayList<Message> displayList, MessagesFragment messagesFragment, Context context) {
        super(displayList, messagesFragment, context);
    }


    @Override
    public MessageHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        return new DocumentHolder(this, inflate(R.layout.adapter_dialog_doc, viewGroup));
    }

    private class DocumentHolder extends DocHolder {

        public DocumentHolder(MessagesAdapter fragment, View itemView) {
            super(fragment, itemView);

        }

        @Override
        protected void bindData(Message message, boolean isUpdated, PreprocessedData preprocessedData) {
            super.bindData(message, isUpdated, preprocessedData);
            container.hideDate();
        }
    }

}