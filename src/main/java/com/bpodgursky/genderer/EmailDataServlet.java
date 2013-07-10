package com.bpodgursky.genderer;

import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class EmailDataServlet extends HttpServlet {
  private static final Logger LOG = LoggerFactory.getLogger(WebServer.class);

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    long startTime = System.currentTimeMillis();

    String email = req.getParameter("email");

    LOG.info("Request email: "+email);

    try {
      if(email == null){
        throw new RuntimeException("require parameter 'email'!");
      }

      String username = EmailHelper.getUsername(email);
      List<String> tokens = StringTokenizer.getTokens(username);
      DeducedName name = NameHelper.getName(tokens);
      Gender gender = GenderHelper.getGender(name);

      JSONObject obj = new JSONObject();
      obj.put("tokens", new JSONArray(tokens));
      obj.put("name", name.toJson());
      obj.put("gender", gender);

      LOG.info("");
      LOG.info("Request email: "+email);
      LOG.info("Tokens: "+tokens);
      LOG.info("Name: "+name);
      LOG.info("Gender: "+gender);

      resp.getWriter().write(obj.toString());

      long endTime = System.currentTimeMillis();
      LOG.info("Query took "+(endTime-startTime)+" ms");
    } catch (Exception e) {
      throw new RuntimeException(e);
    }

  }
}
