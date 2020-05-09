open module com.spinyowl.spinygui.core {

  requires transitive jdom2;
  requires transitive java.xml;
  requires transitive org.joml;
  requires static lombok;

  requires org.lwjgl;
  requires org.lwjgl.natives;

  requires org.lwjgl.yoga;
  requires org.lwjgl.yoga.natives;

  requires guava.base.r03;
  requires guava.collections.r03;
  requires io.github.classgraph;
  requires org.antlr.antlr4.runtime;

  requires transitive org.slf4j;
  requires transitive logback.core;
  requires transitive logback.classic;

  exports com.spinyowl.spinygui.core.animation;
  exports com.spinyowl.spinygui.core.api;

  exports com.spinyowl.spinygui.core.node;
  exports com.spinyowl.spinygui.core.node.intersection;

  exports com.spinyowl.spinygui.core.converter;
  exports com.spinyowl.spinygui.core.converter.annotation;
  exports com.spinyowl.spinygui.core.converter.css.parser;
  exports com.spinyowl.spinygui.core.converter.css.parser.annotations;
  exports com.spinyowl.spinygui.core.converter.css.parser.antlr;
  exports com.spinyowl.spinygui.core.converter.css.parser.visitor;
  exports com.spinyowl.spinygui.core.converter.dom;

  exports com.spinyowl.spinygui.core.event;
  exports com.spinyowl.spinygui.core.event.listener;
  exports com.spinyowl.spinygui.core.event.processor;

  exports com.spinyowl.spinygui.core.layout;

  exports com.spinyowl.spinygui.core.style;

  exports com.spinyowl.spinygui.core.converter.css;
  exports com.spinyowl.spinygui.core.converter.css.extractor;
  exports com.spinyowl.spinygui.core.converter.css.extractor.impl;
  exports com.spinyowl.spinygui.core.converter.css.property;
  exports com.spinyowl.spinygui.core.converter.css.property.border;
  exports com.spinyowl.spinygui.core.converter.css.property.border.radius;
  exports com.spinyowl.spinygui.core.converter.css.property.dimension;
  exports com.spinyowl.spinygui.core.converter.css.property.margin;
  exports com.spinyowl.spinygui.core.converter.css.property.padding;
  exports com.spinyowl.spinygui.core.converter.css.property.position;
  exports com.spinyowl.spinygui.core.converter.css.selector;

  exports com.spinyowl.spinygui.core.style.manager;

  exports com.spinyowl.spinygui.core.style.types;
  exports com.spinyowl.spinygui.core.style.types.border;
  exports com.spinyowl.spinygui.core.style.types.flex;
  exports com.spinyowl.spinygui.core.style.types.length;

  exports com.spinyowl.spinygui.core.util;
  exports com.spinyowl.spinygui.core;

}