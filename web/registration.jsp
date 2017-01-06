<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="myTag" uri="/WEB-INF/tlds/TLDlibrary.tld"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet" type="text/css" />
        <fmt:setBundle basename="controller.i18n.content" var="lang"/>
        <title><fmt:message key="title.registration" bundle="${lang}"/></title>
    </head>
    <body>
        <div class="header">
            <span class="date">
                <myTag:DateToday format="dd.MM.yyyy"/>
            </span>
            <span class="button">
                <form method="POST" action="./Horserace">
                    <input type="hidden" name="command" value="redirect">
                    <input type="submit" name="submit_index" value="<fmt:message key='button.logIn' bundle='${lang}'/>">                                    
                </form>
            </span>
        </div>
        <div class="title">
            <h1>HORSE RACE</h1>
        </div>
        <form method="POST" action="./Horserace">
            <table cellspacing="4px" cellpadding="0">
                <tr>
                    <td class="description" colspan="2"> 
                        <fmt:message key="table.description.registration" bundle="${lang}"/>
                    </td>
                </tr>
                <tr>
                    <td> <fmt:message key="table.field.user.login" bundle="${lang}"/>   </td>
                    <td> <input placeholder="example@ukr.net" type="edit" name="login"> </td>
                </tr>
                <tr>
                    <td> <fmt:message key="table.field.user.pass" bundle="${lang}"/>    </td>
                    <td> <input type="password" name="pass">                            </td>
                </tr>
                <tr>
                    <td> <fmt:message key="table.field.user.name" bundle="${lang}"/>    </td>
                    <td> <input type="edit" name="first">                               </td>
                </tr>
                <tr>
                    <td> <fmt:message key="table.field.user.surname" bundle="${lang}"/> </td>
                    <td> <input type="edit" name="second">                              </td>
                </tr>
                <tr>
                    <td class="totalRow" colspan="2"> 
                        <input type="submit" name="submit" value="<fmt:message key='button.accept' bundle='${lang}'/>">
                    </td>
                </tr>
                <input type="hidden" name="command" value="register">       
            </table>
        </form>
    </body>
</html>
