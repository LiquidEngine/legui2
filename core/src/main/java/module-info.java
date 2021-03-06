open module com.spinyowl.spinygui.core {

  requires transitive org.joml;
  requires static lombok;

  requires org.lwjgl;
  requires org.lwjgl.natives;

  requires org.lwjgl.yoga;
  requires org.lwjgl.yoga.natives;

  requires io.github.classgraph;

  requires transitive org.slf4j;
  requires transitive ch.qos.logback.core;
  requires transitive ch.qos.logback.classic;

  requires org.apache.commons.collections4;
  requires org.apache.commons.lang3;

  exports com.spinyowl.spinygui.core.animation;

  exports com.spinyowl.spinygui.core.node;
  exports com.spinyowl.spinygui.core.node.intersection;

  exports com.spinyowl.spinygui.core.node.style;
  exports com.spinyowl.spinygui.core.node.style.types;
  exports com.spinyowl.spinygui.core.node.style.types.background;
  exports com.spinyowl.spinygui.core.node.style.types.border;
  exports com.spinyowl.spinygui.core.node.style.types.flex;
  exports com.spinyowl.spinygui.core.node.style.types.length;

  exports com.spinyowl.spinygui.core.event;
  exports com.spinyowl.spinygui.core.event.listener;
  exports com.spinyowl.spinygui.core.event.processor;

  exports com.spinyowl.spinygui.core.font;
  exports com.spinyowl.spinygui.core.input;

  exports com.spinyowl.spinygui.core.layout;

  exports com.spinyowl.spinygui.core.style.stylesheet;
  exports com.spinyowl.spinygui.core.style.stylesheet.atrule;
  exports com.spinyowl.spinygui.core.style.stylesheet.property;
  exports com.spinyowl.spinygui.core.style.stylesheet.property.border;
  exports com.spinyowl.spinygui.core.style.stylesheet.property.border.radius;
  exports com.spinyowl.spinygui.core.style.stylesheet.property.dimension;
  exports com.spinyowl.spinygui.core.style.stylesheet.property.margin;
  exports com.spinyowl.spinygui.core.style.stylesheet.property.padding;
  exports com.spinyowl.spinygui.core.style.stylesheet.property.position;
  exports com.spinyowl.spinygui.core.style.stylesheet.selector;
  exports com.spinyowl.spinygui.core.style.stylesheet.selector.combinator;
  exports com.spinyowl.spinygui.core.style.stylesheet.selector.pseudo_class;
  exports com.spinyowl.spinygui.core.style.stylesheet.selector.simple;

  exports com.spinyowl.spinygui.core.style.manager;

  exports com.spinyowl.spinygui.core.util;
  exports com.spinyowl.spinygui.core;

}