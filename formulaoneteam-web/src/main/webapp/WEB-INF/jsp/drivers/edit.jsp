<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="true" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="cc" uri="http://java.sun.com/jsp/jstl/core" %>

<my:pagetemplate title="Edit driver">
    <jsp:attribute name="body">
        <div class="container">
            <form:form action="/pa165/drivers/submit" modelAttribute="driver">
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
                </div>
                <div class="form-group col-md-6 col-xs-12">
                    <form:label path="driverStatus">Driver status</form:label>
                    <form:select cssClass="form-control" path="driverStatus" items="${driverStatusValues}"
                                 itemLabel="displayName"/>
                </div>
                <form:hidden path="id"/>
                <button type="submit" class="btn btn-default">
                    <span class="glyphicon glyphicon-floppy-disk"></span>
                    Save
                </button>
            </form:form>
        </div>
    </jsp:attribute>
</my:pagetemplate>