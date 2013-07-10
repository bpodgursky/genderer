package com.bpodgursky.genderer;

import org.json.JSONArray;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class EmailDataServlet extends HttpServlet {

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    String email = req.getParameter("email");
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

      resp.getWriter().write(obj.toString());

    } catch (Exception e) {
      throw new RuntimeException(e);
    }

  }
}
