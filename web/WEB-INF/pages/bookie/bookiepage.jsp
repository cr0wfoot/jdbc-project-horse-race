<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
        <title><fmt:message key="title.race" bundle="${lang}"/></title>
    </head>
    <body>
        <div class="header">
            <span class="date">
                <myTag:DateToday format="dd.MM.yyyy"/>
            </span>
            <span class="button" style="float:left;">
                <form method="POST" action="./Horserace">
                    <input type="hidden" name="locale" value="en-EN">
                    <input type="hidden" name="command" value="get_all_races">
                    <input type="submit" name="submit" value="EN">
                </form>
                <form method="POST" action="./Horserace">
                    <input type="hidden" name="locale" value="ru-RU">
                    <input type="hidden" name="command" value="get_all_races">
                    <input type="submit" name="submit" value="RU">
                </form>
            </span>
        </div>
        <div class="title">
            <h1>HORSE RACE</h1>
        </div>
        <table cellspacing="4px" cellpadding="0">
            <tr>
                <td class="description" colspan="5"> 
                    <fmt:message key="table.description.bookie.page" bundle="${lang}"/>
                </td>
            </tr>
            <tr class="headRow">
                <td> <fmt:message key="table.field.race.place" bundle="${lang}"/>    </td>
                <td> <fmt:message key="table.field.race.distance" bundle="${lang}"/> </td>
                <td> <fmt:message key="table.field.race.date" bundle="${lang}"/>     </td>
                <td> <fmt:message key="table.field.race.status" bundle="${lang}"/>   </td>
                <td>                                                                 </td>
            </tr>
            <c:forEach  items="${raceList}" var="race" >
            <tr>
                <td> ${race.place}                                                      </td>
                <td> ${race.distance}m                                                  </td>
                <td> ${race.date}                                                       </td>
                <td> ${race.status}                                                     </td>
                <form method="POST" action="./Horserace">
                    <td class="inputRow"> 
                        <input type="hidden" name="command" value="get_race_by_id">
                        <input type="hidden" name="id" value="${race.id}">
                        <input type="submit" name="submit_boo_cff" value="<fmt:message key='button.bookie.coeff' bundle='${lang}'/>">
                    </td> 
                </form>   
            </tr>
            </c:forEach>
        </table>
    </body>
</html>
