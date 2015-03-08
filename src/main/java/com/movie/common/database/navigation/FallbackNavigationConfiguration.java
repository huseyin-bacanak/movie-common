package com.movie.common.database.navigation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.thymeleaf.ThymeleafProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.thymeleaf.TemplateProcessingParameters;
import org.thymeleaf.resourceresolver.IResourceResolver;
import org.thymeleaf.spring4.resourceresolver.SpringResourceResourceResolver;
import org.thymeleaf.templateresolver.ITemplateResolver;
import org.thymeleaf.templateresolver.TemplateResolver;

import java.io.InputStream;

@Configuration
public class FallbackNavigationConfiguration {
  @Autowired
  private ThymeleafProperties properties;

  @Autowired
  private SpringResourceResourceResolver springResourceResourceResolver;

  /**
   * This bean gets picked up automatically by
   * {@link org.springframework.boot.autoconfigure.thymeleaf.ThymeleafAutoConfiguration}.
   */
  @Bean
  public ITemplateResolver navigationTemplateResolver() {
    TemplateResolver resolver = new TemplateResolver();
    resolver.setOrder(30);
    resolver.setResourceResolver(navigationResourceResolver());
    resolver.setPrefix(this.properties.getPrefix());
    resolver.setSuffix(this.properties.getSuffix());
    resolver.setTemplateMode(this.properties.getMode());
    resolver.setCharacterEncoding(this.properties.getEncoding());
    resolver.setCacheable(this.properties.isCache());

    return resolver;
  }

  @Bean
  public IResourceResolver navigationResourceResolver() {
    return new MyResourceResolver();
  }

  private class MyResourceResolver implements IResourceResolver{

    @Override
    public String getName() {
      return "navigationResourceResolver";
    }

    @Override
    public InputStream getResourceAsStream(
        TemplateProcessingParameters templateProcessingParameters,
        String resourceName) {
      if (resourceName.contains("navigation")) {
        return springResourceResourceResolver.getResourceAsStream(templateProcessingParameters,
            "classpath:templates/navigation.html");
      } else {
        return null;
      }
    }
  }

}
