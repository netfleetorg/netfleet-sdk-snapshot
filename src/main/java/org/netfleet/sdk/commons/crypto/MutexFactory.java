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
package org.netfleet.sdk.commons.crypto;

import java.io.Closeable;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author M.Çağrı Tepebaşılı - cagritepebasili [at] protonmail [dot] com
 * @version 1.0.0-RELEASE
 * @since 1.0.0-RELEASE
 */
class MutexFactory {

  private final Map<Object, Mutex> pool = new HashMap<>();
  private final Object MUTEX = new Object();

  public Mutex get(final Object key) {
    Mutex mutex;

    synchronized (MUTEX) {
      mutex = pool.get(key);
      if (mutex == null) {
        mutex = new Mutex(key);
        pool.put(key, mutex);
      }

      mutex.referenceCount++;
    }

    return mutex;
  }

  /**
   * A package private class for to be used in {@link MutexFactory}.
   *
   * @author M.Çağrı Tepebaşılı - cagritepebasili [at] protonmail [dot] com
   * @version 0.0.1
   * @since 0.0.1
   */
  private class Mutex implements Closeable {

    private int referenceCount;
    private final Object key;

    private Mutex(Object key) {
      this.key = key;
    }

    @Override
    public void close() throws IOException {
      synchronized (MUTEX) {
        referenceCount--;
        if (referenceCount == 0) {
          pool.remove(key);
        }
      }
    }

    public Object getKey() {
      return this.key;
    }

    public int getReferanceCount() {
      return referenceCount;
    }

  }

}
