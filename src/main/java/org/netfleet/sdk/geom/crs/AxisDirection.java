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

package org.netfleet.sdk.geom.crs;

import java.io.Serializable;

/**
 * @author M.Çağrı Tepebaşılı - cagritepebasili [at] protonmail [dot] com
 */
public final class AxisDirection implements Serializable, Comparable<AxisDirection> {

  public static final AxisDirection NORTH = new AxisDirection("NORTH", 1);
  public static final AxisDirection EAST = new AxisDirection("EAST", 0);
  public static final AxisDirection SOUTH = new AxisDirection("SOUTH", 1);
  public static final AxisDirection WEST = new AxisDirection("WEST", 0);
  public static final AxisDirection UP = new AxisDirection("UP", 2);
  public static final AxisDirection DOWN = new AxisDirection("DOWN", 2);
  public static final AxisDirection OTHER = new AxisDirection("OTHER", 3);
  public static final AxisDirection UNKNOWN = new AxisDirection("UNKNOWN", 3);
  public static final AxisDirection GEOCENTRIC_X = new AxisDirection("GEOCENTRIC_X", 0);
  public static final AxisDirection GEOCENTRIC_Y = new AxisDirection("GEOCENTRIC_Y", 1);
  public static final AxisDirection GEOCENTRIC_Z = new AxisDirection("GEOCENTRIC_Z", 2);

  private final String name;
  private final int order;

  public AxisDirection(String name, int order) {
    this.name = name;
    this.order = order;
  }

  public String getName() {
    return name;
  }

  public int getOrder() {
    return order;
  }

  @Override
  public int compareTo(AxisDirection o) {
    return Integer.compare(order, o.getOrder());
  }
}
