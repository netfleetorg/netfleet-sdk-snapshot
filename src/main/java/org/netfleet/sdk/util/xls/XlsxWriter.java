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

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Iterator;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.util.CellReference;
import org.apache.poi.openxml4j.util.ZipSecureFile;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class XlsxWriter {

  private final Logger log = LoggerFactory.getLogger(XlsxWriter.class);

  private XSSFSheet sheet;
  private XSSFWorkbook workbook;
  private File file1;

  private XlsxWriter(String destination, String sourceName) {
    createNewXlsx(destination, sourceName);
  }

  public static XlsxWriter newXlsx(String destination, String sourceName) {
    return new XlsxWriter(destination, sourceName);
  }

  private void createNewXlsx(String destination, String sourceName) {
    file1 = new File(destination);
    file1.getParentFile().mkdirs();

    ClassLoader classLoader = getClass().getClassLoader();
    File file = new File(classLoader.getResource(sourceName).getFile());
    try {
      Files.copy(file.toPath(), file1.toPath());
      FileInputStream inputStream = new FileInputStream(file1.toPath().toString());
      ZipSecureFile.setMinInflateRatio(-1.0d);
      workbook = new XSSFWorkbook(inputStream);
      sheet = workbook.getSheetAt(0);
    } catch (IOException e) {
      log.error("", e);
    }
  }

  public void setCellValue(String cellRef, String value) {
    CellReference cellReference = new CellReference(cellRef);
    Row row = sheet.getRow(cellReference.getRow());
    Cell cell = row.getCell(cellReference.getCol());
    cell.setCellValue(value);
  }

  public void setCellValue(int row, int cell, String value) {
    Row row2 = sheet.getRow(row);
    Cell cell2 = row2.getCell(cell);
    if (cell2 == null) {
      cell2 = row2.createCell(cell);
    }
    cell2.setCellValue(value);
  }

  public void setCellBorder(int firstRow, int lastRow, int firstCol, int lastCol, BorderStyle borderStyle) {
    for(int row = firstRow; row<lastRow; row++) {
      for (int cell = firstCol; cell < lastCol; cell++) {
        Row row2 = sheet.getRow(row);
        Cell cell2 = row2.getCell(cell);
        if (cell2 == null) {
          cell2 = row2.createCell(cell);
        }
        CellStyle style = workbook.createCellStyle();
        style.setBorderBottom(borderStyle);
        style.setBorderTop(borderStyle);
        style.setBorderRight(borderStyle);
        style.setBorderLeft(borderStyle);
        cell2.setCellStyle(style);
      }
    }
  }

  public Row copyRow(int sourceRowNum, int destinationRowNum) {
    if (sheet.getRow(destinationRowNum) != null) {
      sheet.shiftRows(destinationRowNum, sheet.getLastRowNum(), 1);
    }
    // Get the source / new row
    Row newRow = sheet.createRow(destinationRowNum);
    Row sourceRow = sheet.getRow(sourceRowNum);


    // Loop through source columns to add to new row
    for (int i = 0; i < sourceRow.getLastCellNum(); i++) {
      // Grab a copy of the old/new cell
      Cell oldCell = sourceRow.getCell(i);
      Cell newCell = newRow.createCell(i);

      // If the old cell is null jump to next cell
      if (oldCell == null) {
        continue;
      }

      // Copy style from old cell and apply to new cell
      CellStyle newCellStyle = workbook.createCellStyle();
      newCellStyle.cloneStyleFrom(oldCell.getCellStyle());
      newCell.setCellStyle(newCellStyle);

      // Set the cell data type
      newCell.setCellType(oldCell.getCellType());

      // Set the cell data value
      switch (oldCell.getCellType()) {
        case HSSFCell.CELL_TYPE_BLANK:
          newCell.setCellValue(oldCell.getStringCellValue());
          break;
        case HSSFCell.CELL_TYPE_FORMULA:
          newCell.setCellFormula(oldCell.getCellFormula());
          //Si tenemos que modificar la formulario lo podemos hacer como string
          //oldCell.getCellFormula().replace("A"+sourceRowNum, "A"+destinationRowNum)
          break;
        case HSSFCell.CELL_TYPE_NUMERIC:
          newCell.setCellValue(oldCell.getNumericCellValue());
          break;
        case HSSFCell.CELL_TYPE_STRING:
          newCell.setCellValue(oldCell.getRichStringCellValue());
          break;
      }
    }
    return newRow;
  }

  private void autoSizeColumns(XSSFWorkbook workbook) {
    int numberOfSheets = workbook.getNumberOfSheets();
    for (int i = 0; i < numberOfSheets; i++) {
      XSSFSheet sheet = workbook.getSheetAt(i);
      if (sheet.getPhysicalNumberOfRows() > 0) {
        for(int j=0; j<sheet.getLastRowNum(); j++) {
          Row row = sheet.getRow(j);
          if(row != null) {
            Iterator<Cell> cellIterator = row.cellIterator();
            while (cellIterator.hasNext()) {
              Cell cell = cellIterator.next();
              if (cell != null) {
                int columnIndex = cell.getColumnIndex();
                sheet.autoSizeColumn(columnIndex);
              }
            }
          }
        }
      }
    }
  }

  public void mergeCells(int rowFrom, int rowTo, int colFrom, int colTo) {
    sheet.addMergedRegion(new CellRangeAddress(rowFrom, rowTo, colFrom, colTo));
  }

  @Deprecated
  public String close() {
    return close(true);
  }

  public String close(boolean autoResize) {
    if (autoResize) {
      autoSizeColumns(workbook);
    }

    try (FileOutputStream outputStream = new FileOutputStream(file1)) {
      workbook.write(outputStream);
      workbook.close();
    } catch (Exception e) {
      System.out.println(e.toString());
    }
    return file1.getAbsolutePath();
  }

  public XSSFSheet getSheet() {
    return sheet;
  }

  public XSSFWorkbook getWorkbook() {
    return workbook;
  }
}
