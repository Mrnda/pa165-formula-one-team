<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="true" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="cc" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="forms" uri="http://www.springframework.org/tags/form" %>

<my:pagetemplate title="Edit driver">
    <jsp:attribute name="body">
        <div class="container">
            <form:form action="/pa165/drivers/submit" modelAttribute="driver">
                <form:hidden path="id"/>
                <div class="form-group col-md-6 col-xs-12">
                    <form:label path="name">Name</form:label>
                    <form:input cssClass="form-control" path="name"/>
                </div>
                <div class="form-group col-md-6 col-xs-12">
                    <form:label path="surname">Surname</form:label>
                    <form:input cssClass="form-control" path="surname"/>
                </div>
                <div class="form-group col-md-6 col-xs-12">
                    <form:label path="email">Email</form:label>
                    <form:input cssClass="form-control" path="email"/>
                </div>
                <div class="form-group col-md-6 col-xs-12">
                    <form:label path="nationality">Nationality</form:label>
                    <form:input cssClass="form-control" path="nationality"/>
                </div>
                <div class="form-group col-md-6 col-xs-12">
                    <form:label path="password">Password</form:label>
                    <form:password cssClass="form-control" path="password"/>
                </div>

                <%--<div class="form-group col-md-6 col-xs-12">--%>
                <%--<form:label path="confirm-password">Confirm password</form:label>--%>
                <%--<form:password cssClass="form-control" path="confirm-password"/>--%>
                <%--</div>--%>
                <div class="form-group col-md-6 col-xs-12">
                    <form:label path="driverStatus">Driver status</form:label>
                    <form:select cssClass="form-control" path="driverStatus" items="${driverStatusValues}"
                                 itemLabel="displayName"/>
                </div>
                <div>
                    <h4>Characteristics</h4>
                    <c:forEach items="${driver.characteristics}" var="characteristicValue" varStatus="status">
                        <div class="form-group col-md-6 col-xs-12">
                            <label for="characteristics[${status.index}].value">
                                <c:out value="${characteristicValue.type.displayName}"/>
                            </label>
                            <input type="hidden"
                                   name="characteristics[${status.index}].id"
                                   value="${characteristicValue.id}"/>
                            <input type="hidden"
                                   name="characteristics[${status.index}].type"
                                   value="${characteristicValue.type.name()}"/>
                            <input class="form-control"
                                   id="characteristics[${status.index}].value"
                                   name="characteristics[${status.index}].value"
                                   value="${characteristicValue.value}"/>
                        </div>
                    </c:forEach>
                </div>
                <button type="submit" class="btn btn-primary">
                    <span class="glyphicon glyphicon-floppy-disk"></span>
                    Save
                </button>
            </form:form>
        </div>
    </jsp:attribute>
</my:pagetemplate>