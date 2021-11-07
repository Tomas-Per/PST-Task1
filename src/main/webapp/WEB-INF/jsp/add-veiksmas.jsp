<%@ include file="common/navigation.jspf"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<div class="container">
    <p>Add/Update Veiksmas:</p>
    <form:form method="post" modelAttribute="naujasVeiksmas">

        <form:select path="veiksmas">
            <form:option value="insert" label="insert"/>
            <form:option value="update" label="update"/>
            <form:option value="delete" label="delete"/>
        </form:select>

        <form:label path="vartotojoId">vartotojo ID</form:label>
        <form:input path="vartotojoId" type="text" required="required" />
        <form:errors path="vartotojoId" />

        <form:label path="data">data</form:label>
        <form:input path="data" type="text" required="required" />
        <form:errors path="data" />

        <button type="submit">OK</button>
    </form:form>
</div>