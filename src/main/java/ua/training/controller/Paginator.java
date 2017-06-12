package ua.training.controller;

import java.io.Writer;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class Paginator extends SimpleTagSupport {
    private String uri;
    private int currentPage;
    private int totalPages;
    private int maxLinks = 10;

    private Writer getWriter() {
        JspWriter jspWriter = getJspContext().getOut();
        return jspWriter;
    }

    @Override
    public void doTag() throws JspException {
        Writer out = getWriter();
        boolean lastPage = currentPage == totalPages;
        int pageStart = Math.max(currentPage - maxLinks / 2, 1);
        int pageEnd = pageStart + maxLinks;
        if (pageEnd > totalPages + 1) {
            int delta = pageEnd - totalPages;
            pageStart -= delta - 1;
            if (pageStart < 1)
                pageStart = 1;
            pageEnd = totalPages + 1;
        }

        try {
            out.write("<ul class=\"booking-request-page-list\">");

            if (currentPage > 1)
                out.write(constructLink(currentPage - 1, "Previous", "previous-page"));

            for (int i = pageStart; i < pageEnd; i++) {
                if (i == currentPage) {
                    out.write("<li class=\"current-page" + (lastPage && i == totalPages ? " last-page" : "") + "\">" + currentPage + "</li>");
                } else {
                    out.write(constructLink(i));
                }
            }

            if (!lastPage)
                out.write(constructLink(currentPage + 1, "Next", "next-page last-page"));

            out.write("</ul>");

        } catch (java.io.IOException ex) {
            throw new JspException("Error in Paginator tag", ex);
        }
    }

    private String constructLink(int page) {
        return constructLink(page, String.valueOf(page), null);
    }

    private String constructLink(int page, String text, String className) {
        StringBuilder link = new StringBuilder("<li");
        if (className != null) {
            link.append(" class=\"");
            link.append(className);
            link.append("\"");
        }
        link.append(">")
                .append("<a href=\"")
                .append(uri.replace("##", String.valueOf(page)))
                .append("\">")
                .append(text)
                .append("</a></li>");
        return link.toString();
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public void setMaxLinks(int maxLinks) {
        this.maxLinks = maxLinks;
    }
}