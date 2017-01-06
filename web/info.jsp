<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import = "java.util.*" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="myTag" uri="/WEB-INF/tlds/TLDlibrary.tld"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet" type="text/css" />
        <fmt:setLocale value="${sessionScope.locale}" />
        <fmt:setBundle basename="controller.i18n.content" var="lang"/>
        <title>Result</title>
    </head>
    <body>
        <div class="header">
            <span class="date">
                <myTag:DateToday format="dd.MM.yyyy"/>
            </span>
        </div>
        <div class="title">
            <h1>HORSE RACE</h1>
        </div>
        <table cellspacing="4px" cellpadding="0">
            <c:if test="${message!=null}">
            <tr class="description">
                <td style="padding: 50px;"><fmt:message key="error.${message}" bundle="${lang}"/></td>
            </tr>
            <form method="POST" action="./Horserace">
                <tr class="totalRow">
                    <td><input type="submit" name="${submit}" value="OK"></td>
                </tr>
                <input type="hidden" name="command" value="${command}">
                <input type="hidden" name="id" value="${id}">
            </form>
            </c:if>
        </table>
        <%
        Enumeration parameterList = request.getAttributeNames();
        String name = null;
        while( parameterList.hasMoreElements() ) {
            name = parameterList.nextElement().toString();
        }
        request.setAttribute("result", request.getAttribute(name));
        %>
        <c:if test="${message==null}">
            <myTag:ResultParser result="${result}" /> 
        </c:if>
    </body>
</html>
