package com.movie.common.roca.core;


import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractResourceAssembler<T,D extends ResourceSupport> {

  /**
   * Return resource.
   */
  public List<D> toResource(List<T> entities) {
    Assert.notNull(entities);
    List<D> resourceList = new ArrayList<>();

    for (T entity : entities) {
      resourceList.add(toResource(entity));
    }

    return resourceList;
  }

  public abstract D toResource(T entity);
}
