<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="true" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<my:pagetemplate title="Driver detail ${driver.name}">
    <jsp:attribute name="body">
        <div class="container">
            <div class="row">
                <my:a href="/drivers/edit/${driver.id}" class="btn btn-primary pull-right">
                    <span class="glyphicon glyphicon-pencil"></span>
                    Update
                </my:a>
            </div>
            <div class="row">
                <div class="col-md-6 col-xs-12">
                    <h4>Driver information</h4>
                    <dl class="dl-horizontal">
                        <dt>First name:</dt>
                        <dd><c:out value="${driver.name}"/></dd>
                        <dt>Surname:</dt>
                        <dd><c:out value="${driver.surname}"/></dd>
                        <dt>Email:</dt>
                        <dd><c:out value="${driver.email}"/></dd>
                        <dt>Nationality:</dt>
                        <dd><c:out value="${driver.nationality}"/></dd>
                        <dt>Birthday:</dt>
                        <dd><fmt:formatDate value="${driver.birthday}" pattern="dd/MM/YYYY"/></dd>
                        <dt>Status:</dt>
                        <dd><c:out value="${driver.driverStatus}"/></dd>
                    </dl>
                </div>
                <div class="col-md-6 col-xs-12">
                    <h4>Characteristic values</h4>
                    <table class="table">
                        <thead>
                        <tr>
                            <th>Name</th>
                            <th>Value</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${driver.characteristics}" var="characteristic">
                                <tr>
                                    <td><c:out value="${characteristic.type}"/></td>
                                    <td><c:out value="${characteristic.value}"/></td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </jsp:attribute>
</my:pagetemplate>