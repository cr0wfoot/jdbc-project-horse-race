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
        <title><fmt:message key="title.admin.horse" bundle="${lang}"/></title>
    </head>
    <body>
        <div class="header">
            <span class="date">
                <myTag:DateToday format="dd.MM.yyyy"/>
            </span>
            <span class="button" style="float:left;">
                <form method="POST" action="./Horserace">
                    <input type="hidden" name="locale" value="en-EN">
                    <input type="hidden" name="command" value="get_all_horses">
                    <input type="submit" name="submit_adm_hrs" value="EN">
                </form>
                <form method="POST" action="./Horserace">
                    <input type="hidden" name="locale" value="ru-RU">
                    <input type="hidden" name="command" value="get_all_horses">
                    <input type="submit" name="submit_adm_hrs" value="RU">
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
                <td class="description" colspan="8"> 
                    <fmt:message key="table.description.admin.horse.change" bundle="${lang}"/>
                </td>
            </tr>
            <tr class="headRow">
                <td> <fmt:message key="table.field.id" bundle="${lang}"/>                                </td>
                <td> <fmt:message key="table.field.horse.name" bundle="${lang}"/>                        </td>
                <td> <fmt:message key="table.field.horse.rider" bundle="${lang}"/>                       </td>
                <td> <fmt:message key="table.field.horse.breed" bundle="${lang}"/>                       </td>
                <td> <fmt:message key="table.field.horse.rank" bundle="${lang}"/>                        </td>
                <td> <fmt:message key="table.field.horse.total" bundle="${lang}"/>                       </td>
                <td>                                                                                     </td>
                <td>                                                                                     </td>
            </tr>
            <c:forEach  items="${horseList}" var="horse" >
            <form method="POST" action="./Horserace">
                <input type="hidden" name="command" value="adm_horse_update">
                <input type="hidden" name="id" value="${horse.id}">
                <tr>
                    <td>                  ${horse.id}                                                        </td>     
                    <td>                  <input type="edit" name="name" value="${horse.name}">              </td>
                    <td>                  <input type="edit" name="rider" value="${horse.rider}">            </td>
                    <td>                  <input type="edit" name="breed" size="10" value="${horse.breed}">  </td>
                    <td>                  <input type="edit" name="rank" size="3" value="${horse.rank}">     </td>
                    <td>                  <input type="edit" name="rc" size="2" value="${horse.racesCount}"> </td>
                    <td class="inputRow"> 
                        <input type="submit" name="update" value="<fmt:message key='button.save' bundle='${lang}'/>"> 
                    </td>
                    <td class="inputRow"> 
                        <input type="submit" name="delete" value="<fmt:message key='button.delete' bundle='${lang}'/>"> 
                    </td>
                </tr>
            </form>
            </c:forEach>
            <tr>
                <td class="description" colspan="8"> 
                    <fmt:message key="table.description.admin.horse.new" bundle="${lang}"/>
                </td>
            </tr>
            <form method="POST" action="./Horserace">
                <input type="hidden" name="command" value="adm_horse_new">
                <tr>
                    <td colspan="4"> <fmt:message key="table.field.horse.name" bundle="${lang}"/>:  </td>
                    <td colspan="4"> <input type="edit" name="name">                                </td>
                </tr>
                <tr>
                    <td colspan="4"> <fmt:message key="table.field.horse.rider" bundle="${lang}"/>: </td>
                    <td colspan="4"> <input  type="edit" name="rider">                              </td>
                </tr>
                <tr>
                    <td colspan="4"> <fmt:message key="table.field.horse.breed" bundle="${lang}"/>: </td>
                    <td colspan="4"> <input type="edit" name="breed">                               </td>
                </tr>
                <tr>
                    <td colspan="4"> <fmt:message key="table.field.horse.rank" bundle="${lang}"/>:  </td>
                    <td colspan="4"> <input type="edit" size="3" name="rank">                       </td>
                </tr>
                <tr>
                    <td colspan="4"> <fmt:message key="table.field.horse.total" bundle="${lang}"/>: </td>
                    <td colspan="4"> <input type="edit" size="3" name="rc">                         </td>
                </tr>
                <tr>
                    <td class="totalRow" colspan="8">
                        <input type="submit" name="submit" value="<fmt:message key='button.insert' bundle='${lang}'/>">
                    </td>
                </tr>
            </form>
        </table>
    </body>
</html>
