/*
 *  DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 *
 *  Copyright (C) 2020 Terra Yazılım Ltd Şti - All Rights Reserved
 *
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, version 3.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <https://www.gnu.org/licenses/>.
 *
 *  Please contact Terra Yazılım, Konya Teknoloji Geliştirme Bölgesi
 *  Büyük Kayacık Mah. 101. Cad. No:2 42250 - Selçuklu Konya or visit
 *  www.terrayazilim.com.tr if you need additional information or have
 *  any questions.
 *
 */
package org.netfleet.sdk.integration.fcm;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class PushNotification {

  public static final String DEFAULT_ENDPOINT = "https://fcm.googleapis.com/fcm/send";

  private String endpoint;
  private String apiKey;

  public PushNotification(String apiKey) {
    this(DEFAULT_ENDPOINT, apiKey);
  }

  public PushNotification(String endpoint, String apiKey) {
    this.endpoint = endpoint;
    this.apiKey = apiKey;
  }

  public boolean send(FirebasePushContext context) throws IOException {
    URL url = new URL(getEndpoint());
    HttpURLConnection conn = (HttpURLConnection) url.openConnection();

    conn.setUseCaches(false);
    conn.setDoInput(true);
    conn.setDoOutput(true);

    conn.setRequestMethod("POST");
    conn.setRequestProperty("Authorization", "key=" + getApiKey());
    conn.setRequestProperty("Content-Type", "application/json");

    ObjectMapper objectMapper = new ObjectMapper();
    String json = objectMapper.writeValueAsString(context);

    boolean result = true;
    try {
      OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
      wr.write(json);
      wr.flush();

      BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
      String output;

      while ((output = br.readLine()) != null) {
        System.out.println(output);
      }
    } catch (Exception e) {
      result = false;
    }

    return result;
  }

  public String getApiKey() {
    return apiKey;
  }

  public String getEndpoint() {
    return endpoint;
  }

  public void setApiKey(String apiKey) {
    this.apiKey = apiKey;
  }

  public void setEndpoint(String endpoint) {
    this.endpoint = endpoint;
  }
}
