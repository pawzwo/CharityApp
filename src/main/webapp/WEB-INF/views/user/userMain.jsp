<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ include file="headerUser.jsp" %>



<section class="help" id="help">
    <h2>Twoje dane:</h2>

    <div class="help--slides active" id = "user-details" data-id="1">
        <ul class="help--slides-items">
            <li>
                <div class="col">
                    <div class="title">Imię: ${user.firstName}</div>
                    <div class="title">Nazwisko: ${user.lastName}</div>
                </div>
                <div class="col">
                    <div class="title">Email: ${user.email}</div>
                    <div class="title">Telefon: ${user.phone}</div>
                </div>
            </li>
            <li>
                <div class="col">
                    <div class="title">Ulica i nr budynku: ${user.street}</div>
                    <div class="title">Miasto: ${user.city}</div>
                </div>
                <div class="col">
                    <div class="title">Kod pocztowy: ${user.zipCode}</div>
                </div>
            </li>
            <div class="form-group form-group--buttons">
                <button type="button" id = "edit-button" class="btn prev-step">Edytuj/Uzupełnij</button>
                <a href="/user/password" class="btn prev-step">Zmień hasło</a>
            </div>
        </ul>
    </div>
    <div class="help--slides active" id="edit-form" data-id="1">
        <ul class="help--slides-items">
            <form:form method="post" modelAttribute="user">
                <li>
                    <div class="col">
                        <div class="title"><form:input path="firstName" placeholder="Imię"/></div>
                        <div class="title"><form:input path="lastName" placeholder="Nazwisko"/></div>
                    </div>
                    <div class="col">
                        <div class="title"><form:input path="email" placeholder="email"/></div>
                        <div class="title"><form:input path="phone" placeholder="telefon"/></div>
                    </div>
                </li>
                <li>
                    <div class="col">
                        <div class="title"><form:input path="street" placeholder="Ulica i nr budynku"/></div>
                        <div class="title"><form:input path="city" placeholder="Miasto"/></div>
                    </div>
                    <div class="col">
                        <div class="title"><form:input path="zipCode" placeholder="Kod pocztowy"/></div>
                    </div>
                </li>
                <div class="form-group form-group--buttons">
                    <button type="button" id="back-button" class="btn prev-step">Wstecz</button>
                    <button type="submit" id = "submit-edit-button" class="btn">Zapisz</button>
                </div>
            </form:form>

        </ul>
    </div>

    <div class="form-group form-group--buttons">
        <a href="/donation/form" class="btn prev-step">Przekaż dary</a>
        <a href="/donation/show" class="btn prev-step">Pokaż dary</a>
    </div>
</section>





</header>


<script src="<c:url value="/resources/js/app.js"/>"></script>
</body>
</html>

