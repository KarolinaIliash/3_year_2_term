package ServerSide;

import org.json.*;
import javax.websocket.*;
import java.io.IOException;

// wrapper on Session object.
public class WebSocketSession {
    Session session = null;
    Thread fireRunner = null;

    WebSocketSession(Session session){
        this.session = session;
    }

    int sleepingTime = 10;

    double time = 0;

    public void fireInit(double x, double y){
        double v0 = 10 + Math.random() * 70; // min + Math.random() * (max - min)
        double angle = Math.PI / 4;
        double gravity = 5 + Math.random() * 16;

        fire(v0, angle, x, y, gravity);
    }

    public void fire(double v0, double angle, double startX, double startY, double gravity) {
        if(fireRunner != null){
            return;
        }
        CoreFly coreFly = new CoreFly(v0, angle, startX, startY, gravity);
        sendInfo(gravity, angle, v0);
        fireRunner = new Thread( ()->{
            while(!Thread.currentThread().isInterrupted()){
                coreFly.calcNext(time/ 1000.);
                sendToClient(coreFly.x, coreFly.y);

                if(coreFly.y <= 0){
                    sendToClient("finish");
                    time = 0;
                    break;
                }
                time += sleepingTime;
                try {
                    Thread.sleep(sleepingTime);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            fireRunner = null;
        }
        );

        fireRunner.setDaemon(true);
        fireRunner.start();
    }

    void sendInfo(double gravity, double angle, double v0){
        String json = new JSONStringer()
                .object()
                .key("command")
                .value("gravity_angle")
                .key("gravity")
                .value(gravity)
                .key("angle")
                .value(angle)
                .key("v0")
                .value(v0)
                .endObject()
                .toString();
        try {
            session.getBasicRemote().sendText(json);
        } catch(IOException e){}
    }

    void sendToClient(double newX, double newY){
        String json = new JSONStringer()
                .object()
                .key("command")
                .value("new_position")
                .key("new_x")
                .value(newX)
                .key("new_y")
                .value(newY)
                .endObject()
                .toString();
        try {
            session.getBasicRemote().sendText(json);
        } catch(IOException e){}
    }

    void sendToClient(String command){
        String json = new JSONStringer()
                .object()
                .key("command")
                .value(command)
                .endObject()
                .toString();
        try {
            session.getBasicRemote().sendText(json);
        } catch(IOException e){}
    }
}
