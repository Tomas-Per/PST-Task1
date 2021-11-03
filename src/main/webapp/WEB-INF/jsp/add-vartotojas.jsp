<%@ include file="common/navigation.jspf"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<div class="container">
	<p>Add/Update Vartotojas:</p>
<form:form method="post" modelAttribute="vartotojas">

	<form:label path="vardas">Vardas</form:label>
	<form:input path="vardas" type="text" required="required" />
	<form:errors path="vardas" />

	<form:label path="telNr">telNr</form:label>
	<form:input path="telNr" type="text" required="required" />
	<form:errors path="telNr" />

	<button type="submit">OK</button>
</form:form>
</div>