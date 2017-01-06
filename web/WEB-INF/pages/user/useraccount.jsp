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
        <title><fmt:message key="title.user.account" bundle="${lang}"/></title>
    </head>
    <body>
        <div class="header">
            <span class="date">
                <myTag:DateToday format="dd.MM.yyyy"/>
            </span>
            <span class="button" style="float:left;">
                <form method="POST" action="./Horserace">
                    <input type="hidden" name="locale" value="en-EN">
                    <input type="hidden" name="command" value="usr_account">
                    <input type="submit" name="submit" value="EN">
                </form>
                <form method="POST" action="./Horserace">
                    <input type="hidden" name="locale" value="ru-RU">
                    <input type="hidden" name="command" value="usr_account">
                    <input type="submit" name="submit" value="RU">
                </form>
            </span>
            <span class="button">
                <form method="POST" action="./Horserace" style="float:left;">
                    <input type="hidden" name="command" value="get_all_races">
                    <input type="submit" name="submit" value="<fmt:message key='button.mainPage' bundle='${lang}'/>">                                    
                </form>
            </span>
        </div>
        <div class="title">
            <h1>HORSE RACE</h1>
        </div>
        <table cellspacing="4px" cellpadding="0">
            <tr>
                <td class="description" colspan="4"> 
                    <fmt:message key="table.description.user.account.info" bundle="${lang}"/>                             
                </td>
            </tr>
            <tr>
                <td colspan="2"> <fmt:message key="table.field.user.name" bundle="${lang}"/>:    </td>
                <td colspan="2"> ${user.firstName}                                               </td>
            </tr>
            <tr>
                <td colspan="2"> <fmt:message key="table.field.user.surname" bundle="${lang}"/>: </td>
                <td colspan="2"> ${user.secondName}                                              </td>
            </tr>
            <tr>
                <td colspan="2"> <fmt:message key="table.field.user.date" bundle="${lang}"/>:    </td>
                <td colspan="2"> ${user.regDate}                                                 </td>
            </tr>
            <tr>
                <td colspan="2"> <fmt:message key="table.field.user.total" bundle="${lang}"/>:   </td>
                <td colspan="2"> ${user.totalRatesCount}                                         </td>
            </tr>
            <tr>
                <td colspan="2"> <fmt:message key="table.field.user.balance" bundle="${lang}"/>: </td>
                <td>             ${user.budget}                                                  </td>
                <form method="POST" action="./Horserace">
                    <td class="inputRow">   
                        <input type="hidden" name="command" value="redirect">
                        <input type="submit" name="submit_usr_bdgt" value="<fmt:message key='button.user.refill' bundle='${lang}'/>">                                    
                    </td>
                </form>
            </tr>
            <tr>
                <td class="description" colspan="4"> 
                    <fmt:message key="table.description.user.account.results" bundle="${lang}"/>                                  
                </td>
            </tr>
            <tr class="headRow">
                <td>             <fmt:message key="table.field.id" bundle="${lang}"/>              </td>
                <td colspan="2"> <fmt:message key="table.field.message.content" bundle="${lang}"/> </td>
                <td>                                                                               </td>
            </tr>
            <c:forEach  items="${messageList}" var="message" >
            <tr>
                <td>             ${message.id}                                                           </td>
                <td colspan="2"> ${message.content}                                                      </td>
                <form method="POST" action="./Horserace">
                    <td class="inputRow">
                        <input type="hidden" name="id" value="${m.id}">
                        <input type="hidden" name="command" value="usr_del_message">
                        <input type="submit" name="submit" value="<fmt:message key='button.delete' bundle='${lang}'/>">
                    </td>
                </form>
            </tr>
            </c:forEach>
            <tr>
                <td class="description" colspan="4"> 
                    <fmt:message key="table.description.user.account.rates" bundle="${lang}"/>                         
                </td>
            </tr>
            <tr class="headRow">
                <td> <fmt:message key="table.field.rate.type" bundle="${lang}"/>   </td>
                <td> <fmt:message key="table.field.rate.money" bundle="${lang}"/>  </td>
                <td> <fmt:message key="table.field.rate.horses" bundle="${lang}"/> </td>
                <td> <fmt:message key="table.field.rate.races" bundle="${lang}"/>  </td>
            </tr>
            <c:forEach  items="${rateList}" var="rate" >
            <tr>   
                <td> ${rate.type}                                                     </td>
                <td> ${rate.money}                                                    </td>
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
                        <c:forEach  items="${rate.races}" var="race" >  
                        <input type="submit" name="id" value="${race}">
                        </c:forEach> 
                    </form>
                </td>  
            </tr>
            </c:forEach>
        </table>
    </body>
</html>
