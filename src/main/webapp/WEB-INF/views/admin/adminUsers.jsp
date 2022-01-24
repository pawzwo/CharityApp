<%@ include file="headerAdmin.jsp" %>

<!-- Begin Page Content -->
<div class="container-fluid">

    <!-- Page Heading -->
    <div class="d-sm-flex align-items-center justify-content-between mb-4">
        <h1 class="h3 mb-0 text-black">Users Panel</h1>
    </div>

    <!-- Users -->
    <div class="row">

        <div class="col-lg-12">

            <div class="card shadow mb-4">
                <div class="card-header py-3">
                    <h6 class="m-0 font-weight-bold text-primary">Users</h6>
                </div>
                <div class="card-body">
                    <div class="table-responsive">
                        <table class="table table-bordered" id="dataTable">
                            <thead>
                            <tr class="row-cols-sm-4">
                                <th>Id</th>
                                <th>Login</th>
                                <th>Phone</th>
                                <th>Name</th>
                                <th>Address</th>
                                <th>Status</th>
                                <th></th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${nonAdmins}" var="user">
                                <tr class="row-cols-sm-4">
                                    <td>${user.id}</td>
                                    <td>${user.email}</td>
                                    <td>${user.phone}</td>
                                    <td>${user.firstName} ${user.lastName} </td>
                                    <td>
                                            ${user.street} <br> ${user.zipCode} ${user.city}
                                    </td>
                                    <td>
                                        <c:choose>
                                            <c:when test="${user.enabled == 1}">Active</c:when>
                                            <c:otherwise>Disabled</c:otherwise>
                                        </c:choose>
                                    </td>
                                    <td>
                                        <div class="row justify-content-end">
                                            <div>
                                                <a href="/admin/reset/${user.id}"
                                                   class="btn btn-danger btn-icon-split btn-sm">
                                                    <span class="text">Reset Password</span>
                                                </a>
                                            </div>
                                            <div>
                                                <a href="/admin/sendMessage/${user.id}"
                                                   class="btn btn-info btn-icon-split btn-sm">
                                                    <span class="text">Send message</span>
                                                </a>
                                            </div>
                                            <div>
                                                <a href="/admin/user/edit/${user.id}"
                                                   class="btn btn-success btn-icon-split btn-sm">
                                                    <span class="text">Edit</span>
                                                </a>
                                            </div>
                                            <div>
                                                <c:choose>
                                                    <c:when test="${user.enabled==1}">
                                                        <a href="/admin/disable/${user.id}"
                                                           class="btn btn-danger btn-icon-split btn-sm">
                                                            <span class="text">Disable</span>
                                                        </a>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <a href="/admin/enable/${user.id}"
                                                           class="btn btn-warning btn-icon-split btn-sm">
                                                            <span class="text">Enable</span>
                                                        </a>
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