package dev.lambdaurora.lambdamap.map.temparyMarker.emc;

import dev.lambdaurora.lambdamap.map.temparyMarker.emc.objects.PlayerData;
import dev.lambdaurora.lambdamap.map.temparyMarker.TempMarker;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;
import java.util.*;

public class GetPlayers extends TimerTask {
    /*public static void main(String[] args) throws IOException {
        runGetPlayers();
    }*/

    public static void runGetPlayers() throws IOException {
        //Timer timer = new Timer();
        //timer.schedule(new GetPlayers(), 0, 5000);
        new GetPlayers();
    }

    public void getPlayers() throws IOException {
        List<PlayerData> playerData = getPlayerData();
        PlayerData playerData1 = playerData.get(0);
        TempMarker.tempMarker(playerData1.x, playerData1.y, playerData1.z,playerData1.playername);
    }

    public List<PlayerData> getPlayerData() throws JSONException, IOException {
        String strURL = "https://emc-toolkit.vercel.app/api/aurora/onlineplayers";
        URL url = new URL(strURL);
        Scanner sc = new Scanner(url.openStream());
        //Instantiating the StringBuffer class to hold the result
        StringBuffer sb = new StringBuffer();
        while (sc.hasNext()) {
            sb.append(sc.next());
            //System.out.println(sc.next());
        }
        //Retrieving the String from the String Buffer object
        String urldata = sb.toString();
        final String JSON_DATA = "{"
                + "  \"playerdata\": " + urldata
                + "}";
        final JSONObject obj = new JSONObject(JSON_DATA);
        final JSONArray geodata = obj.getJSONArray("playerdata");
        final int n = geodata.length();
        List<PlayerData> playerDataList = new ArrayList<>();
        for (int i = 0; i < n; ++i) {
            final JSONObject playerd = geodata.getJSONObject(i);
            PlayerData playerData = new PlayerData(playerd.getInt("x"), playerd.getInt("y"), playerd.getInt("z"), playerd.getBoolean("underground"), playerd.getString("name"));
            System.out.println("Player name: " + playerData.playername + "underground:" + playerData.ug + " x:" + playerData.x + " y:" + playerData.y + " z:" + playerData.z);
            playerDataList.add(playerData);
        }
        return playerDataList;
    }

    @Override
    public void run() {
        try {
            getPlayers();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
