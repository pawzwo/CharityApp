<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ include file="headerForm.jsp" %>

<div class="slogan container container--90">
    <div class="slogan--item">
        <h1>
            Oddaj rzeczy, których już nie chcesz<br/>
            <span class="uppercase">potrzebującym</span>
        </h1>

        <div class="slogan--steps">
            <div class="slogan--steps-title">Wystarczą 4 proste kroki:</div>
            <ul class="slogan--steps-boxes">
                <li>
                    <div><em>1</em><span>Wybierz rzeczy</span></div>
                </li>
                <li>
                    <div><em>2</em><span>Spakuj je w worki</span></div>
                </li>
                <li>
                    <div><em>3</em><span>Wybierz fundację</span></div>
                </li>
                <li>
                    <div><em>4</em><span>Zamów kuriera</span></div>
                </li>
            </ul>
        </div>
    </div>
</div>
</header>

<section class="form--steps">
    <div class="form--steps-instructions">
        <div class="form--steps-container">
            <h3>Ważne!</h3>
            <p data-step="1" class="active">
                Uzupełnij szczegóły dotyczące Twoich rzeczy. Dzięki temu będziemy
                wiedzieć komu najlepiej je przekazać.
            </p>
            <p data-step="2">
                Uzupełnij szczegóły dotyczące Twoich rzeczy. Dzięki temu będziemy
                wiedzieć komu najlepiej je przekazać.
            </p>
            <p data-step="3">
                Wybierz jedną, do
                której trafi Twoja przesyłka.
            </p>
            <p data-step="4">Podaj adres oraz termin odbioru rzeczy.</p>
        </div>
    </div>


    <div class="form--steps-container">
        <div class="form--steps-counter">Krok <span>1</span>/4</div>


        <form:form method="POST" modelAttribute="donation">

            <!-- STEP 1: class .active is switching steps -->
            <div data-step="1" class="active">
                <h3>Zaznacz co chcesz oddać:</h3>

                <c:forEach items="${categories}" var="category">
                    <div class="form-group">
                        <label>
                            <form:checkbox path="categories" value="${category}" class="checked"/>
                            <span class="checkbox"></span>
                            <span class="description">${category.name}</span>
                        </label>
                    </div>
                </c:forEach>
                <div class="form-group form-group--buttons">
                    <button type="button" class="btn next-step">Dalej</button>
                </div>
            </div>


            <!-- STEP 2 -->
            <div data-step="2">
                <h3>Podaj liczbę 60l worków, w które spakowałeś/aś rzeczy:</h3>
                <div class="form-group form-group--inline">
                    <label>
                        Liczba 60l worków:
                        <form:input id="bags" path="quantity" type="number" name="bags" step="1" min="1"/>
                    </label>
                </div>
                <div class="form-group form-group--buttons">
                    <button type="button" class="btn prev-step">Wstecz</button>
                    <button type="button" class="btn next-step">Dalej</button>
                </div>
            </div>

            <!-- STEP 4 -->
            <div data-step="3">
                <h3>Wybierz organizacje, której chcesz pomóc:</h3>
                <c:forEach items="${institutions}" var="institution">
                    <div class="form-group form-group--checkbox">
                        <label>
                            <form:radiobutton path="institution" value="${institution}" class="radio"/>
                            <span class="checkbox radio"></span>
                            <span class="description">
                  <div class="title">${institution.name}</div>
                  <div class="subtitle">${institution.description}</div></span>
                        </label>
                    </div>
                </c:forEach>

                <div class="form-group form-group--buttons">
                    <button type="button" class="btn prev-step">Wstecz</button>
                    <button type="button" class="btn next-step">Dalej</button>
                </div>
            </div>


            <!-- STEP 5 -->
            <div data-step="4">
                <h3>Podaj adres oraz termin odbioru rzecz przez kuriera:</h3>

                <div class="form-section form-section--columns">
                    <div class="form-section--column" id="column1">
                        <h4>Adres odbioru</h4>
                        <div class="form-group form-group--inline">
                            <label> Ulica <form:input path="street"/> </label>
                        </div>

                        <div class="form-group form-group--inline">
                            <label> Miasto <form:input path="city"/> </label>
                        </div>

                        <div class="form-group form-group--inline">
                            <label>
                                Kod pocztowy <form:input path="zipCode"/>
                            </label>
                        </div>

                        <div class="form-group form-group--inline">
                            <label>
                                Numer telefonu <form:input path="phone"/>
                            </label>
                        </div>
                    </div>

                    <div class="form-section--column" id="column2">
                        <h4>Termin odbioru</h4>
                        <div class="form-group form-group--inline">
                            <label> Data <form:input path="pickUpDate" type="date"/> </label>
                        </div>

                        <div class="form-group form-group--inline">
                            <label> Godzina <form:input path="pickUpTime" type="time"/> </label>
                        </div>

                        <div class="form-group form-group--inline">
                            <label>
                                Uwagi dla kuriera
                                <form:textarea path="pickUpComment" rows="5"/>
                            </label>
                        </div>
                    </div>
                </div>
                <div class="form-group form-group--buttons">
                    <button type="button" class="btn prev-step">Wstecz</button>
                    <button type="button" id="summary" class="btn next-step">Dalej</button>
                </div>
            </div>


            <!-- STEP 6 -->
            <div data-step="5">
                <h3>Podsumowanie Twojej darowizny</h3>

                <div class="summary">
                    <div class="form-section">
                        <h4>Oddajesz:</h4>
                        <ul>
                            <li id="no-of-bags">
                                <span class="icon icon-bag"></span>
                            </li>

                            <li id="institution">
                                <span class="icon icon-hand"></span>
                            </li>
                        </ul>
                    </div>

                    <div class="form-section form-section--columns">
                        <div class="form-section--column">
                            <h4>Adres odbioru:</h4>
                            <ul id="contact">
                            </ul>
                        </div>

                        <div class="form-section--column">
                            <h4>Termin odbioru:</h4>
                            <ul id="pick-up">
                            </ul>
                        </div>
                    </div>
                </div>

                <div class="form-group form-group--buttons">
                    <button id="backButton" type="button" class="btn prev-step">Wstecz</button>
                    <button type="submit" class="btn">Potwierdzam</button>
                </div>
            </div>
        </form:form>

        <script>
            document.getElementById("summary").addEventListener("click", function (event) {

                let categories = "";
                document.querySelectorAll(".checked:checked").forEach(el => {
                    categories = categories + el.parentElement.lastElementChild.innerText + "; ";
                })
                const span1 = document.createElement("span")
                span1.classList.add("summary--text")
                span1.innerText = document.getElementById("bags").value + " worki: " + categories;
                document.getElementById("no-of-bags").appendChild(span1)

                const span2 = document.createElement("span")
                span2.classList.add("summary--text")
                span2.innerText = "Dla: " + document.querySelector(".radio:checked").parentElement.lastElementChild.firstElementChild.innerText;
                document.getElementById("institution").appendChild(span2)

                document.querySelector("#column1").querySelectorAll("input").forEach(el => {
                    const li1 = document.createElement("li");
                    li1.innerText = el.value;
                    document.getElementById("contact").appendChild(li1);
                });
                document.querySelector("#column2").querySelectorAll("input").forEach(el => {
                    const li2 = document.createElement("li");
                    li2.innerText = el.value;
                    document.getElementById("pick-up").appendChild(li2);

                });

                const li3 = document.createElement("li");
                li3.innerText = document.querySelector("#column2").querySelector("textarea").value;
                document.getElementById("pick-up").appendChild(li3);
            })

            document.getElementById("backButton").addEventListener("click", function (event){
                document.getElementById("no-of-bags").removeChild(document.getElementById("no-of-bags").lastElementChild);
                document.getElementById("institution").removeChild(document.getElementById("institution").lastElementChild);
                while (document.getElementById("contact").firstChild) {
                    document.getElementById("contact").removeChild(document.getElementById("contact").firstChild);
                };
                while (document.getElementById("pick-up").firstChild) {
                    document.getElementById("pick-up").removeChild(document.getElementById("pick-up").firstChild);
                };
            })
        </script>


    </div>
</section>


<%@ include file="../footer.jsp" %>

