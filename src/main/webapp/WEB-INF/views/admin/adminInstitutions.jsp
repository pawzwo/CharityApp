<%@ include file="headerAdmin.jsp" %>

<!-- Begin Page Content -->
<div class="container-fluid">

    <!-- Page Heading -->
    <div class="d-sm-flex align-items-center justify-content-between mb-4">
        <h1 class="h3 mb-0 text-black">Institutuions Panel</h1>
    </div>

    <div class="d-sm-flex align-items-center justify-content-between mb-4">
        <div>
            <a href="/admin/institution/add"
               class="btn btn-success btn-icon-split btn-sm">
                <span class="text">New Institution</span>
            </a>
        </div>
    </div>
    <!-- Users -->
    <div class="row">

        <div class="col-lg-12">

            <div class="card shadow mb-4">
                <div class="card-header py-3">
                    <h6 class="m-0 font-weight-bold text-primary">Institutions</h6>

                </div>
                <div class="card-body">
                    <div class="table-responsive">
                        <table class="table table-bordered" id="dataTable">
                            <thead>
                            <tr class="row-cols-sm-4">
                                <th>Id</th>
                                <th>Name</th>
                                <th>Description</th>
                                <th>Status</th>
                                <th>Address</th>
                                <th>Contact Details</th>
                                <th></th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${institutions}" var="institution">
                                <tr class="row-cols-sm-4">
                                    <td>
                                            ${institution.id}
                                    </td>
                                    <td>
                                            ${institution.name}
                                    </td>
                                    <td>
                                        ${institution.description}
                                    </td>
                                    <td>
                                        <c:choose>
                                            <c:when test="${1==1}">Active</c:when>
                                            <c:otherwise>Disabled</c:otherwise>
                                        </c:choose>
                                    </td>
                                    <td>
                                        Address
                                    </td>
                                    <td>
                                        Phone, email
                                    </td>
                                    <td>
                                        <div class="row justify-content-end">
                                            <div>
                                                <a href="/admin/sendMessage/${user.id}"
                                                   class="btn btn-info btn-icon-split btn-sm">
                                                    <span class="text">Send message</span>
                                                </a>
                                            </div>
                                            <div>
                                                <a href="/admin/user/details/${user.id}"
                                                   class="btn btn-success btn-icon-split btn-sm">
                                                    <span class="text">Edit</span>
                                                </a>
                                            </div>
                                            <div>
                                                <c:choose>
                                                    <c:when test="${1==1}">
                                                        <form:form action="/admin/institution/disable" method="post">
                                                            <input type="hidden" name="userId" value="${institution.id}">
                                                            <button type="submit"
                                                                    class="btn btn-danger btn-icon-split btn-sm"/>
                                                            <span class="text">Disable</span>
                                                        </form:form>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <form:form action="/admin/institution/enable/" method="post">
                                                            <input type="hidden" name="userId" value="${institution.id}">
                                                            <button type="submit"
                                                                    class="btn btn-warning btn-icon-split btn-sm"/>
                                                            <span class="text">Enable</span>
                                                        </form:form>
                                                    </c:otherwise>
                                                </c:choose>

                                            </div>
                                        </div>
                                    </td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>

    </div>
    <!-- /.container-fluid -->

</div>
<!-- End of Main Content -->
<%@ include file="footerAdmin.jsp" %>