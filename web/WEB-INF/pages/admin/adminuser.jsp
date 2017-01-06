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
        <title><fmt:message key="title.admin.user" bundle="${lang}"/></title>
    </head>
    <body>
        <div class="header">
            <span class="date">
                <myTag:DateToday format="dd.MM.yyyy"/>
            </span>
            <span class="button" style="float:left;">
                <form method="POST" action="./Horserace">
                    <input type="hidden" name="locale" value="en-EN">
                    <input type="hidden" name="command" value="get_all_users">
                    <input type="submit" name="submit" value="EN">
                </form>
                <form method="POST" action="./Horserace">
                    <input type="hidden" name="locale" value="ru-RU">
                    <input type="hidden" name="command" value="get_all_users">
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
                <td class="description" colspan="10"> 
                    <fmt:message key="table.description.admin.user" bundle="${lang}"/>
                </td>
            </tr>
            <tr class="headRow">
                <td> <fmt:message key="table.field.id" bundle="${lang}"/>               </td>
                <td> <fmt:message key="table.field.user.login" bundle="${lang}"/>       </td>
                <td> <fmt:message key="table.field.user.name" bundle="${lang}"/>        </td>
                <td> <fmt:message key="table.field.user.surname" bundle="${lang}"/>     </td>
                <td> <fmt:message key="table.field.user.date" bundle="${lang}"/>        </td>
                <td> <fmt:message key="table.field.user.balance" bundle="${lang}"/>     </td>
                <td> <fmt:message key="table.field.user.total" bundle="${lang}"/>       </td>
                <td> <fmt:message key="table.field.user.access" bundle="${lang}"/>      </td>
                <td>                                                                    </td>
                <td>                                                                    </td>
            </tr>
            <c:forEach  items="${userList}" var="user" >
            <form method="POST" action="./Horserace">
                <input type="hidden" name="id" value="${user.id}">
                <input type="hidden" name="command" value="adm_user_update">
                <tr>
                    <td> ${user.id}                                                        </td>
                    <td> ${user.login}                                                     </td>
                    <td> ${user.firstName}                                                 </td>
                    <td> ${user.secondName}                                                </td>
                    <td> ${user.regDate}                                                   </td>
                    <td> ${user.budget}                                                    </td>
                    <td> ${user.totalRatesCount}                                           </td>
                    <td> <input type="edit" name="access" size="2" value="${user.access}"> </td>
                    <td class="inputRow"> 
                        <input type="submit" name="submit1" value="<fmt:message key='button.save' bundle='${lang}'/>"> 
                    </td>
                    <td class="inputRow">
                        <input type="submit" name="submit2" value="<fmt:message key='button.delete' bundle='${lang}'/>"> 
                    </td>
                </tr>
            </form>
            </c:forEach>
        </table>        
    </body>
</html>
