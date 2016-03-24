package im.actor.sdk.view.markdown;

import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.StyleSpan;
import android.view.View;
import android.widget.Toast;

import im.actor.sdk.ActorSDK;
import im.actor.sdk.R;
import im.actor.sdk.controllers.conversation.ChatActivity;
import im.actor.sdk.controllers.fragment.preview.CodePreviewActivity;
import im.actor.runtime.android.AndroidContext;
import im.actor.runtime.markdown.MDDocument;
import im.actor.runtime.markdown.MDRawText;
import im.actor.runtime.markdown.MDSection;
import im.actor.runtime.markdown.MDSpan;
import im.actor.runtime.markdown.MDText;
import im.actor.runtime.markdown.MDUrl;
import im.actor.runtime.markdown.MarkdownParser;

public class AndroidMarkdown {

    private static final String EXTRA_CUSTOM_TABS_SESSION = "android.support.customtabs.extra.SESSION";
    private static final String EXTRA_CUSTOM_TABS_TOOLBAR_COLOR = "android.support.customtabs.extra.TOOLBAR_COLOR";
    public static final String EXTRA_CUSTOM_TABS_EXIT_ANIMATION_BUNDLE = "android.support.customtabs.extra.EXIT_ANIMATION_BUNDLE";

    public static Spannable processOnlyLinks(String markdown) {
        return processText(markdown, MarkdownParser.MODE_ONLY_LINKS);
    }

    public static Spannable processText(String markdown) {
        return processText(markdown, MarkdownParser.MODE_FULL);
    }

    private static Spannable processText(String markdown, int mode) {
        MDDocument doc = new MarkdownParser(mode).processDocument(markdown);

        SpannableStringBuilder builder = new SpannableStringBuilder();
        boolean isFirst = true;
        for (MDSection s : doc.getSections()) {
            if (isFirst) {
                isFirst = false;
            } else {
                builder.append("\n");
            }
            if (s.getType() == MDSection.TYPE_CODE) {
                int start = builder.length();
                builder.append("View Source Code");
                final String text = s.getCode().getCode();
                builder.setSpan(new RelativeSizeSpan(1.1f), start, builder.length(),
                        Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                builder.setSpan(new ForegroundColorSpan(Color.RED), start, builder.length(),
                        Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                builder.setSpan(new ClickableSpan() {
                    @Override
                    public void onClick(View view) {
                        AndroidContext.getContext().startActivity(
                                new Intent(AndroidContext.getContext(), CodePreviewActivity.class)
                                        .putExtra("source_code", text)
                                        .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
                    }
                }, start, builder.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            } else if (s.getType() == MDSection.TYPE_TEXT) {
                writeText(s.getText(), builder);
            } else {
                throw new RuntimeException("Unknown section type: " + s.getType());
            }
        }
        return builder;
    }

    private static void writeText(MDText[] texts, SpannableStringBuilder builder) {
        for (MDText text : texts) {
            if (text instanceof MDRawText) {
                builder.append(((MDRawText) text).getRawText());
            } else if (text instanceof MDSpan) {
                MDSpan span = (MDSpan) text;
                int start = builder.length();
                writeText(span.getChild(), builder);
                Object spanObj;
                if (span.getSpanType() == MDSpan.TYPE_BOLD) {
                    spanObj = new StyleSpan(Typeface.BOLD);
                } else {
                    spanObj = new StyleSpan(Typeface.ITALIC);
                }
                builder.setSpan(spanObj, start, builder.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            } else if (text instanceof MDUrl) {
                final MDUrl url = (MDUrl) text;
                int start = builder.length();
                builder.append(url.getUrlTitle());
                builder.setSpan(new ClickableSpan() {
                    @Override
                    public void onClick(View view) {
                        Context ctx = view.getContext();
                        if (url.getUrl().startsWith("send:")) {
                            if (ctx instanceof ChatActivity) {
                                ActorSDK.sharedActor().getMessenger().sendMessage(((ChatActivity) ctx).getPeer(), url.getUrl().replace("send:", ""));
                            }
                        } else {
                            Intent intent = new Intent(Intent.ACTION_VIEW)
                                    .setData(Uri.parse(url.getUrl()));
                            Bundle b = addChromeCustomTabData(intent);
                            if (intent.resolveActivity(ctx.getPackageManager()) != null) {
                                ctx.startActivity(
                                        intent);
                            } else {
                                Intent WithSchema = new Intent(Intent.ACTION_VIEW)
                                        .setData(Uri.parse("http://".concat(url.getUrl())));
                                Bundle b1 = addChromeCustomTabData(WithSchema);
                                if (WithSchema.resolveActivity(ctx.getPackageManager()) != null) {
                                    ctx.startActivity(
                                            WithSchema);
                                } else {
                                    Toast.makeText(view.getContext(), "Unknown URL type", Toast.LENGTH_SHORT).show();
                                }
                            }
                        }

                    }
                }, start, builder.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            } else {
                throw new RuntimeException("Unknown text type: " + text);
            }
        }
    }

    public static Bundle addChromeCustomTabData(Intent intent) {
        Bundle extras = new Bundle();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
            extras.putBinder(EXTRA_CUSTOM_TABS_SESSION, null);
        }
        extras.putInt(EXTRA_CUSTOM_TABS_TOOLBAR_COLOR, ActorSDK.sharedActor().style.getMainColor());
        intent.putExtras(extras);


//        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.JELLY_BEAN) {
//            Bundle finishBundle = ActivityOptions.makeCustomAnimation(AndroidContext.getContext(), 0, android.R.anim.slide_in_left).toBundle();
//            intent.putExtra(EXTRA_CUSTOM_TABS_EXIT_ANIMATION_BUNDLE, finishBundle);
//            return ActivityOptions.makeCustomAnimation(AndroidContext.getContext(), android.R.anim.slide_out_right, 0).toBundle();
//        }

        return null;
    }
}