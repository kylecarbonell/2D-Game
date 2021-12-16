package Main;

import Entities.Entity;

public class Collision {
    public boolean collisionOn = false;
    Game gm;

    public Collision(Game gm){
        this.gm = gm;
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

        switch(entity.direction){
            case "up":
                topRow = (entityTop - entity.speed)/gm.tileSize;
                tile1 = gm.map.layout[leftCol][topRow];
                tile2 = gm.map.layout[rightCol][topRow];
                //Check if Collision for that Block is on
                if(gm.map.tiles[tile1].collision || gm.map.tiles[tile2].collision){
                    entity.collisionOn = true;
                }
                break;
            case "down":
                botRow = (entityBottom + entity.speed)/gm.tileSize;
                tile1 = gm.map.layout[leftCol][botRow];
                tile2 = gm.map.layout[rightCol][botRow];
                if(gm.map.tiles[tile1].collision || gm.map.tiles[tile2].collision){
                    entity.collisionOn = true;
                }
                break;
            case "left":
                leftCol = (entityLeft - entity.speed)/gm.tileSize;
                tile1 = gm.map.layout[leftCol][topRow];
                tile2 = gm.map.layout[leftCol][botRow];
                if(gm.map.tiles[tile1].collision || gm.map.tiles[tile2].collision){
                    entity.collisionOn = true;
                }
                break;
            case "right":
                rightCol = (entityRight + entity.speed)/gm.tileSize;
                tile1 = gm.map.layout[rightCol][topRow];
                tile2 = gm.map.layout[rightCol][botRow];
                if(gm.map.tiles[tile1].collision || gm.map.tiles[tile2].collision){
                    entity.collisionOn = true;
                }
                break;
        }
    }

    public int checkObject(Entity entity, boolean player){
        int index = 999;
        for(int i = 0; i < gm.obj.length; i++){

            if(gm.obj[i] != null){

                entity.solidArea.x = entity.worldX + entity.solidArea.x;
                entity.solidArea.y = entity.worldY + entity.solidArea.y;

                gm.obj[i].hitBox.x = gm.obj[i].worldX + gm.obj[i].hitBox.x;
                gm.obj[i].hitBox.y = gm.obj[i].worldY + gm.obj[i].hitBox.y;

                switch(entity.direction){
                    case "up":
                        entity.solidArea.y -= entity.speed;
                        if(entity.solidArea.intersects(gm.obj[i].hitBox)){
                            if(gm.obj[i].collision){
                                entity.collisionOn = true;
                                System.out.println("Here");
                            }
                            if(player){
                                index = i;
                            }
                            System.out.println("here");
                        }
                        break;  
                    case "down":
                        entity.solidArea.y += entity.speed;
                        if(entity.solidArea.intersects(gm.obj[i].hitBox)){
                            if(gm.obj[i].collision){
                                entity.collisionOn = true;
                            }
                            if(player){
                                index = i;
                            }
                        }
                        break;
                    case "left":
                        entity.solidArea.x -= entity.speed;
                        if(entity.solidArea.intersects(gm.obj[i].hitBox)){
                            if(gm.obj[i].collision){
                                entity.collisionOn = true;
                            }
                            if(player){
                                index = i;
                            }
                        }
                        break;
                    case "right":
                        entity.solidArea.x += entity.speed;
                        if(entity.solidArea.intersects(gm.obj[i].hitBox)){
                            if(gm.obj[i].collision){
                                entity.collisionOn = true;
                            }
                            if(player){
                                index = i;
                            }
                        }
                        break;
                }
                //collisionOn = false;
                entity.solidArea.x = entity.solidAreaDefaultX;
                entity.solidArea.x = entity.solidAreaDefaultY;

                gm.obj[i].hitBox.x = gm.obj[i].defaultX;
                gm.obj[i].hitBox.y = gm.obj[i].defaultY;
                
            }
        }

        return index;
    }
}
