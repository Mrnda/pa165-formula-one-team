<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="true" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<my:pagetemplate title="Drivers">
<jsp:attribute name="body">
    <div class="container">
        <table class="table">
            <thead>
            <tr>
                <th>name</th>
                <th>email</th>
                <th>status</th>
                <th>current races</th>
                <th>actions</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${drivers}" var="driver"><tr>
                <td><c:out value="${driver.fullName}"/></td>
                <td><c:out value="${driver.email}"/></td>
                <td><c:out value="${driver.driverStatus}"/></td>
                <td>
                    <c:forEach items="${driver.raceParticipations}" var="participation">
                        <my:a href="/world-championship/detail/${participation.id}">${participation.race.title}</my:a><br/>
                    </c:forEach>
                </td>
                <td>
                    <my:a href="detail/${driver.id}" class="btn btn-primary">Detail</my:a>
                </td>
            </tr>

        </c:forEach>
            </tbody>
        </table>
    </div>
</jsp:attribute>
</my:pagetemplate>
