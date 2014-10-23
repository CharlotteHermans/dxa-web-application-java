<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="com.sdl.webapp.common.api.ScreenWidth" %>
<%@ page import="com.sdl.webapp.main.WebAppConstants" %>
<%@ taglib prefix="tri" uri="http://www.sdl.com/tridion-reference-impl" %>
<jsp:useBean id="regionModel" type="com.sdl.webapp.common.api.model.Region" scope="request"/>
<div typeof="Region" resource="4-Column">
<%
    int entityCount = regionModel.getEntities().size();

    int cols = request.getAttribute(WebAppConstants.SCREEN_WIDTH) == ScreenWidth.SMALL ? 2 : 4;
    int rows = (int) Math.ceil(entityCount / (double) cols);

    for (int i = 0; i < rows; i++) {
        %><div class="row"><%
            for (int j = 0; j < cols && (cols * i + j < entityCount); j++) {
                %><div class="col-sm-6 col-md-3"><tri:entity region="4-Column" index="<%= cols * i + j %>"/></div><%
            }
        %></div><%
    }
%>
</div>