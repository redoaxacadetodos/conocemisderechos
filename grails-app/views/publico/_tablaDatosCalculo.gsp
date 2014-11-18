<g:if test="${anios.size()!=0 }">
<table class='table table-striped table-hover table-bordered' id="tablaDatosCalculo">
	<thead>
		<tr>
			<g:each var="t" in="${titulos }">
				<th rowspan="2">${t }</th>
			</g:each>
			<g:each var="t" in="${titulosAnios }">
				<th colspan="3" class="centrado">${t }</th>
			</g:each>
		</tr>
		<tr>
			<g:each var="t" in="${anios }">
				<th>${t }</th>
			</g:each>
		</tr>
	</thead>
</table>
</g:if>
<g:else>
<table class='table table-striped table-hover table-bordered' id="tablaDatosCalculo">
	<thead>
		<tr>
			<g:each var="t" in="${titulos }">
				<th>${t }</th>
			</g:each>
		</tr>
	</thead>
</table>

</g:else>