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
package org.netfleet.sdk.util.logging;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author M.Çağrı Tepebaşılı - cagritepebasili [at] protonmail [dot] com
 * @version 1.0.0-RELEASE
 * @since 1.0.0-RELEASE
 */
public final class LoggerCache {

  private final Map<Class<?>, Logger> cache = new ConcurrentHashMap<>(50);
  private static final LoggerCache INSTANCE = new LoggerCache();

  private LoggerCache() {
  }

  public static LoggerCache getInstance() {
    return INSTANCE;
  }

  public <T> Logger<T> getOrCreateLogger(final Class<T> clazz) {
    synchronized (LoggerCache.class) {
      Logger logger = cache.get(clazz);
      if (logger == null) {
        logger = new Logger<>(clazz);
        cache.put(clazz, logger);
      }

      return logger;
    }
  }

  public <T> Logger<T> getOrNull(final Class<T> clasz) {
    synchronized (LoggerCache.class) {
      return cache.get(clasz);
    }
  }

  public void clearAll() {
    cache.clear();
  }

}
