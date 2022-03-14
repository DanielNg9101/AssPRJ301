/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dacnt.custom.tag;

import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.JspFragment;
import javax.servlet.jsp.tagext.TagSupport;

/**
 *
 * @author Daniel NG
 */
public class welcome extends TagSupport {

    private String name;

    @Override
    public int doStartTag() throws JspException {
        JspWriter out = pageContext.getOut();
        try {
            out.println("<h3>Welcome " + name + " come back </h3>");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return SKIP_BODY;
    }

    public void setName(String name) {
        this.name = name;
    }

}
