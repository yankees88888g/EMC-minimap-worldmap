package dev.lambdaurora.lambdamap.map.temparyMarker.emc.objects;

public class PlayerData {
    public String playername;
    public int x;
    public int y;
    public int z;
    public boolean ug;
    public PlayerData(int x, int y, int z, boolean underground, String playername){
        this.x = x;
        this.y = y;
        this.z = z;
        this.ug = underground;
        this.playername = playername;
    }
}
