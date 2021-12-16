package Main;

import Entities.Entity;

public class Collision {
    public boolean collisionOn = false;
    Game gm;

    public Collision(Game gm){
        this.gm = gm;
    }

    public void colCheck(Entity entity){

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
                    //Check if the tile has a code
                    if(gm.map.tiles[tile1].interactable){
                        gm.action.code = gm.map.tiles[tile1].code;
                    }
                    else if(gm.map.tiles[tile2].interactable){
                        gm.action.code = gm.map.tiles[tile2].code;
                    }
                    entity.collisionOn = true;
                }
                break;
            case "down":
                botRow = (entityBottom + entity.speed)/gm.tileSize;
                tile1 = gm.map.layout[leftCol][botRow];
                tile2 = gm.map.layout[rightCol][botRow];
                if(gm.map.tiles[tile1].collision || gm.map.tiles[tile2].collision){
                    if(gm.map.tiles[tile1].code != null){
                        gm.action.code = gm.map.tiles[tile1].code;
                    }
                    else if(gm.map.tiles[tile2].code != null){
                        gm.action.code = gm.map.tiles[tile2].code;
                    }
                    entity.collisionOn = true;
                }
                break;
            case "left":
                leftCol = (entityLeft - entity.speed)/gm.tileSize;
                tile1 = gm.map.layout[leftCol][topRow];
                tile2 = gm.map.layout[leftCol][botRow];
                if(gm.map.tiles[tile1].collision || gm.map.tiles[tile2].collision){
                    if(gm.map.tiles[tile1].code != null){
                        gm.action.code = gm.map.tiles[tile1].code;
                    }
                    else if(gm.map.tiles[tile2].code != null){
                        gm.action.code = gm.map.tiles[tile2].code;
                    }
                    entity.collisionOn = true;
                }
                break;
            case "right":
                rightCol = (entityRight + entity.speed)/gm.tileSize;
                tile1 = gm.map.layout[rightCol][topRow];
                tile2 = gm.map.layout[rightCol][botRow];
                if(gm.map.tiles[tile1].collision || gm.map.tiles[tile2].collision){
                    if(gm.map.tiles[tile1].code != null){
                        gm.action.code = gm.map.tiles[tile1].code;
                    }
                    else if(gm.map.tiles[tile2].code != null){
                        gm.action.code = gm.map.tiles[tile2].code;
                    }
                    entity.collisionOn = true;
                }
                break;
        }
    }
}
