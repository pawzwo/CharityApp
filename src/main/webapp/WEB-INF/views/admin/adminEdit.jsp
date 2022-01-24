<%@ include file="headerAdmin.jsp" %>

<!-- Begin Page Content -->
<div class="container-fluid">


    <!-- Page Heading -->
    <div class="d-sm-flex align-items-center justify-content-between mb-4">
        <h1 class="h3 mb-0 text-black">Edit Admin Details</h1>
    </div>
    <div class="row">

        <div class="col-lg-6">
            <!-- Details form -->
            <div class="card shadow mb-4">

                <div class="card-body">
                    <form:form method="post" modelAttribute="admin" class="user">
                        <div class="form-group row">
                            <div class="col-sm-6 mb-3 mb-sm-0">
                                <form:input path="firstName" class="form-control form-control-user" placeholder="First Name"/>
                            </div>
                            <div class="col-sm-6">
                                <form:input path="lastName" class="form-control form-control-user" placeholder="Last Name"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <form:input path="email" class="form-control form-control-user" placeholder="Email"/>
                        </div>
                        <div class="form-group">
                            <form:input path="street" class="form-control form-control-user" placeholder="Street"/>
                        </div>
                        <div class="form-group row">
                            <div class="col-sm-6 mb-3 mb-sm-0">
                                <form:input path="city" class="form-control form-control-user" placeholder="City"/>
                            </div>
                        </div>
                        <div class="form-group row">
                            <div class="col-sm-6 mb-3 mb-sm-0">
                                <form:input path="zipCode" class="form-control form-control-user" placeholder="Zip Code"/>
                            </div>
                        </div>
                        <div class="form-group row">
                            <div class="col-sm-6 mb-3 mb-sm-0">
                                <form:input path="phone" class="form-control form-control-user" placeholder="Phone Number"/>
                            </div>
                        </div>
                        <div class="row justify-content-between">
                            <div>
                                <button type="submit" class="btn btn-primary btn--large">Update</button>
                            </div>
                            <div>
                                <a href="/admin/main"
                                   class="btn btn-primary btn--large">
                                    <span class="text">Back</span>
                                </a>
                            </div>
                        </div>
                    </form:form>
                </div>
            </div>
        </div>

    </div>

</div>
<!-- /.container-fluid -->

</div>
<!-- End of Main Content -->
<%@ include file="footerAdmin.jsp" %>