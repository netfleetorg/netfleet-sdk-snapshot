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
package org.netfleet.sdk.integration.netgsm;

import org.netfleet.sdk.integration.netgsm.response.BadResponse;
import org.netfleet.sdk.integration.netgsm.response.BaseResponse;
import org.netfleet.sdk.integration.netgsm.response.OkResponse;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;

public class SimpleRequest {

  private final HashMap<String, String> SUCCESS_CODES = new HashMap<String, String>() {{
    put("00", "Success");
    put("01", "Success but begin date is incorrect. Begin date has been changed to system date.");
    put("02", "Success but end date is incorrect. End date has been changed to system date.");
  }};

  private final HashMap<String, String> ERROR_CODES = new HashMap<String, String>() {{
    put("20", "The message text is incorrect. The maximum number of characters may be exceeded.");
    put("30", "Invalid user name, password, or your user does not have API access permission.");
    put("40", "If you sending sms, your message header(sender name) otherwise voice file is not defined in the system.");
    put("45", "Cannot find the phone number to send.");
    put("70", "Incorrect questioning. One of the parameters you submitted is incorrect or one of the required fields is missing.");
  }};

  public BaseResponse send(String url, String xmlStr) throws IOException {
    URL u = new URL(url);
    URLConnection uc = u.openConnection();
    HttpURLConnection connection = (HttpURLConnection) uc;
    connection.setDoOutput(true);
    connection.setDoInput(true);
    connection.setRequestMethod("POST");
    OutputStream out = connection.getOutputStream();
    OutputStreamWriter wout = new OutputStreamWriter(out, StandardCharsets.UTF_8);
    wout.write(xmlStr);
    wout.flush();
    out.close();
    InputStream in = connection.getInputStream();
    int c;
    StringBuilder response = new StringBuilder();
    while ((c = in.read()) != -1) {
      response.append((char) c);
    }
    in.close();
    out.close();
    connection.disconnect();
    return findResponse(response.toString());
  }

  private BaseResponse findResponse(String response) {
    BadResponse badResponse = new BadResponse();
    if (response.contains(" ")) {
      String code;
      String id;
      String[] response2 = response.split(" ");
      if (response2.length == 2) {
        code = response2[0];
        id = response2[1];
      }else{
        code = response;
        id = "";
      }
      if (SUCCESS_CODES.containsKey(code)) {
        OkResponse okResponse = new OkResponse();
        okResponse.setResponseCode(code);
        okResponse.setResponseId(id);
        okResponse.setMessage(SUCCESS_CODES.get(code));
        return okResponse;
      } else if (ERROR_CODES.containsKey(code)) {
        badResponse.setResponseCode(code);
        badResponse.setResponseId(id);
        badResponse.setMessage(ERROR_CODES.get(code));
      } else {
        badResponse.setResponseCode(code);
        badResponse.setResponseId(id);
        badResponse.setMessage("Unknown Status");
      }
    }
    return badResponse;
  }
}
