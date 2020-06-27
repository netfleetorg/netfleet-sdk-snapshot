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

import org.netfleet.sdk.integration.netgsm.context.SmsDataContext;
import org.netfleet.sdk.integration.netgsm.context.VoiceDataContext;
import org.netfleet.sdk.integration.netgsm.context.VoiceMailDataContext;
import org.netfleet.sdk.integration.netgsm.response.BadResponse;
import org.netfleet.sdk.integration.netgsm.response.BaseResponse;
import org.netfleet.sdk.integration.netgsm.response.OkResponse;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

public final class DataSender {
  public DataSender() {
  }

  private static final HashMap<String, String> ERROR_CODES = new HashMap<String, String>() {{
    put("10", "The file could not be uploaded to the system.");
    put("20", "Invalid file extension. (Allowed Extensions: \".wav\")");
    put("30", "Invalid user name, password, or your user does not have API access permission.");
    put("40", "The file size you are trying to send exceeds the maximum file size you can send (Maximum file size: 4 MB)");
  }};

  public BaseResponse sendSMS(final SmsDataContext context) throws Exception {
    Authentication auth = context.getAuthentication();
    if (auth.getUsername() == null ||
      auth.getPassword() == null ||
      auth.getBaseUrl() == null ||
      context.getHeader() == null ||
      context.getMessage() == null ||
      context.getNumbers() == null ||
      context.getNumbers().isEmpty()) {
      throw new Exception("In the SendSMS class, all properties must not null!");
    }
    String fullPath = auth.getBaseUrl() + Endpoint.SEND_SMS;
    StringBuilder xml = new StringBuilder();
    xml.append("<?xml version='1.0' encoding='utf8'?> <mainbody> <header>  <company dil='TR'>Netgsm</company>   <usercode>")
      .append(auth.getUsername())
      .append("</usercode> <password>")
      .append(auth.getPassword())
      .append("</password>  <type>1:n</type>  <msgheader>")
      .append(context.getHeader())
      .append("</msgheader>  </header>  <body>  <msg>  <![CDATA[")
      .append(context.getMessage())
      .append("]]>  </msg>  ");

    for (String number : context.getNumbers()) {
      xml.append("<no>")
        .append(number)
        .append("</no>");
    }

    xml.append("</body>  </mainbody>");

    SimpleRequest postRequest = new SimpleRequest();

    return postRequest.send(fullPath, xml.toString());
  }

  public BaseResponse sendVoiceMail(final VoiceMailDataContext context) throws Exception {
    Authentication auth = context.getAuthentication();
    if (auth.getUsername() == null ||
      auth.getPassword() == null ||
      auth.getBaseUrl() == null ||
      context.getAudioId() == null ||
      context.getStartDate() == null ||
      context.getStopDate() == null ||
      context.getNumbers() == null ||
      context.getNumbers().isEmpty()) {
      throw new Exception("In the SendVoiceMail class, all properties must not null!");
    }
    String fullPath = auth.getBaseUrl() + Endpoint.SEND_VOICE_MAIL;
    StringBuilder xml = new StringBuilder();
    xml.append("<?xml version='1.0' encoding='utf8'?> <mainbody> <header>  <usercode>")
      .append(auth.getUsername())
      .append("</usercode> <password>")
      .append(auth.getPassword())
      .append("</password>  <startdate>")
      .append(formatDate(context.getStartDate()))
      .append("</startdate> <starttime>")
      .append(formatTime(context.getStartDate()))
      .append("</starttime> <stopdate>")
      .append(formatDate(context.getStopDate()))
      .append("</stopdate> <stoptime>")
      .append(formatTime(context.getStopDate()))
      .append("</stoptime> <key>");

    if (context.isKeyEnabled()) {
      xml.append("1");
    } else {
      xml.append("0");
    }

    xml.append("</key> </header>  <body> <voicemail> <scenario> <series s='1'> <audioid>")
      .append(context.getAudioId())
      .append("</audioid> </series> <number>");

    for (String number : context.getNumbers()) {
      xml.append("<no>")
        .append(number)
        .append("</no>");
    }

    xml.append("</number> </scenario> </voicemail> </body> </mainbody>");

    SimpleRequest postRequest = new SimpleRequest();
    return postRequest.send(fullPath, xml.toString());
  }

  public BaseResponse uploadVoice(final VoiceDataContext context) throws Exception {
    Authentication auth = context.getAuthentication();

    if (auth.getUsername() == null ||
      auth.getPassword() == null ||
      auth.getBaseUrl() == null ||
      context.getFilePath() == null) {
      throw new Exception("In the UploadVoice class, all properties must not null!");
    }

    File uploadFile = new File(context.getFilePath());
    MultipartUtility multipart = new MultipartUtility(auth.getBaseUrl()+ Endpoint.UPLOAD_VOICE, "UTF-8");
    multipart.addFormField("username", auth.getUsername());
    multipart.addFormField("password", auth.getPassword());
    multipart.addFilePart("dosya", uploadFile);
    String response = multipart.finish();
    return findResponse(response);
  }

  private String formatDate(Date date) {
    SimpleDateFormat format = new SimpleDateFormat("ddMMyyyy");
    return format.format(date);
  }

  private String formatTime(Date date) {
    SimpleDateFormat format = new SimpleDateFormat("HHmm");
    return format.format(date);
  }

  private BaseResponse findResponse(String response) {
    OkResponse okResponse = new OkResponse();
    if (ERROR_CODES.containsKey(response)) {
      BadResponse badResponse = new BadResponse();
      badResponse.setResponseCode(response);
      badResponse.setResponseId("");
      badResponse.setMessage(ERROR_CODES.get(response));
      return badResponse;
    }
    okResponse.setMessage("Success");
    okResponse.setResponseCode("");
    okResponse.setResponseId(response);
    return okResponse;
  }
}
