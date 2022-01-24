<%@ include file="headerAdmin.jsp" %>

<!-- Begin Page Content -->
<div class="container-fluid">

    <!-- Page Heading -->
    <div class="d-sm-flex align-items-center justify-content-between mb-4">
        <h1 class="h3 mb-0 text-black">Donations Panel</h1>
    </div>

    <!-- Users -->
    <div class="row">

        <div class="col-lg-12">

            <div class="card shadow mb-4">
                <div class="card-header py-3">
                    <h6 class="m-0 font-weight-bold text-primary">Donations</h6>
                </div>
                <div class="card-body">
                    <div class="table-responsive">
                        <table class="table table-bordered" id="dataTable">
                            <thead>
                            <tr class="row-cols-sm-4">
                                <th>Id</th>
                                <th>Institution</th>
                                <th>Address</th>
                                <th>No of bags</th>
                                <th>Pick up details</th>
                                <th>Status</th>
                                <th></th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${donations}" var="donation">
                                <tr class="row-cols-sm-4">
                                    <td>
                                            ${donation.id}
                                    </td>
                                    <td>
                                            ${donation.institution.name}
                                    </td>
                                    <td>
                                        ${donation.street}<br>
                                        ${donation.zipCode}, ${donation.city}
                                    </td>
                                    <td>
                                            ${donation.quantity}
                                    </td>
                                    <td>
                                            contact phone: ${donation.phone}<br>
                                            time & date: ${donation.pickUpTime}, ${donation.pickUpDate}
                                    </td>
                                    <td>
                                        Awaiting/Picked/Delivered
                                    </td>
                                    <td>
                                        <div class="row justify-content-end">
                                            <div>
                                                <a href="/admin/sendMessage/${user.id}"
                                                   class="btn btn-success btn-icon-split btn-sm">
                                                    <span class="text">Change status</span>
                                                </a>
                                            </div>
                                            <div>
                                                <a href="/admin/sendMessage/${user.id}"
                                                   class="btn btn-info btn-icon-split btn-sm">
                                                    <span class="text">Send message</span>
                                                </a>
                                            </div>
                                            <div>
                                                <a href="/admin/user/details/${user.id}"
                                                   class="btn btn-danger btn-icon-split btn-sm">
                                                    <span class="text">Cancel</span>
                                                </a>
                                            </div>
                                            <div>
                                                <c:choose>
                                                    <c:when test="${user.enabled==1}">
                                                        <form:form action="/admin/user/disable" method="post">
                                                            <input type="hidden" name="userId" value="${user.id}">
                                                            <button type="submit"
                                                                    class="btn btn-warning btn-icon-split btn-sm"/>
                                                            <span class="text">Hold</span>
                                                        </form:form>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <form:form action="/admin/user/enable/" method="post">
                                                            <input type="hidden" name="userId" value="${user.id}">
                                                            <button type="submit"
                                                                    class="btn btn-warning btn-icon-split btn-sm"/>
                                                            <span class="text">Activate</span>
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