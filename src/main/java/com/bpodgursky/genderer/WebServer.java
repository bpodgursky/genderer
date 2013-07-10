package com.bpodgursky.genderer;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.WebAppContext;

import java.net.URL;
import java.util.concurrent.Semaphore;

public class WebServer implements Runnable{
  public static final int DEFAULT_PORT = 8080;

  private final Semaphore shutdownLock = new Semaphore(0);

  public final void shutdown(){
    shutdownLock.release();
  }

  @Override
  public void run() {
    try{
      Server uiServer = new Server(DEFAULT_PORT);
      final URL warUrl = uiServer.getClass().getClassLoader().getResource("com/bpodgursky/genderer/");
      final String warUrlString = warUrl.toExternalForm();

      WebAppContext webAppContext = new WebAppContext(warUrlString, "/");
      webAppContext.addServlet(EmailDataServlet.class, "/email_data");

      uiServer.setHandler(webAppContext);
      uiServer.start();

      shutdownLock.acquire();
    }catch(Exception e){
      throw new RuntimeException(e);
    }
  }

  public static void main(String[] args) throws InterruptedException {
    new Thread(new WebServer()).start();

    Thread.sleep(100000000);
  }
}
