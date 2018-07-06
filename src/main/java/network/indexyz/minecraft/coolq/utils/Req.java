package network.indexyz.minecraft.coolq.utils;

import net.minecraft.entity.player.EntityPlayerMP;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

public class Req {
    private static Request.Builder getRequest() {
        Request.Builder builder = new Request.Builder();

        if (!(Config.accessToken.equals(""))) {
            builder.addHeader("Authorization", "Token " + Config.accessToken);
        }

        return builder;
    }


    static String getUserNameById(int group, int userId) throws IOException {
        OkHttpClient client = new OkHttpClient();
        Request request = getRequest()
                .url(Config.sendHost + "/get_group_member_info?group_id=" + group + "&user_id=" + userId)
                .build();
        Response response = client.newCall(request).execute();
        String ret = response.body().string();
        response.close();
        return ret;
    }

    public static void sendToQQ(EntityPlayerMP player, String message) throws IOException {
        OkHttpClient client = new OkHttpClient();
        Request request = getRequest()
                .url(Config.sendHost + "/send_group_msg?group_id=" + Config.groupId + "&message=[" + player.getName() + "]: " + message)
                .build();

        client.newCall(request).execute().close();
    }
}
