/**
 * Created by a306877 on 3/9/2015.
 */
public class Tile {
    int worldX;
    int worldY;
    BlockType type;
    boolean solid = false;
    
    public void setWorldX(int worldX)
    {
        this.worldX = worldX;
    }
    public void setWorldY(int worldY)
    {
        this.worldY = worldY;
    }
    
    public int getWorldX()
    {
        return worldX;
    }
    public int getWorldY()
    {
        return worldY;
    }
    
    public BlockType getType()
    {
        return type;
    }
    public void setType(BlockType type)
    {
        this.type = type;
    }
    
    public boolean isSolid()
    {
        return solid;
    }
    public void setSolid(boolean solid)
    {
        this.solid = solid;
    }
    
    
    



}
