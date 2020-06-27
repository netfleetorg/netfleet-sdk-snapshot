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
package org.netfleet.sdk.commons.collection;

import java.util.Enumeration;
import java.util.concurrent.atomic.AtomicReference;

/**
 * Lightweight {@link java.util.concurrent.atomic.AtomicReference} based stack
 * data structure. Non arguably better than {@link java.util.Stack}.
 *
 * @param <T>
 * @author M.Çağrı TEPEBAŞILI - cagritepebasili [at] protonmail [dot] com
 * @version 1.0.0-RELEASE
 * @since 1.0.0-RELEASE
 */
public class AtomicStack<T> implements Enumeration<T> {

  private final AtomicReference<Node<T>> reference = new AtomicReference<>();

  /**
   * @param item the element to be pushed to this structure.
   */
  public void push(final T item) {
    Node<T> newHead = new Node<>(item);
    Node<T> oldHead;

    do {
      oldHead = reference.get();
      newHead.next = oldHead;
    } while (!reference.compareAndSet(oldHead, newHead));
  }

  /**
   * Gets the next element and removes it from structure.
   *
   * @return next element in the structure.
   */
  public T pop() {
    Node<T> oldHead;
    Node<T> newHead;
    do {
      oldHead = reference.get();
      if (oldHead == null) {
        return null;
      }
      newHead = oldHead.next;
    } while (!reference.compareAndSet(oldHead, newHead));

    return oldHead.item;
  }

  /**
   * @return {@code true} if empty,
   * {@code false} if not empty.
   */
  public boolean isEmpty() {
    return reference.get() == null;
  }

  /**
   * Founds the number of elements in the structure. It works with O(n)
   * complexity.
   *
   * @return number of elements in the structure. {@code 0} if empty.
   */
  public int size() {
    int currentSize = 0;
    Node<T> current = reference.get();
    while (current != null) {
      currentSize++;
      current = current.next;
    }

    return currentSize;
  }

  /**
   * It returns current object.
   *
   * @return peeked object. {@literal null} if structure is empty.
   */
  public T peek() {
    Node<T> eNode = reference.get();

    if (eNode == null) {
      return null;
    } else {
      return eNode.item;
    }
  }

  /**
   * Clears the structure with O(n) complexity.
   */
  public void clear() {
    while (reference.get() != null || size() > 0) {
      Node<T> node = reference.get();
      node.next = null;

      pop();
    }
  }

  /**
   * @param <E>
   * @version 0.0.1
   * @since 0.0.1
   */
  private class Node<E> {

    private final E item;
    private Node<E> next;

    Node(final E item) {
      this.item = item;
    }

  }

  @Override
  public boolean hasMoreElements() {
    return !isEmpty();
  }

  @Override
  public T nextElement() {
    return pop();
  }


}
