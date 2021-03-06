/*
 * Copyright (C) 2011.
 * All rights reserved.
 */
package ro.isdc.wro.model.factory;

import java.util.Arrays;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import ro.isdc.wro.model.WroModel;
import ro.isdc.wro.util.Transformer;

/**
 * @author Alex Objelean
 */
public class TestModelTransformerFactory {
  @Mock
  private WroModelFactory mockFactory;

  @Before
  public void setUp() {
    MockitoAnnotations.initMocks(this);
    Mockito.when(mockFactory.create()).thenReturn(new WroModel());
  }

  private ModelTransformerFactory factory;

  @Test(expected=NullPointerException.class)
  public void shouldNotAcceptNullDecoratedModel() {
    factory = new ModelTransformerFactory(null);
  }


  @Test
  public void shouldNotChangeTheModelWhenNoTransformersProvided() {
    factory = new ModelTransformerFactory(mockFactory);
    Assert.assertEquals(new WroModel().getGroups(), factory.create().getGroups());
  }

  @Test
  public void shouldChangeTheModelWhenTransformersProvided() {
    final Transformer<WroModel> transformer = new Transformer<WroModel>() {
      public WroModel transform(final WroModel input) {
        return null;
      }
    };
    factory = new ModelTransformerFactory(mockFactory).setTransformers(Arrays.asList(transformer, transformer));
    Assert.assertNull(factory.create());
  }
}
