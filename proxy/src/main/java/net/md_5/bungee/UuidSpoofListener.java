package net.md_5.bungee;

import net.md_5.bungee.api.chat.hover.content.Entity;
import net.md_5.bungee.api.event.LoginEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.chat.ComponentSerializer;
import net.md_5.bungee.event.EventHandler;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;

import java.util.UUID;

public final class UuidSpoofListener implements Listener {

    public String dashUuid(String in) {
        return in.replaceFirst(
                "(\\p{XDigit}{8})(\\p{XDigit}{4})(\\p{XDigit}{4})(\\p{XDigit}{4})(\\p{XDigit}+)", "$1-$2-$3-$4-$5"
        );
    }

    @EventHandler
    public void on(LoginEvent e) {
        try {
            CloseableHttpClient client = HttpClients.createDefault();
            HttpPost post = new HttpPost("https://api.mojang.com/profiles/minecraft");
            post.setHeader("Content-Type", "application/json");
            post.setHeader("Accept", "application/json");
            post.setEntity(new StringEntity("[\"" + e.getConnection().getName() + "\"]"));

            CloseableHttpResponse response = client.execute(post);

            e.getConnection().setUniqueId(
                    UUID.fromString(
                            dashUuid(new JSONArray(EntityUtils.toString(response.getEntity())).getJSONObject(0).getString("id"))));
        } catch (Exception exception) {
            exception.printStackTrace();
            e.setCancelReason(ComponentSerializer.parse("{\"text\": \"There was an error connecting\"}"));
            e.setCancelled(true);
        }
    }
}
