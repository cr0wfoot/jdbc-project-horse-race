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
        <title><fmt:message key="title.user.budget" bundle="${lang}"/></title>
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
                    <input type="submit" name="submit_usr_bdgt" value="EN">
                </form>
                <form method="POST" action="./Horserace">
                    <input type="hidden" name="locale" value="ru-RU">
                    <input type="hidden" name="command" value="redirect">
                    <input type="submit" name="submit_usr_bdgt" value="RU">
                </form>
            </span>
            <span class="button">
                <form method="POST" action="./Horserace" >
                    <input type="hidden" name="command" value="get_all_races">
                    <input type="submit" name="submit" value="<fmt:message key='button.mainPage' bundle='${lang}'/>">                                    
                </form>
                <form method="POST" action="./Horserace" >
                    <input type="hidden" name="command" value="usr_account">
                    <input type="submit" name="submit" value="<fmt:message key='button.user.profile' bundle='${lang}'/>">                                    
                </form>
            </span>
        </div>
        <div class="title">
            <h1>HORSE RACE</h1>
        </div>
        <form method="POST" action="./Horserace" style="margin-top:170px;">
            <input type="hidden" name="command" value="usr_refill">
            <table cellspacing="4px" cellpadding="0">
                <tr>
                    <td> <fmt:message key='button.insert' bundle='${lang}'/> </td>
                    <td> <input type="edit" name="money">                    </td>
                </tr>
                <tr>
                    <td class="totalRow" colspan="2">
                        <input type="submit" name="submit" value="<fmt:message key='button.accept' bundle='${lang}'/>">
                    </td>
                </tr>
            </table>
        </form>
    </body>
</html>
