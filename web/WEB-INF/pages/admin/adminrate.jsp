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
        <title><fmt:message key="title.admin.rate" bundle="${lang}"/></title>
    </head>
    <body>
        <div class="header">
            <span class="date">
                <myTag:DateToday format="dd.MM.yyyy"/>
            </span>
            <span class="button" style="float:left;">
                <form method="POST" action="./Horserace">
                    <input type="hidden" name="locale" value="en-EN">
                    <input type="hidden" name="command" value="get_all_rates">
                    <input type="submit" name="submit" value="EN">
                </form>
                <form method="POST" action="./Horserace">
                    <input type="hidden" name="locale" value="ru-RU">
                    <input type="hidden" name="command" value="get_all_rates">
                    <input type="submit" name="submit" value="RU">
                </form>
            </span>
            <span class="button">
                <form method="POST" action="./Horserace">
                    <input type="hidden" name="command" value="redirect">
                    <input type="submit" name="submit_adm_pg" value="<fmt:message key='button.mainPage' bundle='${lang}'/>">                                    
                </form>
            </span>
        </div>
        <div class="title">
            <h1>HORSE RACE</h1>
        </div>
        <table cellspacing="4px" cellpadding="0">
            <tr>
                <td class="description" colspan="6"> 
                    <fmt:message key="table.description.admin.rate" bundle="${lang}"/>
                </td>
            </tr>
            <tr>
                <td> <fmt:message key="table.field.id" bundle="${lang}"/>          </td>
                <td> <fmt:message key="table.field.rate.user" bundle="${lang}"/>   </td>
                <td> <fmt:message key="table.field.rate.type" bundle="${lang}"/>   </td>
                <td> <fmt:message key="table.field.rate.money" bundle="${lang}"/>  </td>
                <td> <fmt:message key="table.field.rate.horses" bundle="${lang}"/> </td>
                <td> <fmt:message key="table.field.rate.races" bundle="${lang}"/>  </td>
            </tr>
            <c:forEach  items="${rateList}" var="rate" >
            <tr>   
                <td> ${rate.id}               </td>
                <td class="idCell"> 
                    <form method="POST" action="./Horserace">
                        <input type="hidden" name="command" value="get_user_by_id">
                        <input type="submit" name="id" value="${rate.userId}">
                    </form>                                               
                </td>
                <td> ${rate.type}             </td>
                <td> ${rate.money}            </td>
                <td class="idCell"> 
                    <form method="POST" action="./Horserace">
                        <input type="hidden" name="command" value="get_horse_by_id">
                        <c:forEach  items="${rate.horses}" var="horse" >
                        <input type="submit" name="id" value="${horse.id}">
                        </c:forEach>
                    </form>                                                
                </td>
                <td class="idCell"> 
                    <form method="POST" action="./Horserace">
                        <input type="hidden" name="command" value="get_race_by_id">
                        <c:forEach  items="${rate.races}" var="rate" >  
                        <input type="submit" name="id" value="${rate}">
                        </c:forEach> 
                    </form>                                                
                </td>
            </tr>
            </c:forEach>
        </table>
    </body>
</html>
