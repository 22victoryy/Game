package com.example.game.level3.world;

import com.example.game.level3.collision.CollidableObject;
import com.example.game.level3.collision.CollisionHandler;
import com.example.game.level3.core.Drawable;
import com.example.game.level3.core.Interactable;
import com.example.game.level3.core.Playable;
import com.example.game.level3.core.Updatable;
import com.example.game.level3.core.Vector;
import com.example.game.level3.world.entities.Forest;
import com.example.game.level3.world.entities.GlideStrategy;
import com.example.game.level3.world.entities.Ground;
import com.example.game.level3.world.entities.SugarGlider;
import com.example.game.level3.world.entities.Tree;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is responsible for building objects in the game world.
 */
class GameWorldBuilder {
    /**
     * Method to build objects in the GameWorld.
     */
    static void build(GameWorld gameWorld) {
        int screenWidth = gameWorld.getScreenWidth();
        int screenHeight = gameWorld.getScreenHeight();
        gameWorld.setCollisionHandler(
                new CollisionHandler(new ArrayList<CollidableObject>()));
        GlideStrategy glideStrategy;
        if (gameWorld.getTotalTime() == 0.0) {
            glideStrategy = GlideStrategy.JUMP;
        } else {
            glideStrategy = gameWorld.getSugarGlider().getGlideStrategy();
        }
        gameWorld.setScore(0);
        gameWorld.setOnDeathTimer(0.0);
        gameWorld.setTotalDistance(0.0);
        gameWorld.setTotalTime(0.0);
        gameWorld.setGameState(Playable.GameState.START);
        gameWorld.setUpdatableObjects(new ArrayList<Updatable>());
        gameWorld.setDrawableObjects(new ArrayList<Drawable>());
        gameWorld.setInteractableObjects(new ArrayList<Interactable>());
        gameWorld.getCollisionHandler().clearCollidableObjects();
        addObjects(gameWorld, screenWidth, screenHeight);
        gameWorld.getSugarGlider().setGlideStrategy(glideStrategy);
    }

    /**
     * Add all the objects that <code>GameWorld</code> manages.
     */
    private static void addObjects(GameWorld gameWorld, int screenWidth, int screenHeight) {
        List<CollidableObject> collidables = new ArrayList<>();
        Ground ground = new Ground(0.0, (double) screenWidth,
                (double) screenHeight, (double) screenHeight * 0.7);
        gameWorld.getDrawableObjects().add(ground);
        Forest forest = new Forest(gameWorld.getSugarGliderSpeed(), screenWidth, screenHeight);
        List<Tree> trees = forest.getTrees();
        gameWorld.setSugarGlider(new SugarGlider(gameWorld.getSugarGliderSpeed(),
                new Vector(screenWidth / 2, 510.0),
                new Vector(0.0, 0.0)));
        collidables.add(gameWorld.getSugarGlider());
        for (Tree tree : trees) {
            gameWorld.getUpdatableObjects().add(tree);
            gameWorld.getDrawableObjects().add(tree);
            collidables.add(tree);
        }
        gameWorld.getUpdatableObjects().add(gameWorld.getSugarGlider());
        gameWorld.getDrawableObjects().add(gameWorld.getSugarGlider());
        gameWorld.getInteractableObjects().add(gameWorld.getSugarGlider());
        gameWorld.setCollidableObjects(collidables);
    }
}
