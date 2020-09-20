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

import org.junit.Ignore;
import org.junit.Test;
import org.netfleet.sdk.integration.netgsm.context.impl.SimpleVoiceMailDataContext;
import org.netfleet.sdk.integration.netgsm.response.BaseResponse;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class DataSenderTest {

  @Ignore
  public void testVoiceMail() {
    ArrayList<String> numberList = new ArrayList<>();
    numberList.add("5439638241");
    numberList.add("5348207803");

    Calendar calStart = Calendar.getInstance();
    Date dStart = calStart.getTime();

    Calendar calStop= Calendar.getInstance();
    calStop.add(Calendar.SECOND,30);
    Date dStop = calStop.getTime();

    SimpleVoiceMailDataContext context
        = new SimpleVoiceMailDataContext("", "", "https://api.netgsm.com.tr");
    context.setAudioId("17103604");
    context.setStartDate(dStart);
    context.setStopDate(dStop);
    context.setKeyEnabled(false);
    context.setNumbers(numberList);

    System.out.println("START DATEEEE--- "+dStart.toString());
    System.out.println("STOPP  DATEEEE--- "+dStop.toString());
    System.out.println("numberss--- "+numberList.toString());

    DataSender dataSender = new DataSender();
    try {
      BaseResponse ctx = dataSender.sendVoiceMail(context);
      System.out.println("DÖNEN DEĞER GetMessage--- "+ctx.getMessage());
      System.out.println("DÖNEN DEĞER GetMessage--- "+ctx.getResponseCode());
    }catch (Exception e){
      System.out.println(e.getMessage());
    }
  }
}