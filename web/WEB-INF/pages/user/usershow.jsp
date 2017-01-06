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
                    <input type="hidden" name="command" value="get_race_by_id">
                    <input type="hidden" name="id" value="${race.id}">
                    <input type="submit" name="submit_usr_res" value="EN">
                </form>
                <form method="POST" action="./Horserace">
                    <input type="hidden" name="locale" value="ru-RU">
                    <input type="hidden" name="command" value="get_race_by_id">
                    <input type="hidden" name="id" value="${race.id}">
                    <input type="submit" name="submit_usr_res" value="RU">
                </form>
            </span>
            <span class="button">
                <form method="POST" action="./Horserace" style="float:left;">
                    <input type="hidden" name="command" value="get_all_races">
                    <input type="submit" name="submit" value="<fmt:message key='button.mainPage' bundle='${lang}'/>">                                    
                </form>
                <form method="POST" action="./Horserace" style="float:left;">
                    <input type="hidden" name="command" value="usr_account">
                    <input type="submit" name="submit" value="<fmt:message key='button.user.profile' bundle='${lang}'/>">                                    
                </form>
            </span>
        </div>
        <div class="title">
            <h1>HORSE RACE</h1>
        </div>
        <table cellspacing="4px" cellpadding="0">
            <tr>
                <td class="description" colspan="8"> 
                    ${race.date}: ${race.place}, distance - ${race.distance}.                                
                </td>
            </tr>
            <tr class="headRow">
                <td> <fmt:message key="table.field.id" bundle="${lang}"/>                    </td>
                <td> <fmt:message key="table.field.horse.name" bundle="${lang}"/>            </td>
                <td> <fmt:message key="table.field.horse.rider" bundle="${lang}"/>           </td>
                <td> <fmt:message key="table.field.horse.breed" bundle="${lang}"/>           </td>
                <td> <fmt:message key="table.field.horse.rank" bundle="${lang}"/>            </td>
                <td> <fmt:message key="table.field.horse.total" bundle="${lang}"/>           </td>
                <td> <fmt:message key="table.field.horse.coeff" bundle="${lang}"/>           </td>
                <td> <fmt:message key="table.field.horse.result" bundle="${lang}"/>          </td>
            </tr>
            <c:forEach  items="${race.horses}" var="horse" >
            <tr>
                <td> ${horse.id}                                                                     </td>
                <td> ${horse.name}                                                                   </td>
                <td> ${horse.rider}                                                                  </td>
                <td> ${horse.breed}                                                                  </td>
                <td> <myTag:NumberFormat number="${horse.rank / horse.racesCount}" format="##.00"/>  </td>
                <td> ${horse.racesCount}                                                             </td>
                <td> ${horse.coefficient}                                                            </td>
                <td> ${horse.result}                                                                 </td>
            </tr>
            </c:forEach>
        </table>  
    </body>
</html>
