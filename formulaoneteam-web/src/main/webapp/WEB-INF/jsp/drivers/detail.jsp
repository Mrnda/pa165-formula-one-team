<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="true" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page import="cz.muni.fi.pa165.entity.RaceParticipation" %>

<my:pagetemplate title="Driver detail ${driver.name}">
    <jsp:attribute name="head">
        <link rel="stylesheet" type="text/css"
              href="https://cdn.datatables.net/1.10.19/css/dataTables.bootstrap.min.css"/>
    </jsp:attribute>
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
                    <table class="table" id="characteristics-value-table">
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
                <c:if test="${driver.raceParticipations.size() gt 0}">
                <div class="col-md-6 col-xs-12">
                    <h4>Race participations</h4>
                    <table class="table" id="race-participations-table">
                        <thead>
                        <tr>
                            <th>Date</th>
                            <th>Title</th>
                            <th>Result</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${driver.raceParticipations}" var="participation">
                                <tr class="clickable-row" data-href="/test-drives/detail/${participation.id}">
                                    <td><fmt:formatDate value="${participation.race.date}" pattern="dd/MM/yyyy"/></td>
                                    <td><c:out value="${participation.race.title}"/></td>
                                    <td>
                                        <c:choose>
                                            <c:when test="${participation.resultPosition eq RaceParticipation.NO_RESULT_POSITION}">Unfinished</c:when>
                                            <c:otherwise><c:out value="${participation.resultPosition}"/></c:otherwise>
                                        </c:choose>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
                </c:if>
                <c:if test="${driver.testDrives.size() gt 0}">
                <div class="col-md-6 col-xs-12">
                    <h4>Test drives</h4>
                    <table class="table" id="test-drive-table">
                        <thead>
                        <tr>
                            <th>Date</th>
                            <th>Notes</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${driver.testDrives}" var="drive">
                            <tr class="clickable-row" data-href="/test-drives/detail/${drive.id}">
                                <td><fmt:formatDate value="${drive.date}" pattern="dd/MM/yyyy"/></td>
                                <td><c:out value="${drive.notes}"/></td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
                </c:if>
            </div>
        </div>
    </jsp:attribute>
    <jsp:attribute name="script">
            <script type="text/javascript" charset="utf8"
                    src="https://cdn.datatables.net/1.10.19/js/jquery.dataTables.js"></script>
            <script>
                $(document).ready(function () {
                    $("#race-participations-table").DataTable(
                        {
                            paging: false,
                            searching: false,
                            info: false,
                            order: []
                        }
                    );
                    $("#test-drive-table").DataTable(
                        {
                            paging: false,
                            searching: false,
                            info: false,
                            order: []
                        }
                    );
                    $("#characteristics-value-table").DataTable(
                        {
                            paging: false,
                            searching: false,
                            info: false,
                            order: []
                        }
                    );
                });
            </script>
    </jsp:attribute>
</my:pagetemplate>