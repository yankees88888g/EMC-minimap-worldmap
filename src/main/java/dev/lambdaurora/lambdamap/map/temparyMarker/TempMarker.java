package dev.lambdaurora.lambdamap.map.temparyMarker;

import dev.lambdaurora.lambdamap.map.marker.Marker;
import net.minecraft.text.Text;

public class TempMarker {
    public static void tempMarker(int x, int y, int z, String player) {
        Marker marker = new Marker(null,null,x,y,z, 0, Text.of(player));
        System.out.println("good");
    }
}
