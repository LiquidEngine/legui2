package com.spinyowl.spinygui.core.event;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import org.joml.Vector2f;

@Getter
@EqualsAndHashCode
@ToString
@SuperBuilder
public class ChangePositionEvent extends Event {

  @NonNull
  private final Vector2f oldPos;
  @NonNull
  private final Vector2f newPos;

  public static ChangePositionEvent of(EventTarget target, Vector2f oldPos, Vector2f newPos) {
    return ChangePositionEvent.builder().target(target).oldPos(oldPos).newPos(newPos).build();
  }
}
