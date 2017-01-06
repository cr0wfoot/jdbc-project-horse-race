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
        <title><fmt:message key="title.bookie.coeff" bundle="${lang}"/></title>
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
                    <input type="submit" name="submit_boo_cff" value="EN">
                </form>
                <form method="POST" action="./Horserace">
                    <input type="hidden" name="locale" value="ru-RU">
                    <input type="hidden" name="command" value="get_race_by_id">
                    <input type="hidden" name="id" value="${race.id}">
                    <input type="submit" name="submit_boo_cff" value="RU">
                </form>
            </span>
            <span class="button">
                <form method="POST" action="./Horserace">
                    <input type="hidden" name="command" value="get_all_races">
                    <input type="submit" name="submit" value="<fmt:message key='button.mainPage' bundle='${lang}'/>">                                    
                </form>
            </span>
        </div>
        <div class="title">
            <h1>HORSE RACE</h1>
        </div>
        <form method="POST" action="./Horserace">
            <input type="hidden" name="id" value="${race.id}">
            <input type="hidden" name="command" value="boo_coeff">
            <table cellspacing="4px" cellpadding="0">
                <tr>
                    <td class="description" colspan="7"> 
                        <fmt:message key="table.description.bookie.coeff" bundle="${lang}"/>
                    </td>
                </tr>
                <tr class="headRow">
                    <td> <fmt:message key="table.field.id" bundle="${lang}"/>                   </td>
                    <td> <fmt:message key="table.field.horse.name" bundle="${lang}"/>           </td>
                    <td> <fmt:message key="table.field.horse.rider" bundle="${lang}"/>          </td>
                    <td> <fmt:message key="table.field.horse.breed" bundle="${lang}"/>          </td>
                    <td> <fmt:message key="table.field.horse.rank" bundle="${lang}"/>           </td>
                    <td> <fmt:message key="table.field.horse.total" bundle="${lang}"/>          </td>
                    <td> <fmt:message key="table.field.horse.coeff" bundle="${lang}"/>          </td>
                </tr>
                <c:forEach  items="${race.horses}" var="horse" >
                <tr>
                    <td> ${horse.id}                                                                    </td>     
                    <td> ${horse.name}                                                                  </td>
                    <td> ${horse.rider}                                                                 </td>
                    <td> ${horse.breed}                                                                 </td>
                    <td> ${horse.rank}                                                                  </td>
                    <td> ${horse.racesCount}                                                            </td>
                    <td> <input type="edit" name="${horse.id}" size="3" value="${horse.coefficient}" /> </td>
                </tr>
                </c:forEach>
                <tr>
                    <td class="totalRow" colspan="7">
                        <input type="submit" name="submit" value="<fmt:message key='button.accept' bundle='${lang}'/>">
                    </td>
                </tr>
            </table>
        </form>
    </body>
</html>
