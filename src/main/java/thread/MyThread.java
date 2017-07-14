/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thread;

import java.io.IOException;
import main.MyProperties;
import main.WatchDir;

/**
 *
 * @author mcaikovs
 */
public class MyThread extends Thread {

    WatchDir wd;
    MyProperties p;

    public MyThread(MyProperties p) {
        this.p = p;
    }

    @Override
    public void run() {
        System.out.println(">MyThread:run");
        startTool();
        System.out.println("<MyThread:run");
    }

    public boolean isWatching() {
        return wd != null;
    }

    public void startTool() {
        System.out.println(">MyThread:startTool");
        if (wd == null) {
            System.out.println("MyThread:Starting");
            try {
                wd = new WatchDir(p);
                wd.processEvents();

            } catch (IOException ex) {
                System.out.println("MyThread:Failed to start");
                ex.printStackTrace();
                stopTool();
                System.out.println("MyThread:Preventively stopped");
                return;
            }
            System.out.println("MyThread:Started");
        } else {
            System.out.println("MyThread:Working already");
        }
        System.out.println("<MyThread:startTool");
    }

    public void stopTool() {
        System.out.println(">MyThread:stopTool");
        if (wd != null) {
            System.out.println("MyThread:Stopping");
            try {
                wd.stop();
            } catch (IOException ex) {
                System.out.println("MyThread:Exception while stopping: " + ex);
            }
            wd = null;
            System.out.println("MyThread:Stoppped");
        } else {
            System.out.println("MyThread:Stoppped already");
        }
        System.out.println("<MyThread:stopTool");
    }
}
