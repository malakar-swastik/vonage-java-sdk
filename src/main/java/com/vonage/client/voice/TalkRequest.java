/*
 *   Copyright 2020 Vonage
 *
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 */
package com.vonage.client.voice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vonage.client.VonageUnexpectedException;

/**
 * The request object to send synthesized audio.
 * <p>
 * Contains the {@code uuid} of the {@link Call} and the {@link TalkPayload} to be sent in the request.
 */

public class TalkRequest {
    private TalkPayload talkPayload;
    private String uuid;

    public TalkRequest(String uuid, String text, VoiceName voiceName, int loop) {
        this.talkPayload = new TalkPayload(text, voiceName, loop);
        this.uuid = uuid;
    }

    public TalkRequest(String uuid, String text, VoiceName voiceName) {
        this(uuid, text, voiceName, 1);
    }

    public TalkRequest(String uuid, String text, int loop) {
        this(uuid, text, VoiceName.KIMBERLY, loop);
    }

    public TalkRequest(String uuid, String text) {
        this(uuid, text, VoiceName.KIMBERLY, 1);
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String toJson() {
        try {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.writeValueAsString(this.talkPayload);
        } catch (JsonProcessingException jpe) {
            throw new VonageUnexpectedException("Failed to produce json from TalkRequest object.", jpe);
        }
    }
}
