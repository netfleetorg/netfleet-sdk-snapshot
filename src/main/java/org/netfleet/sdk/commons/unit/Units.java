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
package org.netfleet.sdk.commons.unit;

import javax.measure.Unit;
import javax.measure.quantity.Angle;
import javax.measure.quantity.Length;
import javax.measure.spi.SystemOfUnits;

/**
 * @author M.Çağrı TEPEBAŞILI - cagritepebasili [at] protonmail [dot] com
 * @version 1.0.0
 * @since 1.0.0
 */
public final class Units {

  public static final Unit<Length> METRE, KILOMETRE, MILLIMETRE, CENTIMETRE;
  public static final Unit<Angle> RADIAN, DEGREE;

  static {
    SystemOfUnits units = tec.units.ri.unit.Units.getInstance();

    METRE = units.getUnit(Length.class);
    RADIAN = units.getUnit(Angle.class);

    KILOMETRE = METRE.multiply(1000);
    MILLIMETRE = METRE.divide(1000);
    CENTIMETRE = METRE.divide(100);
    DEGREE = RADIAN.multiply(Math.PI / 180);
  }

  private Units() {
  }

}
