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

package org.netfleet.sdk.data.report;

import org.netfleet.sdk.annotation.Review;

import java.io.Serializable;

/**
 * @author M.Çağrı TEPEBAŞILI - cagritepebasili [at] protonmail [dot] com
 * @version 1.0.0
 */
@Review(
    comment = "StudentBasicReport parametresi ile oluşan yapıcı eklenecek.",
    reviewer = "cagritepebasili")
public class StudentBasicReportMobileProjection implements Serializable {

  private Long studentId;
  private Long transportationId;

  private Integer durationAsSecond;
  private Double distanceAsMeter;

  public StudentBasicReportMobileProjection() {
  }

  public StudentBasicReportMobileProjection(Long studentId, Long transportationId,
                                            Integer durationAsSecond, Double distanceAsMeter) {
    this.studentId = studentId;
    this.transportationId = transportationId;
    this.durationAsSecond = durationAsSecond;
    this.distanceAsMeter = distanceAsMeter;
  }

  public Long getStudentId() {
    return studentId;
  }

  public void setStudentId(Long studentId) {
    this.studentId = studentId;
  }

  public Long getTransportationId() {
    return transportationId;
  }

  public void setTransportationId(Long transportationId) {
    this.transportationId = transportationId;
  }

  public Integer getDurationAsSecond() {
    return durationAsSecond;
  }

  public void setDurationAsSecond(Integer durationAsSecond) {
    this.durationAsSecond = durationAsSecond;
  }

  public Double getDistanceAsMeter() {
    return distanceAsMeter;
  }

  public void setDistanceAsMeter(Double distanceAsMeter) {
    this.distanceAsMeter = distanceAsMeter;
  }
}
