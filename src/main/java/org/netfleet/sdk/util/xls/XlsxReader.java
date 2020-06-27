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
package org.netfleet.sdk.util.xls;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;

public class XlsxReader {

  private final Logger log = LoggerFactory.getLogger(XlsxReader.class);

  private XSSFWorkbook xssfWorkbook;
  private XSSFSheet xssfSheet;

  private DataFormatter dataFormatter;

  public XlsxReader(InputStream stream) {
    createXlsx(stream);
  }

  public static XlsxReader create(InputStream stream) {
    return new XlsxReader(stream);
  }

  private void createXlsx(InputStream stream) {
    try {
      xssfWorkbook = new XSSFWorkbook(stream);
      xssfSheet = xssfWorkbook.getSheetAt(0);
    } catch (IOException e) {
      log.error("", e);
    }
  }

  public String getCellValueAsString(int rowIndex, int cellIndex) {
    Row row = xssfSheet.getRow(rowIndex);
    Cell cell = row.getCell(cellIndex);

    if (dataFormatter == null) {
      dataFormatter = new DataFormatter();
    }

    return dataFormatter.formatCellValue(cell);
  }

  public XSSFSheet getXssfSheet() {
    return xssfSheet;
  }

  public XSSFWorkbook getXssfWorkbook() {
    return xssfWorkbook;
  }

}
