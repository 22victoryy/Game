package com.example.game.level3.world.entities;

/**
 * This enum class dictates the gliding strategy of the sugar glider.
 * This is an example of the strategy design pattern.
 * <p>
 * JUMP: The sugar glider is affected by gravity.
 * The sugar glider must jump in order to avoid obstacles and not hit the ground.
 * FLY: The sugar glider is not affected by gravity, and must either fly up or down.
 * FALL: The sugar glider rises into the air like a Helium balloon. The sugar glider
 * must fall into the ground to avoid obstacles and not fly into space.
 */
public enum GlideStrategy {
    JUMP,
    FLY,
    FALL
}