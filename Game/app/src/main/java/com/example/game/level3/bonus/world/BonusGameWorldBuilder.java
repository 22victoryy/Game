package com.example.game.level3.bonus.world;

import java.util.ArrayList;
import java.util.List;

import com.example.game.level3.core.Updatable;
import com.example.game.level3.core.Drawable;
import com.example.game.level3.core.Interactable;
import com.example.game.level3.collision.*;
import com.example.game.level3.core.Playable;


/**
 * This class is responsible for building objects in the bonus game.
 */
class BonusGameWorldBuilder {
    /**
     * Method to build a new GameWorld.
     *
     * @param gameWorld the bonus game world to create entities in.
     */
    static void build(BonusGameWorld gameWorld) {
        gameWorld.setCollisionHandler(
                new CollisionHandler(new ArrayList<CollidableObject>()));
        int screenWidth = gameWorld.getScreenWidth();
        int screenHeight = gameWorld.getScreenHeight();
        gameWorld.setScore(0);
        gameWorld.setTotalDistance(0.0);
        gameWorld.setTotalTime(0.0);
        gameWorld.setGameState(Playable.GameState.START);
        gameWorld.setUpdatableObjects(new ArrayList<Updatable>());
        gameWorld.setDrawableObjects(new ArrayList<Drawable>());
        gameWorld.setInteractableObjects(new ArrayList<Interactable>());
        gameWorld.getCollisionHandler().clearCollidableObjects();
        addObjects(gameWorld, screenWidth, screenHeight);
    }

    /**
     * Add all the objects that the <code>GameWorld</code> must manage.
     */
    private static void addObjects(BonusGameWorld gameWorld, int screenWidth, int screenHeight) {
        List<CollidableObject> collidables = new ArrayList<>();
        List<Drawable> drawables = gameWorld.getDrawableObjects();
        List<Updatable> updatables = gameWorld.getUpdatableObjects();
        List<Interactable> interactables = gameWorld.getInteractableObjects();
        ArrayList<EasterEgg> easterEggs = EasterEggBuilder.getEasterEggs(screenWidth, screenHeight);
        EasterEggCollector player = new EasterEggCollector(screenWidth, screenHeight);
        player.setStatisticsTracker(gameWorld);
        collidables.addAll(easterEggs);
        collidables.add(player);
        updatables.add(player);
        updatables.addAll(easterEggs);
        drawables.add(player);
        drawables.addAll(easterEggs);
        interactables.add(player);
        gameWorld.setCollidableObjects(collidables);
    }
}