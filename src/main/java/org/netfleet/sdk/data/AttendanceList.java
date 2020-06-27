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
package org.netfleet.sdk.data;

import java.io.Serializable;
import java.util.*;

/**
 * @author M.Çağrı TEPEBAŞILI - cagritepebasili [at] protonmail [dot] com
 * @version 1.0.0-RELEASE
 * @since 1.0.0-RELEASE
 */
public class AttendanceList implements Serializable {

  private List<Attendance> attendances;

  public AttendanceList() {
    this(new ArrayList<Attendance>());
  }

  public AttendanceList(List<Attendance> locations) {
    this.attendances = locations;
  }

  public static AttendanceList of(Collection<Attendance> locations) {
    if (locations == null)
      return new AttendanceList(new ArrayList<Attendance>());
    else
      return new AttendanceList(new ArrayList<>(locations));
  }

  public boolean addAttendance(Attendance attendance) {
    if (attendances == null)
      attendances = new ArrayList<>();

    return attendances.add(attendance);
  }

  public boolean removeAttendance(Attendance attendance) {
    if (attendances == null)
      return false;

    return attendances.remove(attendance);
  }

  public boolean containsAttendance(Attendance attendance) {
    if (attendances == null)
      return false;

    return attendances.contains(attendance);
  }

  public Iterator<Attendance> iterator() {
    if (attendances == null)
      return Collections.emptyIterator();

    return attendances.iterator();
  }

  public void setAttendances(List<Attendance> attendances) {
    this.attendances = attendances;
  }

  public List<Attendance> getAttendances() {
    return attendances;
  }

}
