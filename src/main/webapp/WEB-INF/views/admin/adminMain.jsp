<%@ include file="headerAdmin.jsp" %>

<!-- Begin Page Content -->
<div class="container-fluid">

    <!-- Page Heading -->
    <div class="d-sm-flex align-items-center justify-content-between mb-4">
        <h1 class="h3 mb-0 text-black">Admin Panel</h1>
        <c:if test="${not empty disabled}">
            <h1 class="h3 mb-0 text-black" style="color: red">${disabled}</h1>
        </c:if>
    </div>

    <!-- Personal Details -->
    <div class="row" id="user-details">
        <div class="col-lg-12">
            <div class="card shadow mb-4">
                <div class="card-header py-3">
                    <div class="row justify-content-around">
                        <div class="col-lg-10">
                            <h6 class="m-0 font-weight-bold text-primary">Personal Details</h6>
                        </div>
                        <button id="edit-button" type="button" class="btn btn-primary rounded-pill btn-user btn-sm">Edit</button>
                    </div>
                </div>
                <div class="card-body">
                    <div class="row">
                        <div class="col-lg-6">
                            <ul class="h5 mb-0 font-weight-bold text-gray-800">
                                <p>First name: ${user.firstName}</p>
                                <p>Last name: ${user.lastName}</p>
                                <p>Email: ${user.email}</p>
                                <p>Phone: ${user.phone}</p>
                            </ul>
                        </div>
                        <div class="col-lg-6">
                            <ul class="h5 mb-0 font-weight-bold text-gray-800">
                                <p>Street: ${user.street}</p>
                                <p>City: ${user.city}</p>
                                <p>Zip code: ${user.zipCode}</p>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="row" id="edit-form">
        <div class="col-lg-12">
            <div class="card shadow mb-4">
                <form:form method="post" modelAttribute="user">
                <div class="card-header py-3">
                    <div class="row justify-content-around">
                        <div class="col-lg-10">
                            <h6 class="m-0 font-weight-bold text-primary">Personal Details</h6>
                        </div>
                        <div class="form-group form-group--buttons">
                            <button type="button" id="back-button"class="btn btn-primary rounded-pill btn-user btn-sm">Back</button>
                            <button type="submit" id = "submit-edit-button" class="btn btn-primary rounded-pill btn-user btn-sm">Save</button>
                        </div>
                    </div>
                </div>

                    <div class="card-body">
                        <div class="row">
                            <div class="col-lg-6">
                                <ul class="h5 mb-0 font-weight-bold text-gray-800">
                                    <p><form:input path="firstName" placeholder="First Name"/></p>
                                    <p><form:input path="lastName" placeholder="Last Name"/></p>
                                    <p><form:input path="email" placeholder="Email"/></p>
                                    <p><form:input path="phone" placeholder="Phone"/></p>
                                </ul>
                            </div>
                            <div class="col-lg-6">
                                <ul class="h5 mb-0 font-weight-bold text-gray-800">
                                    <p><form:input path="street" placeholder="Street"/></p>
                                    <p><form:input path="city" placeholder="City"/></p>
                                    <p><form:input path="zipCode" placeholder="Zip code"/></p>
                                </ul>
                            </div>
                        </div>
                    </div>
                </form:form>

            </div>
        </div>
    </div>

    <div class="d-sm-flex align-items-center justify-content-between mb-4">
        <div>
            <a href="/admin/add"
               class="btn btn-success btn-icon-split btn-sm">
                <span class="text">New Admin</span>
            </a>
        </div>
    </div>
    <!-- Admins -->
    <div class="row">

        <div class="col-lg-12">

            <div class="card shadow mb-4">
                <div class="card-header py-3">
                    <h6 class="m-0 font-weight-bold text-primary">Admins</h6>
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
                            <c:forEach items="${admins}" var="admin">
                                <tr class="row-cols-sm-4">
                                    <td>${admin.id}</td>
                                    <td>${admin.email}</td>
                                    <td>${admin.phone}</td>
                                    <td>${admin.firstName} ${admin.lastName} </td>
                                    <td>
                                            ${admin.street} <br> ${admin.zipCode} ${admin.city}
                                    </td>
                                    <td>
                                        <c:choose>
                                            <c:when test="${admin.enabled == 1}">Active</c:when>
                                            <c:otherwise>Disabled</c:otherwise>
                                        </c:choose>
                                    </td>
                                    <td>
                                        <div class="row justify-content-end">
                                            <div>
                                                <a href="/admin/reset/${admin.id}"
                                                   class="btn btn-danger btn-icon-split btn-sm">
                                                    <span class="text">Reset Password</span>
                                                </a>
                                            </div>
                                            <div>
                                                <a href="/admin/sendMessage/${admin.id}"
                                                   class="btn btn-info btn-icon-split btn-sm">
                                                    <span class="text">Send message</span>
                                                </a>
                                            </div>
                                            <div>
                                                <a href="/admin/edit/${admin.id}"
                                                   class="btn btn-success btn-icon-split btn-sm">
                                                    <span class="text">Edit</span>
                                                </a>
                                            </div>
                                            <div>
                                                <c:choose>
                                                    <c:when test="${admin.enabled==1}">
                                                        <a href="/admin/disable/${admin.id}"
                                                           class="btn btn-danger btn-icon-split btn-sm">
                                                            <span class="text">Disable</span>
                                                        </a>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <a href="/admin/enable/${admin.id}"
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