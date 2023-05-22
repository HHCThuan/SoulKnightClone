package com.badlogic.soulknight.Sprites.Monster;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.soulknight.Sprites.Bullets.EnemyBullet;
import com.badlogic.soulknight.Sprites.Player;

public class Shooter extends Monster{
    private float timer;
    private final float FIRERATE = 1.5f;

    public Shooter(World world, OrthographicCamera camera, Vector2 startPos) {
        super(world, camera, startPos);

        sprite = new Sprite(texture, 217, 14, 16, 16);

        SPEED = 0;
        RANGE = 160f;
        health = 25;

        b2body.setLinearDamping(20);
    }

    @Override
    public void update(float dt) {
        if(!isDead) {
            attack(dt);
            render();
        }
    }

    void attack(float dt){
        timer += dt;
        Vector2 distanceToPlayer = new Vector2(currentPos).scl(-1).add(Player.currentPos);

        if(timer > FIRERATE && distanceToPlayer.len() < RANGE){
            timer = 0;

            new EnemyBullet(world, currentPos, new Vector2(Player.currentPos).scl(-1).add(currentPos).nor().scl(-60), 1, 20);
        }
    }
}
