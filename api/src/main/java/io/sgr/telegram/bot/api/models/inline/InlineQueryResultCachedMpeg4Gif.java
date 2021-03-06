/*
 * Copyright 2017-2020 SgrAlpha
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package io.sgr.telegram.bot.api.models.inline;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Strings.isNullOrEmpty;

import io.sgr.telegram.bot.api.models.ParseMode;
import io.sgr.telegram.bot.api.models.markups.InlineKeyboardMarkup;
import io.sgr.telegram.bot.api.utils.JsonUtil;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents a link to a video animation (H.264/MPEG-4 AVC video without sound) stored on the Telegram servers. By
 * default, this animated MPEG-4 file will be sent by the user with an optional caption. Alternatively, you can use
 * input_message_content to send a message with the specified content instead of the animation.
 *
 * @author SgrAlpha
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class InlineQueryResultCachedMpeg4Gif implements InlineQueryResult, CachedItem, ItemWithCaption {

    private static final String TYPE = "mpeg4_gif";

    private final String title;
    private final String id;
    private final String fileId;
    private final String caption;
    private final ParseMode parseMode;
    private final InputMessageContent inputMessageContent;
    private final InlineKeyboardMarkup replyMarkup;

    /**
     * @param id                  Unique identifier for this result, 1-64 bytes.
     * @param fileId              A valid file identifier for the MP4 file.
     * @param title               Optional. Title for the result.
     * @param caption             Optional. Caption of the MPEG-4 file to be sent, 0-200 characters.
     * @param parseMode           Optional. Send Markdown or HTML, if you want Telegram apps to show bold, italic,
     *                            fixed-width text or inline URLs in the media caption.
     * @param replyMarkup         Optional. Inline keyboard attached to the message.
     * @param inputMessageContent Optional. Content of the message to be sent instead of the video animation.
     */
    public InlineQueryResultCachedMpeg4Gif(
            final String id, final String fileId,
            final String title,
            final String caption, final ParseMode parseMode,
            final InlineKeyboardMarkup replyMarkup, final InputMessageContent inputMessageContent) {
        checkArgument(!isNullOrEmpty(id), "Missing ID");
        this.id = id;
        checkArgument(!isNullOrEmpty(fileId), "Missing video URL");
        this.fileId = fileId;
        this.title = title;
        checkArgument(isNullOrEmpty(caption) || caption.length() <= 200,
                String.format("Caption should be shorter than 200 characters, but it's %d", caption.length()));
        this.caption = caption;
        this.parseMode = parseMode;
        this.replyMarkup = replyMarkup;
        this.inputMessageContent = inputMessageContent;
    }

    @JsonProperty("title")
    public String getTitle() {
        return title;
    }

    @JsonProperty("type")
    @Override public String getType() {
        return TYPE;
    }

    @JsonProperty("id")
    @Override public String getId() {
        return this.id;
    }

    @JsonProperty("mpeg4_file_id")
    @Override public String getFileId() {
        return fileId;
    }

    @JsonProperty("caption")
    @Override public String getCaption() {
        return this.caption;
    }

    @JsonProperty("parse_mode")
    @Override public ParseMode getParseMode() {
        return this.parseMode;
    }

    @JsonProperty("input_message_content")
    @Override public InlineKeyboardMarkup getReplyMarkup() {
        return this.replyMarkup;
    }

    @JsonProperty("reply_markup")
    @Override public InputMessageContent getInputMessageContent() {
        return this.inputMessageContent;
    }

    public String toJson() {
        return JsonUtil.toJson(this);
    }

    @Override public String toString() {
        return this.toJson();
    }

}
