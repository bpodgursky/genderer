package com.bpodgursky.genderer;

import org.apache.log4j.xml.DOMConfigurator;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.WebAppContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URL;
import java.util.concurrent.Semaphore;

public class WebServer implements Runnable{
  private static final Logger LOG = LoggerFactory.getLogger(WebServer.class);

  public static final int DEFAULT_PORT = 8080;

  private final Semaphore shutdownLock = new Semaphore(0);

  public final void shutdown(){
    shutdownLock.release();
  }

  private final int port;
  public WebServer(int port){
    this.port = port;
  }

  @Override
  public void run() {
    try{
      LOG.info("Starting webserver...");

      Server uiServer = new Server(port);
      final URL warUrl = uiServer.getClass().getClassLoader().getResource("com/bpodgursky/genderer/www/");
      final String warUrlString = warUrl.toExternalForm();

      WebAppContext webAppContext = new WebAppContext(warUrlString, "/");
      webAppContext.addServlet(EmailDataServlet.class, "/email_data");

      uiServer.setHandler(webAppContext);
      uiServer.start();

      LOG.info("Started!");

      shutdownLock.acquire();
    }catch(Exception e){
      throw new RuntimeException(e);
    }
  }

  public static void main(String[] args) throws InterruptedException {
    DOMConfigurator.configure(WebServer.class.getResource("/com/bpodgursky/genderer/log4j.xml"));

    int port;
    if(args.length == 1){
      port = Integer.parseInt(args[0]);
    }else{
      port = DEFAULT_PORT;
    }

    Thread thread = new Thread(new WebServer(port));
    thread.start();

    thread.join();
  }
}
