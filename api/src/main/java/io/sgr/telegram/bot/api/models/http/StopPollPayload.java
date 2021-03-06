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

package io.sgr.telegram.bot.api.models.http;

import io.sgr.telegram.bot.api.models.markups.InlineKeyboardMarkup;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author SgrAlpha
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class StopPollPayload {

    private final String chatId;
    private final long messageId;
    private final InlineKeyboardMarkup replyMarkup;

    /**
     * @param chatId Unique identifier for the target chat or username of the target channel (in the format @channelusername).
     * @param messageId Identifier of the original message with the poll.
     * @param replyMarkup Optional. A JSON-serialized object for a new message inline keyboard.
     */
    public StopPollPayload(final String chatId, final long messageId, final InlineKeyboardMarkup replyMarkup) {
        this.chatId = chatId;
        this.messageId = messageId;
        this.replyMarkup = replyMarkup;
    }

    @JsonProperty("chat_id")
    public String getChatId() {
        return chatId;
    }

    @JsonProperty("message_id")
    public long getMessageId() {
        return messageId;
    }

    @JsonProperty("reply_markup")
    public InlineKeyboardMarkup getReplyMarkup() {
        return replyMarkup;
    }
}
