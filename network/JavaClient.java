package omok.network;

import omok.gui.BoardPanel;
import omok.model.Board;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;



public class JavaClient {
    private BoardPanel grid = new BoardPanel(new Board(15),3);
    private int [] coord = grid.getCurrStone();

    /**
     * Retrieve the document at the specified URL by sending a GET request;
     * return null if the request/connection fails.
     */
    public String sendGet(String urlString) {
        HttpURLConnection con = null;
        try {
            URL url = new URL(urlString);
            con = (HttpURLConnection) url.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = in.readLine()) != null) {
                response.append(line);
            }
            return response.toString();
        } catch (IOException e) {
            //e.printStackTrace();
        } finally {
            if (con != null) {
                con.disconnect();
            }
        }
        return null;
    }

    public static void main(String[] args) {
        String FORMAT = "http://omok.atwebpages.com/play/?pid=%s&x=%d&y=%d";
        String pid = "57cdc4815e1e5";
        int x = 4;
        int y = 5;
        String url = String.format(FORMAT, pid, x, y);
        String response = new JavaClient().sendGet(url);
        System.out.println(response);
    }
}