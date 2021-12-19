package Main;

import Entities.Entity;
import java.awt.*;

public class Collision {
    public boolean collisionOn = false;
    Rectangle entityRect;
    Rectangle objRect;
    Game gm;

    public Collision(Game gm){
        this.gm = gm;
        entityRect = new Rectangle();
        objRect = new Rectangle();
    }

    public void checkCollision(Entity entity){

        int entityTop = entity.worldY + entity.solidArea.y;
        int entityBottom = entity.worldY + entity.solidArea.y + entity.solidArea.height;
        int entityLeft = entity.worldX + entity.solidArea.x;
        int entityRight = entity.worldX + entity.solidArea.x + entity.solidArea.width;

        int leftCol = entityLeft/gm.tileSize;
        int rightCol = entityRight/gm.tileSize;
        int topRow = entityTop/gm.tileSize;
        int botRow = entityBottom/gm.tileSize;

        int tile1;
        int tile2;

        int[][] layout;
        if(gm.gamestate == gm.townState){
            layout = gm.townMap.townLayout;
        }
        else if(gm.gamestate == gm.forestState){
            layout = gm.forestMap.forestLayout;
        }
        else {
            layout = new int[10][10];
        }

        switch(entity.direction){
            case "up":
                topRow = (entityTop - entity.speed)/gm.tileSize;
                tile1 = layout[leftCol][topRow];
                tile2 = layout[rightCol][topRow];
                //Check if Collision for that Block is on
                if(gm.tiles.tiles[tile1].collision || gm.tiles.tiles[tile2].collision){
                    entity.collisionOn = true;
                }
                break;
            case "down":
                botRow = (entityBottom + entity.speed)/gm.tileSize;
                tile1 = layout[leftCol][botRow];
                tile2 = layout[rightCol][botRow];
                if(gm.tiles.tiles[tile1].collision || gm.tiles.tiles[tile2].collision){
                    entity.collisionOn = true;
                }
                break;
            case "left":
                leftCol = (entityLeft - entity.speed)/gm.tileSize;
                tile1 = layout[leftCol][topRow];
                tile2 = layout[leftCol][botRow];
                if(gm.tiles.tiles[tile1].collision || gm.tiles.tiles[tile2].collision){
                    entity.collisionOn = true;
                }
                break;
            case "right":
                rightCol = (entityRight + entity.speed)/gm.tileSize;
                tile1 = layout[rightCol][topRow];
                tile2 = layout[rightCol][botRow];
                if(gm.tiles.tiles[tile1].collision || gm.tiles.tiles[tile2].collision){
                    entity.collisionOn = true;
                }
                break;
        }
    }

    public int checkObject(Entity entity, boolean player){
        int index = 999;
        for(int i = 0; i < gm.obj.length; i++){

            if(gm.obj[i] != null){
                entityRect.width = entity.solidArea.width;
                entityRect.height = entity.solidArea.height;
                entityRect.x = entity.worldX + entity.solidArea.x;
                entityRect.y = entity.worldY + entity.solidArea.y;

                objRect.x = gm.obj[i].worldX + gm.obj[i].hitBox.x;
                objRect.y = gm.obj[i].worldY + gm.obj[i].hitBox.y;
                objRect.width = gm.obj[i].hitBox.width;
                objRect.height = gm.obj[i].hitBox.height;
                
                switch(entity.direction){
                    case "up":
                        entityRect.y -= entity.speed;
                        if(entityRect.intersects(objRect)){
                            if(gm.obj[i].collision){
                                collisionOn = true;
                            }
                            if(player){
                                index = i;
                            }
                        }
                        break;
                    case "down":
                        entityRect.y += entity.speed;
                        if(entityRect.intersects(objRect)){
                            if(gm.obj[i].collision){
                                collisionOn = true;
                            }
                            if(player){
                                index = i;
                            }
                        }
                        break;
                    case "left":
                        entityRect.x -= entity.speed;
                        if(entityRect.intersects(objRect)){
                            if(gm.obj[i].collision){
                                collisionOn = true;
                            }
                            if(player){
                                index = i;
                            }
                        }
                        break;
                    case "right":
                        entityRect.x += entity.speed;
                        if(entityRect.intersects(objRect)){
                            if(gm.obj[i].collision){
                                collisionOn = true;
                            }
                            if(player){
                                index = i;
                            }
                        }
                        break;
                }
            }
        }

        return index;
    }
}
