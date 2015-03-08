package com.movie.common.roca.core;

import org.springframework.util.Assert;

import java.util.Arrays;
import java.util.Collection;

public class Resource<T> extends ResourceSupport {

  private T content;

  /**
   * Creates a new {@link, Resource} with given content and {@link Link}s (optional).
   *
   * @param content must not be {@literal null}.
   * @param links   the links to add to the {@link Resource}.
   */
  public Resource(T content, Link... links) {
    this(content, Arrays.asList(links));
  }

/**
 * Creates a new {@link Resource} with the given content and {@link Link}s.
 *
 * @param content must not be {@literal null}.
 * @param links the links to add to the {@link Resource}.
 */
  public Resource(T content, Iterable<Link> links) {
    Assert.notNull(content, "Content must not be null");
    Assert.isTrue(!(content instanceof Collection),
                  "Content must not be a collection, use Resources instead.");
    this.content=content;
    this.add(links);
  }

  public T getContent() {
    return content;
  }

}
