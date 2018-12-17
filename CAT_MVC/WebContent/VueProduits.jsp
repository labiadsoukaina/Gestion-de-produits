<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Gérez vos produits</title>
<link rel="stylesheet" type="text/css" href="">
<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
<script type="text/javascript">
	function confirmer(url) {
		var rep = confirm("Vous voulez vraiment suppreimer ce produit?");
		if (rep == true) {
			document.location = url;
		}
	}
</script>

</head>
<body>
	<p class="spacer"></p>
	<div class="container">
		<div class="col-md-6 col-xm-12 col-sm-6 col-md-offset-3">
			<div class="panel panel-primary">
				<div class="panel-heading">Gérez vos projets ici</div>
				<div class="panel-body">

					<form action="controleur.php" method="post">
						<div class="form-group">
							
									<label>MOT CLE:</label>
									<input type="text" name="motCle"
										value="${model.motCle}" class="form-control">
									<input type="submit" value="chercher" name="action" >
														</div>
					</form>
				</div>
				<div class="panel-body">
					<form action="controleur.php" method="post">
						<div class="form-group">

							<input type="hidden" value="${model.mode }" name="mode" class="form-control">
							
								<c:if test="${model.mode=='ajout' }">
									
										<label>REFERENCE:</label>
										<input type="text" name="reference"
											value="${model.produit.reference }" class="form-control">
										
									
								</c:if>

								<c:if test="${model.mode=='edit' }">
									
										<label>REFERENCE:</label>
										${model.produit.reference}<input type="hidden"
											name="reference" value="${model.produit.reference }" class="form-control">
										
									
								</c:if>
							</div>
								<div class="form-group">
									<label>DESIGNATION:</label>
									<input type="text" name="designation"
										value="${model.produit.designation }" class="form-control"></div>
									
								
								<div class="form-group">
									<label>PRIX:</label>
									<input type="text" name="prix"
										value="${model.produit.prix }" class="form-control"></div>
									
								
								<div class="form-group">
									<label>QUANTITE:</label>
									<input type="text" name="quantite"
										value="${model.produit.quantite }" class="form-control"></div>
									
								
								<div class="form-group">
									<input type="submit" value="save" name="action"></div>
								
							
						
					</form>
				</div>

				<div class="form-group">${model.errors }</div>
				
				<div class="panel-body">
				  <div class="form-group">
					<table class="table">
						<tr>
							<th>REFERENCE</th>
							<th>DESIGNATION</th>
							<th>PRIX</th>
							<th>QUANTITE</th>
							
						</tr>
						<c:forEach items="${model.produits}" var="p">
							<tr>
								<td>${p.reference}</td>
								<td>${p.designation}</td>
								<td>${p.prix}</td>
								<td>${p.quantite}</td>
								<td><a
									href="javascript:confirmer('controleur.php?action=delete&ref=${p.reference}')">Supprimer</a></td>
								<td><a href="controleur.php?action=edit&ref=${p.reference}">Modifier</a></td>

							</tr>
						</c:forEach>

					</table>

				</div>
				</div>
			</div>
		</div>

	</div>
</body>
</html>