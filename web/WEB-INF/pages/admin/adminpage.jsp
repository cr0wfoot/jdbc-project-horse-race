<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="myTag" uri="/WEB-INF/tlds/TLDlibrary.tld"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet" type="text/css" />
        <fmt:setLocale value="${sessionScope.locale}" />
        <fmt:setBundle basename="controller.i18n.content" var="lang"/>
        <title><fmt:message key="title.main" bundle="${lang}"/></title>
    </head>
    <body>
        <div class="header">
            <span class="date">
                <myTag:DateToday format="dd.MM.yyyy"/>
            </span>
            <span class="button" style="float:left;">
                <form method="POST" action="./Horserace">
                    <input type="hidden" name="locale" value="en-EN">
                    <input type="hidden" name="command" value="redirect">
                    <input type="submit" name="submit_adm_pg" value="EN">
                </form>
                <form method="POST" action="./Horserace">
                    <input type="hidden" name="locale" value="ru-RU">
                    <input type="hidden" name="command" value="redirect">
                    <input type="submit" name="submit_adm_pg" value="RU">
                </form>
            </span>
        </div>
        <div class="title">
            <h1>HORSE RACE</h1>
        </div>
        <table cellspacing="4px" cellpadding="0">
            <form method="POST" action="./Horserace">
                <input type="hidden" name="command" value="get_race_by_id">
                <tr>
                    <td class="inputRow">
                        <input type="submit" name="submit" value="<fmt:message key='button.admin.getRaceById' bundle='${lang}'/>">
                    </td>
                    <td>                 
                        <input type="edit" name="id">               
                    </td>
                </tr>
            </form>
            <form method="POST" action="./Horserace">
                <input type="hidden" name="command" value="get_user_by_id">
                <tr>
                    <td class="inputRow">
                        <input type="submit" name="submit" value="<fmt:message key='button.admin.getUserById' bundle='${lang}'/>"> 
                    </td>
                    <td>                  
                        <input type="edit" name="id">                                      
                    </td>
                </tr>
            </form>
            <form method="POST" action="./Horserace">
                <input type="hidden" name="command" value="get_rate_by_id">
                <tr>
                    <td class="inputRow"> 
                        <input type="submit" name="submit" value="<fmt:message key='button.admin.getRateById' bundle='${lang}'/>"> 
                    </td>
                    <td>                  
                        <input type="edit" name="id">                 
                    </td>
                </tr>
            </form>
            <form method="POST" action="./Horserace">
                <input type="hidden" name="command" value="get_horse_by_id">
                <tr>
                    <td class="inputRow"> 
                        <input type="submit" name="submit" value="<fmt:message key='button.admin.getHorseById' bundle='${lang}'/>"> 
                    </td>
                    <td>                  
                        <input type="edit" name="id">                            
                    </td>
                </tr>
            </form>
            <form method="POST" action="./Horserace">
                <input type="hidden" name="command" value="get_rate_by_user_id">
                <tr>
                    <td class="inputRow"> 
                        <input type="submit" name="submit" value="<fmt:message key='button.admin.getRateByUserId' bundle='${lang}'/>"> 
                    </td>
                    <td>                  
                        <input type="edit" name="id">  
                    </td>
                </tr>
            </form>
            <form method="POST" action="./Horserace">
                <input type="hidden" name="command" value="get_user_by_login">
                <tr>
                    <td class="inputRow"> 
                        <input type="submit" name="submit" value="<fmt:message key='button.admin.getUserByLogin' bundle='${lang}'/>"> 
                    </td>
                    <td> 
                        <input type="edit" name="login">
                    </td>
                </tr>
            </form>
            <tr>
                <td class="description" colspan="2"></td>
            </tr>
            <form method="POST" action="./Horserace">
                <input type="hidden" name="command" value="get_all_races">
                <tr>
                    <td class="inputRow" colspan="2">
                        <input type="submit" name="submit" value="<fmt:message key='button.admin.seeRaces' bundle='${lang}'/>">
                    </td>
                </tr>
            </form>
            <form method="POST" action="./Horserace">
                <input type="hidden" name="command" value="get_all_horses">
                <tr>
                    <td class="inputRow" colspan="2">
                        <input type="submit" name="submit_adm_hrs" value="<fmt:message key='button.admin.seeHorses' bundle='${lang}'/>">
                    </td>
                </tr>
            </form>
            <form method="POST" action="./Horserace">
                <input type="hidden" name="command" value="get_all_users">
                <tr>
                    <td class="inputRow" colspan="2">
                        <input type="submit" name="submit" value="<fmt:message key='button.admin.seeUsers' bundle='${lang}'/>">
                    </td>
                </tr>
            </form>
            <form method="POST" action="./Horserace">
                <input type="hidden" name="command" value="get_all_rates">
                <tr>
                    <td class="inputRow" colspan="2">
                        <input type="submit" name="submit" value="<fmt:message key='button.admin.seeRates' bundle='${lang}'/>">
                    </td>
                </tr>
            </form>
        </table>
    </body>
</html>
