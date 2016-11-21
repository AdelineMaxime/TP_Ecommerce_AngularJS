monApp.factory('gestionFactory', function($http) {
	
	var urlString = "http://localhost:8080/Projet_Ecommerce_AngularJS/gestion";
	
	
	function getAllCat(callback) {
		$http({
			method: 'GET',
			url: urlString+'/allCat'
		}).success(function(response) {
			console.log(response);
			callback(response);
		}).error(function (response) {
			console.log('Erreur: '+response.statusText);
		});
	}
	

	function getAllProd(callback) {
		$http({
			method: 'GET',
			url: urlString+'/allProd'
		}).success(function(response) {
			console.log(response);
			callback(response);
		}).error(function (response) {
			console.log('Erreur: '+response.statusText);
		});
	}
	
	function suppr(name,callback) {
		$http({
			method : 'DELETE',
			url : urlString + '/supprimerCat/' + name
		}).success(function(response) {
			console.log(response);
			callback(response);
		}).error(function(response) {
			console.log('Erreur: ' + response.statusText);
		});

	}
	
	function getProdByCat(id,callback) {
		$http({
			method: 'GET',
			url: urlString+'/prodByCat/'+id
		}).success(function(response) {
			console.log(response);
			callback(response);
		}).error(function (response) {
			console.log(response);
		});
	}
	
	function supprProd(name,callback) {
		$http({
			method : 'DELETE',
			url : urlString + '/supprimerProd/' + name
		}).success(function(response) {
			console.log(response);
			callback(response);
		}).error(function(response) {
			console.log('Erreur: ' + response.statusText);
		});

	}
	
	function addCat(categorie, callback) {
		$http({
			method : 'POST',
			url : urlString + '/ajouterCat',
			data:angular.toJson(categorie),
			headers:{
				'Content-Type':'application/Json'
			}
			
		}).success(function(response) {
			console.log(response);
			callback(response);

		}).error(function(reason) {
			console.log('Erreur: ' + reason);
		});

	}
	
	function addProd(produit, callback) {
		$http({
			method : 'POST',
			url : urlString + '/ajouterProd',
			data:angular.toJson(produit),
			headers:{
				'Content-Type':'application/Json'
			}
			
		}).success(function(response) {
			console.log(response);
			callback(response);

		}).error(function(reason) {
			console.log('Erreur: ' + reason);
		});

	}
	
	function update(produit, callback) {
		$http({
			method : 'PUT',
			url : urlString + '/modifierProd',
			data:angular.toJson(produit),
			headers:{
				'Content-Type':'application/Json'
			}
			
		}).success(function(response) {
			console.log(response);
			callback(response);

		}).error(function(reason) {
			console.log('Erreur: ' + reason);
		});

	}
	
	
	return {
		getAllProd:getAllProd,
		getAllCat:getAllCat,
		suppr:suppr,
		getProdByCat : getProdByCat,
		supprProd:supprProd,
		addCat:addCat,
		addProd:addProd,
		update:update

	}
	
})